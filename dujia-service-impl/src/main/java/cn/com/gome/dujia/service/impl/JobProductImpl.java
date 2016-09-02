package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.enums.TcInterface;
import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.mapper.business.*;
import cn.com.gome.dujia.model.*;
import cn.com.gome.dujia.service.*;
import cn.com.gome.dujia.thread.ThreadPoolUtil;
import cn.com.gome.dujia.vo.json.order.LineSaleInfoCalenderResult;
import cn.com.gome.dujia.vo.json.productinfo.IncrementProduct;
import cn.com.gome.dujia.vo.json.productinfo.PackageDetail;
import cn.com.gome.dujia.vo.json.productinfo.ResourceGps;
import com.gome.plan.tools.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by sunming on 2016/3/31.
 */
@Service
public class JobProductImpl implements JobProduct {

    @Resource
    private ZbyProductMapper productMapper;

    @Resource
    private ZbyRecomInfoMapper recomInfoMapper;

    @Resource
    private ZbyProductPackageMapper productPackageMapper;

    @Resource
    private ZbyProductAdditionMapper productAdditionMapper;

    @Resource
    private ZbyProductPackageDetailMapper productPriceDetailMapper;

    @Resource
    private TcService tcService;

    @Resource
    private ZbyLineMapper lineMapper;

    @Resource
    private ImageService imageService;

    @Resource
    private SearchService searchService;

    @Resource
    private RedisCacheService redisCacheService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;


    @Resource
    private ZbyProductPackagePriceMapper zbyProductPackagePriceMapper;

    private static final JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON).dateFormat(new SimpleDateFormat(DateUtils.LONG_WEB_FORMAT));
    private static final Logger logger = LoggerFactory.getLogger(JobProductImpl.class);

    /**
     * 产品全量更新
     */
    @Override
    public void allProductUpdate() {
        logger.info("同城线路全亮更新开始");
        Map<String, String> map = new HashMap<String, String>();
        //map.put("ProductId","99844");
        // map.put("CityName","北京");
        //获取全部线路列表

        List<ZbyLine> tclist = tcService.lineIndexInfoList(map);
        if (CollectionUtils.isEmpty(tclist)) {
            logger.error("获取线路列表出错");
            return;
        }

        Set<String> tcProSet = new HashSet<String>();
        for (ZbyLine var : tclist) {
            tcProSet.add(var.getLineId());
        }

        //下架所有无效线路
        ZbyProduct query = new ZbyProduct();
        query.setIsDelete(TrueFalseStatus.FALSE.getValue());
        List<ZbyProduct> dblist = productMapper.select(query);
        for (ZbyProduct var : dblist) {
            if (!tcProSet.contains(var.getProductId())) { //如果同城线路不包含数据中的数据
        //         var.setIsDelete(true);
                ZbyProduct deleteProduct = new ZbyProduct();
                deleteProduct.setProductId(var.getProductId());
                deleteProduct.setIsDelete(TrueFalseStatus.TRUE.getValue());
                productMapper.updateByPrimaryKeySelective(deleteProduct); //把线路更新成无效
                productPackageMapper.deleteByProductId(var.getProductId());//把线路下面所有套餐更新成无效
                zbyProductPackagePriceMapper.deleteInProductId(var.getProductId());//线路下线，删除所有的班期价格
                logger.info("同城线路{}下线，已更新。", var.getProductId());
            }
        }

        //获取线程池，通过ProductInitProcessor把线路信息更新到数据库中
        List<Future> futures = new ArrayList<Future>();
        ThreadPoolExecutor poll = ThreadPoolUtil.getInstance();
        for (ZbyLine var : tclist) {
            Future future = poll.submit(new ProductInitProcessor(var.getLineId(), this, tcService));//提交任务，一个线路一个任务
            futures.add(future);
        }

        //获取线程执行完毕
        for (Future<String> var : futures) {
            try {
                var.get();
            } catch (Exception e) {
            	logger.error("同城线路全亮更新失败", e);
            }
        }
        logger.info("同城线路全亮更新完毕");

    }


    @Override
    /**
     * 增量更新
     */
    public void productIncrement() {
        try {
            String nowTime = DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT);

            //获取最后一次更新时间
            String lastUpdateTime = redisCacheService.get(TcInterface.PRODUCT_INCREMENT_TASK_KEY.getValue(), String.class);
            //如果最后一次获取时间为空、或者是超过时间间隔大于30分钟，小于5分钟，获取最近30分钟的更新数据
            if (StringUtils.isEmpty(lastUpdateTime) || (new Date().getTime() - DateUtils.parse(lastUpdateTime, DateUtils.LONG_WEB_FORMAT).getTime()) > 1000 * 60 * 30 || (new Date().getTime() - DateUtils.parse(lastUpdateTime, DateUtils.LONG_WEB_FORMAT).getTime()) < 1000 * 60 * 5) {
                lastUpdateTime = DateUtils.format(DateUtils.addMinutes(new Date(), -30), DateUtils.LONG_WEB_FORMAT);
            }
            logger.info("增量上次更新时间为+" + lastUpdateTime);
            lastUpdateTime=DateUtils.format(DateUtils.addMinutes(new Date(), -5), DateUtils.LONG_WEB_FORMAT);
            //调用接口结束时间
            Long startTime = System.currentTimeMillis();
            List<ZbyProduct> increList = new ArrayList<ZbyProduct>();
            List<ZbyProduct> allProduct = new ArrayList<ZbyProduct>();



            increList = incrementProducts(TcInterface.INFO_CHANGE_ID.getValue(), lastUpdateTime, nowTime);
            if (!CollectionUtils.isEmpty(increList)) {
                allProduct.addAll(increList);
            }

            //增加线路
            increList = incrementProducts(TcInterface.ONLINE_UPDATE.getValue(), lastUpdateTime, nowTime);
            if (!CollectionUtils.isEmpty(increList)) {
                allProduct.addAll(increList);
            }

            //把套餐详情变更（更新zby_product_package zby_product_addition zby_product_package_detail表 ）
            increList = incrementProducts(TcInterface.PACKAGE_UPDATE.getValue(), lastUpdateTime, nowTime);
            if (!CollectionUtils.isEmpty(increList)) {
                allProduct.addAll(increList);
            }


            //线路更新（product 和 he zby_recom_info表）价格班期更新
            increList = incrementProducts(TcInterface.LINE_PRODUCT_UPDATE.getValue(), lastUpdateTime, nowTime);
            if (!CollectionUtils.isEmpty(increList)) {
                allProduct.addAll(increList);
            }


            //价格信息变更（更新zby_product_package_price表）价格班期更新
            increList = incrementProducts(TcInterface.PRICE_CHANGE_ID.getValue(), lastUpdateTime, nowTime);
            if (!CollectionUtils.isEmpty(increList)) {
                allProduct.addAll(increList);
            }


            //库存更新（更新zby_product_package_price表）
            increList = incrementProducts(TcInterface.INVENTORY_UPDATE.getValue(), lastUpdateTime, nowTime);
            if (!CollectionUtils.isEmpty(increList)) {
                allProduct.addAll(increList);
            }


            //汇总后数据更新
            //把线路设置成无效（更新product表isDelete=1）
            increList = incrementProducts(TcInterface.INVALID_ID.getValue(), lastUpdateTime, nowTime);
            if (!CollectionUtils.isEmpty(increList)) {
                allProduct.addAll(increList);
            }

            if (!CollectionUtils.isEmpty(allProduct)) {
            	//把已失效的线路设置成无效，
            	List<ZbyLine> tclist = tcService.lineIndexInfoList(null);
            	Set<String> tcProSet = new HashSet<String>();
            	for (ZbyLine var : tclist) {
            		tcProSet.add(var.getLineId());
            	}
            	
            	//下架所有无效线路
            	ZbyProduct query = new ZbyProduct();
            	query.setIsDelete(TrueFalseStatus.FALSE.getValue());
            	List<ZbyProduct> dblist = productMapper.select(query);
            	Set<String> products = new HashSet<String>();
            	for (ZbyProduct var : dblist) {
            		if (!tcProSet.contains(var.getProductId())) { //如果同城线路不包含数据中的数据
            			ZbyProduct deleteProduct = new ZbyProduct();
            			deleteProduct.setProductId(var.getProductId());
            			deleteProduct.setIsDelete(TrueFalseStatus.TRUE.getValue());
            			productMapper.updateByPrimaryKeySelective(deleteProduct); //把线路更新成无效
            			productPackageMapper.deleteByProductId(var.getProductId());//把线路下面所有套餐更新成无效
            			zbyProductPackagePriceMapper.deleteInProductId(var.getProductId());//线路下线，删除所有的班期价格。
            			logger.info("同城线路{}下线，已更新", var.getProductId());
            			
            			products.add(var.getProductId());
            		}
            	}
            	
            	//更新线路
            	HashMap<String, ZbyProduct> infoMap = new HashMap<String, ZbyProduct>();
            	if (!CollectionUtils.isEmpty(allProduct)) {
            		for (ZbyProduct var : allProduct) {
            			infoMap.put(var.getProductId(), var);
                        logger.info("增量接口同城线路{}有数据变化，已更新", var.getProductId());
                    }
            		infoChange(new ArrayList(infoMap.values()));
            	}
            	
            	
            	//调用检索服务。同步检索
            	for (ZbyProduct var : allProduct) {
            		products.add(var.getProductId());
            	}
            	searchService.importIncrementData(new ArrayList(products));
            }
            //记录最后一次更新的时间，作为下次的数据更新的开始时间，如抛出异常不记录时间
            redisCacheService.put(TcInterface.PRODUCT_INCREMENT_TASK_KEY.getValue(), nowTime);
            logger.info("线路增量同步成功");
        } catch (Exception e) {
            logger.error("订单任务同步失败", e);
            MonitorUtil.recordOne("JobProduct_vender_task_fail");
        }

    }



    /**
     * 线路信息变更
     *
     * @param tcProList
     */

    public void infoChange(List<ZbyProduct> tcProList) {
        if (CollectionUtils.isEmpty(tcProList)) {
            return;
        }
        //lineProductUpdate更新线路，packageUpdate更新套餐,更新套餐价格
        for (ZbyProduct var : tcProList) {
            logger.info("更新同城同城线路{}，已更新", var.getProductId());
            try {
                updateProduct(var);
            } catch (Exception e) {
                logger.error("更新线路{}报错", var.getProductId(), e);
            }
            try {
                updatePackage(var);
            } catch (Exception e) {
                logger.error("更新线路详情{}报错", var.getProductId(), e);
            }
        }

        //lineProductUpdate更新线路，packageUpdate更新套餐,更新套餐价格
        for (ZbyProduct var : tcProList) {
            //lineProductUpdate更新线路，packageUpdate更新套餐,更新套餐价格
            //更新所有的班期价格
            List<String> packageIds = new ArrayList<String>();
            for (ZbyProductPackage productpackage : var.getProductPackageList()) {
                packageIds.add(productpackage.getPackageId());
            }
            if(packageIds.size()==0){
                zbyProductPackagePriceMapper.deleteByProductIdAndPackageId(var.getProductId(), null);  //删除线路下不存在的套餐价格
            }else{
                zbyProductPackagePriceMapper.deleteByProductIdAndPackageId(var.getProductId(), packageIds);  //删除线路下不存在的套餐价格
            }
            Date now = new Date();
            lineSaleInfoCalendar(var.getProductId(), now, DateUtils.addDays(now, GlobalDisconf.getCalendarAfterDays()));
        }
    }



    @Override
    /**
     * 每天执行班期价格
     */
    public void lineSaleInfoCalendarEveryDay() {
        //传入空map，查询全部路线
        zbyProductPackagePriceMapper.deleteBeforeDate(DateUtils.format(new Date(), DateUtils.WEB_FORMAT));
        List<ZbyLine> dbLinePart = tcService.lineIndexInfoList(null);


        List<Future> futures = new ArrayList<Future>();
        //建立线程池，通过CalenderProcessor把线路信息更新到数据库中
        ThreadPoolExecutor poll = ThreadPoolUtil.getInstance();
        for (ZbyLine var : dbLinePart) {
            Future future = poll.submit(new CalenderProcessor(var, this));
            futures.add(future);
        }
        //等待线程池执行完毕var.get()
        for (Future<String> var : futures) {
            try {
                var.get();
            } catch (Exception e) {
                logger.error("订单班期价格任务调用失败", e);
                MonitorUtil.recordOne("JobProduct_lineSaleInfoCalendarEveryDay_task_fail");
            }
        }
    }

    /**
     * 定时任务每日更新lineSaleInfoCalendarEveryDay()的回到函数
     * @param zbyLine
     */
    @Override
    public void lineSaleInfoCalendarEveryDay(ZbyLine zbyLine) {
        //删除线路下不存在的套餐价格
        zbyProductPackagePriceMapper.deleteByProductIdAndPackageId(zbyLine.getLineId(), zbyLine.getPackageIdListRaw());
        Date now = new Date();
        lineSaleInfoCalendar(zbyLine.getLineId(), now, DateUtils.addDays(now, GlobalDisconf.getCalendarAfterDays()));
    }


    /**
     * @param status
     * @return 获取线路列表
     */
    private List<ZbyProduct> incrementProducts(String status, String startTime, String endTime) {
        Map<String, String> requstMap = new HashMap<String, String>();
        requstMap.put("StartTime", startTime);
        requstMap.put("EndTime", endTime);
        requstMap.put("IncrementType", status);
        List<IncrementProduct> increList = tcService.getIncrementProductList(requstMap);
        if (increList == null) {
            logger.info("同程增量接口：StartTime:{}，EndTime:{}，IncrementType{}，没有数据更新", startTime, endTime, status);
            return null;
        }
        logger.info("同城增量接口数据：StartTime:{}，EndTime:{}，IncrementType:{}，更新线路数据:{}", startTime, endTime, status, jsonUtils.serialize(increList));
        List<ZbyProduct> list = new ArrayList<ZbyProduct>();
        for (IncrementProduct var : increList) {
            Map<String, String> requstMap1 = new HashMap<String, String>();
            requstMap1.put("ProductIds", var.getProductId());
            //requstMap1.put("ProductPriceIds", StringUtils.join(var.getPackageIdList()));
            List<ZbyProduct> part = tcService.getProductDetailInfo(requstMap1);
            if (part != null && part.size() > 0) {
                list.addAll(part);
            } else {
            	logger.error("同程增量接口，查询线路{}详情为空", var.getProductId());
            }
        }

        if (CollectionUtils.isEmpty(list)) {
            logger.info("数据同步接口状态为{}时，没有数据更新", status);
        }
        return list;
    }


    /**
     * 更新线路
     *
     * @param tcProduct
     */
    private void updateProduct(ZbyProduct tcProduct) throws InstantiationException, IllegalAccessException {

        //插入线路zbyline
        ZbyLine line = new ZbyLine();
        line.setLineId(tcProduct.getProductId());
        if (CollectionUtils.isEmpty(lineMapper.select(line))) {//如果数据库line表不存在线路，查询同城接口
            Map<String, String> requestMap = new HashMap<String, String>();
            requestMap.put("ProductId", tcProduct.getProductId());
            List<ZbyLine> lines = tcService.lineIndexInfoList(requestMap); //从同城接口获取线路信息，获取线路数据最多为一条。
            if (!CollectionUtils.isEmpty(lines)) {
                ZbyLine varline = lines.get(0);
                varline.setPackageIdList(StringUtils.join(varline.getPackageIdListRaw(), ",")); //同城接口赋值到PackageIdListRaw字段，需要转换为PackageIdList
                varline.setCreateTime(new Date());
                varline.setUpdateTime(new Date());
                lineMapper.insert(varline);//插入数据库
            }
        }

        //判断数据库是否存在同城接口获取的数据, source为数据库的数据，result为同城变化得到数据
        ZbyProduct source = productMapper.selectByProductId(tcProduct.getProductId());

        if (null == source) {//数据库不存在同城接口的线路ID,做插入数据库操作，否则更新
            //新增
            tcProduct.setCreateTime(new Date());
            tcProduct.setUpdateTime(new Date());
            tcProduct.setBrowseCount(GlobalDisconf.getBrowseCount());
            tcProduct.setSaleCount(0);
            tcProduct.setVenderId(GlobalDisconf.getVenderId());//供应商编号
            tcProduct.setIsDelete(TrueFalseStatus.FALSE.getValue());
            setLocal(tcProduct); //设置图片、日期、可用状态
            //设置图片
            if (StringUtils.isEmpty(tcProduct.getImageUrl())) {
                if (CollectionUtils.isEmpty(tcProduct.getProductImageUrlListRaw())) {
                    tcProduct.setImageUrl(GlobalDisconf.getDefaultImage());
                } else {
                    tcProduct.setImageUrl(tcProduct.getProductImageUrlListRaw().get((int) Math.random() * tcProduct.getProductImageUrlListRaw().size()));
                }
            }
            productMapper.insertSelective(tcProduct);
        } else {
            //把线路设置为可用状态，显示出来
            ZbyProduct result = null;
            setLocal(tcProduct); //设置图片、可用状态、经销商属性
            DecimalFormat df = new DecimalFormat("0.00");
            if (null != tcProduct.getProductMaxPrice()) {
                tcProduct.setProductMaxPrice(new BigDecimal(df.format(tcProduct.getProductMaxPrice())));
            }

            if (null != tcProduct.getProductMinPrice()) {
                tcProduct.setProductMinPrice(new BigDecimal(df.format(tcProduct.getProductMinPrice())));
            }
            tcProduct.setIsDelete(TrueFalseStatus.FALSE.getValue());
            if (source.hashCode() != tcProduct.hashCode()) { //创建时间和更新时间不做对比，已从hashcode排除
                result = BeanUtils.getDiffProperties(source, tcProduct);
                if (null != result) {
                    result.setUpdateTime(new Date()); //设置基础属性
                    result.setProductId(tcProduct.getProductId()); //设置主键
                    //图片有变化或者ImageUrl图片为空,随机一个图片
                    if (null != result.getProductImageUrlListRaw() || StringUtils.isEmpty(tcProduct.getImageUrl())) {
                        if (StringUtils.isEmpty(tcProduct.getImageUrl())) {
                            if (CollectionUtils.isEmpty(tcProduct.getProductImageUrlListRaw())) {
                                result.setImageUrl(GlobalDisconf.getDefaultImage());
                            } else {
                                result.setImageUrl(tcProduct.getProductImageUrlListRaw().get((int) Math.random() * tcProduct.getProductImageUrlListRaw().size()));
                            }
                        }
                    }
                    productMapper.updateByPrimaryKeySelective(result); //更新有变化的字段
                }
            }
        }

        ZbyRecomInfo queryRecomInfo = new ZbyRecomInfo();
        queryRecomInfo.setProductId(queryRecomInfo.getProductId());
        List<ZbyRecomInfo> dbRecomList = recomInfoMapper.selectByProductId(tcProduct.getProductId());
        //内存中处理tcProduct，获取到主题属性，转换到tcRecomList中
        List<ZbyRecomInfo> tcRecomList = zbyRecomInfoList(tcProduct);

        if (CollectionUtils.isEmpty(dbRecomList)) { //数据库不存在主题信息，直接插入数据库
            for (ZbyRecomInfo tcRecom : tcRecomList) {
                tcRecom.setCreateTime(new Date());
                tcRecom.setUpdateUser("system");
                tcRecom.setUpdateTime(new Date());
                tcRecom.setIsDelete(TrueFalseStatus.FALSE.getValue()); //主题状态，在插入时，默认为不可用
                recomInfoMapper.insert(tcRecom); //主题数据不存在，插入数据
            }
        } else {
            //把数据库查询的值，根据主键和删除状态放到map中，dbMap数据库表中的映射，tcMap同城接口表中的映射
            Map<String, Boolean> dbMap = new HashMap<String, Boolean>();
            Map<String, Boolean> tcMap = new HashMap<String, Boolean>();

            //给tcMap赋值
            for (ZbyRecomInfo tcRecom : tcRecomList) {
                tcMap.put(tcRecom.getTitle(), tcRecom.getIsDelete());
            }

            //给dbMap赋值
            for (ZbyRecomInfo dbRecom : dbRecomList) {
                dbMap.put(dbRecom.getTitle(), dbRecom.getIsDelete());
            }

            for (ZbyRecomInfo tcRecom : tcRecomList) {
                if (!dbMap.keySet().contains(tcRecom.getTitle())) {//如果数据库中不包含同城获取到的数据，做插入操作
                    tcRecom.setCreateTime(new Date());
                    tcRecom.setIsDelete(TrueFalseStatus.FALSE.getValue()); //主题状态，在插入时，默认为不可用
                    tcRecom.setUpdateUser("system");
                    tcRecom.setUpdateTime(new Date());
                    recomInfoMapper.insert(tcRecom); //主题数据不存在，插入数据
                }
            }

            for (ZbyRecomInfo dbRecom : dbRecomList) {
                if (!tcMap.keySet().contains(dbRecom.getTitle())) {  //同城过来的数据不包括数据库已存在的数据，做删除操作
                    dbRecom.setIsDelete(TrueFalseStatus.TRUE.getValue());
                    recomInfoMapper.updateIsDelete(dbRecom); //删除操作
                }
            }
        }
    }


    /**
     * 套餐明细更新
     *
     * @param tcProducts
     */
    private void packageChange(List<ZbyProduct> tcProducts) {
        if (CollectionUtils.isEmpty(tcProducts)) {
            logger.info("套餐详情无更新");
            return;
        }
        //套餐更新
        for (ZbyProduct var : tcProducts) {
            try {
                updatePackage(var);
            } catch (Exception e) {
                logger.error("更新线路{}详情失败", var.getProductId(), e);
            }
        }
    }


    /**
     * 套餐更新
     *
     * @param tcProduct
     */
    private void updatePackage(ZbyProduct tcProduct) throws InstantiationException, IllegalAccessException {

        List<ZbyProductPackage> tcPackageList = tcProduct.getProductPackageList();
        if (CollectionUtils.isEmpty(tcPackageList)) {
            return;
        }

        List<String> tcPackageIds = new ArrayList<String>();
        List<String> packageResId=new ArrayList<String>(); //包名和资源ID的唯一值

        for (ZbyProductPackage tcPackage : tcPackageList) {
            //修改线路基础信息
            tcPackageIds.add(tcPackage.getPackageId());
            ZbyProductPackage dbPakcage = productPackageMapper.selectByPackageId(tcPackage.getPackageId());
            if (null == dbPakcage) {
                //新增
                tcPackage.setPackageId(tcPackage.getPackageId());
                tcPackage.setProductId(tcProduct.getProductId());
                tcPackage.setCreateTime(new Date());
                tcPackage.setUpdateTime(new Date());
                tcPackage.setIsDelete(TrueFalseStatus.FALSE.getValue()); //未删除
                tcPackage.setSaleCount(0);
                productPackageMapper.insert(tcPackage);
            } else {
                //接口过来的数据，需要把删除状态更新到可用状态
                tcPackage.setIsDelete(TrueFalseStatus.FALSE.getValue());
                if (dbPakcage.hashCode() != tcPackage.hashCode()) {
                    ZbyProductPackage result = null;
                    result = BeanUtils.getDiffProperties(dbPakcage, tcPackage); //更新时间和创建时间不做比较
                    if (result != null) { //有数据变化，做更新
                        result.setUpdateTime(new Date());
                        result.setPackageId(dbPakcage.getPackageId()); //主键值
                        productPackageMapper.updateByPrimaryKeySelective(result);
                    }
                }
            }

            //删除数据库存在，同城接口不存在的套餐
            ZbyProductPackage query = new ZbyProductPackage();
            query.setIsDelete(TrueFalseStatus.FALSE.getValue());
            query.setProductId(tcProduct.getProductId());
            List<ZbyProductPackage> dbPackageList = productPackageMapper.select(query);
            for (ZbyProductPackage var : dbPackageList) {
                if (!tcPackageIds.contains(var.getPackageId())) {
                    productPackageMapper.deleteByPackageId(var.getPackageId());
                }
            }


            List<ZbyProductPackageDetail> tcDetailList = new ArrayList<ZbyProductPackageDetail>();
            tcDetailList.addAll(tcPackage.getSceneryList());
            tcDetailList.addAll(tcPackage.getHotelList());

            //修改和新增
            for (ZbyProductPackageDetail tcDetail : tcDetailList) {
                packageResId.add(tcPackage.getPackageId()+tcDetail.getResId());
                ZbyProductPackageDetail queryDetail = new ZbyProductPackageDetail();
                queryDetail.setPackageId(tcPackage.getPackageId());
                queryDetail.setResId(tcDetail.getResId());
                ZbyProductPackageDetail dbDetail = productPriceDetailMapper.selectOne(queryDetail);
                Integer key = null;
                if (dbDetail == null) {
                    tcDetail.setProductId(tcProduct.getProductId());
                    tcDetail.setPackageId(tcPackage.getPackageId());
                    setDetailLocal(tcDetail);
                    tcDetail.setUpdateTime(new Date());
                    productPriceDetailMapper.insertReturnPri(tcDetail);
                    key = tcDetail.getId();
                } else {
                    //更新字段内容
                    key = dbDetail.getId();
                    ZbyProductPackageDetail result = null;
                    setDetailLocal(tcDetail);
                    if (dbDetail.hashCode() != tcDetail.hashCode()) {
                        result = BeanUtils.getDiffProperties(dbDetail, tcDetail);
                        if (null != result) {
                            result.setId(dbDetail.getId()); //设置主键
                            result.setUpdateTime(new Date());
                            productPriceDetailMapper.updateByPrimaryKeySelective(result);
                        }
                    }
                }
                //删除历史数据，同时插入接口最新数据
                productAdditionMapper.deleteByDetailId(key);
                List<ZbyProductAddition> additionDetails = new ArrayList<ZbyProductAddition>();
                List<ZbyProductAddition> addtions = tcDetail.getAdditionProductList();
                if (!CollectionUtils.isEmpty(addtions)) {
                    for (ZbyProductAddition addition : addtions) {
                        addition.setPackageDetailId(key);
                        addition.setProductId(tcProduct.getProductId());
                        addition.setPackageId(tcPackage.getPackageId());
                        addition.setIsDelate(TrueFalseStatus.FALSE.getValue());
                        addition.setCreateTime(new Date());
                        addition.setUpdateTime(new Date());
                        productAdditionMapper.insert(addition);
                    }
                }
            }

        }

        //删除多余详情套餐
        List<ZbyProductPackageDetail> deleteDetailList=productPriceDetailMapper.selectByProductIdAndRedId(tcProduct.getProductId(), packageResId);
        for(ZbyProductPackageDetail var:deleteDetailList){
            productPriceDetailMapper.deleteByPrimaryKey(var.getId());
            productAdditionMapper.deleteByDetailId(var.getId());
        }

    }

    /**
     * 更新套餐详情属性
     *
     * @param tcDetail
     */
    private void setDetailLocal(ZbyProductPackageDetail tcDetail) {
        //床型
        if (!CollectionUtils.isEmpty(tcDetail.getProPropsRaw())) {
            tcDetail.setProProps(tcDetail.getProPropsRaw().toString());
            tcDetail.setBedType(tcDetail.getProPropsRaw().get(TcInterface.BED_TYPE.getValue()));
        }
        //图片
        if (!CollectionUtils.isEmpty(tcDetail.getResImageListRaw())) {
            tcDetail.setResImageList(StringUtils.join(tcDetail.getResImageListRaw(), ","));
        }else{
            tcDetail.setResImageList("");
        }
        //精度，维度
        tcDetail.setLat(changeLat(tcDetail.getResourceGpses()));
        tcDetail.setLon(changeLon(tcDetail.getResourceGpses()));
    }

    /**
     * 班期价格
     *
     * @param productId
     * @param startDate
     * @param overDate
     */
    public void lineSaleInfoCalendar(String productId, Date startDate, Date overDate) {
        try {
            //查询班期价格接口，只查询最后一天的数据做为更新
            Map<String, String> requestMap = new HashMap<String, String>();
            requestMap.put("LineId", productId);
            requestMap.put("StartTime", DateUtils.format(startDate, DateUtils.WEB_FORMAT));
            requestMap.put("EndTime", DateUtils.format(overDate, DateUtils.WEB_FORMAT));
            LineSaleInfoCalenderResult lineSaleInfoCalenderResult = tcService.lineSaleInfoCalendar(requestMap);
            //查询班期价格接口结束，赋值到lineSaleInfoCalenderResult

            if (lineSaleInfoCalenderResult != null) {
                List<PackageDetail> tcList = lineSaleInfoCalenderResult.getPackageDetails();
                for (PackageDetail tcPackageDetail : tcList) {
                    //查询数据库，得到班期列表，并转换为map，map的key为日期的主键
                    if (tcPackageDetail.getPackageDetails() != null) {
                        ZbyProductPackagePrice query = new ZbyProductPackagePrice();
                        query.setProductId(productId);
                        query.setPackageId(tcPackageDetail.getPackageId());
                        List<ZbyProductPackagePrice> dbPriceList = zbyProductPackagePriceMapper.select(query);
                        Map<String, ZbyProductPackagePrice> dbMap = new HashMap<String, ZbyProductPackagePrice>();
                        for (ZbyProductPackagePrice var : dbPriceList) {
                            dbMap.put(DateUtils.format(var.getPackageSaleDate(), "MM/dd/yyyy"), var);
                        }
                        //查询数据库，得到班期列表，并转换为map，map的key为日期的主键-完毕

                        //set为同城接口
                        Set<String> tcSet = tcPackageDetail.getPackageDetails().keySet();

                        List<ZbyProductPackagePrice> inserts = new ArrayList<ZbyProductPackagePrice>();
                        List<ZbyProductPackagePrice> updates = new ArrayList<ZbyProductPackagePrice>();

                        for (String dataKey : tcSet) {
                            ZbyProductPackagePrice tcPrice = tcPackageDetail.getPackageDetails().get(dataKey);
                            tcPrice.setProductId(productId);
                            tcPrice.setPackageId(tcPackageDetail.getPackageId());
                            tcPrice.setBeforehandBookingDay(tcPackageDetail.getBeforehandBookingDay());
                            DecimalFormat df = new DecimalFormat("0.00");
                            if (null != tcPrice.getTcDirectPrice()) {
                                tcPrice.setTcDirectPrice(new BigDecimal(df.format(tcPrice.getTcDirectPrice())));
                            }
                            if (null != tcPrice.getMinPrice()) {
                                tcPrice.setMinPrice(new BigDecimal(df.format(tcPrice.getMinPrice())));
                            }
                            if (null != tcPrice.getMaxPrice()) {
                                tcPrice.setMaxPrice(new BigDecimal(df.format(tcPrice.getMaxPrice())));
                            }
                            if (null != tcPrice.getRetailPrice()) {
                                tcPrice.setRetailPrice(new BigDecimal(df.format(tcPrice.getRetailPrice())));
                            }
                            if (null != tcPrice.getDistributionSalePrice()) {
                                tcPrice.setDistributionSalePrice(new BigDecimal(df.format(tcPrice.getDistributionSalePrice())));
                            }

                            tcPrice.setPackageSaleDate(DateUtils.parseDate(dataKey, "MM/dd/yyyy HH:mm:ss"));
                            ZbyProductPackagePrice dbPrice = dbMap.get(DateUtils.format(tcPrice.getPackageSaleDate(), "MM/dd/yyyy"));
                            if (dbPrice != null) {
                                //把线路设置为可用状态，显示出来
                                ZbyProductPackagePrice result = null;
                                if (dbPrice.hashCode() != tcPrice.hashCode()) {
                                    result = BeanUtils.getDiffProperties(dbPrice, tcPrice);
                                }
                                if (result != null) { //没有变化，给赋值，不做更新
                                    result.setId(dbPrice.getId());  //设置主键值
                                    updates.add(result);
                                }
                            } else {
                                inserts.add(tcPrice);
                            }
                        }


                        if (!CollectionUtils.isEmpty(inserts)) {
                            zbyProductPackagePriceMapper.batchInsert(inserts);
                        }
                        if (!CollectionUtils.isEmpty(updates)) {
                            zbyProductPackagePriceMapper.batchUpdate(updates);
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("每日更新班期{}价格出错", jsonUtils.serialize(productId), e);
            MonitorUtil.recordOne("JobProduct_lineSaleInfoCalendar_task_fail");
        }
    }


    private String changeLat(List<ResourceGps> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        if (StringUtils.isNotEmpty(list.get(0).getLat())) {
            return list.get(0).getLat();
        }
        if (list.size() > 1) {
            return list.get(1).getLat();
        }
        return "";
    }

    private String changeDate(String var) {
        if (StringUtils.isNotEmpty(var) && var.length() > 18) {
            return DateUtils.format(new Date(Long.valueOf(var.substring(6, 19))), DateUtils.SHORT_FORMAT);
        }
        return "";
    }

    private String changeLon(List<ResourceGps> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        if (StringUtils.isNotEmpty(list.get(0).getLon())) {
            return list.get(0).getLon();
        }
        if (list.size() > 1) {
            return list.get(1).getLon();
        }
        return "";
    }


    /**
     * 从同城接口获取主题列表
     *
     * @param tcProduct
     * @return
     */
    private List<ZbyRecomInfo> zbyRecomInfoList(ZbyProduct tcProduct) {
        List<ZbyRecomInfo> zbyRecomInfoList = new ArrayList<ZbyRecomInfo>();
        if (CollectionUtils.isEmpty(tcProduct.getRecomInfo())) {
            return zbyRecomInfoList;
        }
        for (ZbyRecomInfo zbyRecomInfo : tcProduct.getRecomInfo()) {
            zbyRecomInfo.setProductId(tcProduct.getProductId());
            zbyRecomInfo.setCreateTime(new Date());
            zbyRecomInfo.setIsDelete(TrueFalseStatus.TRUE.getValue());
            zbyRecomInfoList.add(zbyRecomInfo);
        }
        return zbyRecomInfoList;
    }

    /**
     * 设置路线的基础属性
     *
     * @param tcProduct
     */

    private void setLocal(ZbyProduct tcProduct) {

        tcProduct.setBeginDate(changeDate(tcProduct.getBeginDate()));
        tcProduct.setOverDate(changeDate(tcProduct.getOverDate()));
        //图片处理，如果读取不到图片数据，设置为默认值

        if (CollectionUtils.isEmpty(tcProduct.getProductImageUrlListRaw())) {
            tcProduct.setProductImageUrlList(GlobalDisconf.getDefaultImage());
        } else {
            tcProduct.setProductImageUrlList(StringUtils.join(tcProduct.getProductImageUrlListRaw(), ","));
        }
    }

}

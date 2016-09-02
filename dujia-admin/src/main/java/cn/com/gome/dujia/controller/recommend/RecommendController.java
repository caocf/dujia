package cn.com.gome.dujia.controller.recommend;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.gome.dujia.controller.venue.CommonController;
import cn.com.gome.dujia.disconf.RecommendDisconf;
import cn.com.gome.dujia.dto.PageInfo;
import cn.com.gome.dujia.enums.OrderSource;
import cn.com.gome.dujia.model.Advert;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.service.AdvertService;

import com.alibaba.fastjson.JSONObject;
import com.gome.plan.mybatis.pagehelper.Page;
import com.gome.plan.mybatis.pagehelper.PageHelper;
import com.gome.plan.tools.utils.DateUtils;

@Controller
@RequestMapping(value="/admin/recommend")
public class RecommendController extends CommonController{

	public static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	private AdvertService advertService;
	
	@RequestMapping(value="recommendProList")
	public String queryList( HttpServletRequest request,Map<String, Object> model,Advert advert){
		try{
			// 获取分页参数
			int currentPage = 1;// 当前页码
			String currentPageP = request.getParameter("currentPage");
			if ( currentPageP != null && currentPageP.trim().length() != 0 ) {
				currentPage = Integer.parseInt(currentPageP);
			}
			int perPageRows = 15;// 每页行数
			String perPageRowsP = request.getParameter("perPageRows");
			if ( perPageRowsP != null && perPageRowsP.trim().length() != 0 ) {
				perPageRows = Integer.parseInt(perPageRowsP);
			}
			Page<Advert> pageList = PageHelper.startPage( currentPage , perPageRows );
			advertService.getRecommendList(advert);
		    long totalCount = pageList.getTotal();
			// 设置分页显示信息
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurrentPage(currentPage);
			pageInfo.setPerPageRows(perPageRows);
			pageInfo.setTotalRows(totalCount);
			pageInfo.setPageList( pageList );
			// 设置返回数据
			model.put("pageInfo", pageInfo);
		    model.put("advert", advert);
		    model.put("pAdvert", loadRecommendMap());
			model.put("sourceMap", loadOrderSourceMap());
		} catch (Exception e) {
			logger.error("queryList出错{}", e);
		}
		return "recommend/recommendPro_list";
	}

	/**
	 * 去推荐的添加修改页面
	 * @param request
	 * @param model
	 * @param advert
	 * @return
	 */
	@RequestMapping(value="/toRecommendAddorUp")
	public String toRecommendAddorUp(Map<String, Object> model,Advert advert){
		try{
			//如果id不空，则查询广告详情
			if(null!=advert && null != advert.getId() && advert.getId()>0){
				advert = advertService.selectAdvertById(advert.getId());
				model.put("advert", advert);
			}
		} catch (Exception e) {
			logger.error("toRecommendAddorUp出错{}", e);
		}
		return "recommend/recommendPro_add";
	}
	
	/**
	 * 查看页面
	 * @param request
	 * @param model
	 * @param advert
	 * @return
	 */
	@RequestMapping(value="/showRecommen")
	public String showRecommen(Map<String, Object> model,Advert advert){
		try{
			advert = advertService.selectAdvertById(advert.getId());
			model.put("advert", advert);
		    model.put("pAdvert", loadRecommendMap());
		    model.put("sourceMap", loadOrderSourceMap());
		    model.put("channelType", loadChannelTypeMap());
		} catch (Exception e) {
			logger.error("showRecommen出错{}", e);
		}
		return "recommend/recommendPro_show";
	}
	
	/**
	 * 保存推荐商品
	 * @param request
	 * @param model
	 * @param advert
	 * @return
	 */
	@RequestMapping(value="/saveRecommend")
	public void saveRecommend(HttpServletResponse response,@RequestBody Advert advert){
		logger.info("更新推荐商品参数："+JSONObject.toJSONString(advert));
		String result = "";
		try {
			if(null != advert) {
				//校验广告是否投放重复
				if (vidateRecommend(advert) > 0) {
					result = "该时间段已配置推荐商品，请修改!";
				} else {
					//如果id不空，则保存，否则添加
					advert.setUpdateUser(getUserSessionInfo());
					advert.setUpdateTime(new Date());
					if(null != advert.getId() && advert.getId()>0){
						//修改广告
						result = updateRecommend(advert);
					}else{
						//添加广告
						advert.setCreateUser(getUserSessionInfo());
						advert.setCreateTime(new Date());
						result = addRecommend(advert);
					}
				}
			}else{
				result = "failure";
			}
		} catch (Exception e) {
			if(null != advert.getId() && advert.getId()>0){
				logger.error("更新推荐出错{}", e);
			}else{
				logger.error("添加推荐出错{}", e);
			}
			result = "failure";
		} finally {
			writeResult(response, result);
		}
	}
	
	/**
	 * 添加推荐
	 * @param advert
	 * @return
	 */
	private String addRecommend(Advert advert){
		logger.info("添加推荐内容", advert);
		advert.setDataType(1);
		advert.setPage(loadPage(advert.getModule()));
		if (advertService.insert(advert) == 1) {
			return "success";
		} else {
			return "failure";
		}
	}
	/**
	 * 修改推荐
	 * @param advert
	 * @return
	 */
	private String updateRecommend(Advert advert){
		logger.info("修改推荐内容", advert);
		advert.setPage(loadPage(advert.getModule()));
		if (advertService.updateAdvertById(advert) == 1) {
			return "success";
		} else {
			return "failure";
		}
	}
		
	/**
	 * 校验推荐是否已投放
	 * @param advert
	 * @return
	 */
	private int vidateRecommend(Advert advert){
		Date nowDate = new Date();
		if (null == advert.getStates()) {//预防状态为空值
			advert.setStates(0);
	    }
		if(null != advert.getStates() && advert.getStates()!=3){
			String nowDateString = DateUtils.format(nowDate, "yyyy-MM-dd");
			String startDateString = DateUtils.format(advert.getStartTime(), "yyyy-MM-dd");
			String endDateString = DateUtils.format(advert.getEndTime(), "yyyy-MM-dd");
			if (startDateString.compareTo(nowDateString) <= 0 && endDateString.compareTo(nowDateString) >= 0) {
				advert.setStates(1);// 设置为启用状态
			} else if (startDateString.compareTo(nowDateString) > 0) {
				advert.setStates(0);// 设置为待排期状态
			} else if (endDateString.compareTo(nowDateString) < 0) {
				advert.setStates(4);// 设置为已过期状态
			}
		}
		return advertService.findCount(advert);
	}
	/**
	 * 获取平台(recommendList页面)
	 * 
	 * @param response
	 * @param selected
	 */
	@RequestMapping(value = "/getPlateformList")
	@ResponseBody
	public void getPlateformList(HttpServletResponse response, int selected){
		StringBuffer sbf = new StringBuffer();
		try{
			OrderSource[] orders = OrderSource.values();
			sbf.append("<option value=\"\">全部</option>");
			for (OrderSource orderSource : orders) {
				sbf.append("<option ");
				if (orderSource.getValue().intValue()==selected) {
					sbf.append("selected=\"selected\" ");
				}
				sbf.append("value=\"" + orderSource.getValue() + "\">" + orderSource.getName() + "</option>");
			}
		} catch (Exception e) {
			logger.error("getPlateformList出错{}", e);
		}finally{
			writeResult(response, sbf.toString());
		}
	}
	/**
	 * 获取平台(添加修改页面)
	 * @param response
	 * @param selected
	 */
	@RequestMapping(value = "/getRadioPlateform")
	@ResponseBody
	public void getRadioPlateform(HttpServletResponse response, int selected) {
		StringBuffer sbf = new StringBuffer();
		try{
			OrderSource[] orders = OrderSource.values();
			for (int i=0;i<orders.length;i++) {
				OrderSource orderSource =orders[i];
				sbf.append("<span class=\"LeftNavbot\"><input type=\"radio\" id=\"plateform\" name=\"plateform\" data-key=\"plateform\"");
				if(selected==0 && i==0){
					sbf.append("checked=\"checked\" ");
				}else if(orderSource.getValue().intValue()==selected){
					sbf.append("checked=\"checked\" ");
				}
				sbf.append("value=\"" + orderSource.getValue() + "\">" + orderSource.getName() + "</span>");
			}
		} catch (Exception e) {
			logger.error("getRadioPlateform出错{}", e);
		}finally{
			writeResult(response, sbf.toString());
		}
	}

	/**
	 * 推荐,展示页面(recommendList页面)
	 * 
	 * @param response
	 * @param selected
	 */
	@RequestMapping(value = "/getModuleList")
	@ResponseBody
	public void getModuleList(HttpServletResponse response, String selected) {
		StringBuffer sbf = new StringBuffer();
		try{
			List<cn.com.gome.dujia.disconf.Advert> adverts = RecommendDisconf.getRecommends();
			sbf.append("<option value=\"\">全部</option>");
			for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
				sbf.append("<option ");
				if (advert.getValue().equals(selected)) {
					sbf.append("selected=\"selected\" ");
				}
				sbf.append("value=\"" + advert.getValue() + "\">" + advert.getName() + "</option>");
			}
		} catch (Exception e) {
			logger.error("getModuleList出错{}", e);
		}finally{
			writeResult(response, sbf.toString());
		}
	}
	/**
	 * 推荐,展示位置(添加修改页面)
	 * @param response
	 * @param selected
	 */
	@RequestMapping(value = "/getRadioModule")
	@ResponseBody
	public void getRadioModule(HttpServletResponse response, String selected) {
		StringBuffer sbf = new StringBuffer();
		try{
			List<cn.com.gome.dujia.disconf.Advert> adverts = RecommendDisconf.getRecommends();
			for (int i=0;i<adverts.size();i++) {
				cn.com.gome.dujia.disconf.Advert advert = adverts.get(i);
				sbf.append("<span class=\"LeftNavbot\"><input type=\"radio\" id=\"module"+(i+1)+"\" name=\"module\" data-key=\"module\"  onclick=\"chaneModule();\" ");
				if("".equals(selected) && i==0){
					sbf.append("checked=\"checked\" ");
				}else if(advert.getValue().equals(selected)){
					sbf.append("checked=\"checked\" ");
				}
				sbf.append("value=\"" + advert.getValue() + "\">" + advert.getName() + "</span>");
			}
		} catch (Exception e) {
			logger.error("getRadioModule出错{}", e);
		}finally{
			writeResult(response, sbf.toString());
		}
	}


	/**
	 * 推荐,展示页面下一级的展示位置(recommendList页面)
	 * @param response
	 * @param platform 平台
	 * @param module 选中的页面设置
	 * @param selected 广告位置设置
	 */
	@RequestMapping(value = "/getPositionList")
	@ResponseBody
	public void getPositionList(HttpServletResponse response, String platform, String module, String selected) {
		StringBuffer sbf = new StringBuffer();
		try{
			List<cn.com.gome.dujia.disconf.Advert> adverts = RecommendDisconf.getRecommends();
			sbf.append("<option value=\"\">全部</option>");
			for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
				if(advert.getValue().equals(module)){
					List<cn.com.gome.dujia.disconf.Advert> p = advert.getPositions();
					for (cn.com.gome.dujia.disconf.Advert pAdvert : p) {
						sbf.append("<option ");
						if (pAdvert.getValue().equals(selected)) {
							sbf.append("selected=\"selected\" ");
						}
						sbf.append("value=\"" + pAdvert.getValue() + "\">" + pAdvert.getName() + "</option>");
					}
				}
			}
		} catch (Exception e) {
			logger.error("getPositionList出错{}", e);
		}finally{
			writeResult(response, sbf.toString());
		}
	}
	/**
	 * 推荐,展示页面下一级的展示位置(添加修改页面)
	 * @param response
	 * @param platform 平台
	 * @param module 选中的页面设置
	 * @param selected 广告位置
	 */
	@RequestMapping(value = "/getRadioPosition")
	@ResponseBody
	public void getRadioPosition(HttpServletResponse response, String platform, String module, String selected) {
		StringBuffer sbf = new StringBuffer();
		try{
			List<cn.com.gome.dujia.disconf.Advert> adverts = RecommendDisconf.getRecommends();
			for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
				if(advert.getValue().equals(module)){
					List<cn.com.gome.dujia.disconf.Advert> p = advert.getPositions();
					for (int i=0;i<p.size();i++) {
						cn.com.gome.dujia.disconf.Advert pAdvert = p.get(i);
						sbf.append("<span class=\"LeftNavbot\"><input type=\"radio\" id=\"position"+(i+1)+"\" name=\"position\" data-key=\"position\" onclick=\"removeFirstPos();\" ");
						if("".equals(selected) && i==0){
							sbf.append("checked=\"checked\" ");
						}else if(pAdvert.getValue().equals(selected)){
							sbf.append("checked=\"checked\" ");
						}
						sbf.append("value=\"" + pAdvert.getValue() + "\">" + pAdvert.getName() + "</span>");
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("getRadioPosition出错{}", e);
		}finally{
			writeResult(response, sbf.toString());
		}
	}
	/**
	 * 根据产品id检索产品
	 * @param response
	 * @param platform
	 */
	@RequestMapping(value="findProduct")
	public void findProduct(HttpServletResponse response, String productId){
		JSONObject object = new JSONObject();
		try{
			ZbyProduct zbyProduct = advertService.findProductById(productId);
			object.put("status", "success");
			object.put("product", zbyProduct);
		}catch(Exception e){
			object.put("status", "error");
		}finally{
			writeAjaxStr(response,  JSONObject.toJSONString( object ));
		}
	}
	
	/**
	 * 根据版块的值获取所在展示页面
	 * @param name
	 * @return
	 */
	private String loadPage(String module){
		String page = "";
		try{
			List<cn.com.gome.dujia.disconf.Advert> adverts = RecommendDisconf.getRecommends();
			for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
				if(advert.getValue().equals(module)){
					page = advert.getModule();
					break;
				}
			}
		} catch (Exception e) {
			logger.error("loadPage出错{}", e);
		}
		return page;
	}
}

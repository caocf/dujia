package cn.com.gome.dujia.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import cn.com.gome.dujia.enums.TrueFalseStatus;
import cn.com.gome.dujia.mapper.business.ZbyLineMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductAdditionMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackageDetailMapper;
import cn.com.gome.dujia.mapper.business.ZbyProductPackageMapper;
import cn.com.gome.dujia.mapper.business.ZbyRecomInfoMapper;
import cn.com.gome.dujia.model.ZbyLine;
import cn.com.gome.dujia.model.ZbyProduct;
import cn.com.gome.dujia.model.ZbyProductAddition;
import cn.com.gome.dujia.model.ZbyProductPackage;
import cn.com.gome.dujia.model.ZbyProductPackageDetail;
import cn.com.gome.dujia.model.ZbyRecomInfo;
import cn.com.gome.dujia.service.TcService;
import cn.com.gome.dujia.vo.json.productinfo.ResourceGps;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"}) //用于指定配置文件所在的位置
public class TcServiceImplTest {

    @Autowired
    private TcService tcService;

    @Autowired
    private ZbyLineMapper lineMapper;
    
    @Autowired
    private ZbyProductMapper productMapper;
    
    @Autowired
    private ZbyProductPackageMapper productPackageMapper;
    
    @Autowired
    private ZbyProductPackageDetailMapper productPackageDetailMapper;
    
    @Autowired
    private ZbyRecomInfoMapper recomInfoMapper;
    
    @Autowired
    private ZbyProductAdditionMapper productAdditionMapper;

    @Test
    public void line() {
    	List<ZbyLine> lines = tcService.lineIndexInfoList(null);
    	if (null != lines) {
    		for (ZbyLine line : lines) {
    			line.setPackageIdList(line.getPackageIdListRaw().toString().replace("[", "").replace("]", ""));
    			line.setCreateTime(new Date());
    			line.setUpdateTime(new Date());
    			lineMapper.insertSelective(line);
    		}
    	}
    	System.out.println("成功");
    }
    
    private <T> List<List<T>> pageList(List<T> list, int pageRows) {
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		//如果集合个数<=每页显示个数 直接返回该集合
		if(list.size() <= pageRows) {
			return Arrays.asList(list);
		}
		//总个数
		int totalRows = list.size();
		//页数
		int totalPages = getTotalPages(totalRows, pageRows);
		List<List<T>> pageList = Lists.newArrayList();
		for(int i=0; i<totalPages; i++) {
			int fromIndex = pageRows * i; //开始索引位置
			int toIndex = pageRows * (i+1) > totalRows ? totalRows : pageRows * (i+1); //结束索引位置
			pageList.add(list.subList(fromIndex, toIndex));
		}
		return pageList;
	}
    
    /**
	 * 计算页数
	 * @param totalRows  总个数
	 * @param pageRows   每页显示个数
	 * @return
	 */
	private int getTotalPages(int totalRows, int pageRows) {
    	if(totalRows <= 0 || pageRows <= 0) {
    		return 0;
    	}
		if(totalRows % pageRows == 0) {
			return totalRows / pageRows;
		}else{
			return totalRows / pageRows + 1;
		}
    }
    
    @Test
    public void product() {
    	List<ZbyLine> lines = tcService.lineIndexInfoList(null);
    	if (null != lines) {
    		List<List<ZbyLine>> lists = pageList(lines, 10);
			for(List<ZbyLine> list : lists) {
				StringBuilder ids = new StringBuilder();
				for (ZbyLine line : list) {
					if (ids.length() > 0) {
						ids.append(",");
					}
					ids.append(line.getLineId());
				}
				//保存数据库
				insert(ids.toString());
			}
    	}
    	
    }
    
    private void insert(String ids) {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("ProductIds", ids);
    	List<ZbyProduct> products = tcService.getProductDetailInfo(map);
    	if (null != products) {
    		for (ZbyProduct product : products) {
    			product.setVenderId("70000719");
    			product.setBrowseCount(100);
    			product.setCreateTime(new Date());
    			product.setUpdateTime(new Date());
    			product.setIsDelete(TrueFalseStatus.FALSE.getValue());
    			productMapper.insertSelective(product);
    			
    			if (null != product.getRecomInfo()) {
    				for (ZbyRecomInfo recomInfo : product.getRecomInfo()) {
    					recomInfo.setProductId(product.getProductId());
    					product.setCreateTime(new Date());
    	    			product.setIsDelete(TrueFalseStatus.FALSE.getValue());
    	    			recomInfoMapper.insertSelective(recomInfo);
    				}
    			}
    			
    			if (null != product.getProductPackageList()) {
    				for (ZbyProductPackage productPackage : product.getProductPackageList()) {
    					productPackage.setProductId(product.getProductId());
    					productPackage.setCreateTime(new Date());
    					productPackage.setUpdateTime(new Date());
    					productPackage.setIsDelete(TrueFalseStatus.FALSE.getValue());
    					productPackageMapper.insertSelective(productPackage);
    					
    					if (null != productPackage.getHotelList()) {
    						for (ZbyProductPackageDetail productPackageDetail : productPackage.getHotelList()) {
    							productPackageDetail.setProductId(product.getProductId());
    							productPackageDetail.setPackageId(productPackage.getPackageId());
    							
    							String lon = null;
    							String lat = null;
    							if (null != productPackageDetail.getResourceGpses()) {
    								for (ResourceGps gps : productPackageDetail.getResourceGpses()) {
    									if (gps.getGpsType().equals("1")) {
    										lon = gps.getLon();
    										lat = gps.getLat();
    										break;
    									}
    								}
    								if (null == lon && null == lat) {
    									for (ResourceGps gps : productPackageDetail.getResourceGpses()) {
        									if (gps.getGpsType().equals("2")) {
        										lon = "xxx";
        										lat = "xxx";
        										break;
        									}
        								}
    								}
    							}
    							productPackageDetail.setLon(lon);
    							productPackageDetail.setLat(lat);
    							productPackageDetailMapper.insertSelective(productPackageDetail);
    							
    							if (null != productPackageDetail.getAdditionProductList()) {
    								for (ZbyProductAddition productAddition : productPackageDetail.getAdditionProductList()) {
    									productAddition.setProductId(product.getProductId());
    									productAddition.setPackageId(productPackage.getPackageId());
    									//productAddition.setPackageDetailId(productPackageDetail.getd);
    									productAddition.setCreateTime(new Date());
    									productAddition.setUpdateTime(new Date());
    									productAddition.setIsDelate(TrueFalseStatus.FALSE.getValue());
    									productAdditionMapper.insertSelective(productAddition);
    								}
    							}
    						}
    					}
    					
    					if(null != productPackage.getSceneryList()) {
    						for (ZbyProductPackageDetail productPackageDetail : productPackage.getSceneryList()) {
    							productPackageDetail.setProductId(product.getProductId());
    							productPackageDetail.setPackageId(productPackage.getPackageId());
    							
    							String lon = null;
    							String lat = null;
    							if (null != productPackageDetail.getResourceGpses()) {
    								for (ResourceGps gps : productPackageDetail.getResourceGpses()) {
    									if (gps.getGpsType().equals("1")) {
    										lon = gps.getLon();
    										lat = gps.getLat();
    										break;
    									}
    								}
    								if (null == lon && null == lat) {
    									for (ResourceGps gps : productPackageDetail.getResourceGpses()) {
        									if (gps.getGpsType().equals("2")) {
        										lon = "xxx";
        										lat = "xxx";
        										break;
        									}
        								}
    								}
    							}
    							productPackageDetail.setLon(lon);
    							productPackageDetail.setLat(lat);
    							productPackageDetailMapper.insertSelective(productPackageDetail);
    						}
    					}
    				}
    			}
    		}
    		System.out.println("成功");
    	}
    }

}

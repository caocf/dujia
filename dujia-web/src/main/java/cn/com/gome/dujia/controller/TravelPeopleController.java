package cn.com.gome.dujia.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.gome.plan.tools.utils.RegularUtils;
import com.gome.plan.tools.utils.StringUtils;
import com.gome.sso.model.UserCookie;

import cn.com.gome.trip.unite.model.TripFlag;
import cn.com.gome.trip.unite.model.UserConcat;
import cn.com.gome.trip.unite.model.UserTraveler;
import cn.com.gome.trip.unite.service.ConcatService;
import cn.com.gome.trip.unite.service.UserTravelerService;
/**
 * @author wangweiran
 *
 * 常用联系人、游客信息
 */
@Controller
@RequestMapping(produces = { "application/json;charset=UTF-8" })
public class TravelPeopleController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(TravelPeopleController.class);
	
	@Autowired
	private UserTravelerService userTravelerService;
	@Autowired
	private ConcatService concatService;

	/**
	 * 获取游客信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/travel/list/index.html", method = { RequestMethod.GET })
	@ResponseBody
	public List<UserTraveler> getTravelPeople(HttpServletRequest request, HttpServletResponse response) {
		this.checkLogin(request, response);
		UserCookie uc = getUserCookie(request);
		List<UserTraveler> list = new ArrayList<>();
		try {
			list = userTravelerService.fetchUserConcatList(uc.getId(), TripFlag.HOTEL);
			if(CollectionUtils.isNotEmpty(list)){
				for(UserTraveler traveler : list){
					traveler.setMobile(RegularUtils.sourceMosaic(traveler.getMobile()));
					if(traveler.getCertificateType() == 1){
						//如果是身份证
						traveler.setCertificateCode(RegularUtils.sourceMosaic(traveler.getCertificateCode()));
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("获取基础数据常用游客信息错误{}", e);
		}
		return list;
	}
	
	/**
	 * 增加游客信息
	 * @param userTraveler
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/travel/add/index.html", method = { RequestMethod.GET })
	@ResponseBody
	public List<UserTraveler> addTravelPeople(UserTraveler userTraveler, HttpServletRequest request, HttpServletResponse response) {
		this.checkLogin(request, response);
		UserCookie uc = getUserCookie(request);
		List<UserTraveler> list = new ArrayList<>();
		if(uc == null)
			return list;
		if(!validateAddTravelPeople(userTraveler)){
			return list;
		}
		userTraveler.setUserId(uc.getId());
		userTraveler.setTripFlag(TripFlag.HOTEL.getFlag());
		userTraveler.setCertificateType(1);
		userTraveler.setCanUse(1);
		userTraveler.setCreateTime(new Date());
		try {
			userTravelerService.addUserTraveler(userTraveler);
			list = userTravelerService.fetchUserConcatList(uc.getId(), TripFlag.HOTEL);
		} catch (Exception e) {
			logger.error("添加基础数据常用游客信息错误{}", e);
		}
		return list;
	}
	
	/**
	 * 更新游客信息
	 * @param userTraveler
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/travel/update/index.html", method = { RequestMethod.GET })
	@ResponseBody
	public List<UserTraveler> updateTravelPeople(UserTraveler userTraveler, HttpServletRequest request, HttpServletResponse response) {
		this.checkLogin(request, response);
		UserCookie uc = getUserCookie(request);
		userTraveler.setUserId(uc.getId());
		List<UserTraveler> list = new ArrayList<>();
		try {
			userTravelerService.updateTraveler(userTraveler);
		} catch (Exception e) {
			logger.error("修改基础数据常用游客信息错误{}", e);
		}
		return list;
	}
	/**
	 * 删除游客信息
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/travel/del/index.html", method = { RequestMethod.GET })
	@ResponseBody
	public Integer delTravelPeople(Integer id, HttpServletRequest request, HttpServletResponse response) {
		this.checkLogin(request, response);
		UserCookie uc = getUserCookie(request);
		Integer result = 0;
		try {
			if(null != id && StringUtils.isNotEmpty(uc.getId()))
				result = userTravelerService.deleteTraveler(id, uc.getId());
		} catch (Exception e) {
			logger.error("删除常用游客信息错误{}", e);
		}
		return result;
	}
	/**
	 * 获取常用联系人列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/concat/list/index.html", method = { RequestMethod.GET })
	@ResponseBody
	public List<UserConcat> getConcat(HttpServletRequest request, HttpServletResponse response) {
		this.checkLogin(request, response);
		UserCookie uc = getUserCookie(request);
		List<UserConcat> list = new ArrayList<>();
		try {
			list = concatService.fetchUserConcatList(uc.getId(), TripFlag.HOTEL);
			if(CollectionUtils.isNotEmpty(list)){
				for(UserConcat userConcat : list){
					userConcat.setMobile(RegularUtils.sourceMosaic(userConcat.getMobile()));
					userConcat.setEmail(RegularUtils.sourceMosaic(userConcat.getEmail()));
				}
			}
		} catch (Exception e) {
			logger.error("获取用户信息错误{}", e);
		}
		return list;
	}
	/**
	 * 添加常用联系人信息
	 * @param userConcat
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/concat/add/index.html", method = { RequestMethod.GET })
	@ResponseBody
	public List<UserConcat> addConcat(UserConcat userConcat, HttpServletRequest request, HttpServletResponse response) {
		this.checkLogin(request, response);
		List<UserConcat> list = new ArrayList<>();
		if(!validateAddConcat(userConcat)){
			//返回错误信息
			return list;
		}
		UserCookie uc = getUserCookie(request);
		userConcat.setUserId(uc.getId());
		userConcat.setTripFlag(TripFlag.HOTEL.getFlag());
		userConcat.setCanUse(1);
		userConcat.setCreateTime(new Date());
		try {
			concatService.addConcat(userConcat);
		} catch (Exception e) {
			logger.error("添加信息错误{}", e);
		}
		return list;
	}
	/**
	 * 更新常用联系人信息
	 * @param userConcat
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/concat/update/index.html", method = { RequestMethod.GET })
	@ResponseBody
	public List<UserConcat> updateConcat(UserConcat userConcat, HttpServletRequest request, HttpServletResponse response) {
		this.checkLogin(request, response);
		UserCookie uc = getUserCookie(request);
		userConcat.setUserId(uc.getId());
		List<UserConcat> list = new ArrayList<>();
		try {
			concatService.updateConcat(userConcat);
		} catch (Exception e) {
			logger.error("更新联系人信息错误{}", e);
		}
		return list;
	}
	/**
	 * 删除常用联系人信息
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/concat/del/index.html", method = { RequestMethod.GET })
	@ResponseBody
	public Integer delConcat(Integer id, HttpServletRequest request, HttpServletResponse response) {
		this.checkLogin(request, response);
		UserCookie uc = getUserCookie(request);
		Integer result = 0;
		try {
			if(null != id && StringUtils.isNotEmpty(uc.getId())){
				concatService.deleteConcat(id, uc.getId());
				result = 1;
			}
		} catch (Exception e) {
			logger.error("删除联系人信息错误{}", e);
			return result;
		}
		return result;
	}
	
	private boolean validateAddTravelPeople(UserTraveler traveler){
		boolean result = true;
		if(null == traveler){
			result = false;
		}
		else if(StringUtils.isEmpty(traveler.getMobile()) || !RegularUtils.validate(traveler.getMobile(), RegularUtils.MOBILE)){
			result = false;
		}
		else if(traveler.getCertificateType() == 1 && 
				(StringUtils.isEmpty(traveler.getCertificateCode()) || 
						!RegularUtils.validate(traveler.getCertificateCode(), RegularUtils.IDCARD))){
			result = false;
		}
		else if(StringUtils.isEmpty(traveler.getName())){
			result = false;
		}
		else if(StringUtils.isNotEmpty(traveler.getEmail()) && !RegularUtils.validate(traveler.getEmail(), RegularUtils.EMAIL)){
			result = false;
		}
		return result;
	}
	
	private boolean validateAddConcat(UserConcat concat){
		boolean result = true;
		if(null == concat){
			result = false;
		}
		else if(StringUtils.isEmpty(concat.getMobile()) || !RegularUtils.validate(concat.getMobile(), RegularUtils.MOBILE)){
			result = false;
		}
		else if(StringUtils.isEmpty(concat.getName())){
			result = false;
		}
		else if(StringUtils.isNotEmpty(concat.getEmail()) && !RegularUtils.validate(concat.getEmail(), RegularUtils.EMAIL)){
			result = false;
		}
		return result;
	}
}
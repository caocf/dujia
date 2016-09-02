package cn.com.gome.dujia.controller.baseData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import cn.com.gome.dujia.controller.venue.CommonController;
import cn.com.gome.dujia.dto.PageInfo;
import cn.com.gome.dujia.dto.ZbyCityDto;
import cn.com.gome.dujia.enums.ChannelType;
import cn.com.gome.dujia.enums.OrderSource;
import cn.com.gome.dujia.model.AroundCity;
import cn.com.gome.dujia.model.ZbyCity;
import cn.com.gome.dujia.model.ZbyRecomInfo;
import cn.com.gome.dujia.service.AroundCityService;
import cn.com.gome.dujia.service.ZbyCityService;
import cn.com.gome.dujia.service.ZbyRecomInfoService;

import com.alibaba.fastjson.JSONArray;
import com.gome.plan.mybatis.pagehelper.Page;
import com.gome.plan.mybatis.pagehelper.PageHelper;

@Controller
@RequestMapping(value = "/admin/basedata")
public class BaseDataController extends CommonController {

    public static final Logger logger = LoggerFactory.getLogger(BaseDataController.class);

    @Autowired
    private ZbyRecomInfoService recomInfoService;

    @Autowired
    private AroundCityService aroundCityService;

    @Autowired
    private ZbyCityService zbyCityService;

    /**
     * @Title queryRecomInfoListByPage
     * @Author: lzx
     * @date：2016年4月29日 上午10:25:45
     * @Description：标签列表
     */
    @RequestMapping(value = "/recomInfoList")
    public String queryRecomInfoListByPage(HttpServletRequest request, Model model, ZbyRecomInfo recomInfo) {
        try {
            // 获取分页参数
            int currentPage = 1;// 当前页码
            String currentPageP = request.getParameter("currentPage");
            if (currentPageP != null && currentPageP.trim().length() != 0) {
                currentPage = Integer.parseInt(currentPageP);
            }
            int perPageRows = 15;// 每页行数
            String perPageRowsP = request.getParameter("perPageRows");
            if (perPageRowsP != null && perPageRowsP.trim().length() != 0) {
                perPageRows = Integer.parseInt(perPageRowsP);
            }
            Page<ZbyRecomInfo> pageList = PageHelper.startPage(currentPage, perPageRows);
            // 查询数据
            recomInfoService.queryRecomInfoList(recomInfo);
            long totalCount = pageList.getTotal();
            // 设置分页显示信息
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurrentPage(currentPage);
            pageInfo.setPerPageRows(perPageRows);
            pageInfo.setTotalRows(totalCount);
            pageInfo.setPageList(pageList);
            // 设置返回数据
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("recomInfo", recomInfo);
        } catch (Exception e) {
            logger.error("查询订单列表出错{}", e);
        }
        return "basedata/recom_info_list";
    }

    /**
     * @Title updateRecomInfoStatus
     * @Author: lzx
     * @date：2016年4月29日 上午10:35:22
     * @Description：修改标签状态
     */
    @RequestMapping(value = "/updateRecomInfoStatus", method = RequestMethod.POST)
    public void updateRecomInfoStatus(HttpServletResponse response, ZbyRecomInfo recomInfo) {
        try {
            StringBuffer sbf = new StringBuffer();
            recomInfo.setUpdateUser(this.getUserSessionInfo());
            recomInfoService.updateRecomInfoStatus(recomInfo);
            // 返回数据
            writeResult(response, sbf.toString());
        } catch (Exception e) {
            logger.error("修改标签状态出错", e);
        }
    }


    /**
     * @Title queryAroundCityListByPage
     * @Author: lzx
     * @date：2016年4月29日 下午4:02:29
     * @Description：周边城市列表
     */
    @RequestMapping(value = "/aroundCityList")
    public String queryAroundCityListByPage(HttpServletRequest request, Model model, AroundCity aroundCity) {
        try {
            // 获取分页参数
            int currentPage = 1;// 当前页码
            String currentPageP = request.getParameter("currentPage");
            if (currentPageP != null && currentPageP.trim().length() != 0) {
                currentPage = Integer.parseInt(currentPageP);
            }
            int perPageRows = 15;// 每页行数
            String perPageRowsP = request.getParameter("perPageRows");
            if (perPageRowsP != null && perPageRowsP.trim().length() != 0) {
                perPageRows = Integer.parseInt(perPageRowsP);
            }
            Page<AroundCity> pageList = PageHelper.startPage(currentPage, perPageRows);
            // 查询数据
            aroundCityService.queryAroundCityList(aroundCity);
            long totalCount = pageList.getTotal();
            // 设置分页显示信息
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurrentPage(currentPage);
            pageInfo.setPerPageRows(perPageRows);
            pageInfo.setTotalRows(totalCount);
            pageInfo.setPageList(pageList);
            // 设置返回数据
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("aroundCity", aroundCity);
        } catch (Exception e) {
            logger.error("查询周边城市列表出错{}", e);
        }
        return "basedata/around_city_list";
    }

    /**
     * @return String
     * @Title toInsertAroundCityPage
     * @Author: lzx
     * @date：2016年5月3日 上午10:13:39
     * @Description：跳转到周边城市添加页面
     */
    @RequestMapping(value = "/toInsertAroundCityPage")
    public String toInsertAroundCityPage(Model model) {
        OrderSource[] orderSource = OrderSource.values();
        ChannelType[] channelTypes = ChannelType.values();
        model.addAttribute("orderSource", orderSource);
        model.addAttribute("channelTypes", channelTypes);
        return "basedata/around_city_detail";
    }

    /**
     * @Title toUpdateAroundCityPage
     * @Author: lzx
     * @date：2016年5月3日 上午10:27:28
     * @Description：跳转到到周边城市修改页面
     */
    @RequestMapping(value = "/toUpdateAroundCityPage")
    public String toUpdateAroundCityPage(Model model, Integer id) {
        AroundCity aroundCity = aroundCityService.selectAroundCityById(id);
        OrderSource[] orderSource = OrderSource.values();
        ChannelType[] channelTypes = ChannelType.values();
        model.addAttribute("orderSource", orderSource);
        model.addAttribute("channelTypes", channelTypes);
        model.addAttribute("aroundCity", aroundCity);
        return "basedata/around_city_detail";
    }

    /**
     * @Title addAroundCity
     * @Author: lzx
     * @date：2016年5月3日 上午10:34:58
     * @Description：保存周边城市信息
     */
    @RequestMapping(value = "/saveAroundCity", method = RequestMethod.POST, consumes = "application/json")
    public void saveAroundCity(HttpServletResponse response, @RequestBody AroundCity aroundCity) {
        String result = "";
        try {
            Date nowDate = new Date();
            // 特殊符号处理
            aroundCity.setKeyword(HtmlUtils.htmlEscape(aroundCity.getKeyword()));
            aroundCity.setUpdateUser(getUserSessionInfo());
            aroundCity.setUpdateTime(nowDate);
            if (aroundCity != null && aroundCity.getId() != null) {
                aroundCityService.updateByAroundCity(aroundCity);
                result = "success";
            } else {
                if (aroundCityService.findCount(aroundCity) > 0) {
                    result = "已存在此城市，请修改";
                } else {
                    aroundCity.setCreateUser(getUserSessionInfo());
                    aroundCity.setCreateTime(nowDate);
                    aroundCityService.insert(aroundCity);
                    result = "success";
                }
            }
        } catch (Exception e) {
            result = "failure";
            logger.error("保存周边城市信息出错{}", e);
        }
        writeResult(response, result);
    }

    /**
     * @Title findCityInfoByName
     * @Author: lzx
     * @date：2016年5月3日 下午2:02:55
     * @Description：查询城市列表
     */
    @RequestMapping(value = "/findCityInfoByName", method = RequestMethod.POST)
    public void findCityInfoByName(HttpServletResponse response, ZbyCity zbyCity) {
        try {
            List<ZbyCityDto> list = zbyCityService.queryCityByNameOrPinYin(zbyCity);
            writeResult(response, JSONArray.toJSONString(list));
        } catch (Exception e) {
            logger.error("查询城市列表出错{}", e);
        }
    }

    /**
     * 更新状态
     *
     * @param response
     * @param status
     * @param id
     */
    @RequestMapping(value = "/updateStatus")
    public void updateStatus(HttpServletResponse response, boolean status, int id) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            if (id > 0) {
                AroundCity aroundCity = aroundCityService.selectAroundCityById(id);
                aroundCity.setStates(status);
                aroundCityService.updateByAroundCity(aroundCity);
                result.put("result", "0");
                result.put("msg", "更新状态成功");
            } else {
                result.put("result", "-1");
                result.put("msg", "id为空，更新状态失败");
            }
        } catch (Exception e) {
            result.put("result", "-1");
            result.put("msg", "更新状态异常");
        } finally {
            this.writeAjaxStr(response, JSONObject.toJSON(result).toString());
        }

    }
}

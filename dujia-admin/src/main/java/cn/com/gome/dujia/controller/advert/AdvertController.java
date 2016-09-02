package cn.com.gome.dujia.controller.advert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.com.gome.dujia.controller.venue.CommonController;
import cn.com.gome.dujia.disconf.AdvertDisconf;
import cn.com.gome.dujia.dto.PageInfo;
import cn.com.gome.dujia.enums.OrderSource;
import cn.com.gome.dujia.model.Advert;
import cn.com.gome.dujia.service.AdvertService;
import cn.com.gome.dujia.service.ImageService;

import com.alibaba.fastjson.JSONObject;
import com.gome.plan.mybatis.pagehelper.Page;
import com.gome.plan.mybatis.pagehelper.PageHelper;
import com.gome.plan.tools.utils.DateUtils;

@Controller
@RequestMapping(value = "/admin/advert")
public class AdvertController extends CommonController {

    public static final Logger logger = LoggerFactory.getLogger(AdvertController.class);

    @Autowired
    private AdvertService advertService;
    @Resource
    private ImageService imageService;


    /**
     * 广告查询
     *
     * @param request
     * @param model
     * @param advert
     * @return
     */
    @RequestMapping(value = "/homeFocusList")
    public String queryHomeFocusList(HttpServletRequest request, Map<String, Object> model, Advert advert) {
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
            Page<Advert> pageList = PageHelper.startPage(currentPage, perPageRows);
            advertService.getAdvertList(advert);
            long totalCount = pageList.getTotal();
            // 设置分页显示信息
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurrentPage(currentPage);
            pageInfo.setPerPageRows(perPageRows);
            pageInfo.setTotalRows(totalCount);
            pageInfo.setPageList(pageList);
            // 设置返回数据
            model.put("pageInfo", pageInfo);
            model.put("advert", advert);
            model.put("sourceMap", loadOrderSourceMap());
            model.put("pAdvert", loadAdvertMap());
        } catch (Exception e) {
            logger.error("查询广告列表出错{}", e);
        }
        return "advert/homeFoucs_list";
    }

    /**
     * 去广告添加或修改页面
     *
     * @param request
     * @param model
     * @param advert
     * @return
     */
    @RequestMapping(value = "/toHomeFocusAddorUp")
    public String toHomeFocusAdd(Map<String, Object> model, Advert advert) {
        try {
            //如果id不空，则查询广告详情
            if (null != advert && null != advert.getId() && advert.getId() > 0) {
                advert = advertService.selectAdvertById(advert.getId());
                model.put("advert", advert);
            }
        } catch (Exception e) {
            logger.error("toHomeFocusAdd出错{}", e);
        }
        return "advert/homeFoucs_add";
    }

    /**
     * 查看页面
     *
     * @param request
     * @param model
     * @param advert
     * @return
     */
    @RequestMapping(value = "/showHomeFocus")
    public String showHomeFocus(Map<String, Object> model, Advert advert) {
        try {
            advert = advertService.selectAdvertById(advert.getId());
            model.put("advert", advert);
            model.put("sourceMap", loadOrderSourceMap());
            model.put("pAdvert", loadAdvertMap());
            model.put("channelType", loadChannelTypeMap());
        } catch (Exception e) {
            logger.error("showHomeFocus出错{}", e);
        }
        return "advert/homeFoucs_show";
    }

    /**
     * 更改状态
     *
     * @param model
     * @param id
     */
    @RequestMapping(value = "/updateStatus")
    public void updateStatus(HttpServletResponse response, int status, int id) {
        Map<String, String> result = new HashMap<String, String>();
        boolean b = true;
        try {
            if (id > 0) {
                Advert advertInfo = advertService.selectAdvertById(id);
                advertInfo.setStates(status);
                if (status == 3) {//如果传入为3为暂停操作，否则为启用操作
                    advertInfo.setStates(3);
                } else {
                    if (vidateAdvert(advertInfo) > 0) {
                        b = false;
                        result.put("result", "-1");
                        result.put("msg", "该时间段已投放广告，请修改!");
                    }
                }
                if (b) {
                    advertService.updateAdvertById(advertInfo);
                    result.put("result", "0");
                    result.put("msg", "更新状态成功");
                }
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


    /**
     * 保存广告
     *
     * @param request
     * @param model
     * @param advert
     * @return
     */
    @RequestMapping(value = "/saveHomeFocs")
    public void saveHomeFocs(HttpServletResponse response, @RequestBody Advert advert) {
        logger.info("更新广告参数：" + JSONObject.toJSONString(advert));
        String result = "";
        try {
            if (null != advert) {
                //校验广告是否投放重复
                if (vidateAdvert(advert) > 0) {
                    result = "该时间段已投放广告，请修改!";
                } else {
                    //如果id不空，则保存，否则添加
                    advert.setUpdateUser(getUserSessionInfo());
                    advert.setUpdateTime(new Date());
                    if (null != advert.getId() && advert.getId() > 0) {
                        //修改广告
                        result = updateAdvert(advert);
                    } else {
                        //添加广告
                        advert.setCreateUser(getUserSessionInfo());
                        advert.setCreateTime(new Date());
                        result = addAdvert(advert);
                    }
                }
            } else {
                result = "failure";
            }
        } catch (Exception e) {
            if (null != advert.getId() && advert.getId() > 0) {
                logger.error("更新广告出错{}", e);
            } else {
                logger.error("添加广告出错{}", e);
            }
            result = "failure";
        } finally {
            writeResult(response, result);
        }
    }

    /**
     * 添加广告
     *
     * @param advert
     * @return
     */
    private String addAdvert(Advert advert) throws Exception {
        logger.info("添加广告位内容", advert);
        advert.setDataType(0);
        if (advertService.insert(advert) == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    /**
     * 修改广告
     *
     * @param advert
     * @return
     */
    private String updateAdvert(Advert advert) throws Exception {
        logger.info("修改广告内容", advert);
        if (advertService.updateAdvertById(advert) == 1) {
            return "success";
        } else {
            return "failure";
        }
    }

    /**
     * 校验广告是否已投放
     *
     * @param advert
     * @return
     */
    private int vidateAdvert(Advert advert) throws Exception {
        Date nowDate = new Date();
        if (null == advert.getStates()) {//预防状态为空值
            advert.setStates(0);
        }
        if (null != advert.getStates() && advert.getStates() != 3) {
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
    public void getPlateformList(HttpServletResponse response, int selected) {
        StringBuffer sbf = new StringBuffer();
        try {
            OrderSource[] orders = OrderSource.values();
            sbf.append("<option value=\"\">全部</option>");
            for (OrderSource orderSource : orders) {
                sbf.append("<option ");
                if (orderSource.getValue().intValue() == selected) {
                    sbf.append("selected=\"selected\" ");
                }
                sbf.append("value=\"" + orderSource.getValue() + "\">" + orderSource.getName() + "</option>");
            }
        } catch (Exception e) {
            logger.error("getPlateformList出错{}", e);
        } finally {
            writeResult(response, sbf.toString());
        }
    }

    /**
     * 获取平台(添加修改页面)
     *
     * @param response
     * @param selected
     */
    @RequestMapping(value = "/getRadioPlateform")
    @ResponseBody
    public void getRadioPlateform(HttpServletResponse response, int selected) {
        StringBuffer sbf = new StringBuffer();
        try {
            OrderSource[] orders = OrderSource.values();
            for (int i = 0; i < orders.length; i++) {
                OrderSource orderSource = orders[i];
                sbf.append("<span class=\"LeftNavbot\"><input type=\"radio\" id=\"plateform\" name=\"plateform\" data-key=\"plateform\"");
                if (selected == 0 && i == 0) {
                    sbf.append("checked=\"checked\" ");
                } else if (orderSource.getValue().intValue() == selected) {
                    sbf.append("checked=\"checked\" ");
                }
                sbf.append("value=\"" + orderSource.getValue() + "\">" + orderSource.getName() + "</span>");
            }
        } catch (Exception e) {
            logger.error("getRadioPlateform出错{}", e);
        } finally {
            writeResult(response, sbf.toString());
        }
    }

    /**
     * 广告,展示页面(homeFocusList页面)
     *
     * @param response
     * @param selected
     */
    @RequestMapping(value = "/getModuleList")
    @ResponseBody
    public void getModuleList(HttpServletResponse response, String selected) {
        StringBuffer sbf = new StringBuffer();
        try {
            List<cn.com.gome.dujia.disconf.Advert> adverts = AdvertDisconf.getAdverts();
            sbf.append("<option value=\"\">全部</option>");
            if (null != adverts) {
                for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
                    sbf.append("<option ");
                    if (advert.getValue().equals(selected)) {
                        sbf.append("selected=\"selected\" ");
                    }
                    sbf.append("value=\"" + advert.getValue() + "\">" + advert.getName() + "</option>");
                }
            }
        } catch (Exception e) {
            logger.error("getModuleList出错{}", e);
        } finally {
            writeResult(response, sbf.toString());
        }
    }

    /**
     * 添加广告,页面位置(添加修改页面)
     *
     * @param response
     * @param selected
     */
    @RequestMapping(value = "/getRadioModule")
    @ResponseBody
    public void getRadioModule(HttpServletResponse response, String selected) {
        StringBuffer sbf = new StringBuffer();
        try {
            List<cn.com.gome.dujia.disconf.Advert> adverts = AdvertDisconf.getAdverts();
            if (null != adverts) {
                for (int i = 0; i < adverts.size(); i++) {
                    cn.com.gome.dujia.disconf.Advert advert = adverts.get(i);
                    sbf.append("<span class=\"LeftNavbot\"><input type=\"radio\" id=\"module" + (i + 1) + "\" name=\"module\" data-key=\"module\"  onclick=\"chaneModule();\" ");
                    if ("".equals(selected) && i == 0) {
                        sbf.append("checked=\"checked\" ");
                    } else if (advert.getValue().equals(selected)) {
                        sbf.append("checked=\"checked\" ");
                    }
                    sbf.append("value=\"" + advert.getValue() + "\">" + advert.getName() + "</span>");
                }
            }
        } catch (Exception e) {
            logger.error("getRadioModule出错{}", e);
        } finally {
            writeResult(response, sbf.toString());
        }
    }


    /**
     * 广告,展示页面下一级的展示位置(homeFocusList页面)
     *
     * @param response
     * @param platform 平台
     * @param module   选中的展示页面
     * @param selected 选中的展示位置
     */
    @RequestMapping(value = "/getPositionList")
    @ResponseBody
    public void getPositionList(HttpServletResponse response, String platform, String module, String selected) {
        StringBuffer sbf = new StringBuffer();
        try {
            List<cn.com.gome.dujia.disconf.Advert> adverts = AdvertDisconf.getAdverts();
            sbf.append("<option value=\"\">全部</option>");
            if (null != adverts) {
                for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
                    if (advert.getValue().equals(module)) {
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
            }
        } catch (Exception e) {
            logger.error("getPositionList出错{}", e);
        } finally {
            writeResult(response, sbf.toString());
        }
    }

    /**
     * 广告,展示页面下一级的展示位置(添加修改页面)
     *
     * @param response
     * @param platform 平台
     * @param module   选中的展示页面
     * @param selected 选中的展示位置
     */
    @RequestMapping(value = "/getRadioPosition")
    @ResponseBody
    public void getRadioPosition(HttpServletResponse response, String platform, String module, String selected) {
        StringBuffer sbf = new StringBuffer();
        try {
            List<cn.com.gome.dujia.disconf.Advert> adverts = AdvertDisconf.getAdverts();
            if (null != adverts) {
                for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
                    if (advert.getValue().equals(module)) {
                        List<cn.com.gome.dujia.disconf.Advert> p = advert.getPositions();
                        for (int i = 0; i < p.size(); i++) {
                            cn.com.gome.dujia.disconf.Advert pAdvert = p.get(i);
                            sbf.append("<span class=\"LeftNavbot\"><input type=\"radio\" id=\"position" + (i + 1) + "\" name=\"position\" data-key=\"position\" ");
                            if ("".equals(selected) && i == 0) {
                                sbf.append("checked=\"checked\" ");
                            } else if (pAdvert.getValue().equals(selected)) {
                                sbf.append("checked=\"checked\" ");
                            }
                            sbf.append("value=\"" + pAdvert.getValue() + "\">" + pAdvert.getName() + "</span>");
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("getRadioPosition出错{}", e);
        } finally {
            writeResult(response, sbf.toString());
        }
    }

    @RequestMapping(value = "fileUpload")
    public void fileUpload(HttpServletResponse response, HttpServletRequest request, @RequestParam(value = "file", required = false) CommonsMultipartFile file) {
        /** 判断文件是否为空,空直接返回上传错误 **/
        Map<String, String> result = new HashMap<String, String>();
        try {
            if (file != null && !file.isEmpty()) {
                /** 获取扩展名 */
                String suffix = this.getSuffix(file.getOriginalFilename());
                if (suffix == null) {
                    result.put("result", "-1");
                    result.put("msg", "不允许的文件类型");
//                    this.writeAjaxStr(response, JSONObject.toJSON(result).toString());
                    return;
                }

                /** 检查扩展类型 */
                String[] fileTypes = new String[]{".gif", ".jpg", ".png", ".jpeg"};// 定义允许上传的文件扩展名
                if (!checkType(file.getOriginalFilename(), fileTypes)) {
                    result.put("result", "-1");
                    result.put("msg", "不允许的文件类型");
//                    this.writeAjaxStr(response, JSONObject.toJSON(result).toString());
                    return;
                }

                /** 检查图片尺寸 **/
                String heightStr = request.getParameter("height");
                if (Integer.parseInt(heightStr) > 0) {
                    boolean checkImgWidthRes = this.checkImgWidth(file, request);
                    if (!checkImgWidthRes) {
                        result.put("result", "-1");
                        result.put("msg", "图片尺寸不符合要求");
//                        this.writeAjaxStr(response, JSONObject.toJSON(result).toString());
                        return;
                    }
                }

                // =====上传到gfs===begin
                String localUrl = imageService.uploadRemoteFile2GFS(file.getBytes());
                if (localUrl == null) {
                    result.put("result", "-1");
                    result.put("msg", "文件写入失败");
                    result.put("url", "");
                } else {
                    // =====上传到gfs====end
                    result.put("result", "1");
                    result.put("msg", "上传成功");
                    result.put("url", localUrl);
                }
            } else {
                result.put("result", "-1");
                result.put("msg", "上传失败");
            }
        } catch (Exception e) {
            logger.error("fileUpload出错{}", e);
        } finally {
            this.writeAjaxStr(response, JSONObject.toJSON(result).toString());
        }
    }

}

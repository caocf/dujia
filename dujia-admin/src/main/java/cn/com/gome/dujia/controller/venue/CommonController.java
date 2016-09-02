package cn.com.gome.dujia.controller.venue;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.com.gome.dujia.controller.baseData.DateEditor;
import cn.com.gome.dujia.disconf.AdvertDisconf;
import cn.com.gome.dujia.disconf.RecommendDisconf;
import cn.com.gome.dujia.enums.ChannelType;
import cn.com.gome.dujia.enums.OrderSource;

import com.gome.common.web.context.LoginContext;

/**
 * 所有controller的基类，提供操作Session对象的方法。
 *
 * @version $Revision$
 */
@ControllerAdvice
@RequestMapping(value = "/common")
public abstract class CommonController {

    public static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    protected HttpServletRequest request;

    public static final String JSON = "json";
    public static final String HTML = "html";

    private static String APP_DOMAIN;
    private static String CART_DOMAIN;

    static {
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // 对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 等价于request.getSession().getAttribute(key).
     */
    @SuppressWarnings("unchecked")
    protected <T> T getSessionAttribute(final String key) {
        return (T) request.getSession().getAttribute(key);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getParameter(final String key) {
        return (T) request.getParameter(key);
    }

    public String getAppDomain() {
        return APP_DOMAIN;
    }

    public String getCartDomain() {
        return CART_DOMAIN;
    }

    protected String dataType;

    /**
     * 得到UserSessionObj
     *
     * @return 获得的UserSessionObj
     */
    public String getUserSessionInfo() {
        // 从erm权限系统中获取登录用户账号
        LoginContext ermLogincontext = LoginContext.getLoginContext();
        return ermLogincontext.getPin();
    }

    /**
     * 获取登录用户信息
     *
     * @return
     * @author WenJie Mai
     */
    public LoginContext getLoginUserInfo() {
        return LoginContext.getLoginContext();
    }

    /**
     * 保存UserSessionObj到用户的Session中，并同步OnlineUserMap。
     *
     * @param sessionObj 要保存的UserSessionObj
     */
    public void setUserSessionInfo(String username) {
        request.getSession().setAttribute("username", username);
    }

    /**
     * 清除用户的Session中的UserSessionObj。
     */
    public void clearUserSessionInfo() {
        request.getSession().setAttribute("username", null);
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    /**
     * 后台清除用户的Session中的UserSessionObj。
     */
    public void clearAdminSessionInfo() {
        request.getSession().setAttribute("admin", null);
        request.getSession().invalidate();
    }

    /**
     * 加入值到cookies里
     *
     * @param key   键
     * @param value 值
     */
    public void addCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        // 这个要设置，不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
        cookie.setMaxAge(365 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    public void addCookie(HttpServletResponse response, String key, String value, int time) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        // 这个要设置，不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
        cookie.setMaxAge(time);
        response.addCookie(cookie);
    }

    public void addCookie(HttpServletResponse response, String key, String value, String domain) {
        addCookie(response, key, value, domain, (365 * 24 * 60 * 60));
    }

    public void addCookie(HttpServletResponse response, String key, String value, String domain, int time) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        // 这个要设置，不设置的话，则cookies不写入硬盘,而是写在内存,只在当前页面有用,以秒为单位
        cookie.setMaxAge(time);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /**
     * 根据键获取对应的cookie对象
     *
     * @param key 对应的key
     * @return key对应的cookie值
     */
    public Cookie getCookie(String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length < 1)
            return null;
        for (Cookie temp : cookies) {
            if (temp.getName().equals(key)) {
                return temp;
            }
        }
        return null;
    }

    /**
     * 获得cookie对应的值
     *
     * @param key 对应的键
     * @return 返回值
     */
    public String getCookieValue(String key) {
        Cookie cookie = this.getCookie(key);
        if (cookie != null) {
            try {
                return URLDecoder.decode(cookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("getCookieValue出错", e);
            }
        }
        return null;
    }

    /**
     * 获取管理员后台操作员姓名
     *
     * @return
     */
    protected String getAdminSessionOperator() {
        try {
            // 从erm权限系统中获取登录用户账号
            LoginContext ermLogincontext = LoginContext.getLoginContext();
            return ermLogincontext.getPin();
        } catch (Exception ex) {
            return "";
        }
    }

    protected void setLastQueryString() {
        String requesturl = this.request.getQueryString();
        request.getSession().setAttribute("lasturl", requesturl);
    }

    protected String getLastQueryString() {
        Object lastRequesturl = request.getSession().getAttribute("lasturl");
        if (lastRequesturl != null)
            return (String) lastRequesturl;
        return "";
    }

    protected void removeLastQueryString() {
        request.getSession().removeAttribute("lasturl");
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    protected void writeAjaxStr(HttpServletResponse response, String str) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("writeAjaxStr出错", e);
        }
    }


    /**
     * 获取1个3位数
     *
     * @return
     */
    public int getCounter() {
        return (int) Math.round(Math.random() * 899 + 100);
    }

    /**
     * 将返回值写入页面
     *
     * @param str
     */
    public void writeResult(HttpServletResponse response, String str) {
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(str);
        } catch (IOException e) {
            logger.error("writeResult出错", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 导出名称格式化
     *
     * @param request
     * @param fileName
     * @return
     */
    public String encodeFilename(HttpServletRequest request, String fileName) {
        String agent = request.getHeader("USER-AGENT");
        try {
            if (null != agent && -1 != agent.indexOf("MSIE")) {
                // IE
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
                // Firefox//此分支为了解决IE下火狐下载文件名为乱码问题
                fileName = new String(fileName.getBytes("GB2312"), "ISO-8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            try {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            } catch (UnsupportedEncodingException e1) {
            }
            logger.error("导出名称格式化失败", e);
        }
        return fileName;
    }

    /**
     * 获取推荐的所有模块及位置map
     *
     * @return
     */
    protected HashMap<String, String> loadRecommendMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        List<cn.com.gome.dujia.disconf.Advert> adverts = RecommendDisconf.getRecommends();
        if (null != adverts) {
            for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
                map.put(advert.getValue(), advert.getName());
                List<cn.com.gome.dujia.disconf.Advert> p = advert.getPositions();
                for (int i = 0; i < p.size(); i++) {
                    cn.com.gome.dujia.disconf.Advert pAdvert = p.get(i);
                    map.put(pAdvert.getValue(), pAdvert.getName());
                }
            }
        }
        return map;
    }

    /**
     * 获取广告的平台
     *
     * @return
     */
    protected HashMap<Integer, String> loadOrderSourceMap() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (OrderSource orderSource : OrderSource.values()) {
            map.put(orderSource.getValue(), orderSource.getName());
        }
        return map;
    }

    /**
     * 获取广告的所有模块及位置map
     *
     * @return
     */
    protected HashMap<String, String> loadAdvertMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        List<cn.com.gome.dujia.disconf.Advert> adverts = AdvertDisconf.getAdverts();
        if (null != adverts) {
            for (cn.com.gome.dujia.disconf.Advert advert : adverts) {
                map.put(advert.getValue(), advert.getName());
                List<cn.com.gome.dujia.disconf.Advert> p = advert.getPositions();
                for (int i = 0; i < p.size(); i++) {
                    cn.com.gome.dujia.disconf.Advert pAdvert = p.get(i);
                    map.put(pAdvert.getValue(), pAdvert.getName());
                }
            }
        }
        return map;
    }

    /**
     * 获取展示的频道来源
     *
     * @return
     */
    protected HashMap<Integer, String> loadChannelTypeMap() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (ChannelType channelType : ChannelType.values()) {
            map.put(channelType.getValue(), channelType.getName());
        }
        return map;
    }

    /**
     * 截取文件的类型
     *
     * @param uploadnameFileName
     * @return
     */
    public String getSuffix(String uploadnameFileName) {

        if (uploadnameFileName.lastIndexOf(".") == -1) {
            return null;
        } else {
            return uploadnameFileName.substring(uploadnameFileName.lastIndexOf(".")).toLowerCase();
        }
    }

    /**
     * 判断文件是否在类型范围内
     *
     * @param uploadnameFileName 文件名
     * @param type               类型数组
     * @return
     */
    public boolean checkType(String uploadnameFileName, String[] type) {
        String suffix = getSuffix(uploadnameFileName);
        for (String s : type) {
            if (s.equalsIgnoreCase(suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断上传的图片宽和高是否符合要求
     *
     * @param file
     * @param width
     * @param height
     * @return
     */
    public boolean checkImgWidth(CommonsMultipartFile file, HttpServletRequest request) {
        String heightStr = request.getParameter("height");
        String widthStr = request.getParameter("width");
        Integer height = 0;
        Integer width = 0;
        if (!StringUtils.isEmpty(heightStr)) {
            height = Integer.parseInt(heightStr);
        }
        if (!StringUtils.isEmpty(widthStr)) {
            width = Integer.parseInt(widthStr);
        }
        try {
            BufferedImage srcImage = ImageIO.read(file.getInputStream());
            int w = srcImage.getWidth();
            int h = srcImage.getHeight();
            if (width.intValue() == w && height.intValue() == h) {
                return true;
            }
        } catch (IOException e) {
            logger.error("检查尺寸大小失败", e);

        }
        return false;
    }

    /**
     * 判断上传的图片大小是否符合要求
     *
     * @param img
     * @param size 图片大小，单位k
     * @return
     */
    @SuppressWarnings("resource")
    public boolean checkImgSize(File img, Integer size) {
        try {
            FileInputStream fis = new FileInputStream(img);
            long imgsize = fis.available();
            // 默认图片最大不能超过100K
            if (imgsize > size * 1024) {
                return false;
            }
        } catch (Exception e) {
            logger.error("checkImgSize出错", e);
        }
        return true;
    }

    /**
     * 添加广告,展示频道(添加修改页面)
     *
     * @param response
     * @param selected
     */
    @RequestMapping(value = "/getRadioType")
    @ResponseBody
    public void getRadioType(HttpServletResponse response, int selected) {
        StringBuffer sbf = new StringBuffer();
        try {
            ChannelType[] channelTypes = ChannelType.values();
            for (int i = 0; i < channelTypes.length; i++) {
                ChannelType channelType = channelTypes[i];
                sbf.append("<span class=\"LeftNavbot\"><input type=\"radio\" id=\"channelType" + (i + 1) + "\" name=\"channelType\" data-key=\"type\"  onclick=\"typeChange();\" ");
                if (channelType.getValue() == selected) {
                    sbf.append("checked=\"checked\" ");
                }
                sbf.append("value=\"" + channelType.getValue() + "\">" + channelType.getName() + "</span>");
            }
        } catch (Exception e) {
            logger.error("getRadioType出错{}", e);
        } finally {
            writeResult(response, sbf.toString());
        }
    }
}

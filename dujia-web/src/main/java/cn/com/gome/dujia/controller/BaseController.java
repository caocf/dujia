package cn.com.gome.dujia.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.exception.BizException;

import com.gome.memberCore.lang.model.UserResult;
import com.gome.sso.common.tools.SsoUserCookieTools;
import com.gome.sso.model.UserCookie;
/**
 * Controller基类，所有web的Controller类必须继承此类
 * 
 * @author xiongyan
 * @date 2015年10月29日 下午1:36:18
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
     * 获取认证的用户ID
     *
     * @return
     */
    protected String getUserId(HttpServletRequest request) {
        UserCookie uc = getUserCookie(request);
        if (uc != null) {
            return uc.getId();
        }
        return null;
    }

    /**
     * 获取认证的用户名
     *  
     * @return
     */
    protected String getUserName(HttpServletRequest request) {
        UserCookie uc = getUserCookie(request);
        if (uc != null) {
            return uc.getLogin();
        }
        return null;
    }

	/**
     * 检查是否登录
     *
     * @return
     */
    protected boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {
        UserCookie userCookie = getUserCookie(request);
        if (userCookie == null) {
            try {
            	//获取当前请求的url
                String orginURI = URLEncoder.encode(getRequestURI(request), "UTF-8");
                response.sendRedirect(GlobalDisconf.loginUrl + "?orginURI=" + orginURI);
                response.getOutputStream().flush(); // Send out whatever hasn't been sent out yet.
                response.getOutputStream().close();
            } catch (IOException e) {
                logger.error("检查登录跳转到登陆页错误", e);
            }
            return false;
        }
        return true;
    }
    
    /**
     * 获取认证的用户ID
     *
     * @return
     */
    protected UserCookie getUserCookie(HttpServletRequest request) {
        UserResult<UserCookie> userCookie = SsoUserCookieTools.checkIsLoginByCookie(request, null);
        if (null != userCookie && userCookie.isSuccess()) {
            UserCookie uc = userCookie.getBuessObj();
            return uc;
        } else {
            logger.debug("未获取到SSO用户LoginName");
            return null;
        }
    }
    
    /**
     * 获取当前请求的url
     *
     * @return
     */
    private String getRequestURI(HttpServletRequest request) {
        String url = request.getScheme() + "://"; //请求协议 http 或 https
        url += request.getHeader("host");  // 请求服务器
        url += request.getRequestURI();     // 工程名
        if (request.getQueryString() != null) //判断请求参数是否为空
            url += "?" + request.getQueryString();   // 参数
        return url;
    }
    
    /**
     * 获取异常信息
     * @param e
     * @return
     */
    public String getEM(Exception e) {
    	if(e != null) {
    		if(e.getClass().equals(BizException.class)) {
    			if("ticket_error_msg".equals(e.getMessage())) {
    				return GlobalDisconf.getTicketErrorMsg();
    			}else{
    				return e.getMessage();
    			}
			}else{
				return GlobalDisconf.getTicketErrorMsg();
			}
    	}
    	return null;
    }
}

package cn.com.gome.dujia.tag;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.gome.dujia.constant.Constants;
import cn.com.gome.dujia.service.RedisCacheService;

import com.gome.plan.tools.utils.Md5Util;

/**
 * 自定义标签
 * 
 * @author xiongyan
 * @date 2015年11月12日 下午12:14:37
 */
public class TokenTag extends SimpleTagSupport {

	private static final Logger logger = LoggerFactory.getLogger(TokenTag.class);

	/**
	 * 执行标签
	 */
	public void doTag() throws JspException, IOException {
		// 获取PageContext对象
		PageContext context = (PageContext) this.getJspContext();
		// 创建tokenValue
		String value = new BigInteger(165, new Random()).toString(36).toUpperCase() + System.nanoTime();
		// 把tokenValue放入redis中
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context.getServletContext());
		RedisCacheService redisCacheService = (RedisCacheService) wac.getBean("redisCacheService");
		// 时间戳（以毫微秒为单位）+token值进行md5机密生成key
		String key = Md5Util.md5(Constants.GOME_TOKEN_NAME + value);
		// 在缓存中保存30分钟
		redisCacheService.push(key, value, 1800);
		logger.info("防止重复提交：redis缓存中key={}，value={}，time={}秒", key, value, 1800);
		// 把两个隐藏域写入页面
		context.getOut().write("<input type=\"hidden\" id=\"" + Constants.GOME_TOKEN_NAME + "\" name=\"" + Constants.GOME_TOKEN_NAME + "\" value=\"" + key + "\"/>");
	}

}

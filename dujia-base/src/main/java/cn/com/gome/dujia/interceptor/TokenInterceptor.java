package cn.com.gome.dujia.interceptor;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.gome.dujia.constant.Constants;
import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.dto.ResponseDto;
import cn.com.gome.dujia.service.RedisCacheService;

import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.Md5Util;

/**
 * 防止重复提交拦截器
 * 
 * @author xiongyan
 * @date 2015年11月12日 上午11:58:16
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);
	
	private JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON);
	
	@Autowired
	private RedisCacheService redisCacheService;
	
	//需要拦截验证token的url
	private static Set<String> urls = new HashSet<String>();
	static {
		urls.add("/order/submits.html");
	}


	/**
	 * 进入方法之前执行
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!CollectionUtils.isEmpty(urls)) {
			String uri = request.getRequestURI();
			// 拦截验证token的url中是否包含此请求
			if (urls.contains(uri)) {
				// 订单开关是否开启
				if (!GlobalDisconf.orderSwitch) {
					writeJSON(response, "服务不可用");
					return false;
				}

				// 验证token是否有效
				if (!validateToken(request)) {
					// token无效,弹出提示
					writeJSON(response, "请勿重复提交");
					return false;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * 验证token
	 * 
	 * @param request
	 * @return
	 */
	private boolean validateToken(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		if (CollectionUtils.isEmpty(map) || !map.containsKey(Constants.GOME_TOKEN_NAME)) {
			return true;
		}

		// 获取tokenValue
		String[] tokenValues = map.get(Constants.GOME_TOKEN_NAME);
		if (ArrayUtils.isEmpty(tokenValues)) {
			logger.error("从请求中获取{}的值为空", Constants.GOME_TOKEN_NAME);
			return false;
		}
		String tokenValue = tokenValues[0];

		// 从ridis中获取tokenValue
		String redisToken = redisCacheService.pop(tokenValue);
		if (StringUtils.isEmpty(redisToken)) {
			logger.error("从redis中获取{}的值为空", tokenValue);
			return false;
		}
		logger.info("tokenName={}，tokenValue={}，redisToken={}", Constants.GOME_TOKEN_NAME, tokenValue, redisToken);

		// 验证是否一致
		if (tokenValue.equals(Md5Util.md5(Constants.GOME_TOKEN_NAME + redisToken))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 向响应中写入数据
	 * @param response
	 * @param data
	 */
	private void writeJSON(HttpServletResponse response, String data) {
		try {
			// 设置响应头
			response.setContentType("application/json"); // 指定内容类型为 JSON 格式
			response.setCharacterEncoding("UTF-8"); // 防止中文乱码
			// 向响应中写入数据
			PrintWriter writer = response.getWriter();
			writer.write(jsonUtils.serialize(ResponseDto.bulidFail(data)));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error("在响应中写数据出错！", e);
			throw new RuntimeException(e);
		}
	}

}

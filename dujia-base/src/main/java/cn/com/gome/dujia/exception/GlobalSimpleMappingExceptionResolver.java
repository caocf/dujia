package cn.com.gome.dujia.exception;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import cn.com.gome.dujia.disconf.GlobalDisconf;
import cn.com.gome.dujia.dto.ResponseDto;

import com.gome.plan.tools.utils.JsonUtils;

/**
 * 全局异常处理
 * 
 * @author xiongyan
 * @date 2015年12月18日 下午6:12:56
 */
public class GlobalSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalSimpleMappingExceptionResolver.class);
	
	private JsonUtils jsonUtils = new JsonUtils(JsonUtils.JSON);

	/**
	 * 异常处理
	 */
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
		//错误日记记录
		logger.error("全局异常信息：", e);
		
		if(isAjaxRequest(request)) {
			//ajax请求
			writeJSON(response, GlobalDisconf.errorMessage);
			return null;
		} else {
			//非ajax请求
			return getModelAndView(GlobalDisconf.errorMessage, e, request);
		}
	}
	
	/**
     * 判断请求是否ajax请求
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
    	if (null != request.getHeader("x-requested-with") && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { 
    		return true;
    	}else{ 
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
		}
	}
}

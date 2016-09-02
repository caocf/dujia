package cn.com.gome.dujia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 跨站脚本过滤器
 * 
 * @author wangweiran
 */
public class XSSFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 强制类型转换 HttpServletRequest
		HttpServletRequest httpReq = (HttpServletRequest) request;
		// 构造HttpRequestWrapper对象处理XSS
		HttpRequestWrapper httpReqWarp = new HttpRequestWrapper(httpReq);
		// 执行下一个过滤器
		chain.doFilter(httpReqWarp, response);
	}

	public void destroy() {

	}
}

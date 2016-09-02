package cn.com.gome.dujia.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.gome.plan.tools.utils.XXSUtils;

/**
 * HttpRequest 增强类
 * 
 * @author wangweiran
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {

	public HttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		// 遍历每一个参数，检查是否含有
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = XXSUtils.cleanXSS(values[i]);
		}
		return encodedValues;
	}

	public String getServletPath() {
		return XXSUtils.cleanXSS(super.getServletPath());
	}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return XXSUtils.cleanXSS(value);
	}

	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return XXSUtils.cleanXSS(value);
	}
}

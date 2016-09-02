package cn.com.gome.dujia.extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuanyue
 */
public class ContentTypeFilter implements Filter {


    Logger logger = LoggerFactory.getLogger(ContentTypeFilter.class);
    /**
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI().toUpperCase();
        logger.debug(url);
        if (url.contains(".JSON")) {
            IE8FilterResponseWrapper wrapper = new IE8FilterResponseWrapper(response);
            wrapper.forceContentType("text/html; charset=utf-8");
            chain.doFilter(servletRequest, wrapper);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     *
     */
    @Override
    public void destroy() {

    }
}

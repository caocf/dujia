package cn.com.gome.dujia.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.WeakHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.gome.dujia.constant.Constants;
import cn.com.gome.dujia.disconf.GlobalDisconf;

import com.gome.plan.tools.utils.StringUtils;

/**
 * 合并js，css请求，压缩内容
 *
 * @author xiongyan
 * @date 2016年2月29日 上午11:01:00
 */
public class MergerContentFilter implements Filter {
	
	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(MergerContentFilter.class);

	/**
	 * 内容缓存
	 */
	private static Map<String, String> cache = new WeakHashMap<String, String>();

	/**
	 * 初始化
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

    /**
     * doFilter
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        long if_modified_since = httpRequest.getDateHeader("If-Modified-Since");
        long cacheDuration = 24 * 60 * 60 * 1000; //本地缓存一天
        long now = System.currentTimeMillis(); //当前时间
        
        // 判断是否在有效期内
        if (if_modified_since > 0 && if_modified_since + cacheDuration > now) {
            httpResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        } else {
        	String path = httpRequest.getServletContext().getRealPath("/");
            String uri = httpRequest.getRequestURI();
            String version = request.getParameter("_v");
            logger.info("合并压缩文件：{}，版本号{}", uri, version);
            
            boolean isJs = uri.endsWith("js"); //js文件
        	boolean isCss = uri.endsWith("css"); //css文件
        	
            //设置浏览器缓存
            httpResponse.setDateHeader("Last-Modified", System.currentTimeMillis());
            httpResponse.setDateHeader("Expires", System.currentTimeMillis() + cacheDuration);
            httpResponse.setHeader("Cache-Control", "public");
            httpResponse.setHeader("Pragma", "Pragma");
            if (isJs) {
                httpResponse.setContentType("application/javascript");
                httpResponse.setCharacterEncoding("utf-8");
            }
            if (isCss) {
                httpResponse.setContentType("text/css;charset=UTF-8");
                httpResponse.setCharacterEncoding("utf-8");
            }
            
            // 判断内存中是否已经存在
            if (cache.containsKey(uri + version)) {
                httpResponse.getWriter().write(cache.get(uri + version));
            } else {
                StringBuilder content = new StringBuilder();
                String names[] = uri.split(",");
                if (null != names) {
                    for (String name : names) {
                        File file = new File(path + File.separator + name);
                        String fileContent = null;
                        if (Constants.PRD.equals(GlobalDisconf.env)) {
	                        if (isJs) {
	                        	fileContent = new CompressionFile.JsCompress(file).compress();
	                        }else if (isCss) {
                        		fileContent = new CompressionFile.CssCompress(file).compress();
	                        }
                    	} else {
                    		fileContent = CompressionFile.getFileContent(new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")));
                    	}
                        if (StringUtils.isNotEmpty(fileContent)) {
                        	if (fileContent.indexOf("../") > 0) {
                        		fileContent = fileContent.replaceAll("\\.\\./", GlobalDisconf.domainDujia + "/");
                            }
                        	if (content.length() > 0) {
                        		content.append("\r\n");
                        	}
                        	content.append(fileContent);
                        }
                    }
                }
                String resourceContent = content.toString();
                // 不为空则放置缓存
                if (StringUtils.isNotEmpty(resourceContent)) {
                    //把内容放入缓存中
                	if (Constants.UAT.equals(GlobalDisconf.env) || Constants.PRD.equals(GlobalDisconf.env)) {
                		cache.put(uri + version, resourceContent);
                	}
                }
                //直接输出
                httpResponse.getWriter().write(resourceContent);
            }
            // 把压缩合并后的内容写入浏览器
            httpResponse.flushBuffer();
        }
    }

    /**
     * 销毁
     */
    public void destroy() {

    }
    
}

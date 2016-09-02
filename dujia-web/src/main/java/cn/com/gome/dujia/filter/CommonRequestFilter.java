package cn.com.gome.dujia.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;

import cn.com.gome.dujia.disconf.GlobalDisconf;
/**
 * Created by yuanyue on 2015/11/12.
 */
public class CommonRequestFilter extends CharacterEncodingFilter {
    private String encoding;
    private boolean forceEncoding = false;

    public CommonRequestFilter() {
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setForceEncoding(boolean forceEncoding) {
        this.forceEncoding = forceEncoding;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
            request.setCharacterEncoding(this.encoding);
            if (this.forceEncoding) {
                response.setCharacterEncoding(this.encoding);
            }
        }

    	//静态资源版本
        request.setAttribute("_v", GlobalDisconf.resourceVersion);
        //环境
        request.setAttribute("_env", GlobalDisconf.env);
        //资源环境
        request.setAttribute("_resource_env", GlobalDisconf.resourceEnv); 
        //域名
        request.setAttribute("_domain", GlobalDisconf.domainDujia); 
        //旅行首页域名
        request.setAttribute("_domain_lvxing", GlobalDisconf.domainLvxing); 
        //机票域名
        request.setAttribute("_domain_jipiao", GlobalDisconf.domainJipiao); 
        //火车票域名
        request.setAttribute("_domain_train", GlobalDisconf.domainTrain); 
        //酒店域名
        request.setAttribute("_domain_hotel", GlobalDisconf.domainHotel); 

        filterChain.doFilter(request, response);
    }
}

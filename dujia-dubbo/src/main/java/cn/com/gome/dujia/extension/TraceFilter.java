package cn.com.gome.dujia.extension;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import com.gome.plan.tools.utils.JsonUtils;
import com.gome.plan.tools.utils.StringUtils;

/**
 * @author xiongyan
 */
@Priority(Priorities.USER)
public class TraceFilter implements ContainerRequestFilter, ContainerResponseFilter {

    public void filter(ContainerRequestContext requestContext) throws IOException {

    }


    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        String callback = containerRequestContext.getUriInfo().getQueryParameters().getFirst("callback");
        if (StringUtils.isNotEmpty(callback)) {//处理jsonp
            Object result = containerResponseContext.getEntity();
            String json;
            if (result == null) {
                json = "";
            } else {
                json = new JsonUtils().serialize(result);
            }
            containerResponseContext.setEntity(callback + "(" + json + ")");
        }
    }

}
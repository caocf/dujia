package cn.com.gome.dujia.extension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author xiongyan
 */
public class CustomExceptionMapper implements ExceptionMapper<Exception> {
    /**
     * logger
     */
    static Logger logger = LoggerFactory.getLogger(CustomExceptionMapper.class);

    /**
     * 自定义错误
     *
     * @param e Exception
     * @return Response
     */
    public Response toResponse(Exception e) {
        logger.error("CustomExceptionMapper", e);
        return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).type(MediaType.TEXT_PLAIN + "; " + MediaType.CHARSET_PARAMETER + "=UTF-8").build();
    }
}

package cn.com.gome.dujia.extension;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 处理ie8以下浏览器application/json 不兼容问题
 */
public class IE8FilterResponseWrapper extends HttpServletResponseWrapper {

    /**
     * constructor
     *
     * @param response
     */
    public IE8FilterResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    /**
     * 设置ContentType
     *
     * @param type
     */
    public void forceContentType(String type) {
        super.setContentType(type);
    }

    /**
     * 覆盖父类,不做任何处理
     *
     * @param type
     */
    public void setContentType(String type) {

    }

    /**
     * 覆盖父类方法,不对Content-Type进行设置
     *
     * @param name
     * @param value
     */
    public void setHeader(String name, String value) {

        if (!name.equals("Content-Type")) {
            super.setHeader(name, value);
        }
    }

    /**
     * 覆盖父类方法,不对Content-Type进行设置
     *
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        if (!name.equals("Content-Type")) {
            super.addHeader(name, value);
        }

    }

    /**
     * 获取content-type
     *
     * @return
     */
    public String getContentType() {
        return super.getContentType();
    }
}
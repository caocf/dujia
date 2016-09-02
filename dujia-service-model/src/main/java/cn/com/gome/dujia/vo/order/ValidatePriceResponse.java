package cn.com.gome.dujia.vo.order;

import java.io.Serializable;

/**
 * Created by zhaoxiang-ds on 2016/5/23.
 */
public class ValidatePriceResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    // 响应码
    private String code;
    // 响应原因
    private String message;
    //data
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

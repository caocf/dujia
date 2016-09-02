package cn.com.gome.dujia.vo.json.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sunming on 2016/4/26.
 */
public class CancelReasonResult  implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value="IsSuccessed")
    private String isSuccessed;
    @JsonProperty(value="Msg")
    private String msg;

    public String getIsSuccessed() {
        return isSuccessed;
    }

    public void setIsSuccessed(String isSuccessed) {
        this.isSuccessed = isSuccessed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

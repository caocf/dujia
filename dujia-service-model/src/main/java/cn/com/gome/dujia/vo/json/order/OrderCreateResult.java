package cn.com.gome.dujia.vo.json.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sunming on 2016/4/19.
 */
public class OrderCreateResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "Msg")
    private String msg;
    @JsonProperty(value = "OrderId")
    private String orderId;
    @JsonProperty(value = "OrderMoney")
    private String orderMoney;
    @JsonProperty(value = "IsAffirm")
    private String isAffirm;
    @JsonProperty(value = "ResultCode")
    private String resultCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getIsAffirm() {
        return isAffirm;
    }

    public void setIsAffirm(String isAffirm) {
        this.isAffirm = isAffirm;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}

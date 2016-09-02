package cn.com.gome.dujia.exception;

/**
 * Description  : 错误码
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/4/18 17:18;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public enum ErrorCode {

    /**
     * 成功
     */
    E200("200"),

    /**
     * E300XX开头为rb相关错误信息
     */
    E30001("无效的请求地址"),

    E30002("建立rb服务失败"),

    E30003("rb推送失败"),

    
    /**
     * E400XX开头为sap收款相关错误信息
     */
    E40001("SAP收款接口地址不能为空"),
    
    E40002("SAP收款接口用户名不能为空"),
    
    E40003("SAP收款接口密码不能为空"),
    
    E40004("SAP收款请求异常"),
    
    E40005("SAP收款订单类型异常"),
    
    
    /**
     * E410XX开头为sap收入相关错误信息
     */
    E41001("SAP收入接口地址不能为空"),
    
    E41002("SAP收入接口用户名不能为空"),
    
    E41003("SAP收入接口密码不能为空"),
    
    E41004("SAP收入请求异常"),
    
    E41005("SAP收入订单类型异常"),
    
    
    /**
     * E500XX开头为同程相关错误信息
     */
    E50001("无效的同程订单号"),
    
    E50002("无效的同程退款单号"),
    
    E50003("无效的同程退款金额"),
    
    E50004("退款单号已存在，禁止重复退款"),
    
    E50005("无效的订单"),
    
    E50006("退款金额不足，禁止退款"),

    E50007("操作类型不能为空"),

    E50008("订单有效性不能为空"),
    
    
    /**
     * E510XX开头为支付相关错误信息
     */
    E51001("支付状态异常"),
    
    E51002("订单状态异常"),
    
    
    /**
	 * E6000*开头，为退款业务异常代码
	 */
	E60001("退款单不存在"),
	
	E60002("退款单状态异常"),

    /**
     * 911** sap异常
     */
    E91100("推送sap失败"),

    /**
     * E900XX开头为系统请求
     */
    E90000("操作成功"),
    
	E90001("系统异常"),
	
	E90002("非法请求"),
	
	E90003("验签失败");


    private String desc;

    ErrorCode(String desc) {
        this.setDesc(desc);
    }

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public String toString(String msg) {
		return String.format(desc, msg);
	}

    @Override
    public String toString() {
        return name() + ":" + desc;
    }

}

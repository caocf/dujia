package cn.com.gome.dujia.exception;

/**
 * Description  : 业务异常
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/4/18 17:18;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public class BizException extends InterfaceException {

    private static final long serialVersionUID = -2331040814100016076L;

    public static final String SEQ_KEY="||";

    public BizException() {
		super();
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(ErrorCode errorCode) {
		super(errorCode.toString());
	}

	public BizException(ErrorCode errorCode, String hotKey) {
		super(errorCode.toString() + SEQ_KEY + hotKey);
	}
	
	public BizException(String message, String hotKey) {
		super(message + SEQ_KEY + hotKey);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

}

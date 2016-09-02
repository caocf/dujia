package cn.com.gome.dujia.dto;

/**
 * Description : Copyright : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/4/18 17:18;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public class ResponseDto extends BaseDto {

    private static final long serialVersionUID = -4823751808465357344L;

    /**
     * 成功标识
     */
    private static final String SUCCESS = "Y";

    /**
     * 确认标示
     */
    private static final String CONFIRM = "C";

    /**
     * 失败标识
     */
    private static final String FAIL = "N";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回结果
     */
    private Object data;

    /**
     * 默认构造方法
     */
    public ResponseDto() {

    }

    /**
     * 成功
     *
     * @return
     */
    public static ResponseDto bulidSuccess() {
        ResponseDto dto = new ResponseDto();
        dto.setCode(SUCCESS);
        return dto;
    }

    /**
     * 成功
     *
     * @param message 操作信息
     * @return
     */
    public static ResponseDto bulidSuccess(String message) {
        ResponseDto dto = new ResponseDto();
        dto.setCode(SUCCESS);
        dto.setMessage(message);
        return dto;
    }

    /**
     * 成功
     *
     * @param data 返回结果信息
     * @return
     */
    public static ResponseDto bulidSuccess(Object data) {
        ResponseDto dto = new ResponseDto();
        dto.setCode(SUCCESS);
        dto.setData(data);
        return dto;
    }

    /**
     * 成功
     *
     * @param message 操作信息
     * @param data    返回结果信息
     * @return
     */
    public static ResponseDto bulidSuccess(String message, Object data) {
        ResponseDto dto = new ResponseDto();
        dto.setCode(SUCCESS);
        dto.setMessage(message);
        dto.setData(data);
        return dto;
    }

    /**
     * 失败
     *
     * @return
     */
    public static ResponseDto bulidFail() {
        ResponseDto dto = new ResponseDto();
        dto.setCode(FAIL);
        return dto;
    }

    /**
     * 失败
     *
     * @param message 操作信息
     * @return
     */
    public static ResponseDto bulidFail(String message) {
        ResponseDto dto = new ResponseDto();
        dto.setCode(FAIL);
        dto.setMessage(message);
        return dto;
    }

    /**
     * 失败
     *
     * @param data 返回结果信息
     * @return
     */
    public static ResponseDto bulidFail(Object data) {
        ResponseDto dto = new ResponseDto();
        dto.setCode(FAIL);
        dto.setData(data);
        return dto;
    }

    /**
     * 失败
     *
     * @param message 操作信息
     * @param data    返回结果信息
     * @return
     */
    public static ResponseDto bulidFail(String message, Object data) {
        ResponseDto dto = new ResponseDto();
        dto.setCode(FAIL);
        dto.setMessage(message);
        dto.setData(data);
        return dto;
    }

    /**
     * 确认
     *
     * @return
     */
    public static ResponseDto bulidConfirm() {
        ResponseDto dto = new ResponseDto();
        dto.setCode(CONFIRM);
        return dto;
    }

    /**
     * 确认
     *
     * @return
     */
    public static ResponseDto bulidConfirm(String msg) {
        ResponseDto dto = new ResponseDto();
        dto.setCode(CONFIRM);
        dto.setMessage(msg);
        return dto;
    }


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

package cn.com.gome.dujia.enums;

/**
 * Created by zhaoxiang-ds on 2016/5/23.
 */
public enum OrderErrorCode {

    /**
     * 成功
     */
    E200("200", "成功"),

    /**
     * 套餐不可定（库存不足、无效套餐）
     */
    E501("501", "套餐不可定（库存不足、无效套餐）"),

    /**
     * 套餐价格已变动
     */
    E502("502", "套餐价格已变动");

    private String code;
    private String desc;

    OrderErrorCode(String code, String desc) {
        this.setCode(code);
        this.setDesc(desc);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

package cn.com.gome.dujia.enums;

/**
 * Created by liuhexin on 2016/5/26.
 */
public enum RefundAuditStatus {
    SYSTEM_AUDIT("系统审核"),

    MANUAL_AUDIT("人工审核");

    private String name;

    private RefundAuditStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 通过枚举标识，获得枚举对象
     *
     * @param index
     * @return
     * @author WenJie Mai
     */
    public static RefundAuditStatus getEnumByIndex(Integer index) {

        switch (index) {
            case 0:
                return SYSTEM_AUDIT;
            case 1:
                return MANUAL_AUDIT;
        }

        return null;
    }
}

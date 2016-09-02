package cn.com.gome.dujia.enums;

/**
 * 后台配置的页面类型
 * Created by zhaoxiang-ds on 2016/5/5.
 */
public enum PageModelType {

    PAGE_INDEX_TRAVEL("旅行首页", "travel"),
    PAGE_INDEX("首页", "index"),
    PAGE_LIST("列表页", "list");

    private String name;
    private String value;


    private PageModelType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

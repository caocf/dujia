package cn.com.gome.dujia.enums;

/**
 * 平台：APP WAP WEB
 * Created by zhaoxiang-ds on 2016/5/4.
 */
public enum PlatformType {
    WEB("WEB", 1),

    APP("APP", 2),

    WAP("WAP", 3);

    private String name;
    private Integer value;


    private PlatformType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

package cn.com.gome.dujia.enums;

/**
 * 周边城市类型:0周边城市,1周末去哪,2热门城市
 * Created by zhaoxiang-ds on 2016/5/4.
 */
public enum AroundCityType {


    AROUND("周边城市", 0),

    WEEKEND("周末去哪", 1),

    HOT("热门城市", 2);


    private String name;
    private Integer value;

    private AroundCityType(String name, Integer value) {
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

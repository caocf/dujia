package cn.com.gome.dujia.enums;

/**
 * 广告状态
 * 0-待排期，1-展示中，3-已暂停，4-已过期
 * Created by zhaoxiang-ds on 2016/5/24.
 */
public enum ADStates {

    WAITING("待排期", 0),
    DISPLAY("展示中", 1),
    PAUSE("已暂停", 3),
    EXPIRE("已过期", 4);

    private String name;
    private Integer value;

    private ADStates(String name, Integer value) {
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

package cn.com.gome.dujia.dto;

import java.io.Serializable;

/**
 * 城市DTO
 * Created by zhaoxiang-ds on 2016/4/14.
 */
public class ZbyCityDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 城市Id
     */
    private Integer id;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 城市拼音
     */
    private String pinyin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}

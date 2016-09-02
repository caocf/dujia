package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 周边游首页响应-线路DTO
 * Created by zhaoxiang-ds on 2016/4/13.
 */
public class ZbyProductRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块
     */
    private String model;
    /**
     * 模块名称
     */
    private String name;

    /**
     *
     */
    private String value;

    /**
     * 第一个位置是否配置标记
     */
    private boolean flag = false;

    /**
     * 推荐线路列表
     */
    private List<ZbyProductSimpleDto> data;



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public List<ZbyProductSimpleDto> getData() {
        return data;
    }

    public void setData(List<ZbyProductSimpleDto> data) {
        this.data = data;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

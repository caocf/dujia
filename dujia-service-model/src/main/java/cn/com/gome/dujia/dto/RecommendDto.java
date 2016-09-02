package cn.com.gome.dujia.dto;

import cn.com.gome.dujia.model.Advert;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhaoxiang-ds on 2016/5/5.
 */
public class RecommendDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private String model;
    private String name;
    private String value;
    private List<Advert> recommend;


}

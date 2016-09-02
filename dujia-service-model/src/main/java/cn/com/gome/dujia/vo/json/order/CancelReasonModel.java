package cn.com.gome.dujia.vo.json.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sunming on 2016/4/26.
 */
public class CancelReasonModel  implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value="Id")
    private String Id;
    @JsonProperty(value="reasonName")
    private String ReasonName;
    @JsonProperty(value="parentId")
    private String ParentId;
    @JsonProperty(value="sort")
    private String Sort;
    @JsonProperty(value="category")
    private String Category;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getReasonName() {
        return ReasonName;
    }

    public void setReasonName(String reasonName) {
        ReasonName = reasonName;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getSort() {
        return Sort;
    }

    public void setSort(String sort) {
        Sort = sort;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}

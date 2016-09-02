package cn.com.gome.dujia.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "zby_recom_info")
public class ZbyRecomInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id") 
	private Integer id;
	
	@Column(name="product_id") 
	private String productId;
	
	@Column(name="title") 
	@JsonProperty(value= "Title")
	private String title;
	
	@Column(name="image_url") 
	@JsonProperty(value= "ImageUrl")
	private String imageUrl;
	
	@Column(name="image_url_local") 
	private String imageUrlLocal;
	
	@Column(name="create_time") 
	private Date createTime;
	
	@Column(name="update_user") 
	private String updateUser;
	
	@Column(name="update_time") 
	private Date updateTime;
	
	@Column(name="is_delete") 
	private Boolean isDelete;

	/**
    * @Fields startTime : 开始时间
    */
    @Transient
    private Date startTime;
    
    /**
    * @Fields endTime : 结束时间
    */
    @Transient
    private Date endTime;
    
    /**
    * @Fields titleCount : 分类数量
    */
    @Transient
    private int titleCount;
    
    /**
     * @Fields titleCount : 标签数组
     */
    @Transient
    private String[] titleArray;
	


	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setProductId(String value) {
		this.productId = value;
	}
	
	public String getProductId() {
		return this.productId;
	}

	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setImageUrl(String value) {
		this.imageUrl = value;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrlLocal(String value) {
		this.imageUrlLocal = value;
	}
	
	public String getImageUrlLocal() {
		return this.imageUrlLocal;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setIsDelete(Boolean value) {
		this.isDelete = value;
	}
	
	public Boolean getIsDelete() {
		return this.isDelete;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getTitleCount() {
		return titleCount;
	}

	public void setTitleCount(int titleCount) {
		this.titleCount = titleCount;
	}

	public String[] getTitleArray() {
		return titleArray;
	}

	public void setTitleArray(String[] titleArray) {
		this.titleArray = titleArray;
	}

	
	public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ZbyRecomInfo other = (ZbyRecomInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getImageUrlLocal() == null ? other.getImageUrlLocal() == null : this.getImageUrlLocal().equals(other.getImageUrlLocal()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getImageUrlLocal() == null) ? 0 : getImageUrlLocal().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }
}
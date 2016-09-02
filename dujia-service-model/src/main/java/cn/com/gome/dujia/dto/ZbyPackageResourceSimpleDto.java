package cn.com.gome.dujia.dto;

import java.io.Serializable;

/**
 * 线路详情 - 套餐列表
 * Created by zhaoxiang-ds on 2016/4/28.
 */
public class ZbyPackageResourceSimpleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 线路id
     */
    private String productId;

    /**
     * 套餐id
     */
    private String packageId;

    /**
     * 资源Id
     */
    private String resId;

    /**
     * 资源名称
     */
    private String resName;

    /**
     * 资源类型:0酒店，1景区
     */
    private Integer resType;

    /**
     * 资源产品ID
     */
    private Integer resProId;

    /**
     * 资源产品名称
     */
    private String resProName;

    /**
     * 供应商销售策略ID
     */
    private Integer supPpId;

    /**
     * 供应商销售策略名称
     */
    private String supPpName;

    /**
     * 产品单位（1：酒店可住*晚、2：景区门票*张）
     */
    private Integer proContainType;

    /**
     * 根据ProContainType 类 来存放 房间数、套票数,或单票张数
     */
    private Integer proContainCount;

    /**
     * 床型
     */
    private String bedType;


    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public Integer getResProId() {
        return resProId;
    }

    public void setResProId(Integer resProId) {
        this.resProId = resProId;
    }

    public String getResProName() {
        return resProName;
    }

    public void setResProName(String resProName) {
        this.resProName = resProName;
    }

    public Integer getSupPpId() {
        return supPpId;
    }

    public void setSupPpId(Integer supPpId) {
        this.supPpId = supPpId;
    }

    public String getSupPpName() {
        return supPpName;
    }

    public void setSupPpName(String supPpName) {
        this.supPpName = supPpName;
    }

    public Integer getProContainType() {
        return proContainType;
    }

    public void setProContainType(Integer proContainType) {
        this.proContainType = proContainType;
    }

    public Integer getProContainCount() {
        return proContainCount;
    }

    public void setProContainCount(Integer proContainCount) {
        this.proContainCount = proContainCount;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


}

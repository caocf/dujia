package cn.com.gome.dujia.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 套餐列表
 * Created by zhaoxiang-ds on 2016/4/26.
 */
public class ZbyPackageRespDto implements Serializable {

    private static final long serialVersionUID = 1L;

    // 日期
    private String saleDate;
    // 最低价
    private BigDecimal minPrice;
    // 最高价
    private BigDecimal maxPrice;
    // 套餐列表
    private List<ZbyPackageDto> packages;


    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<ZbyPackageDto> getPackages() {
        return packages;
    }

    public void setPackages(List<ZbyPackageDto> packages) {
        this.packages = packages;
    }
}

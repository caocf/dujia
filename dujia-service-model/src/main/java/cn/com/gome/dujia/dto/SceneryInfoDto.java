package cn.com.gome.dujia.dto;

import java.util.Date;

/**
 * 景区信息
 * Created by zhaoxiang-ds on 2016/4/26.
 */
public class SceneryInfoDto extends BaseDto {

    // 景区id
    private String id;
    // 景区名称
    private String name;
    // 景区关联产品名称
    private String proName;
    // 门票数量
    private int count;
    // 地址
    private String address;
    // 开园时间
    private Date openTime;
    // 取票方式
    private String ticketWay;
    // 折扣
    private String discountInfo;
    // 门票说明
    private String ticketDesc;
    // 详情url
    private String detailDsecUrl;
    // 必要证件
    private String needCard;
    // 景区图片url
    private String imgUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getTicketWay() {
        return ticketWay;
    }

    public void setTicketWay(String ticketWay) {
        this.ticketWay = ticketWay;
    }

    public String getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(String discountInfo) {
        this.discountInfo = discountInfo;
    }

    public String getTicketDesc() {
        return ticketDesc;
    }

    public void setTicketDesc(String ticketDesc) {
        this.ticketDesc = ticketDesc;
    }

    public String getDetailDsecUrl() {
        return detailDsecUrl;
    }

    public void setDetailDsecUrl(String detailDsecUrl) {
        this.detailDsecUrl = detailDsecUrl;
    }

    public String getNeedCard() {
        return needCard;
    }

    public void setNeedCard(String needCard) {
        this.needCard = needCard;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}

package cn.com.gome.dujia.dto;

/**
 * 酒店信息
 * Created by zhaoxiang-ds on 2016/4/26.
 */
public class HotelInfoDto extends BaseDto {

    // 酒店id
    private String id;
    // 酒店名称
    private String name;
    // 关联产品名称
    private String proName;
    // 数量,入住?晚
    private int count;
    // 早餐
    private String breakfast;
    // 地址
    private String address;
    // 房间面积
    private String roomArea;
    // 卫浴设施
    private String bathRoom;
    // 床宽
    private String bedWidth;
    // 床型
    private String bedType;
    // 加床
    private String addBed;
    // 加床价格
    private String addBedPrice;
    // 网络
    private String net;
    // 卧室图片
    private String roomImgUrl;
    // 详情url
    private String detailDsecUrl;

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

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public String getBathRoom() {
        return bathRoom;
    }

    public void setBathRoom(String bathRoom) {
        this.bathRoom = bathRoom;
    }

    public String getBedWidth() {
        return bedWidth;
    }

    public void setBedWidth(String bedWidth) {
        this.bedWidth = bedWidth;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getAddBed() {
        return addBed;
    }

    public void setAddBed(String addBed) {
        this.addBed = addBed;
    }

    public String getAddBedPrice() {
        return addBedPrice;
    }

    public void setAddBedPrice(String addBedPrice) {
        this.addBedPrice = addBedPrice;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getRoomImgUrl() {
        return roomImgUrl;
    }

    public void setRoomImgUrl(String roomImgUrl) {
        this.roomImgUrl = roomImgUrl;
    }

    public String getDetailDsecUrl() {
        return detailDsecUrl;
    }

    public void setDetailDsecUrl(String detailDsecUrl) {
        this.detailDsecUrl = detailDsecUrl;
    }
}

package cn.com.gome.dujia.vo.json.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by sunming on 2016/4/26.
 */
public class CountModel  implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value="AdultCount")
    private String  adultCount;

    @JsonProperty(value="ChildCount")
    private String   childCount;

    @JsonProperty(value="PriceFraction")
    private String   priceFraction;

    @JsonProperty(value="RoomCount")
    private String  roomCount;

    @JsonProperty(value="Days")
    private String   days;

    public String getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(String adultCount) {
        this.adultCount = adultCount;
    }

    public String getChildCount() {
        return childCount;
    }

    public void setChildCount(String childCount) {
        this.childCount = childCount;
    }

    public String getPriceFraction() {
        return priceFraction;
    }

    public void setPriceFraction(String priceFraction) {
        this.priceFraction = priceFraction;
    }

    public String getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(String roomCount) {
        this.roomCount = roomCount;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}

package cn.com.gome.dujia.vo.json.productinfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunming on 2016/4/26.
 */
public class TcPriceInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty(value="TCPriceId")
    private String tCPriceId;
    @JsonProperty(value="VerifyDate")
    private Date verifyDate;
    @JsonProperty(value="CheckWay")
    private String  checkWay;
    @JsonProperty(value="TotalTicketNumber")
    private String  totalTicketNumber;
    @JsonProperty(value="TCResourceId")
    private String  tCResourceId;
    @JsonProperty(value="TCResProductId")
    private String  tCResProductId;
    @JsonProperty(value="IntervalType")
    private String  intervalType;
    @JsonProperty(value="IntervalTime")
    private String intervalTime;
    @JsonProperty(value="TCResName")
    private String tCResName;
    @JsonProperty(value="TCResProductName")
    private String tCResProductName;
    @JsonProperty(value="TCPriceName")
    private String tCPriceName;


    public String gettCPriceId() {
        return tCPriceId;
    }

    public void settCPriceId(String tCPriceId) {
        this.tCPriceId = tCPriceId;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getCheckWay() {
        return checkWay;
    }

    public void setCheckWay(String checkWay) {
        this.checkWay = checkWay;
    }

    public String getTotalTicketNumber() {
        return totalTicketNumber;
    }

    public void setTotalTicketNumber(String totalTicketNumber) {
        this.totalTicketNumber = totalTicketNumber;
    }

    public String gettCResourceId() {
        return tCResourceId;
    }

    public void settCResourceId(String tCResourceId) {
        this.tCResourceId = tCResourceId;
    }

    public String gettCResProductId() {
        return tCResProductId;
    }

    public void settCResProductId(String tCResProductId) {
        this.tCResProductId = tCResProductId;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String gettCResName() {
        return tCResName;
    }

    public void settCResName(String tCResName) {
        this.tCResName = tCResName;
    }

    public String gettCResProductName() {
        return tCResProductName;
    }

    public void settCResProductName(String tCResProductName) {
        this.tCResProductName = tCResProductName;
    }

    public String gettCPriceName() {
        return tCPriceName;
    }

    public void settCPriceName(String tCPriceName) {
        this.tCPriceName = tCPriceName;
    }
}

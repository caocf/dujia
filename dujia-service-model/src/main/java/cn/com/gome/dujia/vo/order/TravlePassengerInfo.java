package cn.com.gome.dujia.vo.order;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author wangweiran
 * 
 * 同程创建订单出游人信息
 */
public class TravlePassengerInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 客人姓名
	 * Y
	 */
	@JsonProperty(value = "Name")
	private String name;
	/**
	 * 客人手机号
	 */
	@JsonProperty(value = "Mobile")
	private String mobile;
	/**
	 * 证件号
	 */
	@JsonProperty(value = "idNo")
	private String idNo;
	/**
	 * 性别
	 * 0->女，1->男
	 */
	@JsonProperty(value = "Sex")
	private Integer sex;
	/**
	 * 生日
	 */
	@JsonProperty(value = "BirthDay")
	private String birthDay;
	/**
	 * 证件类型
	 * -1=> 未 填 写 ;0=> 身 份 证 ;1=> 护 照 ;2=> 军官证;3=>士兵证;4=>港澳通行证;5=>临时身份证;
	 * 6=>户口本;7=>其他;9=>警官证;12=>外国人居住证;15=>回乡证;16=>企业执照证;17=>法人代表证;18=>台胞证
	 */
	@JsonProperty(value = "CertType")
	private Integer certType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public Integer getCertType() {
		return certType;
	}
	public void setCertType(Integer certType) {
		this.certType = certType;
	}
}

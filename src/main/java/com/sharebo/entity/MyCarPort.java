package com.sharebo.entity;

import java.io.Serializable;

/**
 * 个人车位信息
 * @author niewei
 */
public class MyCarPort implements Serializable{
	private static final long serialVersionUID = 1L;
	private String carportId;//
	private String housId;// '小区外键(对应小区)',
	private String detailedAddress;// '入口详细地址',
	private String housType;// '车位类型(地面；室内；充电桩)',
	private String serialnumber;// 车位编号
	private String parkremark;// 车位说明
	private Integer rentoutType;// '出租类型(0:长租：1:临时出租) 如果为长租，计费为长租表/数据，临时出租j数据为星期规则和日期规则',
	private String userid;// 用户外键
	private Double money;// '单价费用',
	private Integer feeType;//收费类型、 1：按次收费  2:按小时
	private String contacts;
	private String information;
	private String Invidualimg;
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getInvidualimg() {
		return Invidualimg;
	}
	public void setInvidualimg(String invidualimg) {
		Invidualimg = invidualimg;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public String getHousType() {
		return housType;
	}
	public void setHousType(String housType) {
		this.housType = housType;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getParkremark() {
		return parkremark;
	}
	public void setParkremark(String parkremark) {
		this.parkremark = parkremark;
	}
	public Integer getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(Integer rentoutType) {
		this.rentoutType = rentoutType;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "MyCarPort [carportId=" + carportId + ", housId=" + housId + ", detailedAddress=" + detailedAddress
				+ ", housType=" + housType + ", serialnumber=" + serialnumber + ", parkremark=" + parkremark
				+ ", rentoutType=" + rentoutType + ", userid=" + userid + ", money=" + money + ", feeType=" + feeType
				+ "]";
	}
	
}

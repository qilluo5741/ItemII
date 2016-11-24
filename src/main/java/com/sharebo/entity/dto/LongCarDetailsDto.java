package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;


public class LongCarDetailsDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String carportId;//车位主键
	private Integer rentoutType;//出租类型
	private String  housId;//
	private String detailedAddress;//
	private String housType;//
	private String serialnumber;//
	private String parkremark;//
	private Double money;//
	private Integer timetype;//
	private Date beginmonthTime;//
	private Date endmonthTime;
	private String beginDayTime;
	private String endDayTime;
	private String housName;
	private String housAddress;
	private String thecontact;
	private String contact;
	public String getThecontact() {
		return thecontact;
	}
	public void setThecontact(String thecontact) {
		this.thecontact = thecontact;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getHousName() {
		return housName;
	}
	public void setHousName(String housName) {
		this.housName = housName;
	}
	public String getHousAddress() {
		return housAddress;
	}
	public void setHousAddress(String housAddress) {
		this.housAddress = housAddress;
	}
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	public Integer getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(Integer rentoutType) {
		this.rentoutType = rentoutType;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getTimetype() {
		return timetype;
	}
	public void setTimetype(Integer timetype) {
		this.timetype = timetype;
	}
	public Date getBeginmonthTime() {
		return beginmonthTime;
	}
	public void setBeginmonthTime(Date beginmonthTime) {
		this.beginmonthTime = beginmonthTime;
	}
	public Date getEndmonthTime() {
		return endmonthTime;
	}
	public void setEndmonthTime(Date endmonthTime) {
		this.endmonthTime = endmonthTime;
	}
	public String getBeginDayTime() {
		return beginDayTime;
	}
	public void setBeginDayTime(String beginDayTime) {
		this.beginDayTime = beginDayTime;
	}
	public String getEndDayTime() {
		return endDayTime;
	}
	public void setEndDayTime(String endDayTime) {
		this.endDayTime = endDayTime;
	}
}

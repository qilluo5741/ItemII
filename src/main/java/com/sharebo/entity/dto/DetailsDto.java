package com.sharebo.entity.dto;

import java.io.Serializable;

public class DetailsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String housName;
	private String housAddress;
	private String housId;
	private String carportId;
	private String detailedAddress;
	private String housType;
	private Double money;
	private int feeType;
	private String name;
	private String phone;
	private String parkremark;
	private int isexist;
	private String serialnumber;
	private int status;
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
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getParkremark() {
		return parkremark;
	}
	public void setParkremark(String parkremark) {
		this.parkremark = parkremark;
	}
	public int getIsexist() {
		return isexist;
	}
	public void setIsexist(int isexist) {
		this.isexist = isexist;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	/*public String getThecontact() {
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
	public String getPersonalphoto() {
		return personalphoto;
	}
	public void setPersonalphoto(String personalphoto) {
		this.personalphoto = personalphoto;
	}*/
}

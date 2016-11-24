package com.sharebo.entity.dto;

import java.io.Serializable;

public class UserorregidDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String carNo;
	private String regid;
	private String phone;
	private String parkId;
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
}

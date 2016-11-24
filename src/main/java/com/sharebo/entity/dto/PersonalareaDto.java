package com.sharebo.entity.dto;

import java.io.Serializable;

public class PersonalareaDto  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String  userid;
	private String  housid;
	private String  housName;
	private String  housAddress;
	private String  cartype;
	private Integer rentoutType;
	private double  money;
	private Integer feeType;
	private String  identifying;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getHousid() {
		return housid;
	}
	public void setHousid(String housid) {
		this.housid = housid;
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
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public Integer getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(Integer rentoutType) {
		this.rentoutType = rentoutType;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public String getIdentifying() {
		return identifying;
	}
	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}
}

package com.sharebo.entity.dto;

public class ParkLongRentDto {
	private String housName;
	private String housAddress;
	private Double money;
	private String beginMonthTime;//开始时间
	private String endMonthTime;//结束时间
	private String housId;
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
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getBeginMonthTime() {
		return beginMonthTime;
	}
	public void setBeginMonthTime(String beginMonthTime) {
		this.beginMonthTime = beginMonthTime;
	}
	public String getEndMonthTime() {
		return endMonthTime;
	}
	public void setEndMonthTime(String endMonthTime) {
		this.endMonthTime = endMonthTime;
	}
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
}

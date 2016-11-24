package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class ParkChargeInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date beginTime;//开始计时时间
	private Date endTime;//结束计时时间
	private String parkName;//停车场名字
	private double practicalMoney;//实收价格
	private int payState;//支付状态（0：未支付 1：已经支付）
	private String payidentifying;//(支付标识)
	public String getPayidentifying() {
		return payidentifying;
	}
	public void setPayidentifying(String payidentifying) {
		this.payidentifying = payidentifying;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public double getPracticalMoney() {
		return practicalMoney;
	}
	public void setPracticalMoney(double practicalMoney) {
		this.practicalMoney = practicalMoney;
	}
	public int getPayState() {
		return payState;
	}
	public void setPayState(int payState) {
		this.payState = payState;
	}
}

package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class ParkChargeDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String parkcharge;
	private double practicalMoney;//实收价格
	private int payState;//支付状态（0：未支付 1：已经支付）
	private String payidentifying;//(支付标识)
	private Date beginTime;//支付时间
	private String parkName;//停车场名字
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
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
	public String getParkcharge() {
		return parkcharge;
	}
	public void setParkcharge(String parkcharge) {
		this.parkcharge = parkcharge;
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

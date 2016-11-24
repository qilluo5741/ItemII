package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * Í£³µ½É·Ñ
 * @author Administrator
 *
 */
public class ParkPayment   implements Serializable{
	private static final long serialVersionUID = 1L;
	private String parkcharge;
	private String payidentifying;
	private Integer payState;
	private Date beginTime;
	private Date endTime;
	private String parkname;
	private String parkAddress;
	private double payMoney;
	public String getParkcharge() {
		return parkcharge;
	}
	public void setParkcharge(String parkcharge) {
		this.parkcharge = parkcharge;
	}
	public String getPayidentifying() {
		return payidentifying;
	}
	public void setPayidentifying(String payidentifying) {
		this.payidentifying = payidentifying;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
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
	public String getParkname() {
		return parkname;
	}
	public void setParkname(String parkname) {
		this.parkname = parkname;
	}
	public String getParkAddress() {
		return parkAddress;
	}
	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
	}
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
}

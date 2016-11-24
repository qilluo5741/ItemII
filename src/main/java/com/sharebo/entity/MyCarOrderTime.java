package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class MyCarOrderTime implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String motId;//Ö÷¼ü
	private Date rentDate;//ÈÕÆÚ
	private String beginTime;//
	private String endTime;//
	private CarPortOrderDetailInfo cpod;
	public String getMotId() {
		return motId;
	}
	public void setMotId(String motId) {
		this.motId = motId;
	}
	public Date getRentDate() {
		return rentDate;
	}
	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public CarPortOrderDetailInfo getCpod() {
		return cpod;
	}
	public void setCpod(CarPortOrderDetailInfo cpod) {
		this.cpod = cpod;
	}
	
}         

package com.sharebo.entity;

import java.util.Date;

public class ParkOrderDetailsInfo {
	private String porderId;//��������
	private String parkId;//ͣ��������
	private Date beginTime;//��ʼʱ��
	private Date endTime;//����ʱ��
	private Double reservationfee;//ԤԼ��
	private Double cash;//��Сʱ��֤��
	private Double thankFee;//��л��
	private OrderInfo order;
	public String getPorderId() {
		return porderId;
	}
	public void setPorderId(String porderId) {
		this.porderId = porderId;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
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
	public Double getReservationfee() {
		return reservationfee;
	}
	public void setReservationfee(Double reservationfee) {
		this.reservationfee = reservationfee;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getThankFee() {
		return thankFee;
	}
	public void setThankFee(Double thankFee) {
		this.thankFee = thankFee;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	
}

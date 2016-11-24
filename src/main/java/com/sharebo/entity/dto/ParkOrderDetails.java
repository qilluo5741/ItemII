package com.sharebo.entity.dto;

import java.util.Date;

/**
 * 停车场订单详情
 * @author niewei
 */
public class ParkOrderDetails {
	private String orderId;//
	private String orderNum;//
	private Integer order_state;//
	private Integer payType;
	private String parkName;
	private String parkAddress;
	private Date beginTime;
	private Date endTime;
	private Double thankFee;
	private Double reservationFee;
	private Double cash;
	private Double countMoney;//总费用
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getParkAddress() {
		return parkAddress;
	}
	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
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
	public Double getThankFee() {
		return thankFee;
	}
	public void setThankFee(Double thankFee) {
		this.thankFee = thankFee;
	}
	public Double getReservationFee() {
		return reservationFee;
	}
	public void setReservationFee(Double reservationFee) {
		this.reservationFee = reservationFee;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(Double countMoney) {
		this.countMoney = countMoney;
	}
	
}

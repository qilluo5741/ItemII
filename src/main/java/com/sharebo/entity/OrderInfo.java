package com.sharebo.entity;

import java.util.Date;

public class OrderInfo {
	private String orderId;//订单主键
	private String orderNum;//订单号码
	private Integer orderType;//订单类型(0:公共停车场1 ：私人车位）
	private Date placeorde_time=new Date();//下订单时间
	private Integer payType=0;//默认  未支付支付方式（0：未支付 ，1：支付宝 2：微信 3:余额）
	private Integer order_state=0;//订单状态(0:等待中，1:已完成 2：已拒绝)
	private String carNo;//车牌号
	private String userid;
	private Double countMoney;//需要支付的金额
	
	public Double getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(Double countMoney) {
		this.countMoney = countMoney;
	}
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
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Date getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(Date placeorde_time) {
		this.placeorde_time = placeorde_time;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}

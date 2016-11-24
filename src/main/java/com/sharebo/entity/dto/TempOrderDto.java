package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 临时订单详情查询
 * @author Administrator
 *
 */
public class TempOrderDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String orderNum;//订单号
	private String orderId;//订单主键
	private Integer order_state;//订单状态(0:等待中，1:已完成 2：已拒绝)
	private Integer payType;//支付方式（0：未支付 ，1：支付宝 2：微信 3:余额）
	private Double countMoney;//总费用
	private String phone;//车主手机号码
	private String housName;//小区名字
	private String housAddress;//小区地址
    private Date rentDate;//日期
    private String beginTime;//开始时间
    private String endTime;//结束时间
	private Integer hour;//小时
	private Double money;
	private int feeType;
	public Integer getHour() {
		String beginTimes=this.beginTime;
		String endTimes=this.endTime;
		Integer beginMin=Integer.valueOf(beginTimes.substring(3,5));
		Integer endMin=Integer.valueOf(endTimes.substring(3,5));
		Integer beginHour=Integer.valueOf(beginTimes.substring(0,2));
		Integer endHour=Integer.valueOf(endTimes.substring(0,2));
		if(beginHour==endHour||endMin>beginMin){
			hour=(endHour-beginHour)+1;
		}
		else{
		hour=endHour-beginHour;
		}
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public Double getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(Double countMoney) {
		this.countMoney = countMoney;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHousName() {
		return housName;
	}
	public void setHousName(String housName) {
		this.housName = housName;
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
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
}

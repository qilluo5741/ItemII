package com.sharebo.entity.dto;
/**
 * 供方临时出租订单
 */
import java.io.Serializable;
import java.util.Date;

public class TemporaryRentDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;//订单主键
	private String orderNum;//订单号
	private String phone;//用户手机号码
	private String carNo;//车牌号
	private int order_state;//订单状态(0:等待中，1:已完成 2：已拒绝)
	private Date rentDate;//日期
	private String beginTime;//开始时间
	private String endTime;//结束时间
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
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
}

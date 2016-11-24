package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * 供方长租订单查询
 */
public class LongRentDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;//订单主键
	private String orderNum;//订单号
	private String phone;//用户手机号码
	private String carNo;//车牌号
	private int order_state;//订单状态(0:等待中，1:已完成 2：已拒绝)
	private int timetype;//长租时间类型(0：全天出租，1：参考天时间段 ) 如果为全天出租 就参照可出租月开始结束时间，其相反
	private String begindayTime;//长租可租天开始时间（一天中什么时候开始，在长租时间段类型为非全天可租可用）
	private String enddayTime;//长租可租天结束时间（一天中，什么时候结束）
	private Date beginmonthTime;//长租可租月开始时间
	private Date endmonthTime;//长租可租月结束时间
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
	public int getTimetype() {
		return timetype;
	}
	public void setTimetype(int timetype) {
		this.timetype = timetype;
	}
	public String getBegindayTime() {
		return begindayTime;
	}
	public void setBegindayTime(String begindayTime) {
		this.begindayTime = begindayTime;
	}
	public String getEnddayTime() {
		return enddayTime;
	}
	public void setEnddayTime(String enddayTime) {
		this.enddayTime = enddayTime;
	}
	public Date getBeginmonthTime() {
		return beginmonthTime;
	}
	public void setBeginmonthTime(Date beginmonthTime) {
		this.beginmonthTime = beginmonthTime;
	}
	public Date getEndmonthTime() {
		return endmonthTime;
	}
	public void setEndmonthTime(Date endmonthTime) {
		this.endmonthTime = endmonthTime;
	}
}

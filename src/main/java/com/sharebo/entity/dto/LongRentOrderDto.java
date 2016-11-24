package com.sharebo.entity.dto;

import java.util.Date;

/**
 * 长租订单详情查询
 * @author Administrator
 *
 */
public class LongRentOrderDto {
	private String orderNum;//订单号
	private String orderId;//订单主键
	private Integer order_state;//订单状态(0:等待中，1:已完成 2：已拒绝)
	private Integer payType;//支付方式（0：未支付 ，1：支付宝 2：微信 3:余额）
	private Double countMoney;//总费用
	private String phone;//车主手机号码
	private String housName;//小区名字
	private String housAddress;//小区地址
	private Integer timetype;//长租时间类型(0：全天出租，1：参考天时间段 ) 如果为全天出租 就参照可出租月开始结束时间，其相反
	private String enddayTime;//长租可租天结束时间（一天中，什么时候结束）
	private String begindayTime;//长租可租天开始时间（一天中什么时候开始，在长租时间段类型为非全天可租可用）
	private Date endmonthTime;//长租可租月结束时间
	private Date beginmonthTime;//长租可租月开始时间
	public String getEnddayTime() {
		return enddayTime;
	}
	public void setEnddayTime(String enddayTime) {
		this.enddayTime = enddayTime;
	}
	public String getBegindayTime() {
		return begindayTime;
	}
	public void setBegindayTime(String begindayTime) {
		this.begindayTime = begindayTime;
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
	public Integer getTimetype() {
		return timetype;
	}
	public void setTimetype(Integer timetype) {
		this.timetype = timetype;
	}
	public Date getEndmonthTime() {
		return endmonthTime;
	}
	public void setEndmonthTime(Date endmonthTime) {
		this.endmonthTime = endmonthTime;
	}
	public Date getBeginmonthTime() {
		return beginmonthTime;
	}
	public void setBeginmonthTime(Date beginmonthTime) {
		this.beginmonthTime = beginmonthTime;
	}
	public String getHousAddress() {
		return housAddress;
	}
	public void setHousAddress(String housAddress) {
		this.housAddress = housAddress;
	}
}

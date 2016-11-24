package com.sharebo.entity.dto;
/**
 * 专位订单记录查询
 */
import java.util.Date;

public class CarportOrderInfo {
	private String housName;//小区名字
	private Integer payType;//支付方式（0：未支付 ，1：支付宝 2：微信 3:余额）
	private Double countMoney;//合计费用
	private Date placeorde_time;//下订单时间
	private Integer rentoutType;//出租类型(0:长租：1:临时出租)  数据来自车位表  如果为长租  数据对应长租时间表  临时出租 数据对应临时时间表
	private String orderNum;//订单号
	private String orderId;//订单主键
	private Integer order_state;//订单状态(0:等待中，1:已完成 2：已拒绝)
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getHousName() {
		return housName;
	}
	public void setHousName(String housName) {
		this.housName = housName;
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
	public Date getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(Date placeorde_time) {
		this.placeorde_time = placeorde_time;
	}
	public Integer getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(Integer rentoutType) {
		this.rentoutType = rentoutType;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}

package com.sharebo.entity.dto;
/**
 * 公共停车场订单记录查询
 */
import java.util.Date;

public class ParkOrderInfo {
	private Integer payType;//支付方式（0：未支付 ，1：支付宝 2：微信 3:余额）
	private Double countMoney;//合计费用
	private Date placeorde_time;//下订单时间
	private String orderNum;//订单号
	private String orderId;//订单主键
	private Integer order_state;//订单状态(0:等待中，1:已完成 2：已拒绝)
	private String parkName;//停车场名字
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
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
}

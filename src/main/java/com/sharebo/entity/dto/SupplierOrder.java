package com.sharebo.entity.dto;
/**
 * 供方我的订单
 */
import java.io.Serializable;

public class SupplierOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String carNo;//车牌号
	private String orderId;//订单主键
	private String phone;//用户手机号码
	private Integer order_state;//订单状态
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
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
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
}

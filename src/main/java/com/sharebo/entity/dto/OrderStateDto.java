package com.sharebo.entity.dto;

import java.io.Serializable;

public class OrderStateDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer order_state;//订单状态
	private Integer payType;//支付类型
	private String userid;//车位主人Id
	private double countMoney;//订单总费用
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public double getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(double countMoney) {
		this.countMoney = countMoney;
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
}

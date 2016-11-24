package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class UpdateOrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date payment_time=new Date();//支付时间
	private Integer payType;//支付方式（0：未支付 ，1：支付宝 2：微信 3:余额）
	public Date getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(Date payment_time) {
		this.payment_time = payment_time;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class UpdateOrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date payment_time=new Date();//֧��ʱ��
	private Integer payType;//֧����ʽ��0��δ֧�� ��1��֧���� 2��΢�� 3:��
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

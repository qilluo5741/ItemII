package com.sharebo.entity.dto;

import java.io.Serializable;

/**
 * ��ѯԤԼ�Ѻͱ�֤��
 * @author niewei
 *
 */
public class ParkOrderFeeDto  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double reservationfee;
	private Double cash;
	public Double getReservationfee() {
		return reservationfee;
	}
	public void setReservationfee(Double reservationfee) {
		this.reservationfee = reservationfee;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	
}

package com.sharebo.entity.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String payNo;
	private double availableBalance;
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
}

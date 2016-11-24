package com.sharebo.entity.dto;

import java.io.Serializable;

/**
 * ÍË¿î²Ù×÷
 * @author niewei
 *
 */
public class CallBackRefuceInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userid;
	private Double countMoney;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Double getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(Double countMoney) {
		this.countMoney = countMoney;
	}
	
}

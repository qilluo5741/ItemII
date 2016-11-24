package com.sharebo.entity.dto;

import java.io.Serializable;

public class UserWithDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String payNo;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
}

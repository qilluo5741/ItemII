package com.sharebo.entity.dto;

import java.io.Serializable;

public class AuthUserinfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String phone;
	private String password;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

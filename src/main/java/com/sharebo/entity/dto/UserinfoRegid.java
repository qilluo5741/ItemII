package com.sharebo.entity.dto;

import java.io.Serializable;

public class UserinfoRegid implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String regid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
}

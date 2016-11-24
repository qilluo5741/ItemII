package com.sharebo.entity.dto;

import java.io.Serializable;

public class UserTokenDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String valToken;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getValToken() {
		return valToken;
	}
	public void setValToken(String valToken) {
		this.valToken = valToken;
	}
}

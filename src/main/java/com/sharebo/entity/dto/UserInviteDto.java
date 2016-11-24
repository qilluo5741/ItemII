package com.sharebo.entity.dto;

import java.io.Serializable;

public class UserInviteDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String inviteCode;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
}

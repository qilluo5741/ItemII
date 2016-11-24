package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * ÑûÇëºÃÓÑ¼ÇÂ¼
 * @author Administrator
 *
 */
public class InviteFriendsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tophone;
	private String inviteTime;//ÑûÇëÊ±¼ä
	private int inviteState;//ÑûÇë×´Ì¬£¨0:yÒÑ¾­ÑûÇëÎ´×¢²á  1£ºÒÑ¾­×¢²á£©
	public String getTophone() {
		return tophone;
	}
	public void setTophone(String tophone) {
		this.tophone = tophone;
	}
	public String getInviteTime() {
		return inviteTime;
	}
	public void setInviteTime(String inviteTime) {
		this.inviteTime = inviteTime;
	}
	public int getInviteState() {
		return inviteState;
	}
	public void setInviteState(int inviteState) {
		this.inviteState = inviteState;
	}
}

package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * ������Ѽ�¼
 * @author Administrator
 *
 */
public class InviteFriendsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tophone;
	private String inviteTime;//����ʱ��
	private int inviteState;//����״̬��0:y�Ѿ�����δע��  1���Ѿ�ע�ᣩ
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

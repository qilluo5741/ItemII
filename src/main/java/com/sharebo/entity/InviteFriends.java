package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ������ѱ�
 * @author Administrator
 *
 */
public class InviteFriends implements Serializable {
	private static final long serialVersionUID = 1L;
	private String inviteId;//             varchar(36) not null,
	private String tophone;//              varchar(11) not null,
	private Date inviteTime;//           datetime not null comment '����ʱ��',
	private String carNo;//                varchar(50) not null comment '���ƺ�(�������˵ĳ��ƺ�)',
	private String userid;//               varchar(36) not null comment '�û����(˭�����)',
	private String inviteCode;//           varchar(20) not null comment '�����루�����û��������룩',
	private int inviteState;//          int(1) comment '����״̬��0:y�Ѿ�����δע��  1���Ѿ�ע�ᣩ'
	public String getInviteId() {
		return inviteId;
	}
	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}
	public String getTophone() {
		return tophone;
	}
	public void setTophone(String tophone) {
		this.tophone = tophone;
	}
	public Date getInviteTime() {
		return inviteTime;
	}
	public void setInviteTime(Date inviteTime) {
		this.inviteTime = inviteTime;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
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
	public int getInviteState() {
		return inviteState;
	}
	public void setInviteState(int inviteState) {
		this.inviteState = inviteState;
	}
}

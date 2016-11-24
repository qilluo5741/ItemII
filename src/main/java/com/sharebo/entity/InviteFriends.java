package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请好友表
 * @author Administrator
 *
 */
public class InviteFriends implements Serializable {
	private static final long serialVersionUID = 1L;
	private String inviteId;//             varchar(36) not null,
	private String tophone;//              varchar(11) not null,
	private Date inviteTime;//           datetime not null comment '邀请时间',
	private String carNo;//                varchar(50) not null comment '车牌号(被邀请人的车牌号)',
	private String userid;//               varchar(36) not null comment '用户外键(谁邀请的)',
	private String inviteCode;//           varchar(20) not null comment '邀请码（邀请用户的邀请码）',
	private int inviteState;//          int(1) comment '邀请状态（0:y已经邀请未注册  1：已经注册）'
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

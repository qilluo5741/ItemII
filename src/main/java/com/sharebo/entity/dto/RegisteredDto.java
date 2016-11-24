package com.sharebo.entity.dto;

import java.util.Date;

/**
 * 注册验证
 * @author niewei
 *
 */
public class RegisteredDto {
	private String userid;
	private String password;//密码是否为空
	private Date regCodeTime;//上一次发送验证码时间
	private Date retrievePwdSmsCodeTime;
	
	
	 
	public Date getRetrievePwdSmsCodeTime() {
		return retrievePwdSmsCodeTime;
	}
	public void setRetrievePwdSmsCodeTime(Date retrievePwdSmsCodeTime) {
		this.retrievePwdSmsCodeTime = retrievePwdSmsCodeTime;
	}
	public Date getRegCodeTime() {
		return regCodeTime;
	}
	public void setRegCodeTime(Date regCodeTime) {
		this.regCodeTime = regCodeTime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegisteredDto [userid=" + userid + ", password=" + password
				+ "]";
	}
	
}

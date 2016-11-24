package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class Carauthentication  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String carauthenticationId;
	private String email;
	private String xlicense;
	private String jlicence;
	private String carId;
	private String userid;
	private int carauthenticationState;//认证状态(0和null 为未认证中，1：认证成功 2：认证失败)
	private Date carauthenticationTime;
	public String getCarauthenticationId() {
		return carauthenticationId;
	}
	public void setCarauthenticationId(String carauthenticationId) {
		this.carauthenticationId = carauthenticationId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getXlicense() {
		return xlicense;
	}
	public void setXlicense(String xlicense) {
		this.xlicense = xlicense;
	}
	public String getJlicence() {
		return jlicence;
	}
	public void setJlicence(String jlicence) {
		this.jlicence = jlicence;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCarauthenticationState() {
		return carauthenticationState;
	}
	public void setCarauthenticationState(int carauthenticationState) {
		this.carauthenticationState = carauthenticationState;
	}
	public Date getCarauthenticationTime() {
		return carauthenticationTime;
	}
	public void setCarauthenticationTime(Date carauthenticationTime) {
		this.carauthenticationTime = carauthenticationTime;
	}
}

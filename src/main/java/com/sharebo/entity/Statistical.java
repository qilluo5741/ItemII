package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class Statistical implements Serializable {
	private static final long serialVersionUID = 1L;
	private String statisticalId;
	private String userid;
	private Date statisticalTime;
	public String getStatisticalId() {
		return statisticalId;
	}
	public void setStatisticalId(String statisticalId) {
		this.statisticalId = statisticalId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getStatisticalTime() {
		return statisticalTime;
	}
	public void setStatisticalTime(Date statisticalTime) {
		this.statisticalTime = statisticalTime;
	}
}

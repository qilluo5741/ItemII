package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class Hous implements Serializable{
	private static final long serialVersionUID = 1L;
	private String housId;
	private String housName;//'小区名字',
	private String housAddress;//'小区地址',
	private Date createTime;//'录入时间',
	private int isaudit;//是否审核(0和null为 未通过 1：通过)',
	private String gaodeIds;
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getHousName() {
		return housName;
	}
	public void setHousName(String housName) {
		this.housName = housName;
	}
	public String getHousAddress() {
		return housAddress;
	}
	public void setHousAddress(String housAddress) {
		this.housAddress = housAddress;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsaudit() {
		return isaudit;
	}
	public void setIsaudit(int isaudit) {
		this.isaudit = isaudit;
	}
	public String getGaodeIds() {
		return gaodeIds;
	}
	public void setGaodeIds(String gaodeIds) {
		this.gaodeIds = gaodeIds;
	}
}

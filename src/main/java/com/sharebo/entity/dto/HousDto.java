package com.sharebo.entity.dto;

import java.io.Serializable;

public class HousDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String housId;
	private String housName;//'小区名字',
	private String housAddress;//'小区地址',
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
}

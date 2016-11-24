package com.sharebo.entity.dto;

import java.io.Serializable;

public class PsonaividualDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String carportId;
	private String rentoutType;
	private String housName;
	private String housAddress;
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	public String getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(String rentoutType) {
		this.rentoutType = rentoutType;
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

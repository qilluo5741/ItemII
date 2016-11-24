package com.sharebo.entity.dto;

import java.io.Serializable;

public class MonthlyDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String monthId;
	private String parkname;
	private String parkAddress;
	private String appropriate;
	public String getAppropriate() {
		return appropriate;
	}
	public void setAppropriate(String appropriate) {
		this.appropriate = appropriate;
	}
	public String getMonthId() {
		return monthId;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	public String getParkname() {
		return parkname;
	}
	public void setParkname(String parkname) {
		this.parkname = parkname;
	}
	public String getParkAddress() {
		return parkAddress;
	}
	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
	}
}

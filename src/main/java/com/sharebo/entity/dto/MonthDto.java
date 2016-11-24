package com.sharebo.entity.dto;

import java.io.Serializable;
//°üÔÂÍ£³µ
public class MonthDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String monthId;
	private String parkname;
	private String parkAddress;
	private String parkprice;
	private String source;
	private String parktype;
	private int appropriate;
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
	public String getParkprice() {
		return parkprice;
	}
	public void setParkprice(String parkprice) {
		this.parkprice = parkprice;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getParktype() {
		return parktype;
	}
	public void setParktype(String parktype) {
		this.parktype = parktype;
	}
	public int getAppropriate() {
		return appropriate;
	}
	public void setAppropriate(int appropriate) {
		this.appropriate = appropriate;
	}
}

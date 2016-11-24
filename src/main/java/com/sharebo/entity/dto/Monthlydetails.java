package com.sharebo.entity.dto;

import java.io.Serializable;

public class Monthlydetails implements Serializable{
	private static final long serialVersionUID = 1L;
	private String monthId;
	private String parkname;
	private String parkAddress;
	private String entryaddress;
	private String parkprice;
	private String source;
	private String parktype;
	private String parktips;
	private String business;
	private int appropriate;
	private String contact;
	private String informa;
	private String parkimage;
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
	public String getEntryaddress() {
		return entryaddress;
	}
	public void setEntryaddress(String entryaddress) {
		this.entryaddress = entryaddress;
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
	public String getParktips() {
		return parktips;
	}
	public void setParktips(String parktips) {
		this.parktips = parktips;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public int getAppropriate() {
		return appropriate;
	}
	public void setAppropriate(int appropriate) {
		this.appropriate = appropriate;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getInforma() {
		return informa;
	}
	public void setInforma(String informa) {
		this.informa = informa;
	}
	public String getParkimage() {
		return parkimage;
	}
	public void setParkimage(String parkimage) {
		this.parkimage = parkimage;
	}
}

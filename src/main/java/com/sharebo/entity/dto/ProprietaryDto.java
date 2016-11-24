package com.sharebo.entity.dto;

import java.io.Serializable;

public class ProprietaryDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String carportId;
	private String housAddress;
	private String housName;
	private String housId;
	private String housType;
	private String detailedAddress;
	private String serialnumber;
	private String parkremark;
	private int rentoutType;
	private double money;
	private int feeType;
	private String contacts;
	private String information;
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	public String getHousAddress() {
		return housAddress;
	}
	public void setHousAddress(String housAddress) {
		this.housAddress = housAddress;
	}
	public String getHousName() {
		return housName;
	}
	public void setHousName(String housName) {
		this.housName = housName;
	}
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getParkremark() {
		return parkremark;
	}
	public void setParkremark(String parkremark) {
		this.parkremark = parkremark;
	}
	public int getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(int rentoutType) {
		this.rentoutType = rentoutType;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
	public String getHousType() {
		return housType;
	}
	public void setHousType(String housType) {
		this.housType = housType;
	}
}

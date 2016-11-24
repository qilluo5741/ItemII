package com.sharebo.entity.dto;

import java.io.Serializable;

public class ParkMonth implements Serializable{
	private static final long serialVersionUID = 1L;
	private String parkName;
	private String parkAddress;
	private String parkbusinessType;
	private String entryAddress;
	private int parkstate=2;
	private String parkType;
	private String feeModel;
	private String parkpicture;
	private String shouldstop;
	private String parkPrice;
	private String chargeType;
	private int parkSum=0;
	public int getParkSum() {
		return parkSum;
	}
	public void setParkSum(int parkSum) {
		this.parkSum = parkSum;
	}
	public String getShouldstop() {
		return shouldstop;
	}
	public void setShouldstop(String shouldstop) {
		this.shouldstop = shouldstop;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getParkAddress() {
		return parkAddress;
	}
	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
	}
	public String getParkbusinessType() {
		return parkbusinessType;
	}
	public void setParkbusinessType(String parkbusinessType) {
		this.parkbusinessType = parkbusinessType;
	}
	public String getEntryAddress() {
		return entryAddress;
	}
	public void setEntryAddress(String entryAddress) {
		this.entryAddress = entryAddress;
	}
	public int getParkstate() {
		return parkstate;
	}
	public void setParkstate(int parkstate) {
		this.parkstate = parkstate;
	}
	public String getParkType() {
		return parkType;
	}
	public void setParkType(String parkType) {
		this.parkType = parkType;
	}
	public String getFeeModel() {
		return feeModel;
	}
	public void setFeeModel(String feeModel) {
		this.feeModel = feeModel;
	}
	public String getParkpicture() {
		return parkpicture;
	}
	public void setParkpicture(String parkpicture) {
		this.parkpicture = parkpicture;
	}
	public String getParkPrice() {
		return parkPrice;
	}
	public void setParkPrice(String parkPrice) {
		this.parkPrice = parkPrice;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
}

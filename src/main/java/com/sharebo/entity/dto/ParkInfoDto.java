package com.sharebo.entity.dto;

import java.io.Serializable;

public class ParkInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String parkId;
	private String parkName;//'停车场/小区名字',
    private String parkAddress;//'停车场/小区地址',
    private int chargeType;//普通收费类型(0：按次收费   1:按小时收费)',
    private int parkstate;//'停车场状态(0:可预约 1：不可预约 )',
    private String entryAddress;//'入口地址',
    private String parkType;// '停车位类型(地面，室内，。。)',
    private String feeModel;//收费模式
    private double parkprice;//停车价格
    private String parkbusinessType;
    private String shouldstop;
    public Integer isCollection;//是否收藏(0: 为收藏  1:已收藏)
    public Integer getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
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
	public int getChargeType() {
		return chargeType;
	}
	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}
	public int getParkstate() {
		return parkstate;
	}
	public void setParkstate(int parkstate) {
		this.parkstate = parkstate;
	}
	public String getEntryAddress() {
		return entryAddress;
	}
	public void setEntryAddress(String entryAddress) {
		this.entryAddress = entryAddress;
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
	public double getParkprice() {
		return parkprice;
	}
	public void setParkprice(double parkprice) {
		this.parkprice = parkprice;
	}
	public String getParkbusinessType() {
		return parkbusinessType;
	}
	public void setParkbusinessType(String parkbusinessType) {
		this.parkbusinessType = parkbusinessType;
	}
	public String getShouldstop() {
		return shouldstop;
	}
	public void setShouldstop(String shouldstop) {
		this.shouldstop = shouldstop;
	}
}

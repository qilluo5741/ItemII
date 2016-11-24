package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class ParkingDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String parkId; 
	private String parkName;
	private String parkAddress;
	private String parkbusinessType;
	private String entryAddress;
	private Integer ispulic;
	private String beBegin;
	private String beEnd;
	private Integer parkSum;
	private String parkType;
	private Integer automaticOrder;
	private Double parkprice;
	private Integer chargeType;
	private Double reservationfee;
	private Double reservationfeedivide;
	private Double parkingisdivided;
	private Double cash;
	private Integer iswhether;
	private String gaodeId;
	private String partner;
	private String userid;
	private Date createtime;
	private Integer parkstate;
	private String feeModel;
	private String housId;
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
	public Integer getIspulic() {
		return ispulic;
	}
	public void setIspulic(Integer ispulic) {
		this.ispulic = ispulic;
	}
	public String getBeBegin() {
		return beBegin;
	}
	public void setBeBegin(String beBegin) {
		this.beBegin = beBegin;
	}
	public String getBeEnd() {
		return beEnd;
	}
	public void setBeEnd(String beEnd) {
		this.beEnd = beEnd;
	}
	public Integer getParkSum() {
		return parkSum;
	}
	public void setParkSum(Integer parkSum) {
		this.parkSum = parkSum;
	}
	public String getParkType() {
		return parkType;
	}
	public void setParkType(String parkType) {
		this.parkType = parkType;
	}
	public Integer getAutomaticOrder() {
		return automaticOrder;
	}
	public void setAutomaticOrder(Integer automaticOrder) {
		this.automaticOrder = automaticOrder;
	}
	public Double getParkprice() {
		return parkprice;
	}
	public void setParkprice(Double parkprice) {
		this.parkprice = parkprice;
	}
	public Integer getChargeType() {
		return chargeType;
	}
	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}
	public Double getReservationfee() {
		return reservationfee;
	}
	public void setReservationfee(Double reservationfee) {
		this.reservationfee = reservationfee;
	}
	public Double getReservationfeedivide() {
		return reservationfeedivide;
	}
	public void setReservationfeedivide(Double reservationfeedivide) {
		this.reservationfeedivide = reservationfeedivide;
	}
	public Double getParkingisdivided() {
		return parkingisdivided;
	}
	public void setParkingisdivided(Double parkingisdivided) {
		this.parkingisdivided = parkingisdivided;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Integer getIswhether() {
		return iswhether;
	}
	public void setIswhether(Integer iswhether) {
		this.iswhether = iswhether;
	}
	public String getGaodeId() {
		return gaodeId;
	}
	public void setGaodeId(String gaodeId) {
		this.gaodeId = gaodeId;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getParkstate() {
		return parkstate;
	}
	public void setParkstate(Integer parkstate) {
		this.parkstate = parkstate;
	}
	public String getFeeModel() {
		return feeModel;
	}
	public void setFeeModel(String feeModel) {
		this.feeModel = feeModel;
	}
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
}

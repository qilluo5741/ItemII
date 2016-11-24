package com.sharebo.entity.dto;

import java.io.Serializable;

/**
 * 公共停车场
 * @author Administrator
 *
 */
public class AltogetherDto  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String parkid;
	private String parkname;
	private String parkAddress;
	private double parkprice;
	private String cartype;
	private int chargeType;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getParkid() {
		return parkid;
	}
	public void setParkid(String parkid) {
		this.parkid = parkid;
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
	public double getParkprice() {
		return parkprice;
	}
	public void setParkprice(double parkprice) {
		this.parkprice = parkprice;
	}
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public int getChargeType() {
		return chargeType;
	}
	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}
}

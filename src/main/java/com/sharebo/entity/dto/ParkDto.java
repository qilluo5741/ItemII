package com.sharebo.entity.dto;

import java.io.Serializable;

public class ParkDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int chargeType;//普通收费类型(0：按次收费   1:按小时收费)',
	private int parkstate;//'停车场状态(0:可预约 1：不可预约 2:免预约 )',
	private double parkprice;//停车价格
	private String _distance;//距离
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
	public double getParkprice() {
		return parkprice;
	}
	public void setParkprice(double parkprice) {
		this.parkprice = parkprice;
	}
	public String get_distance() {
		return _distance;
	}
	public void set_distance(String _distance) {
		this._distance = _distance;
	}
}

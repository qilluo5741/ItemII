package com.sharebo.entity.dto;

import java.util.Arrays;

public class ParkDetailsDto {
	private String _name;
	private String _address;
	private String parkId;//停车场主键
	private String housId;//小区主键
	private String _distance;//距离
	private String[] _image;
	private Double money;//价格
	private String beginMonthTime;//开始时间
	private String endMonthTime;//结束时间
	private String carportId;//车位主键
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	public String getBeginMonthTime() {
		return beginMonthTime;
	}
	public void setBeginMonthTime(String beginMonthTime) {
		this.beginMonthTime = beginMonthTime;
	}
	public String getEndMonthTime() {
		return endMonthTime;
	}
	public void setEndMonthTime(String endMonthTime) {
		this.endMonthTime = endMonthTime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String[] get_image() {
		return _image;
	}
	public void set_image(String[] _image) {
		this._image = _image;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_address() {
		return _address;
	}
	public void set_address(String _address) {
		this._address = _address;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public String get_distance() {
		return _distance;
	}
	public void set_distance(String _distance) {
		this._distance = _distance;
	}
	@Override
	public String toString() {
		return "ParkDetailsDto [_name=" + _name + ", _address=" + _address + ", parkId=" + parkId + ", housId=" + housId
				+ ", _distance=" + _distance + ", _image=" + Arrays.toString(_image) + ", money=" + money
				+ ", beginMonthTime=" + beginMonthTime + ", endMonthTime=" + endMonthTime + ", carportId=" + carportId
				+ "]";
	}
	
}

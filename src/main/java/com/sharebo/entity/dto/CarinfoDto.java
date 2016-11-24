package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * ³µÁ¾±í
 * @author Administrator
 */
public class CarinfoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String carId;
	private String carNo;
	private String carType;
	private String carColor;
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
}

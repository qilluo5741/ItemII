package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * ³µÁ¾±í
 * @author Administrator
 */
public class CarDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String carId;
	private String carNo;
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
}

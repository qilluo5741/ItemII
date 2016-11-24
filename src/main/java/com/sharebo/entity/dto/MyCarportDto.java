package com.sharebo.entity.dto;

import java.io.Serializable;

public class MyCarportDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String carportId;//车位主键
	private String name;//用户姓名
    private String phone;//用户手机号码
    private int rentoutType;//出租类型(0:长租：1:临时出租)  如果为长租，计费为长租表 数据，临时出租j数据为星期规则和日期规则
    private int feeType;//收费类型(1:次2  小时：)
    private double money;//费用
    private int MyCarportState=1;//状态(绿色)
    public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	public int getMyCarportState() {
		return MyCarportState;
	}
	public void setMyCarportState(int myCarportState) {
		MyCarportState = myCarportState;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(int rentoutType) {
		this.rentoutType = rentoutType;
	}
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
}

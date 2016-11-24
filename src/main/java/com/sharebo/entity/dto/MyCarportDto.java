package com.sharebo.entity.dto;

import java.io.Serializable;

public class MyCarportDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String carportId;//��λ����
	private String name;//�û�����
    private String phone;//�û��ֻ�����
    private int rentoutType;//��������(0:���⣺1:��ʱ����)  ���Ϊ���⣬�Ʒ�Ϊ����� ���ݣ���ʱ����j����Ϊ���ڹ�������ڹ���
    private int feeType;//�շ�����(1:��2  Сʱ��)
    private double money;//����
    private int MyCarportState=1;//״̬(��ɫ)
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

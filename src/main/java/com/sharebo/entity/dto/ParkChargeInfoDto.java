package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class ParkChargeInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date beginTime;//��ʼ��ʱʱ��
	private Date endTime;//������ʱʱ��
	private String parkName;//ͣ��������
	private double practicalMoney;//ʵ�ռ۸�
	private int payState;//֧��״̬��0��δ֧�� 1���Ѿ�֧����
	private String payidentifying;//(֧����ʶ)
	public String getPayidentifying() {
		return payidentifying;
	}
	public void setPayidentifying(String payidentifying) {
		this.payidentifying = payidentifying;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public double getPracticalMoney() {
		return practicalMoney;
	}
	public void setPracticalMoney(double practicalMoney) {
		this.practicalMoney = practicalMoney;
	}
	public int getPayState() {
		return payState;
	}
	public void setPayState(int payState) {
		this.payState = payState;
	}
}

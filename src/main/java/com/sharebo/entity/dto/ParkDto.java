package com.sharebo.entity.dto;

import java.io.Serializable;

public class ParkDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int chargeType;//��ͨ�շ�����(0�������շ�   1:��Сʱ�շ�)',
	private int parkstate;//'ͣ����״̬(0:��ԤԼ 1������ԤԼ 2:��ԤԼ )',
	private double parkprice;//ͣ���۸�
	private String _distance;//����
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

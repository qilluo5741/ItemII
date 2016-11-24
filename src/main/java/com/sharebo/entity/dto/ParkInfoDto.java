package com.sharebo.entity.dto;

import java.io.Serializable;

public class ParkInfoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String parkId;
	private String parkName;//'ͣ����/С������',
    private String parkAddress;//'ͣ����/С����ַ',
    private int chargeType;//��ͨ�շ�����(0�������շ�   1:��Сʱ�շ�)',
    private int parkstate;//'ͣ����״̬(0:��ԤԼ 1������ԤԼ )',
    private String entryAddress;//'��ڵ�ַ',
    private String parkType;// 'ͣ��λ����(���棬���ڣ�����)',
    private String feeModel;//�շ�ģʽ
    private double parkprice;//ͣ���۸�
    private String parkbusinessType;
    private String shouldstop;
    public Integer isCollection;//�Ƿ��ղ�(0: Ϊ�ղ�  1:���ղ�)
    public Integer getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}
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
	public String getEntryAddress() {
		return entryAddress;
	}
	public void setEntryAddress(String entryAddress) {
		this.entryAddress = entryAddress;
	}
	public String getParkType() {
		return parkType;
	}
	public void setParkType(String parkType) {
		this.parkType = parkType;
	}
	public String getFeeModel() {
		return feeModel;
	}
	public void setFeeModel(String feeModel) {
		this.feeModel = feeModel;
	}
	public double getParkprice() {
		return parkprice;
	}
	public void setParkprice(double parkprice) {
		this.parkprice = parkprice;
	}
	public String getParkbusinessType() {
		return parkbusinessType;
	}
	public void setParkbusinessType(String parkbusinessType) {
		this.parkbusinessType = parkbusinessType;
	}
	public String getShouldstop() {
		return shouldstop;
	}
	public void setShouldstop(String shouldstop) {
		this.shouldstop = shouldstop;
	}
}

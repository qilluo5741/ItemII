package com.sharebo.entity;

import java.io.Serializable;

/**
 * ���˳�λ��Ϣ
 * @author niewei
 */
public class MyCarPort implements Serializable{
	private static final long serialVersionUID = 1L;
	private String carportId;//
	private String housId;// 'С�����(��ӦС��)',
	private String detailedAddress;// '�����ϸ��ַ',
	private String housType;// '��λ����(���棻���ڣ����׮)',
	private String serialnumber;// ��λ���
	private String parkremark;// ��λ˵��
	private Integer rentoutType;// '��������(0:���⣺1:��ʱ����) ���Ϊ���⣬�Ʒ�Ϊ�����/���ݣ���ʱ����j����Ϊ���ڹ�������ڹ���',
	private String userid;// �û����
	private Double money;// '���۷���',
	private Integer feeType;//�շ����͡� 1�������շ�  2:��Сʱ
	private String contacts;
	private String information;
	private String Invidualimg;
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getInvidualimg() {
		return Invidualimg;
	}
	public void setInvidualimg(String invidualimg) {
		Invidualimg = invidualimg;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public String getHousType() {
		return housType;
	}
	public void setHousType(String housType) {
		this.housType = housType;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getParkremark() {
		return parkremark;
	}
	public void setParkremark(String parkremark) {
		this.parkremark = parkremark;
	}
	public Integer getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(Integer rentoutType) {
		this.rentoutType = rentoutType;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "MyCarPort [carportId=" + carportId + ", housId=" + housId + ", detailedAddress=" + detailedAddress
				+ ", housType=" + housType + ", serialnumber=" + serialnumber + ", parkremark=" + parkremark
				+ ", rentoutType=" + rentoutType + ", userid=" + userid + ", money=" + money + ", feeType=" + feeType
				+ "]";
	}
	
}

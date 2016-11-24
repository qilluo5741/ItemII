package com.sharebo.entity;

import java.io.Serializable;

/**
 * ���˶�������
 * 
 * @author niewei
 *
 */
public class CarPortOrderDetailInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpodId;// ���˶�����������
	private String housId;// 'С�����(��ӦС��)',
	private String detailedAddress;// �����ϸ��ַ',
	private Integer rentoutType;// '��������(0:���⣺1:��ʱ����)
								// �������Գ�λ�� ���Ϊ���� ���ݶ�Ӧ����ʱ��� ��ʱ���� ���ݶ�Ӧ��ʱʱ���',
	private String housType;// '��λ����(���棻���ڣ����׮)',
	private String serialnumber;// '��λ���',
	private String parkremark;// 'ͣ��˵��',
	private OrderInfo order;// '�������',
	private Double money;// '���ⵥ�۷���',
	private Integer feeType;// '�շ�����(1:�����շ� 2��Сʱ�շ�)',
	private String carportId;// '��λ���',
	public String getCpodId() {
		return cpodId;
	}
	public void setCpodId(String cpodId) {
		this.cpodId = cpodId;
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
	public Integer getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(Integer rentoutType) {
		this.rentoutType = rentoutType;
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
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
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
	
}

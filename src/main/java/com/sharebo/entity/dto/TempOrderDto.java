package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * ��ʱ���������ѯ
 * @author Administrator
 *
 */
public class TempOrderDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String orderNum;//������
	private String orderId;//��������
	private Integer order_state;//����״̬(0:�ȴ��У�1:����� 2���Ѿܾ�)
	private Integer payType;//֧����ʽ��0��δ֧�� ��1��֧���� 2��΢�� 3:��
	private Double countMoney;//�ܷ���
	private String phone;//�����ֻ�����
	private String housName;//С������
	private String housAddress;//С����ַ
    private Date rentDate;//����
    private String beginTime;//��ʼʱ��
    private String endTime;//����ʱ��
	private Integer hour;//Сʱ
	private Double money;
	private int feeType;
	public Integer getHour() {
		String beginTimes=this.beginTime;
		String endTimes=this.endTime;
		Integer beginMin=Integer.valueOf(beginTimes.substring(3,5));
		Integer endMin=Integer.valueOf(endTimes.substring(3,5));
		Integer beginHour=Integer.valueOf(beginTimes.substring(0,2));
		Integer endHour=Integer.valueOf(endTimes.substring(0,2));
		if(beginHour==endHour||endMin>beginMin){
			hour=(endHour-beginHour)+1;
		}
		else{
		hour=endHour-beginHour;
		}
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Double getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(Double countMoney) {
		this.countMoney = countMoney;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHousName() {
		return housName;
	}
	public void setHousName(String housName) {
		this.housName = housName;
	}
	public Date getRentDate() {
		return rentDate;
	}
	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getHousAddress() {
		return housAddress;
	}
	public void setHousAddress(String housAddress) {
		this.housAddress = housAddress;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
}

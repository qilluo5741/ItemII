package com.sharebo.entity;

import java.util.Date;

public class OrderInfo {
	private String orderId;//��������
	private String orderNum;//��������
	private Integer orderType;//��������(0:����ͣ����1 ��˽�˳�λ��
	private Date placeorde_time=new Date();//�¶���ʱ��
	private Integer payType=0;//Ĭ��  δ֧��֧����ʽ��0��δ֧�� ��1��֧���� 2��΢�� 3:��
	private Integer order_state=0;//����״̬(0:�ȴ��У�1:����� 2���Ѿܾ�)
	private String carNo;//���ƺ�
	private String userid;
	private Double countMoney;//��Ҫ֧���Ľ��
	
	public Double getCountMoney() {
		return countMoney;
	}
	public void setCountMoney(Double countMoney) {
		this.countMoney = countMoney;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Date getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(Date placeorde_time) {
		this.placeorde_time = placeorde_time;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}

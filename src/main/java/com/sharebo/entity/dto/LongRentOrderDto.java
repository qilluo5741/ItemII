package com.sharebo.entity.dto;

import java.util.Date;

/**
 * ���ⶩ�������ѯ
 * @author Administrator
 *
 */
public class LongRentOrderDto {
	private String orderNum;//������
	private String orderId;//��������
	private Integer order_state;//����״̬(0:�ȴ��У�1:����� 2���Ѿܾ�)
	private Integer payType;//֧����ʽ��0��δ֧�� ��1��֧���� 2��΢�� 3:��
	private Double countMoney;//�ܷ���
	private String phone;//�����ֻ�����
	private String housName;//С������
	private String housAddress;//С����ַ
	private Integer timetype;//����ʱ������(0��ȫ����⣬1���ο���ʱ��� ) ���Ϊȫ����� �Ͳ��տɳ����¿�ʼ����ʱ�䣬���෴
	private String enddayTime;//������������ʱ�䣨һ���У�ʲôʱ�������
	private String begindayTime;//��������쿪ʼʱ�䣨һ����ʲôʱ��ʼ���ڳ���ʱ�������Ϊ��ȫ�������ã�
	private Date endmonthTime;//��������½���ʱ��
	private Date beginmonthTime;//��������¿�ʼʱ��
	public String getEnddayTime() {
		return enddayTime;
	}
	public void setEnddayTime(String enddayTime) {
		this.enddayTime = enddayTime;
	}
	public String getBegindayTime() {
		return begindayTime;
	}
	public void setBegindayTime(String begindayTime) {
		this.begindayTime = begindayTime;
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
	public Integer getTimetype() {
		return timetype;
	}
	public void setTimetype(Integer timetype) {
		this.timetype = timetype;
	}
	public Date getEndmonthTime() {
		return endmonthTime;
	}
	public void setEndmonthTime(Date endmonthTime) {
		this.endmonthTime = endmonthTime;
	}
	public Date getBeginmonthTime() {
		return beginmonthTime;
	}
	public void setBeginmonthTime(Date beginmonthTime) {
		this.beginmonthTime = beginmonthTime;
	}
	public String getHousAddress() {
		return housAddress;
	}
	public void setHousAddress(String housAddress) {
		this.housAddress = housAddress;
	}
}

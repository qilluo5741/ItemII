package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * �������ⶩ����ѯ
 */
public class LongRentDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;//��������
	private String orderNum;//������
	private String phone;//�û��ֻ�����
	private String carNo;//���ƺ�
	private int order_state;//����״̬(0:�ȴ��У�1:����� 2���Ѿܾ�)
	private int timetype;//����ʱ������(0��ȫ����⣬1���ο���ʱ��� ) ���Ϊȫ����� �Ͳ��տɳ����¿�ʼ����ʱ�䣬���෴
	private String begindayTime;//��������쿪ʼʱ�䣨һ����ʲôʱ��ʼ���ڳ���ʱ�������Ϊ��ȫ�������ã�
	private String enddayTime;//������������ʱ�䣨һ���У�ʲôʱ�������
	private Date beginmonthTime;//��������¿�ʼʱ��
	private Date endmonthTime;//��������½���ʱ��
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}
	public int getTimetype() {
		return timetype;
	}
	public void setTimetype(int timetype) {
		this.timetype = timetype;
	}
	public String getBegindayTime() {
		return begindayTime;
	}
	public void setBegindayTime(String begindayTime) {
		this.begindayTime = begindayTime;
	}
	public String getEnddayTime() {
		return enddayTime;
	}
	public void setEnddayTime(String enddayTime) {
		this.enddayTime = enddayTime;
	}
	public Date getBeginmonthTime() {
		return beginmonthTime;
	}
	public void setBeginmonthTime(Date beginmonthTime) {
		this.beginmonthTime = beginmonthTime;
	}
	public Date getEndmonthTime() {
		return endmonthTime;
	}
	public void setEndmonthTime(Date endmonthTime) {
		this.endmonthTime = endmonthTime;
	}
}

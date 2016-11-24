package com.sharebo.entity.dto;
/**
 * ������ʱ���ⶩ��
 */
import java.io.Serializable;
import java.util.Date;

public class TemporaryRentDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;//��������
	private String orderNum;//������
	private String phone;//�û��ֻ�����
	private String carNo;//���ƺ�
	private int order_state;//����״̬(0:�ȴ��У�1:����� 2���Ѿܾ�)
	private Date rentDate;//����
	private String beginTime;//��ʼʱ��
	private String endTime;//����ʱ��
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
}

package com.sharebo.entity.dto;
/**
 * רλ������¼��ѯ
 */
import java.util.Date;

public class CarportOrderInfo {
	private String housName;//С������
	private Integer payType;//֧����ʽ��0��δ֧�� ��1��֧���� 2��΢�� 3:��
	private Double countMoney;//�ϼƷ���
	private Date placeorde_time;//�¶���ʱ��
	private Integer rentoutType;//��������(0:���⣺1:��ʱ����)  �������Գ�λ��  ���Ϊ����  ���ݶ�Ӧ����ʱ���  ��ʱ���� ���ݶ�Ӧ��ʱʱ���
	private String orderNum;//������
	private String orderId;//��������
	private Integer order_state;//����״̬(0:�ȴ��У�1:����� 2���Ѿܾ�)
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getHousName() {
		return housName;
	}
	public void setHousName(String housName) {
		this.housName = housName;
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
	public Date getPlaceorde_time() {
		return placeorde_time;
	}
	public void setPlaceorde_time(Date placeorde_time) {
		this.placeorde_time = placeorde_time;
	}
	public Integer getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(Integer rentoutType) {
		this.rentoutType = rentoutType;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}

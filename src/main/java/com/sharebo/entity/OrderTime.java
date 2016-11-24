package com.sharebo.entity;
import java.io.Serializable;
import java.util.Date;
//����ʱ�������Ϊ��λ���� һ��������һ���ʱ��Ρ�����Ӧһ�������Ƕ��챾�����ݣ�
public class OrderTime  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String otId;//'����ʱ�������',
	private String begin_time;//��ʼʱ��
	private String end_time;//����ʱ��
	private Date thisDate;//'��ǰ����',
	private String orderid;//'����������������ݶ�Ӧ�����ĸ�������',
	public String getOtId() {
		return otId;
	}
	public void setOtId(String otId) {
		this.otId = otId;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String beginTime) {
		begin_time = beginTime;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String endTime) {
		end_time = endTime;
	}
	public Date getThisDate() {
		return thisDate;
	}
	public void setThisDate(Date thisDate) {
		this.thisDate = thisDate;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public OrderTime(String beginTime, String endTime, Date thisDate) {
		this.begin_time = beginTime;
		this.end_time = endTime;
		this.thisDate = thisDate;
	}
	public OrderTime() {
	}
}

package com.sharebo.entity.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ͣ��������
 * @author Administrator
 *
 */
public class Parkcooperation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pcId;
	private String housId;//С��
	private String contactName;//��ϵ������
	private String phone;//��ϵ�˺���
	private String entrance;//���
	private String type;//ͣ��������
	private Double money;//�շ�
	private String  beginTime;//��ʼ��Ӫʱ��
	private String  endTime;//������Ӫʱ��
	private Integer cooperationintention;//��������(1:������ߣ�2��������ҵ 3����������)
	private Integer feeType;//�շ�ģʽ(1:�����շ�  2:��Сʱ�շ�)
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
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
	public Integer getCooperationintention() {
		return cooperationintention;
	}
	public void setCooperationintention(Integer cooperationintention) {
		this.cooperationintention = cooperationintention;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public static void main(String[] args) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String time=dateFormat.format(now);
		System.out.println(time);
	}
}

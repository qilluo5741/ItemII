package com.sharebo.entity;

import java.util.Date;

/**
 * ���˳�λ���ⶩ������
 * 
 * @author niewei
 *
 */
public class LeaseOrderDetailInfo {

	private String lodId;// ���˳�λ���ⶩ����������
	private Integer timetype;// '����ʱ������(0��ȫ����⣬1���ο���ʱ��� ) ���Ϊȫ�����
							// �Ͳ��տɳ����¿�ʼ����ʱ�䣬���෴',
	private String enddayTime;// '������������ʱ�䣨һ���У�ʲôʱ�������',
	private String begindayTime;// '��������쿪ʼʱ�䣨һ����ʲôʱ��ʼ���ڳ���ʱ�������Ϊ��ȫ�������ã�',
	private Date endmonthTime;// '��������½���ʱ��',
	private Date beginmonthTime;// '��������¿�ʼʱ��',
	private CarPortOrderDetailInfo cpod;// '���˶������',
	public String getLodId() {
		return lodId;
	}
	public void setLodId(String lodId) {
		this.lodId = lodId;
	}
	public Integer getTimetype() {
		return timetype;
	}
	public void setTimetype(Integer timetype) {
		this.timetype = timetype;
	}
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
	public CarPortOrderDetailInfo getCpod() {
		return cpod;
	}
	public void setCpod(CarPortOrderDetailInfo cpod) {
		this.cpod = cpod;
	}
	
}

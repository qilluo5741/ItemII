package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class Carcollection implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ccId;
	private int carType;//'��λ����(1:����ͣ���� 2:���˳�λ )'
	private String userid;// '�û����(˭�ղص�)'
	private String identifying;//'�ղر�ʶ��һ���복λ����ƥ�䣬���磺��λ����Ϊ����ͣ��������ô�����ݾ�Ϊ����ͣ��������������˽��ͣ��λ���Ǿ���˽��ͣ��λ�ĳ�λ������',
	private Date collectTime;
	private int isDelete;//'�Ƿ���Ч(0��null  ������Ч  1������ʧЧ)'
	public String getCcId() {
		return ccId;
	}
	public void setCcId(String ccId) {
		this.ccId = ccId;
	}
	public int getCarType() {
		return carType;
	}
	public void setCarType(int carType) {
		this.carType = carType;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getIdentifying() {
		return identifying;
	}
	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}

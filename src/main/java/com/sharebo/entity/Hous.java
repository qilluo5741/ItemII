package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class Hous implements Serializable{
	private static final long serialVersionUID = 1L;
	private String housId;
	private String housName;//'С������',
	private String housAddress;//'С����ַ',
	private Date createTime;//'¼��ʱ��',
	private int isaudit;//�Ƿ����(0��nullΪ δͨ�� 1��ͨ��)',
	private String gaodeIds;
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getHousName() {
		return housName;
	}
	public void setHousName(String housName) {
		this.housName = housName;
	}
	public String getHousAddress() {
		return housAddress;
	}
	public void setHousAddress(String housAddress) {
		this.housAddress = housAddress;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsaudit() {
		return isaudit;
	}
	public void setIsaudit(int isaudit) {
		this.isaudit = isaudit;
	}
	public String getGaodeIds() {
		return gaodeIds;
	}
	public void setGaodeIds(String gaodeIds) {
		this.gaodeIds = gaodeIds;
	}
}

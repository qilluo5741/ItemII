package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ����
 * @author Administrator
 *
 */
public class Advertis implements Serializable {
	private static final long serialVersionUID = 1L;
	private String advertisId; 
	private String advertisName;//'���ͼƬ����',
	private String advertisAddress;//'���ͼƬ��ַ',
	private String advertisEvent;//'���ͼƬ�¼�',
	private Date advertisAddTime;
	private String opinion;
	private int isdelete; // '�Ƿ�ɾ����0:δɾ��  1���Ѿ�ɾ����',
	public String getAdvertisId() {
		return advertisId;
	}
	public void setAdvertisId(String advertisId) {
		this.advertisId = advertisId;
	}
	public String getAdvertisName() {
		return advertisName;
	}
	public void setAdvertisName(String advertisName) {
		this.advertisName = advertisName;
	}
	public String getAdvertisAddress() {
		return advertisAddress;
	}
	public void setAdvertisAddress(String advertisAddress) {
		this.advertisAddress = advertisAddress;
	}
	public String getAdvertisEvent() {
		return advertisEvent;
	}
	public void setAdvertisEvent(String advertisEvent) {
		this.advertisEvent = advertisEvent;
	}
	public Date getAdvertisAddTime() {
		return advertisAddTime;
	}
	public void setAdvertisAddTime(Date advertisAddTime) {
		this.advertisAddTime = advertisAddTime;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
}

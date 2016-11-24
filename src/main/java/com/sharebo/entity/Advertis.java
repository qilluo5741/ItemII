package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告表
 * @author Administrator
 *
 */
public class Advertis implements Serializable {
	private static final long serialVersionUID = 1L;
	private String advertisId; 
	private String advertisName;//'广告图片名称',
	private String advertisAddress;//'广告图片地址',
	private String advertisEvent;//'广告图片事件',
	private Date advertisAddTime;
	private String opinion;
	private int isdelete; // '是否删除（0:未删除  1：已经删除）',
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

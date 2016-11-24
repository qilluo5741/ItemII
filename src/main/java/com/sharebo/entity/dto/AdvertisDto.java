package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * 广告表
 * @author Administrator
 *
 */
public class AdvertisDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String advertisId; 
	private String advertisAddress;//'广告图片地址',
	public String getAdvertisId() {
		return advertisId;
	}
	public void setAdvertisId(String advertisId) {
		this.advertisId = advertisId;
	}
	public String getAdvertisAddress() {
		return advertisAddress;
	}
	public void setAdvertisAddress(String advertisAddress) {
		this.advertisAddress = advertisAddress;
	}
}

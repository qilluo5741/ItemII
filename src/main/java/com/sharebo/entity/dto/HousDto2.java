package com.sharebo.entity.dto;

import java.io.Serializable;

public class HousDto2 implements Serializable{
	private static final long serialVersionUID = 1L;
	private String housId;
	private String gaodeIds;
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getGaodeIds() {
		return gaodeIds;
	}
	public void setGaodeIds(String gaodeIds) {
		this.gaodeIds = gaodeIds;
	}
	
}

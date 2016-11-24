package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

public class Carcollection implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ccId;
	private int carType;//'车位类型(1:公共停车场 2:个人车位 )'
	private String userid;// '用户外键(谁收藏的)'
	private String identifying;//'收藏标识（一般与车位类型匹配，比如：车位类型为公共停车场，那么该数据就为公共停车场的主键，是私人停车位。那就是私人停车位的车位主键）',
	private Date collectTime;
	private int isDelete;//'是否有效(0和null  代表有效  1：代表失效)'
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

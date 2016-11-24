package com.sharebo.entity;

import java.io.Serializable;

/**
 * 图片版本控制表
 * @author Administrator
 *
 */
public class Iconversion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int vid;//'只有1 2',
	private int iconNo;// '图标版本',
	private int pno;// '广告版本',
	private int carNo;//车辆信息版本控制
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public int getIconNo() {
		return iconNo;
	}
	public void setIconNo(int iconNo) {
		this.iconNo = iconNo;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getCarNo() {
		return carNo;
	}
	public void setCarNo(int carNo) {
		this.carNo = carNo;
	}
}

package com.sharebo.entity;

import java.io.Serializable;

/**
 * ͼƬ�汾���Ʊ�
 * @author Administrator
 *
 */
public class Iconversion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int vid;//'ֻ��1 2',
	private int iconNo;// 'ͼ��汾',
	private int pno;// '���汾',
	private int carNo;//������Ϣ�汾����
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

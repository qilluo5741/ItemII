package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ͼ���
 * @author Administrator
 *
 */
public class Icon implements Serializable {
	private static final long serialVersionUID = 1L;
	private String iconId;// 'ͼ������',
	private String iconName;// 'ͼ������',
	private String iconUrl;// '����ͼ�������',
	private String iconLogo;// 'ͼ���ʶ(����ǰ�����ж���ĳ������ƥ��)',
	private Date iconAddTime;// 'ͼ�����ʱ��',
	private int isdelete; //  int(1) comment '�Ƿ�ɾ����0��δɾ��  1:�Ѿ�ɾ����'
	public String getIconId() {
		return iconId;
	}
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getIconLogo() {
		return iconLogo;
	}
	public void setIconLogo(String iconLogo) {
		this.iconLogo = iconLogo;
	}
	public Date getIconAddTime() {
		return iconAddTime;
	}
	public void setIconAddTime(Date iconAddTime) {
		this.iconAddTime = iconAddTime;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
}

package com.sharebo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 图标表
 * @author Administrator
 *
 */
public class Icon implements Serializable {
	private static final long serialVersionUID = 1L;
	private String iconId;// '图标主键',
	private String iconName;// '图标名字',
	private String iconUrl;// '访问图标的链接',
	private String iconLogo;// '图标标识(用于前端来判断与某个功能匹配)',
	private Date iconAddTime;// '图标添加时间',
	private int isdelete; //  int(1) comment '是否删除（0：未删除  1:已经删除）'
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

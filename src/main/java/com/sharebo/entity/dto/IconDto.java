package com.sharebo.entity.dto;

import java.io.Serializable;

public class IconDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String iconId;
	private String iconName;
	private String iconUrl;
	private String iconLogo;
	private String iconMark;
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
	public String getIconMark() {
		return iconMark;
	}
	public void setIconMark(String iconMark) {
		this.iconMark = iconMark;
	}
}

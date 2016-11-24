package com.sharebo.entity;

import java.io.Serializable;
/**
 * APP版本控制表
 * @author Administrator
 *
 */
public class AppVersion implements Serializable {
	private static final long serialVersionUID = 1L;
	private String avId;//'app版本控制id',
	private String versionNo;//'版本号',
	private int versionTimes;//'版本次数',
	private String url;//'下载链接',
	private String releasetime;
	public String getAvId() {
		return avId;
	}
	public void setAvId(String avId) {
		this.avId = avId;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public int getVersionTimes() {
		return versionTimes;
	}
	public void setVersionTimes(int versionTimes) {
		this.versionTimes = versionTimes;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
}

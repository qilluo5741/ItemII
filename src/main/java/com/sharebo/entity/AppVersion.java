package com.sharebo.entity;

import java.io.Serializable;
/**
 * APP�汾���Ʊ�
 * @author Administrator
 *
 */
public class AppVersion implements Serializable {
	private static final long serialVersionUID = 1L;
	private String avId;//'app�汾����id',
	private String versionNo;//'�汾��',
	private int versionTimes;//'�汾����',
	private String url;//'��������',
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

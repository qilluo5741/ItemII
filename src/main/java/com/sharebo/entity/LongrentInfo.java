package com.sharebo.entity;

import java.io.Serializable;
/**
 * 长租信息
 * 
 * @author niewei
 *
 */
public class LongrentInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String longrentId;//
	private Integer timetype;// 长租时间类型(0：全天出租，1：参考天时间段 ) 如果为全天出租//就参照可出租月开始结束时间，其相反',
	private String beginmonthTime;// 时间戳 只截取年月日 '可租月开始时间（年月日）',
	private String endmonthTime;// 时间戳 只截取年月日 '可租结束时间（年月日）',
	private String begindayTime;// 只要时分秒 '可租天开始时间',
	private String enddayTime;// 只要时分秒 ''可租天结束时间',
	private String thecontact;
	private String contact;
	private String personalphoto;
	private MyCarPort carport;
	public String getThecontact() {
		return thecontact;
	}
	public void setThecontact(String thecontact) {
		this.thecontact = thecontact;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPersonalphoto() {
		return personalphoto;
	}
	public void setPersonalphoto(String personalphoto) {
		this.personalphoto = personalphoto;
	}
	public String getLongrentId() {
		return longrentId;
	}
	public void setLongrentId(String longrentId) {
		this.longrentId = longrentId;
	}
	public Integer getTimetype() {
		return timetype;
	}
	public void setTimetype(Integer timetype) {
		this.timetype = timetype;
	}
	
	public String getBeginmonthTime() {
		return beginmonthTime;
	}
	public void setBeginmonthTime(String beginmonthTime) {
		this.beginmonthTime = beginmonthTime;
	}
	public String getEndmonthTime() {
		return endmonthTime;
	}
	public void setEndmonthTime(String endmonthTime) {
		this.endmonthTime = endmonthTime;
	}
	public String getBegindayTime() {
		return begindayTime;
	}
	public void setBegindayTime(String begindayTime) {
		this.begindayTime = begindayTime;
	}
	public String getEnddayTime() {
		return enddayTime;
	}
	public void setEnddayTime(String enddayTime) {
		this.enddayTime = enddayTime;
	}
	public MyCarPort getCarport() {
		return carport;
	}
	public void setCarport(MyCarPort carport) {
		this.carport = carport;
	}
/*	public String getThecontact() {
		return thecontact;
	}
	public void setThecontact(String thecontact) {
		this.thecontact = thecontact;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPersonalphoto() {
		return personalphoto;
	}
	public void setPersonalphoto(String personalphoto) {
		this.personalphoto = personalphoto;
	}*/
}

package com.sharebo.entity;

import java.io.Serializable;
/**
 * ������Ϣ
 * 
 * @author niewei
 *
 */
public class LongrentInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String longrentId;//
	private Integer timetype;// ����ʱ������(0��ȫ����⣬1���ο���ʱ��� ) ���Ϊȫ�����//�Ͳ��տɳ����¿�ʼ����ʱ�䣬���෴',
	private String beginmonthTime;// ʱ��� ֻ��ȡ������ '�����¿�ʼʱ�䣨�����գ�',
	private String endmonthTime;// ʱ��� ֻ��ȡ������ '�������ʱ�䣨�����գ�',
	private String begindayTime;// ֻҪʱ���� '�����쿪ʼʱ��',
	private String enddayTime;// ֻҪʱ���� ''���������ʱ��',
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

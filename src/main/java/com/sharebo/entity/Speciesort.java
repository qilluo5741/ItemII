package com.sharebo.entity;

import java.io.Serializable;
/**
 * ������
 * @author Administrator
 */
public class Speciesort implements Serializable {
	private static final long serialVersionUID = 1L;
	private String specieid;
	private String speciename;//'��������',
	private String specietype;//'��������'
	public String getSpecieid() {
		return specieid;
	}
	public void setSpecieid(String specieid) {
		this.specieid = specieid;
	}
	public String getSpeciename() {
		return speciename;
	}
	public void setSpeciename(String speciename) {
		this.speciename = speciename;
	}
	public String getSpecietype() {
		return specietype;
	}
	public void setSpecietype(String specietype) {
		this.specietype = specietype;
	}
}

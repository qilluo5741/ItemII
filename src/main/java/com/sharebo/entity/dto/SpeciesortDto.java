package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.List;

public class SpeciesortDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String specieid;
	private String speciename;//'��������',
	private String specietype;//'��������'
	private List<Patterntype> ptype;
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
	public List<Patterntype> getPtype() {
		return ptype;
	}
	public void setPtype(List<Patterntype> ptype) {
		this.ptype = ptype;
	}
	
	

}

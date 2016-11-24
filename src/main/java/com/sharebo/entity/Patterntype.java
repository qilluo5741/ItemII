package com.sharebo.entity;

import java.io.Serializable;
/**
 * ³µÀà±ğ
 * @author Administrator
 */
public class Patterntype implements Serializable {
	private static final long serialVersionUID = 1L;
	private String  patternid;
	private String  patternname;
	private Speciesort specie;
	public String getPatternid() {
		return patternid;
	}
	public void setPatternid(String patternid) {
		this.patternid = patternid;
	}
	public String getPatternname() {
		return patternname;
	}
	public void setPatternname(String patternname) {
		this.patternname = patternname;
	}
	public Speciesort getSpecie() {
		return specie;
	}
	public void setSpecie(Speciesort specie) {
		this.specie = specie;
	}
}

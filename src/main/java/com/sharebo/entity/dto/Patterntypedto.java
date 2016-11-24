package com.sharebo.entity.dto;

import java.io.Serializable;

public class Patterntypedto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String  patternid;
	private String  patternname;
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
}

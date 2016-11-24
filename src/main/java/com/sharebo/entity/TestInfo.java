package com.sharebo.entity;

import java.io.Serializable;

public class TestInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String gender;
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	

}

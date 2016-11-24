package com.sharebo.entity.dto;

import java.io.Serializable;

public class Userheadportrait implements Serializable {
	private static final long serialVersionUID = 1L;
	private String headportrait;
	private String name;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}

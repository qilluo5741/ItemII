package com.sharebo.entity;

import java.io.Serializable;

public class Startlaunch  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String startlaid;
	private int startsize;
	private String starturl;
	public String getStartlaid() {
		return startlaid;
	}
	public void setStartlaid(String startlaid) {
		this.startlaid = startlaid;
	}
	public int getStartsize() {
		return startsize;
	}
	public void setStartsize(int startsize) {
		this.startsize = startsize;
	}
	public String getStarturl() {
		return starturl;
	}
	public void setStarturl(String starturl) {
		this.starturl = starturl;
	}
}

package com.sharebo.entity;
import java.io.Serializable;
import java.util.List;

public class ResMonth  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int year;//当前年
	private int month;//当前月
	private List<ResDate> rd;
	
	public List<ResDate> getRd() {
		return rd;
	}
	public void setRd(List<ResDate> rd) {
		this.rd = rd;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
}

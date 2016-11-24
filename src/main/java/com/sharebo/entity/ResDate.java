package com.sharebo.entity;
import java.io.Serializable;
public class ResDate  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int day;//日
	private boolean isdisable;//是否禁用当天
	private boolean iste;//时间是否有效
	
	public boolean isIste() {
		return iste;
	}
	public void setIste(boolean iste) {
		this.iste = iste;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public boolean isIsdisable() {
		return isdisable;
	}
	public void setIsdisable(boolean isdisable) {
		this.isdisable = isdisable;
	}
	public ResDate() {
	}
	public ResDate(int day, boolean isdisable, boolean iste) {
		this.day = day;
		this.isdisable = isdisable;
		this.iste = iste;
	}
	
}

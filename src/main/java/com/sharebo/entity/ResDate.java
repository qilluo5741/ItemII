package com.sharebo.entity;
import java.io.Serializable;
public class ResDate  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int day;//��
	private boolean isdisable;//�Ƿ���õ���
	private boolean iste;//ʱ���Ƿ���Ч
	
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

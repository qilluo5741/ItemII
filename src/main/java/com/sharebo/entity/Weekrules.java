package com.sharebo.entity;

/**
 * 星期规则表
 * @author niewei
 *
 */
public class Weekrules implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  weekid;
	private String  monday_begin;//'星期一（开始）',
	private String  monday_end;//'星期一（结束）',
	private String  tuesday_begin;//'星期二（开始）',
	private String  tuesday_end;// '星期二（结束）',
	private String  wednesday_begin;//'星期三（开始）',
	private String  wednesday_end;//'星期三（结束）',
	private String  thursday_begin;//'星期四（开始）',
	private String  thursday_end;//'星期四（结束）',
	private String  friday_begin;//'星期五（开始）',
	private String  friday_end;//'星期五（结束）',
	private String  saturday_begin;//'星期六（开始）',
	private String  saturday_end;// '星期六（结束）',
	private String  sunday_begin;//'星期日（开始）',
	private String  sunday_end;//'星期日（结束）',
	public String getWeekid(){
		return weekid;
	}


	public void setWeekid(String weekid) {
		this.weekid = weekid;
	}


	public String getMonday_begin() {
		return monday_begin;
	}


	public void setMonday_begin(String mondayBegin) {
		monday_begin = mondayBegin;
	}


	public String getMonday_end() {
		return monday_end;
	}


	public void setMonday_end(String mondayEnd) {
		monday_end = mondayEnd;
	}


	public String getTuesday_begin() {
		return tuesday_begin;
	}


	public void setTuesday_begin(String tuesdayBegin) {
		tuesday_begin = tuesdayBegin;
	}


	public String getTuesday_end() {
		return tuesday_end;
	}


	public void setTuesday_end(String tuesdayEnd) {
		tuesday_end = tuesdayEnd;
	}


	public String getWednesday_begin() {
		return wednesday_begin;
	}


	public void setWednesday_begin(String wednesdayBegin) {
		wednesday_begin = wednesdayBegin;
	}


	public String getWednesday_end() {
		return wednesday_end;
	}


	public void setWednesday_end(String wednesdayEnd) {
		wednesday_end = wednesdayEnd;
	}


	public String getThursday_begin() {
		return thursday_begin;
	}


	public void setThursday_begin(String thursdayBegin) {
		thursday_begin = thursdayBegin;
	}


	public String getThursday_end() {
		return thursday_end;
	}


	public void setThursday_end(String thursdayEnd) {
		thursday_end = thursdayEnd;
	}


	public String getFriday_begin() {
		return friday_begin;
	}


	public void setFriday_begin(String fridayBegin) {
		friday_begin = fridayBegin;
	}


	public String getFriday_end() {
		return friday_end;
	}


	public void setFriday_end(String fridayEnd) {
		friday_end = fridayEnd;
	}


	public String getSaturday_begin() {
		return saturday_begin;
	}


	public void setSaturday_begin(String saturdayBegin) {
		saturday_begin = saturdayBegin;
	}


	public String getSaturday_end() {
		return saturday_end;
	}


	public void setSaturday_end(String saturdayEnd) {
		saturday_end = saturdayEnd;
	}


	public String getSunday_begin() {
		return sunday_begin;
	}


	public void setSunday_begin(String sundayBegin) {
		sunday_begin = sundayBegin;
	}


	public String getSunday_end() {
		return sunday_end;
	}


	public void setSunday_end(String sundayEnd) {
		sunday_end = sundayEnd;
	}


	@Override
	public String toString() {
		return "Timerules [friday_begin=" + friday_begin + ", friday_end="
				+ friday_end + ", monday_begin=" + monday_begin
				+ ", monday_end=" + monday_end + ", saturday_begin="
				+ saturday_begin + ", saturday_end=" + saturday_end
				+ ", sunday_begin=" + sunday_begin + ", sunday_end="
				+ sunday_end + ", thursday_begin=" + thursday_begin
				+ ", thursday_end=" + thursday_end + ", tuesday_begin="
				+ tuesday_begin + ", tuesday_end=" + tuesday_end
				+ ", wednesday_begin=" + wednesday_begin + ", wednesday_end="
				+ wednesday_end + "]";
	}
	
}

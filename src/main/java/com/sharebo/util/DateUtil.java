package com.sharebo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * @author niewei
 *
 */
public class DateUtil {
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getDate(){
		return new Date();
	}
	/**
	 * 传入时间格式返回对象格式数据
	 * @param str
	 * @return
	 */
	public static String getDate(String str){
		SimpleDateFormat s=new SimpleDateFormat(str);
		return s.format(getDate());
	}
	/***
	 * //传入的时间返回相应的串
	 * @param dateString当前格式
	 * @param todateString被转成后的格式
	 * @param date与格式对应的时间
	 * @return
	 */
	public String getDate(String dateString,String todateString,String date){
		SimpleDateFormat s2=null;
		SimpleDateFormat s=null;
		String res="";
		try {
			s2 = null;
			s = new SimpleDateFormat(dateString);
			s.parse(date);
			s2 = new SimpleDateFormat(todateString);
			res=s2.format(s.parse(date));
		} catch (ParseException e) {
			return null;
		}
		return res;
	}
	
}

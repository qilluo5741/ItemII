package com.sharebo.util;


import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;

import com.sharebo.config.SmsConfig;
/**
 * 短信工具类
 * @author niewei
 *
 */
public class SmsUtil {
	
	public static void sendMessage(String mobile,String Plate){
	try 
		  { 
			IOUtils.toString(
			new URL(SmsConfig.getValueMap().get("smsHuaxin.url")+ "&mobile=" + 
	        mobile + "&content=" + 
	        URLEncoder.encode(SmsConfig.getValueMap().get("smsHuaxin.content_1").replace("@",Plate), "utf-8"))
	        .openConnection().getInputStream(), "utf-8");
	    }catch (Exception e){
	      throw new RuntimeException("Sms Send Error Caused.", e);
	    }
	}
	public static void sendUserMessage(String mobile,String authCode){
		try 
			  { 
			  IOUtils.toString(
				new URL(SmsConfig.getValueMap().get("smsHuaxin.url")+ "&mobile=" + 
		        mobile + "&content=" + 
		        URLEncoder.encode(SmsConfig.getValueMap().get("smsHuaxin.content_2").replace("@",authCode), "utf-8"))
		        .openConnection().getInputStream(), "utf-8");
		    }catch (Exception e){
		      throw new RuntimeException("Sms Send Error Caused.", e);
		    }
	}
	public static void sendisMessage(String mobile,String authCode){
		try 
		  { 
			IOUtils.toString(
			new URL(SmsConfig.getValueMap().get("smsHuaxin.url") + "&mobile=" + 
	        mobile + "&content=" + 
	        URLEncoder.encode(SmsConfig.getValueMap().get("smsHuaxin.content_3") .replace("@",authCode), "utf-8"))
	        .openConnection().getInputStream(), "utf-8");
	    }catch (Exception e){
	      throw new RuntimeException("Sms Send Error Caused.", e);
	    }
	}
	public static void sendchargeMessage(String mobile,String Plate){
		try 
		  { 
			IOUtils.toString(
			new URL(SmsConfig.getValueMap().get("smsHuaxin.url")  + "&mobile=" + 
	        mobile + "&content=" + 
	        URLEncoder.encode(SmsConfig.getValueMap().get("smsHuaxin.content_4").replace("@",Plate), "utf-8"))
	        .openConnection().getInputStream(), "utf-8");
	    }catch (Exception e){
	      throw new RuntimeException("Sms Send Error Caused.", e);
	    }
		}
	public static void sendchargeMessages(String phone,String tophone,String inviteCode){
		try {
			IOUtils.toString(
			new URL(SmsConfig.getValueMap().get("smsHuaxin.url")  + "&mobile=" + 
			tophone + "&content=" + 
			URLEncoder.encode(SmsConfig.getValueMap().get("smsHuaxin.content_5").replace("@1",phone).replace("@",inviteCode), "utf-8"))
			.openConnection().getInputStream(), "utf-8");
		}catch (IOException e) {
			throw new RuntimeException("Sms Send Error Caused.", e);
		}
	}
	/**
	 * 生成验证码
	 * @return
	 */
	public static String generateCode(){
		return  RandomStringUtils.randomNumeric(6);
	}
}

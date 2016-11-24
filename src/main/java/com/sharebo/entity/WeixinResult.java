package com.sharebo.entity;
/**
 * @author Administrator
 */
public class WeixinResult implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String return_code;
	private String return_msg;
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String returnCode) {
		return_code = returnCode;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String returnMsg) {
		return_msg = returnMsg;
	}
	public WeixinResult(String returnCode, String returnMsg) {
		return_code = returnCode;
		return_msg = returnMsg;
	}
	public WeixinResult(){}
}

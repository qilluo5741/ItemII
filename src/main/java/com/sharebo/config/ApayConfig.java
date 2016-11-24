package com.sharebo.config;

/**
 * 支付宝配置参数 采用单利模式配置
 * @author niewei
 */
public  class ApayConfig {
	private static ApayConfig apay;

	// 不让外部初始化对象
	ApayConfig() {
		apay = this;
	}
	/**
	 * 获取当前对象
	 * @return
	 */
	public static ApayConfig newApayConfig() {
		return apay;
	}
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public  static String partner;
	// 商户的私钥
	public  static String private_key; 
	// 支付宝的公钥，无需修改该值
	public  static String ali_public_key;
	// 调试用，创建TXT日志文件夹路径
	public  static  String log_path = "D:\\";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	// 签名方式 不需修改
	public  static String sign_type = "RSA";

	public static String getPartner() {
		return partner;
	}
	public static void setPartner(String partner) {
		ApayConfig.partner = partner;
	}

	public static String getPrivate_key() {
		return private_key;
	}

	public static void setPrivate_key(String private_key) {
		ApayConfig.private_key = private_key;
	}

	public static String getAli_public_key() {
		return ali_public_key;
	}

	public static void setAli_public_key(String ali_public_key) {
		ApayConfig.ali_public_key = ali_public_key;
	}

	public static String getLog_path() {
		return log_path;
	}

	public static void setLog_path(String log_path) {
		ApayConfig.log_path = log_path;
	}
	public static String getSign_type() {
		return sign_type;
	}

	public static void setSign_type(String sign_type) {
		ApayConfig.sign_type = sign_type;
	}

	public static String getInput_charset() {
		return input_charset;
	}

	public static void setInput_charset(String input_charset) {
		ApayConfig.input_charset = input_charset;
	}
}
package com.sharebo.config;

/**
 * 微信支付配置参数
 * @author niewei
 */
public class WeiXinConfig {
	private static WeiXinConfig weixin;
	// 不让外部初始化对象
	WeiXinConfig() {
		weixin = this;
	}

	public static WeiXinConfig newWeiXinConfig() {
		return weixin;
	}
	//秘钥
	public static String aap_key;
	//商户ID
	public static String app_id;
	public static String getAap_key() {
		return aap_key;
	}

	public static void setAap_key(String aap_key) {
		WeiXinConfig.aap_key = aap_key;
	}

	public static String getApp_id() {
		return app_id;
	}

	public static void setApp_id(String app_id) {
		WeiXinConfig.app_id = app_id;
	}
}

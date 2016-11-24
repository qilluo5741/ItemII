package com.sharebo.config;

/**
 * Õ∆ÀÕ≈‰÷√≤Œ ˝
 * @author niewei
 */
public class JpushConfig {
	private static JpushConfig jp;

	JpushConfig() {
		jp = this;
	}

	public static JpushConfig newJpushConfig() {
		return jp;
	}
	private static String masterSecret;
	private static String appKey;
	@SuppressWarnings("static-access")
	public static String getMasterSecret() {
		return jp.masterSecret;
	}
	public static void setMasterSecret(String masterSecret) {
		JpushConfig.masterSecret = masterSecret;
	}
	@SuppressWarnings("static-access")
	public static String getAppKey() {
		return jp.appKey;
	}
	public static void setAppKey(String appKey) {
		JpushConfig.appKey = appKey;
	}
}

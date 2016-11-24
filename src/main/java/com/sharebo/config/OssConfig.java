package com.sharebo.config;
/**
 * OSS配置文件
 */
public class OssConfig {
	private static OssConfig ossconfig;
	OssConfig(){
		ossconfig=this;
	}
	public static OssConfig newOssConfig(){
		return ossconfig;
	}
	public static void setOssConfig(OssConfig oss){
		ossconfig=oss;
	}
	 /**
	  * ossHost
	  */
	  public static  String ossHost;
	  /**
	   * ossHostInternal
	   * sharebo.oss-cn-shanghai-internal.aliyuncs.com
	   */
	  public static  String ossHostInternal;
	  /**
	   * ossBucket
	   */
	  public static  String ossBucket;
	  /**
	   * ossFolder
	   * 后面必须加上  ‘/’dev/
	   */
	  public  static  String ossFolder;
	  /**
	   * ossAccount
	   */
	  public static  String ossAccount;
	  /**
	   * ossPassword
	   */
	  public static  String ossPassword;
	@SuppressWarnings("static-access")
	public static OssConfig getOssconfig() {
		return ossconfig.ossconfig;
	}
	public static void setOssconfig(OssConfig ossconfig) {
		OssConfig.ossconfig = ossconfig;
	}
	@SuppressWarnings("static-access")
	public static String getOssHost() {
		return ossconfig.ossHost;
	}
	public static void setOssHost(String ossHost) {
		OssConfig.ossHost = ossHost;
	}
	@SuppressWarnings("static-access")
	public static String getOssHostInternal() {
		return ossconfig.ossHostInternal;
	}
	public static void setOssHostInternal(String ossHostInternal) {
		OssConfig.ossHostInternal = ossHostInternal;
	}
	@SuppressWarnings("static-access")
	public static String getOssBucket() {
		return ossconfig.ossBucket;
	}
	public static void setOssBucket(String ossBucket) {
		OssConfig.ossBucket = ossBucket;
	}
	@SuppressWarnings("static-access")
	public static String getOssFolder() {
		return ossconfig.ossFolder;
	}
	public static void setOssFolder(String ossFolder) {
		OssConfig.ossFolder = ossFolder;
	}
	@SuppressWarnings("static-access")
	public static String getOssAccount() {
		return ossconfig.ossAccount;
	}
	public static void setOssAccount(String ossAccount) {
		OssConfig.ossAccount = ossAccount;
	}
	@SuppressWarnings("static-access")
	public static String getOssPassword() {
		return ossconfig.ossPassword;
	}
	public static void setOssPassword(String ossPassword) {
		OssConfig.ossPassword = ossPassword;
	}
}

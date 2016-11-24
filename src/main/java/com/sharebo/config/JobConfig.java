package com.sharebo.config;
/**
 * 任务调度配置
 * @author niewei
 */
public class JobConfig {
	@SuppressWarnings("unused")
	private static JobConfig job;
	JobConfig(){
		job=this;
	}
	private static String  jobName_orderCancel;
	private static String  jobName_orderRefused;
	public static String getJobName_orderCancel() {
		return jobName_orderCancel;
	}
	public static void setJobName_orderCancel(String jobName_orderCancel) {
		JobConfig.jobName_orderCancel = jobName_orderCancel;
	}
	public static String getJobName_orderRefused() {
		return jobName_orderRefused;
	}
	public static void setJobName_orderRefused(String jobName_orderRefused) {
		JobConfig.jobName_orderRefused = jobName_orderRefused;
	}
	
	
}

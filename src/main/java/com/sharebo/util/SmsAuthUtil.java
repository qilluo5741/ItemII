package com.sharebo.util;

import com.sharebo.config.SmsConfig;

/**
 * 用户邀请，注册。。短信
 * 
 * @author niewei
 *
 */
public class SmsAuthUtil {
	/**
	 * 发送短信
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public static String SendMessage(String mobile,String code) throws Exception {
		return HttpSender.batchSend(
				SmsConfig.getValueMap().get("smsChuanglan.url"), SmsConfig
						.getValueMap().get("smsChuanglan.account"), SmsConfig
						.getValueMap().get("smsChuanglan.pswd"), mobile,
				SmsConfig.getValueMap().get("smsChuanglan.content_1").replace("@",code), Boolean
						.getBoolean(SmsConfig.getValueMap().get(
								"smsChuanglan.needstatus")), "");
	}
}

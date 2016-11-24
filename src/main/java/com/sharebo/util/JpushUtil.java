package com.sharebo.util;

import java.util.Iterator;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.report.ReceivedsResult;

import com.google.gson.JsonObject;
import com.sharebo.config.JpushConfig;

/**
 *消息推送工具类
 * @author niewei
 *
 */
public class JpushUtil {
	private static JPushClient jpushClient = new JPushClient(JpushConfig.getMasterSecret(),JpushConfig.getAppKey());

	/**
	 * 广播通知安卓——ios平台。
	 * @param alert
	 *            通知栏内容
	 * @param title
	 *            标题
	 * @param mapExtra
	 *            其他参数
	 * @return {"msg_id":4183310715,"sendno":988717333}
	 * @throws APIRequestException
	 * @throws APIConnectionException
	 */
	public static PushResult radioAll(String alert, String title,
			Map<String, String> mapExtra) throws APIConnectionException,
			APIRequestException {
		JsonObject jo = new JsonObject();
		// 遍历
		Iterator<String> iter = mapExtra.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			jo.addProperty(key, mapExtra.get(key));
		}
		PushPayload s = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				// 设置推送对象
				.setAudience(Audience.all())
				.setNotification(
						Notification
								.newBuilder()
								.setAlert(alert)
								.addPlatformNotification(
										IosNotification.newBuilder()
												.setBadge(+1)
												.setContentAvailable(true)
												.addExtra("parameter", jo)
												.build())
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.setTitle(title)
												.addExtra("parameter", jo)
												.build()).build()).build();
		return sendPush(s);
	}

	/**
	 * regid单个通知
	 * 
	 * @param msg
	 *            消息
	 * @param regid
	 *            推送目的地
	 * @param map
	 *            额外参数
	 * @return {"msg_id":1550715169,"sendno":1258501849}
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	public static PushResult pushRegistration_id(String msg, String regid,
			Map<String, String> map) throws APIConnectionException,
			APIRequestException {
		// 额外参数
		PushPayload ppl = PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				// 设置推送对象
				.setAudience(
						Audience.newBuilder()
								.addAudienceTarget(
										AudienceTarget.registrationId(regid))// 这个是通过regid推送消息（点对点）
								.build())
				.setMessage(
						Message.newBuilder().setMsgContent(msg).addExtras(map)
								.build()).build();
		return sendPush(ppl);
	}

	/**
	 * 查看消息情况
	 * 
	 * @param msg_id
	 *            消息id
	 * @return jsonStr
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	public static String getReportReceiveds(String msg_id)
			throws APIConnectionException, APIRequestException {
		ReceivedsResult result = jpushClient.getReportReceiveds(msg_id);
		return result.toString();
	}

	// 发送
	private static PushResult sendPush(PushPayload p)
			throws APIConnectionException, APIRequestException {
		return jpushClient.sendPush(p);
	}
}

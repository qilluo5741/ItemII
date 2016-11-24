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
 *��Ϣ���͹�����
 * @author niewei
 *
 */
public class JpushUtil {
	private static JPushClient jpushClient = new JPushClient(JpushConfig.getMasterSecret(),JpushConfig.getAppKey());

	/**
	 * �㲥֪ͨ��׿����iosƽ̨��
	 * @param alert
	 *            ֪ͨ������
	 * @param title
	 *            ����
	 * @param mapExtra
	 *            ��������
	 * @return {"msg_id":4183310715,"sendno":988717333}
	 * @throws APIRequestException
	 * @throws APIConnectionException
	 */
	public static PushResult radioAll(String alert, String title,
			Map<String, String> mapExtra) throws APIConnectionException,
			APIRequestException {
		JsonObject jo = new JsonObject();
		// ����
		Iterator<String> iter = mapExtra.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			jo.addProperty(key, mapExtra.get(key));
		}
		PushPayload s = PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				// �������Ͷ���
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
	 * regid����֪ͨ
	 * 
	 * @param msg
	 *            ��Ϣ
	 * @param regid
	 *            ����Ŀ�ĵ�
	 * @param map
	 *            �������
	 * @return {"msg_id":1550715169,"sendno":1258501849}
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	public static PushResult pushRegistration_id(String msg, String regid,
			Map<String, String> map) throws APIConnectionException,
			APIRequestException {
		// �������
		PushPayload ppl = PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				// �������Ͷ���
				.setAudience(
						Audience.newBuilder()
								.addAudienceTarget(
										AudienceTarget.registrationId(regid))// �����ͨ��regid������Ϣ����Ե㣩
								.build())
				.setMessage(
						Message.newBuilder().setMsgContent(msg).addExtras(map)
								.build()).build();
		return sendPush(ppl);
	}

	/**
	 * �鿴��Ϣ���
	 * 
	 * @param msg_id
	 *            ��Ϣid
	 * @return jsonStr
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	public static String getReportReceiveds(String msg_id)
			throws APIConnectionException, APIRequestException {
		ReceivedsResult result = jpushClient.getReportReceiveds(msg_id);
		return result.toString();
	}

	// ����
	private static PushResult sendPush(PushPayload p)
			throws APIConnectionException, APIRequestException {
		return jpushClient.sendPush(p);
	}
}

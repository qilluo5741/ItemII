package com.sharebo.entity;

import java.util.Date;

public class Feedback {
	private String feedbackId;
	private String feedbackText;//'��������'
	private String feedbackImage;//'����ͼƬ'
	private String userid;//'�û�����(����û�Ϊ��½����ô���ֶ�Ϊnull)'
	private Date feedbackTime;//'����ʱ��'
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getFeedbackText() {
		return feedbackText;
	}
	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}
	public String getFeedbackImage() {
		return feedbackImage;
	}
	public void setFeedbackImage(String feedbackImage) {
		this.feedbackImage = feedbackImage;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
}

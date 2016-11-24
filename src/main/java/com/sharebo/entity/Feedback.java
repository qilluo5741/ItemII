package com.sharebo.entity;

import java.util.Date;

public class Feedback {
	private String feedbackId;
	private String feedbackText;//'反馈内容'
	private String feedbackImage;//'反馈图片'
	private String userid;//'用户主键(如果用户为登陆，那么该字段为null)'
	private Date feedbackTime;//'反馈时间'
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

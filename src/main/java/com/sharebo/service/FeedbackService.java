package com.sharebo.service;

import com.sharebo.entity.Feedback;

public interface FeedbackService {
	/**
	 * 添加用户反馈内容
	 * @param feed
	 * @return
	 */
	public int addFeedback(Feedback feed);
}

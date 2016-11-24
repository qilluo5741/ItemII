package com.sharebo.mapper;

import com.sharebo.entity.Feedback;

public interface FeedbackMapper {
	/**
	 * 添加用户反馈内容
	 * @param feed
	 * @return
	 */
	public int addFeedback(Feedback feed);
}

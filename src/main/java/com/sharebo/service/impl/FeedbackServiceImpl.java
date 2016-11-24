package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Feedback;
import com.sharebo.mapper.FeedbackMapper;
import com.sharebo.service.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackMapper mapper;
	/**
	 * ����û���������
	 * @param feed
	 * @return
	 */
	@Override
	public int addFeedback(Feedback feed) {
		return mapper.addFeedback(feed);
	}
}

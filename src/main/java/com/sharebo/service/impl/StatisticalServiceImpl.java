package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Statistical;
import com.sharebo.mapper.StatisticalMapper;
import com.sharebo.service.StatisticalService;
@Service
public class StatisticalServiceImpl implements StatisticalService {
	@Autowired
	private StatisticalMapper mapper;
	/**
	 * 添加更新统计
	 */
	@Override
	public int addStatistical(Statistical stical) {
		return mapper.addStatistical(stical);
	}
}

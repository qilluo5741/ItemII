package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Startlaunch;
import com.sharebo.mapper.StartlaunchMapper;
import com.sharebo.service.StartlaunchService;
@Service
public class StartlaunchServiceImpl implements StartlaunchService {
	@Autowired
	private StartlaunchMapper mapper;
	@Override
	public Startlaunch getloadAllStartlaunch(int startsize, String startopinion) {
		return mapper.getloadAllStartlaunch(startsize, startopinion);
	}
}

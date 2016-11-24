package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.AppVersion;
import com.sharebo.mapper.AppVersionMapper;
import com.sharebo.service.AppVersionService;
@Service
public class AppVersionServiceImpl implements AppVersionService {
	@Autowired
	private AppVersionMapper mapper;
	/**
	 * 版本更新查询
	 */
	@Override
	public AppVersion getloadAppVersionAll() {
		return mapper.getloadAppVersionAll();
	}
}

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
	 * �汾���²�ѯ
	 */
	@Override
	public AppVersion getloadAppVersionAll() {
		return mapper.getloadAppVersionAll();
	}
}

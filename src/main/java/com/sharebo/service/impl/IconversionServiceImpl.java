package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Iconversion;
import com.sharebo.mapper.IconversionMapper;
import com.sharebo.service.IconversionService;
@Service
public class IconversionServiceImpl implements IconversionService {
	@Autowired
	private IconversionMapper mapper;
	/**
	 * 版本信息控制查询
	 */
	@Override
	public Iconversion getloadAllconversion() {
		return mapper.getloadAllconversion();
	}
}

package com.sharebo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.AdvertisDto;
import com.sharebo.mapper.AdvertisMapper;
import com.sharebo.service.AdvertisService;
@Service
public class AdvertisServiceImpl implements AdvertisService {
	@Autowired
	private AdvertisMapper mapper;
	/**
	 * 广告也查询
	 */
	@Override
	public List<AdvertisDto> getloadAdvertis(String opinion) {
		return mapper.getloadAdvertis(opinion);
	}
}

package com.sharebo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.DetailsDto;
import com.sharebo.mapper.DetailsMapper;
import com.sharebo.service.DetailsService;
@Service
public class DetailsServiceImpl implements DetailsService {
	@Autowired
	private DetailsMapper mapper;
	@Override
	public DetailsDto getloadAllDetails(String carportId) {
		return mapper.getloadAllDetails(carportId);
	}
	@Override
	public Integer getloadAllDetailCount(String userid, String identifying) {
		return mapper.getloadAllDetailCount(userid, identifying);
	}
}

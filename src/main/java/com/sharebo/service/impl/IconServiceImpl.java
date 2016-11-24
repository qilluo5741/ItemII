package com.sharebo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.IconDto;
import com.sharebo.mapper.IconMapper;
import com.sharebo.service.IconService;

@Service
public class IconServiceImpl implements IconService {
	@Autowired
	private IconMapper mapper;
	@Override
	public List<IconDto> getloadIconertis(String opinions) {
		return mapper.getloadIconertis(opinions);
	}
}

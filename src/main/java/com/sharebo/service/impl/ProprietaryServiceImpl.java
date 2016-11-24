package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.ProprietaryDto;
import com.sharebo.mapper.ProprietaryMapper;
import com.sharebo.service.ProprietaryService;
@Service
public class ProprietaryServiceImpl implements ProprietaryService {
	@Autowired
	private ProprietaryMapper mapper;
	@Override
	public ProprietaryDto getloadAllProprietary(String carportId) {
		return mapper.getloadAllProprietary(carportId);
	}
}

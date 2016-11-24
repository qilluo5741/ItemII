package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.PsonaividualDto;
import com.sharebo.mapper.PsonaividualMapper;
import com.sharebo.service.PsonaividualService;
@Service
public class PsonaividualServiceImpl implements PsonaividualService {
	@Autowired
	private PsonaividualMapper mapper;
	@Override
	public List<PsonaividualDto> getloadPsonaividualAll(Map<String, Object> map) {
		return mapper.getloadPsonaividualAll(map);
	}
	@Override
	public Integer getloadPsonaividualAllCount(String userid) {
		return mapper.getloadPsonaividualAllCount(userid);
	}
}

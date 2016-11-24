package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.Parkcooperation;
import com.sharebo.mapper.ParkcooperationMapper;
import com.sharebo.service.ParkcooperationService;
@Service
public class ParkcooperationServiceImpl implements ParkcooperationService {
	@Autowired
	private ParkcooperationMapper mapper;
	/**
	 * 添加停车场合作
	 * @param park
	 * @return
	 */
	@Override
	public int insertParkcooperation(Parkcooperation park) {
		return mapper.insertParkcooperation(park);
	}
}

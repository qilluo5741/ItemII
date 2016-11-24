package com.sharebo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Patterntype;
import com.sharebo.entity.Speciesort;
import com.sharebo.entity.dto.SpeciesortDto;
import com.sharebo.mapper.SpeciesortMapper;
import com.sharebo.service.SpeciesortService;
@Service
public class SpeciesortServiceImpl implements SpeciesortService {
	@Autowired
	private SpeciesortMapper mapper;
	/**
	 * 查询车类型
	 */
	@Override
	public List<Speciesort> loadSpeciesortAll() {
		return mapper.loadSpeciesortAll();
	}
	/**
	 * 查询车类别
	 */
	@Override
	public List<Patterntype> loadAllPatterntype(String specieid) {
		return mapper.loadAllPatterntype(specieid);
	}
	@Override
	public List<SpeciesortDto> loadAllPatterntype2() {
		return mapper.loadAllPatterntype2();
	}
}

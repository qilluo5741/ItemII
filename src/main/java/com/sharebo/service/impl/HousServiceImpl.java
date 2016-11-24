package com.sharebo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Hous;
import com.sharebo.entity.dto.HousDto;
import com.sharebo.mapper.HousMapper;
import com.sharebo.service.HousService;
@Service
public class HousServiceImpl implements HousService {
	@Autowired
	private HousMapper mapper;
	@Override
	public List<HousDto> selectByhousNameorhousAddress(String housName) {
		return mapper.selectByhousNameorhousAddress(housName);
	}
	/**
	 * 停车场添加
	 */
	@Override
	public int addHous(Hous hous) {
		return mapper.addHous(hous);
	}
	/**
	 * 查询停车场是否存在
	 */
	@Override
	public String gethousNameBydoesitexist(String housName) {
		return mapper.gethousNameBydoesitexist(housName);
	}
	@Override
	public List<HousDto> selectHousSynchronizeGaodeId() {
		return mapper.selectHousSynchronizeGaodeId();
	}
	@Override
	public int updatehousbygaodeid(String gaodeIds, String housId) {
		return mapper.updatehousbygaodeid(gaodeIds, housId);
	}
}

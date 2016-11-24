package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Carcollection;
import com.sharebo.entity.dto.AltogetherDto;
import com.sharebo.entity.dto.PersonalareaDto;
import com.sharebo.mapper.CarcollectionMapper;
import com.sharebo.service.CarcollectionService;
@Service
public class CarcollectionServiceImpl implements CarcollectionService {
	@Autowired
	private CarcollectionMapper mapper;
	@Override
	public int addCarcollection(Carcollection carcoll) {
		return mapper.addCarcollection(carcoll);
	}

	@Override
	public int getCarcollectionydoesitexist(Integer carType, String userid, String identifying) {
		return mapper.getCarcollectionydoesitexist(carType, userid, identifying);
	}
	@Override
	public int updateCarcollectiony(Integer isDelete, String userid, Integer carType, String identifying) {
		return mapper.updateCarcollectiony(isDelete, userid, carType, identifying);
	}

	@Override
	public List<AltogetherDto> getloadAllAltogether(Map<String, Object> map) {
		return mapper.getloadAllAltogether(map);
	}

	@Override
	public Integer getAltogetherCount(String userid) {
		return mapper.getAltogetherCount(userid);
	}

	@Override
	public List<PersonalareaDto> getloadAllPersonalarea(Map<String, Object> map) {
		return mapper.getloadAllPersonalarea(map);
	}
	@Override
	public Integer getloadAllPersonalareaCount(String userid) {
		return mapper.getAltogetherCount(userid);
	}
}

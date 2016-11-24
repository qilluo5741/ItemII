package com.sharebo.service;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.ProprietaryDto;

public interface ProprietaryService {
	/**
	 * 专有车位查询
	 * @param carportId
	 * @return
	 */
	public ProprietaryDto getloadAllProprietary(@Param("carportId")String carportId);
}

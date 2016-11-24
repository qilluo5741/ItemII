package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.ProprietaryDto;

public interface ProprietaryMapper {
	/**
	 * 专有车位查询
	 * @param carportId
	 * @return
	 */
	public ProprietaryDto getloadAllProprietary(@Param("carportId")String carportId);
}

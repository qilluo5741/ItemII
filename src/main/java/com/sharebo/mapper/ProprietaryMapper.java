package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.ProprietaryDto;

public interface ProprietaryMapper {
	/**
	 * ר�г�λ��ѯ
	 * @param carportId
	 * @return
	 */
	public ProprietaryDto getloadAllProprietary(@Param("carportId")String carportId);
}

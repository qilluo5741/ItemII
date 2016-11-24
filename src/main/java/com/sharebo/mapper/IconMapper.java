package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.IconDto;

public interface IconMapper {
	/**
	 * Í¼±êico
	 * @param opinion
	 * @return
	 */
	public List<IconDto> getloadIconertis(@Param("opinions")String opinions);
}

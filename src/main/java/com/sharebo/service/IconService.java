package com.sharebo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.IconDto;

public interface IconService {
	/**
	 * Í¼±êico
	 * @param opinion
	 * @return
	 */
	public List<IconDto> getloadIconertis(@Param("opinions")String opinions);
}

package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.AdvertisDto;

public interface AdvertisMapper {
	/**
	 * π„∏Ê≤È—Ø
	 * @param opinion
	 * @return
	 */
	public List<AdvertisDto> getloadAdvertis(@Param("opinion")String opinion);
}

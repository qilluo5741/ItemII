package com.sharebo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.AdvertisDto;

public interface AdvertisService {
	/**
	 * ����ѯ
	 * @param opinion
	 * @return
	 */
	public List<AdvertisDto> getloadAdvertis(@Param("opinion")String opinion);
}

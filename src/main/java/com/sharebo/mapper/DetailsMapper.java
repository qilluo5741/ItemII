package com.sharebo.mapper;


import org.apache.ibatis.annotations.Param;
import com.sharebo.entity.dto.DetailsDto;

public interface DetailsMapper {
	/**
	 * 车位详情
	 * @param userid
	 * @return
	 */
	public DetailsDto getloadAllDetails(@Param("carportId")String carportId);
	/**
	 * 查询是否收藏
	 * @param userid
	 * @param identifying
	 * @return
	 */
	public Integer getloadAllDetailCount(@Param("userid")String userid,@Param("identifying")String identifying);
}

package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.PsonaividualDto;

public interface PsonaividualMapper {
	/**
	 * 个人车位查询
	 * @param map
	 * @return
	 */
	public List<PsonaividualDto> getloadPsonaividualAll(Map<String, Object> map);
	/**
	 * 个人车位查询总数
	 * @param userid
	 * @return
	 */
	public Integer getloadPsonaividualAllCount(@Param("userid")String userid);
}

package com.sharebo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.PsonaividualDto;

public interface PsonaividualService {
	/**
	 * ���˳�λ��ѯ
	 * @param map
	 * @return
	 */
	public List<PsonaividualDto> getloadPsonaividualAll(Map<String, Object> map);
	/**
	 * ���˳�λ��ѯ����
	 * @param userid
	 * @return
	 */
	public Integer getloadPsonaividualAllCount(@Param("userid")String userid);
}

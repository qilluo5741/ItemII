package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.ParkChargeDto;
import com.sharebo.entity.dto.ParkChargeInfoDto;

public interface ParkChargeMapper {
	/**
	 * ͣ���ɷѼ�¼
	 * @param map
	 * @return
	 */
	public List<ParkChargeDto> selectParkCharge(Map<String,Object> map);
	/**
	 * ��ѯ�ɷѼ�¼����
	 * @param userid
	 * @return
	 */
	public int selectParkChargeCount(@Param("userid")String userid);
	/**
	 * ͣ���ɷ�
	 * @param parkcharge
	 * @return
	 */
	public ParkChargeInfoDto selectParkChargeInfo(@Param("parkcharge")String parkcharge);

}

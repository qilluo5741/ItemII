package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.ParkChargeDto;
import com.sharebo.entity.dto.ParkChargeInfoDto;

public interface ParkChargeMapper {
	/**
	 * 停车缴费记录
	 * @param map
	 * @return
	 */
	public List<ParkChargeDto> selectParkCharge(Map<String,Object> map);
	/**
	 * 查询缴费记录总数
	 * @param userid
	 * @return
	 */
	public int selectParkChargeCount(@Param("userid")String userid);
	/**
	 * 停车缴费
	 * @param parkcharge
	 * @return
	 */
	public ParkChargeInfoDto selectParkChargeInfo(@Param("parkcharge")String parkcharge);

}

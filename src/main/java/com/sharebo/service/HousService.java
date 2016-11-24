package com.sharebo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Hous;
import com.sharebo.entity.dto.HousDto;

public interface HousService {
	/**
	 * 模糊查询停车场
	 * @param housName
	 * @return
	 */
	public List<HousDto> selectByhousNameorhousAddress(@Param("housName")String housName);
	/**
	 * 添加停车场
	 * @param hous
	 * @return
	 */
	public int addHous(Hous hous);
	/**
	 * 查询停车场是否存在
	 * @param housName
	 * @return
	 */
	public String gethousNameBydoesitexist(@Param("housName")String housName);
	/**
	 * 同步高德查询测试数据
	 * @return
	 */
	public List<HousDto> selectHousSynchronizeGaodeId();
	public int updatehousbygaodeid(@Param("gaodeIds")String gaodeIds,@Param("housId")String housId);
}

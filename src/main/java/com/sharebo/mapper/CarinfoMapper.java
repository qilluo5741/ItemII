package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Carinfo;
import com.sharebo.entity.dto.CarDto;
import com.sharebo.entity.dto.CarinfoDto;

public interface CarinfoMapper {
	/**
	 * 删除车牌
	 */
	public int delectCarinfocarNo(@Param("carId")String carId);
	/**
	 * 查询车牌信息
	 * @param userid
	 * @return
	 */
	public List<CarinfoDto> getloadAllCarinfo(@Param("userid")String userid);
	/**
	 * 添加车牌
	 * @param car
	 * @return
	 */
	public int addCarinfo(Carinfo car);
	/**
	 * 查询车牌是否存在
	 * @param userid
	 * @return
	 */
	public String getcarNoBydoesitexist(@Param("carNo")String carNo);
	/**
	 * 查询车牌
	 */
	public List<CarDto> getloadCar(@Param("userid")String userid);
}

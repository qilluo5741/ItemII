package com.sharebo.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sharebo.entity.Carinfo;
import com.sharebo.entity.dto.CarDto;
import com.sharebo.entity.dto.CarinfoDto;
import com.sharebo.mapper.CarinfoMapper;
import com.sharebo.service.CarinfoService;
@Service
public class CarinfoServiceImpl implements CarinfoService {
	@Autowired
	private CarinfoMapper mapper;
	/**
	 * 删除车牌
	 */
	@Override
	public int delectCarinfocarNo(String carId) {
		return mapper.delectCarinfocarNo(carId);
	}
	/**
	 * 查询所有车辆信息
	 */
	@Override
	public List<CarinfoDto> getloadAllCarinfo(String userid) {
		return mapper.getloadAllCarinfo(userid);
	}
	/**
	 * 添加车辆
	 */
	@Override
	public int addCarinfo(Carinfo car) {
		return mapper.addCarinfo(car);
	}
	/**
	 * 查询是否存在
	 */
	@Override
	public String getcarNoBydoesitexist(@Param("carNo")String carNo){
		return mapper.getcarNoBydoesitexist(carNo);
	}
	@Override
	public List<CarDto> getloadCar(String userid) {
		return mapper.getloadCar(userid);
	}
}

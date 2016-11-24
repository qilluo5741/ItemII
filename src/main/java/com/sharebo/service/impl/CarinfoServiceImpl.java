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
	 * ɾ������
	 */
	@Override
	public int delectCarinfocarNo(String carId) {
		return mapper.delectCarinfocarNo(carId);
	}
	/**
	 * ��ѯ���г�����Ϣ
	 */
	@Override
	public List<CarinfoDto> getloadAllCarinfo(String userid) {
		return mapper.getloadAllCarinfo(userid);
	}
	/**
	 * ��ӳ���
	 */
	@Override
	public int addCarinfo(Carinfo car) {
		return mapper.addCarinfo(car);
	}
	/**
	 * ��ѯ�Ƿ����
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

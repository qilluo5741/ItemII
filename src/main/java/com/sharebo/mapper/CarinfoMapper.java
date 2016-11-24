package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Carinfo;
import com.sharebo.entity.dto.CarDto;
import com.sharebo.entity.dto.CarinfoDto;

public interface CarinfoMapper {
	/**
	 * ɾ������
	 */
	public int delectCarinfocarNo(@Param("carId")String carId);
	/**
	 * ��ѯ������Ϣ
	 * @param userid
	 * @return
	 */
	public List<CarinfoDto> getloadAllCarinfo(@Param("userid")String userid);
	/**
	 * ��ӳ���
	 * @param car
	 * @return
	 */
	public int addCarinfo(Carinfo car);
	/**
	 * ��ѯ�����Ƿ����
	 * @param userid
	 * @return
	 */
	public String getcarNoBydoesitexist(@Param("carNo")String carNo);
	/**
	 * ��ѯ����
	 */
	public List<CarDto> getloadCar(@Param("userid")String userid);
}

package com.sharebo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Hous;
import com.sharebo.entity.dto.HousDto;

public interface HousService {
	/**
	 * ģ����ѯͣ����
	 * @param housName
	 * @return
	 */
	public List<HousDto> selectByhousNameorhousAddress(@Param("housName")String housName);
	/**
	 * ���ͣ����
	 * @param hous
	 * @return
	 */
	public int addHous(Hous hous);
	/**
	 * ��ѯͣ�����Ƿ����
	 * @param housName
	 * @return
	 */
	public String gethousNameBydoesitexist(@Param("housName")String housName);
	/**
	 * ͬ���ߵ²�ѯ��������
	 * @return
	 */
	public List<HousDto> selectHousSynchronizeGaodeId();
	public int updatehousbygaodeid(@Param("gaodeIds")String gaodeIds,@Param("housId")String housId);
}

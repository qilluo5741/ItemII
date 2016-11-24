package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Patterntype;
import com.sharebo.entity.Speciesort;
import com.sharebo.entity.dto.SpeciesortDto;

/**
 * @author Administrator
 * ������
 */
public interface SpeciesortMapper {
	/**
	 * ��ѯȫ��������
	 * @return
	 */
	public List<Speciesort> loadSpeciesortAll();
	/**
	 * ��ѯ�����
	 * @param specieid
	 * @return
	 */
	public List<Patterntype>loadAllPatterntype(@Param("specieid")String specieid);
	public List<SpeciesortDto> loadAllPatterntype2();
}

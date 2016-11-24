package com.sharebo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Hous;
import com.sharebo.entity.Monthly;
import com.sharebo.entity.dto.MonthDto;
import com.sharebo.entity.dto.MonthlyDto;
import com.sharebo.entity.dto.Monthlydetails;
import com.sharebo.entity.dto.ParkMonth;

public interface MonthlyService {
	/**
	 * �����Ϣ
	 * @param monthly
	 * @return
	 */
	public int addMonthlyation(Monthly monthly);
	/**
	 * �޸İ�����Ϣ
	 * @param monthly
	 * @return
	 */
	public int updateMonthlyation(Monthly monthly);
	/**
	 * �����ͣ��Ϣ
	 * @param monthly
	 * @return
	 */
	public int addShouldMonthlyation(Monthly monthly);
	/**
	 * �޸���ͣ��Ϣ
	 * @param monthly
	 * @return
	 */
	public int updateShouldMonthlyation(Monthly monthly);
	/**
	 * ģ����ѯ
	 * @param parkname
	 * @return
	 */
	public List<MonthlyDto> selectByparknameorparkAddress(@Param("parkname")String parkname);
	/**
	 * ��֤�Ƿ����
	 * @param parkname
	 * @return
	 */
	public Integer valMonthlyIsExixtsByparkname(@Param("parkname")String parkname);
	/**
	 * ��ѯ
	 * @param monthId
	 * @return
	 */
	public Monthly getMonthlybymonthId(@Param("monthId")String monthId);
	public List<Monthly> getMonthlybyappropriate();
	//����ͣ����ҳ
	public List<MonthDto> getloadAllMonthDtoer(Map<String, Object> map);
	//����ͣ����ѯ����
	public Integer getMonthDtoerCount();
	//����ͣ������Ϣ
	public Monthlydetails getMonthlydetailsbymonthId(String monthId);
	//ͬ��������Ϣ
	public int addParkMonthation(ParkMonth parkmonth);
	//ͬ����ѯδͬ��������
	public List<Monthly> getMonthly();
	//��hous��������
	public int addHous(Hous hous);
}

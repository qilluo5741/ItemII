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
	 * 添加信息
	 * @param monthly
	 * @return
	 */
	public int addMonthlyation(Monthly monthly);
	/**
	 * 修改包月信息
	 * @param monthly
	 * @return
	 */
	public int updateMonthlyation(Monthly monthly);
	/**
	 * 添加宜停信息
	 * @param monthly
	 * @return
	 */
	public int addShouldMonthlyation(Monthly monthly);
	/**
	 * 修改宜停信息
	 * @param monthly
	 * @return
	 */
	public int updateShouldMonthlyation(Monthly monthly);
	/**
	 * 模糊查询
	 * @param parkname
	 * @return
	 */
	public List<MonthlyDto> selectByparknameorparkAddress(@Param("parkname")String parkname);
	/**
	 * 验证是否存在
	 * @param parkname
	 * @return
	 */
	public Integer valMonthlyIsExixtsByparkname(@Param("parkname")String parkname);
	/**
	 * 查询
	 * @param monthId
	 * @return
	 */
	public Monthly getMonthlybymonthId(@Param("monthId")String monthId);
	public List<Monthly> getMonthlybyappropriate();
	//包月停车分页
	public List<MonthDto> getloadAllMonthDtoer(Map<String, Object> map);
	//包月停车查询总数
	public Integer getMonthDtoerCount();
	//包月停车场信息
	public Monthlydetails getMonthlydetailsbymonthId(String monthId);
	//同步包月信息
	public int addParkMonthation(ParkMonth parkmonth);
	//同步查询未同步的数据
	public List<Monthly> getMonthly();
	//往hous插入数据
	public int addHous(Hous hous);
}

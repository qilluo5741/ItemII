package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Hous;
import com.sharebo.entity.Monthly;
import com.sharebo.entity.dto.MonthDto;
import com.sharebo.entity.dto.MonthlyDto;
import com.sharebo.entity.dto.Monthlydetails;
import com.sharebo.entity.dto.ParkMonth;
import com.sharebo.mapper.MonthlyMapper;
import com.sharebo.service.MonthlyService;
@Service
public class MonthlyServiceImpl implements MonthlyService {
	@Autowired
	private MonthlyMapper mapper;
	@Override
	public int addMonthlyation(Monthly monthly) {
		return mapper.addMonthlyation(monthly);
	}
	@Override
	public int addShouldMonthlyation(Monthly monthly) {
		return mapper.addShouldMonthlyation(monthly);
	}
	@Override
	public List<MonthlyDto> selectByparknameorparkAddress(String parkname) {
		return mapper.selectByparknameorparkAddress(parkname);
	}
	@Override
	public Integer valMonthlyIsExixtsByparkname(String parkname) {
		return mapper.valMonthlyIsExixtsByparkname(parkname);
	}
	@Override
	public Monthly getMonthlybymonthId(String monthId) {
		return mapper.getMonthlybymonthId(monthId);
	}
	@Override
	public int updateMonthlyation(Monthly monthly) {
		return mapper.updateMonthlyation(monthly);
	}
	@Override
	public int updateShouldMonthlyation(Monthly monthly) {
		return mapper.updateShouldMonthlyation(monthly);
	}
	@Override
	public List<Monthly> getMonthlybyappropriate() {
		return mapper.getMonthlybyappropriate();
	}
	@Override
	public List<MonthDto> getloadAllMonthDtoer(Map<String, Object> map) {
		return mapper.getloadAllMonthDtoer(map);
	}
	@Override
	public Integer getMonthDtoerCount() {
		return mapper.getMonthDtoerCount();
	}
	@Override
	public Monthlydetails getMonthlydetailsbymonthId(String monthId) {
		return mapper.getMonthlydetailsbymonthId(monthId);
	}
	@Override
	public int addParkMonthation(ParkMonth parkmonth) {
		return mapper.addParkMonthation(parkmonth);
	}
	@Override
	public List<Monthly> getMonthly() {
		return mapper.getMonthly();
	}
	@Override
	public int addHous(Hous hous) {
		return mapper.addHous(hous);
	}
}

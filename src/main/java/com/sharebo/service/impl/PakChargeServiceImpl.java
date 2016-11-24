package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.ParkChargeDto;
import com.sharebo.entity.dto.ParkChargeInfoDto;
import com.sharebo.mapper.ParkChargeMapper;
import com.sharebo.service.ParkChargeService;
@Service
public class PakChargeServiceImpl implements ParkChargeService {
	@Autowired
	private ParkChargeMapper mapper;
	/**
	 * ͣ���ɷѼ�¼
	 * @param map
	 * @return
	 */
	@Override
	public List<ParkChargeDto> selectParkCharge(Map<String, Object> map) {
		return mapper.selectParkCharge(map);
	}
	/**
	 * ��ѯ�ɷѼ�¼����
	 * @param userid
	 * @return
	 */
	@Override
	public int selectParkChargeCount(String userid) {
		return mapper.selectParkChargeCount(userid);
	}
	/**
	 * ͣ���ɷ�
	 * @param parkcharge
	 * @return
	 */
	@Override
	public ParkChargeInfoDto selectParkChargeInfo(String parkcharge) {
		return mapper.selectParkChargeInfo(parkcharge);
	}

}

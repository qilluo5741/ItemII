package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.entity.dto.WithdrawalRecordDto;
import com.sharebo.mapper.WithdrawalRecordMapper;
import com.sharebo.service.WithdrawalRecordService;
@Service
public class WithdrawalRecordServiceImpal implements WithdrawalRecordService {
	@Autowired
	private WithdrawalRecordMapper mapper;

	@Override
	public List<WithdrawalRecordDto> getWithdrawalRecordDtoByUserid(Map<String,Object> map) {
		return mapper.getWithdrawalRecordDtoByUserid(map);
	}

	@Override
	public Integer pagerWithdrawalRecordCount(@Param("userid")String userid) {
		return mapper.pagerWithdrawalRecordCount(userid);
	}
	@Override
	public int valAvailableBalance(String userid, Double money) {
		return mapper.valAvailableBalance(userid, money);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean withdrawalRecord(String userid, Double money) throws Exception {
		//修改余额
		if(mapper.updateAvailableBalance(userid, money)<1){
			return false;
		}
		//添加提现记录
		if(mapper.insertwithdrawalrecord(userid, money)!=1){
			throw new Exception("提现记录修改失败！");
		}
		return true;
	}
	@Override
	public int valuserinfoByPayNo(String userid) {
		return mapper.valuserinfoByPayNo(userid);
	}
}

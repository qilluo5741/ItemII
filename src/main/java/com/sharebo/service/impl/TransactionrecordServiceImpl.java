package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.TransactionrecordDto;
import com.sharebo.mapper.TransactionrecordMapper;
import com.sharebo.service.TransactionrecordService;
@Service
public class TransactionrecordServiceImpl implements TransactionrecordService {
	@Autowired
	private TransactionrecordMapper mapper;
	
	@Override
	public List<TransactionrecordDto> getTransactionrecordByPager(Map<String,Object> map) {
		return mapper.getTransactionrecordByPager(map);
	}
	@Override
	public Integer getTransactionrecordcount(String userid) {
		return mapper.getTransactionrecordcount(userid);
	}
}

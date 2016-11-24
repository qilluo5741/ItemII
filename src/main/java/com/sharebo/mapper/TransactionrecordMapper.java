package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.TransactionrecordDto;

public interface TransactionrecordMapper {
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<TransactionrecordDto> getTransactionrecordByPager(Map<String,Object> map);
	/**
	 * 查询交易记录总数
	 * @return
	 */
	public int getTransactionrecordcount(@Param("userid")String userid);
}

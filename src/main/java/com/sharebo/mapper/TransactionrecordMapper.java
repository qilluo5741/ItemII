package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.TransactionrecordDto;

public interface TransactionrecordMapper {
	/**
	 * ��ҳ��ѯ
	 * @param map
	 * @return
	 */
	public List<TransactionrecordDto> getTransactionrecordByPager(Map<String,Object> map);
	/**
	 * ��ѯ���׼�¼����
	 * @return
	 */
	public int getTransactionrecordcount(@Param("userid")String userid);
}

package com.sharebo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.WithdrawalRecordDto;

public interface WithdrawalRecordService {
	/**
	 * 
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<WithdrawalRecordDto> getWithdrawalRecordDtoByUserid(
			Map<String, Object> map);

	/**
	 * 查询总数
	 * 
	 * @param userid
	 * @return
	 */
	public Integer pagerWithdrawalRecordCount(@Param("userid") String userid);

	/**
	 *  验证提现金额是否合法
	 * @param userid
	 * @param money
	 * @return
	 */
	public int valAvailableBalance(String userid, Double money);
	
	public boolean  withdrawalRecord(String userid, Double money) throws Exception;
	/**
	 * 验证用户表中的userid是否为空
	 * @param userid
	 * @return
	 */
	public int valuserinfoByPayNo(String userid);
}

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
	 * ��ѯ����
	 * 
	 * @param userid
	 * @return
	 */
	public Integer pagerWithdrawalRecordCount(@Param("userid") String userid);

	/**
	 *  ��֤���ֽ���Ƿ�Ϸ�
	 * @param userid
	 * @param money
	 * @return
	 */
	public int valAvailableBalance(String userid, Double money);
	
	public boolean  withdrawalRecord(String userid, Double money) throws Exception;
	/**
	 * ��֤�û����е�userid�Ƿ�Ϊ��
	 * @param userid
	 * @return
	 */
	public int valuserinfoByPayNo(String userid);
}

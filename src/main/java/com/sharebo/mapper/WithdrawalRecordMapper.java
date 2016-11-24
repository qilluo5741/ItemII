package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.WithdrawalRecordDto;

public interface WithdrawalRecordMapper {
	/**
	 * 
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<WithdrawalRecordDto> getWithdrawalRecordDtoByUserid(Map<String,Object> map);
	/**
	 * 查询总数
	 * @param userid
	 * @return
	 */
	public Integer pagerWithdrawalRecordCount(@Param("userid")String userid);
	/**
	 * 验证提现金额是否合法
	 * @param userid
	 * @param money
	 * @return
	 */
	public int valAvailableBalance(@Param("userid") String userid,@Param("money") Double money);
	/**
	 * 修改余额
	 * @param userid
	 * @param money
	 * @return
	 */
	public int updateAvailableBalance(@Param("userid") String userid,@Param("money") Double money);
	/**
	 * 添加提现记录
	 * @param userid
	 * @param money
	 * @return
	 */
	public int insertwithdrawalrecord(@Param("userid") String userid,@Param("money") Double money);
	/**
	 * 验证用户表中的userid是否为空
	 * @param userid
	 * @return
	 */
	public int valuserinfoByPayNo(@Param("userid") String userid);
}

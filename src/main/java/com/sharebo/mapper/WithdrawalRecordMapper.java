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
	 * ��ѯ����
	 * @param userid
	 * @return
	 */
	public Integer pagerWithdrawalRecordCount(@Param("userid")String userid);
	/**
	 * ��֤���ֽ���Ƿ�Ϸ�
	 * @param userid
	 * @param money
	 * @return
	 */
	public int valAvailableBalance(@Param("userid") String userid,@Param("money") Double money);
	/**
	 * �޸����
	 * @param userid
	 * @param money
	 * @return
	 */
	public int updateAvailableBalance(@Param("userid") String userid,@Param("money") Double money);
	/**
	 * ������ּ�¼
	 * @param userid
	 * @param money
	 * @return
	 */
	public int insertwithdrawalrecord(@Param("userid") String userid,@Param("money") Double money);
	/**
	 * ��֤�û����е�userid�Ƿ�Ϊ��
	 * @param userid
	 * @return
	 */
	public int valuserinfoByPayNo(@Param("userid") String userid);
}

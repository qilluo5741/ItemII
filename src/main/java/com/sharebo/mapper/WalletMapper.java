package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Wallet;
import com.sharebo.entity.dto.WalletDto;

public interface WalletMapper {
	/**
	 * ��ѯ�û��������Ͷ�����
	 * @param userid
	 * @return
	 */
	public WalletDto getAvailableBalance(@Param("userid")String userid);
	public int addWalletation(Wallet wallet);
}

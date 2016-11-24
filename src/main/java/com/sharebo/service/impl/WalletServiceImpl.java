package com.sharebo.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Wallet;
import com.sharebo.entity.dto.WalletDto;
import com.sharebo.mapper.WalletMapper;
import com.sharebo.service.WalletService;
@Service
public class WalletServiceImpl implements WalletService {
	@Autowired
	private WalletMapper mapper;
	/**
	 * 查询用户可用余额和冻结金额
	 * @param userid
	 * @return
	 */
	@Override
	public WalletDto getAvailableBalance(@Param("userid")String userid) {
		return mapper.getAvailableBalance(userid);
	}
	@Override
	public int addWalletation(Wallet wallet) {
		return mapper.addWalletation(wallet);
	}

}

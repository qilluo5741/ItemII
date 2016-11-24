package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.mapper.AuthuserinfoMapper;
import com.sharebo.service.AuthuserinfoService;
@Service
public class AuthuserinfoServiceImpl implements AuthuserinfoService {
	@Autowired
	private AuthuserinfoMapper mapper;
	@Override
	public String getAuthloginByuserid(String phone,String password) {
		return mapper.getAuthloginByuserid(phone, password);
	}
	@Override
	public int updateUserInfoTokenByUserId(String userid, String token) {
		return mapper.updateUserInfoTokenByUserId(userid, token);
	}
	@Override
	public com.sharebo.entity.dto.RegisteredDto getUserInfoIsNullByPhone(String phone) {
		return mapper.getUserInfoIsNullByPhone(phone);
	}
	@Override
	public int addUserInfo(String phone, String code) {
		return mapper.addUserInfo(phone, code);
	}
	@Override
	public int updateRegCodeByUserid(String userid, String code) {
		return mapper.updateRegCodeByUserid(userid, code);
	}
	@Override
	public int valCodeByphoneAndCode(String phone,String code) {
		return mapper.valCodeByphoneAndCode(phone, code);
	}
	@Override
	public int valCommitCodeByPhoneAndUserId(String commitCode, String phone) {
		return mapper.valCommitCodeByPhoneAndUserId(commitCode, phone);
	}
	@Override
	public int updateUserPasswordByPhone(String phone, String password,String inviteCode) {
		return mapper.updateUserPasswordByPhone(phone, password,inviteCode);
	}
	@Override
	public int updateInviteState(String phone) {
		return mapper.updateInviteState(phone);
	}
	@Override
	public int insertWallet(String phone) {
		return mapper.insertWallet(phone);
	}
	@Override
	public int updateretrievePwdCode(String phone, String code) {
		return mapper.updateretrievePwdCode(phone, code);
	}
	@Override
	public int valrievePhoneCode(String phone, String code) {
		return mapper.valrievePhoneCode(phone, code);
	}
	@Override
	public int retrievePwdUpdate(String phone, String password, String userid) {
		return mapper.retrievePwdUpdate(phone, password, userid);
	}
	@Override
	public int valCommitCodeByPhoneAndUserIdToretrievePwd(String commitCode,
			String phone) {
		return mapper.valCommitCodeByPhoneAndUserIdToretrievePwd(commitCode, phone);
	}
}

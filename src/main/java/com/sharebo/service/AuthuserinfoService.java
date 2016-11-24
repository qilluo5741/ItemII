package com.sharebo.service;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.RegisteredDto;

public interface AuthuserinfoService {
	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public String getAuthloginByuserid(@Param("phone") String phone,
			@Param("password") String password);

	// 修改userToken
	public int updateUserInfoTokenByUserId(String userid, String token);

	// 通过手机号码验证user是否已经存在 密码是否为空
	public RegisteredDto getUserInfoIsNullByPhone(String phone);

	// 添加一个用户 只有手机号，发送时间
	public int addUserInfo(String phone, String code);

	// 根据用户id修改验证码
	public int updateRegCodeByUserid(String userid, String code);

	// 根据手机号码和验证码验证是否比对
	public int valCodeByphoneAndCode(String phone, String code);

	// 验证数据库中的手机号与提交码是否一致
	public int valCommitCodeByPhoneAndUserId(String commitCode, String phone);

	// 验证数据库中的手机号与提交码是否一致
	public int valCommitCodeByPhoneAndUserIdToretrievePwd(String commitCode,
			String phone);

	// 修改用户密码
	public int updateUserPasswordByPhone(String phone, String password,String inviteCode);

	// 修改邀请记录状态
	public int updateInviteState(String phone);

	// 给用户初始化钱包
	public int insertWallet(String phone);

	// 修改找回密码验证码
	public int updateretrievePwdCode(String phone, String code);

	// 验证手机号与找回密码code是否一致
	public int valrievePhoneCode(String phone, String code);

	// 找回密码 修改密码
	public int retrievePwdUpdate(String phone, String password, String userid);
}

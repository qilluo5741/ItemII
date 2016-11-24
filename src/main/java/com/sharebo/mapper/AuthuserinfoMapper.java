package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

public interface AuthuserinfoMapper {
	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public String getAuthloginByuserid(@Param("phone") String phone,
			@Param("password") String password);

	/**
	 * 修改用户token
	 * 
	 * @param userid
	 * @param token
	 * @return
	 */
	public int updateUserInfoTokenByUserId(@Param("userid") String userid,
			@Param("token") String token);

	// 通过手机号码验证user是否已经存在 密码是否为空
	public com.sharebo.entity.dto.RegisteredDto getUserInfoIsNullByPhone(
			String phone);

	// 添加一个用户 只有手机号，发送时间
	public int addUserInfo(@Param("phone") String phone,
			@Param("code") String code);

	// 根据用户id修改验证码
	public int updateRegCodeByUserid(@Param("userid") String userid,
			@Param("code") String code);

	// 根据手机号码和验证码验证是否比对
	public int valCodeByphoneAndCode(@Param("phone") String phone,
			@Param("regCode") String code);

	// 验证数据库中的手机号与提交码是否一致（用户注册）
	public int valCommitCodeByPhoneAndUserId(
			@Param("commitCode") String commitCode, @Param("phone") String phone);

	// 验证数据库中的手机号与提交码是否一致(找回密码)
	public int valCommitCodeByPhoneAndUserIdToretrievePwd(
			@Param("commitCode") String commitCode, @Param("phone") String phone);

	// 修改用户密码
	public int updateUserPasswordByPhone(@Param("phone") String phone,
			@Param("password") String password,@Param("inviteCode") String inviteCode);

	// 修改邀请记录状态
	public int updateInviteState(@Param("phone") String phone);

	// 给用户初始化钱包
	public int insertWallet(@Param("phone") String phone);

	// 修改找回密码验证码
	public int updateretrievePwdCode(@Param("phone") String phone,
			@Param("code") String code);

	// 验证手机号与找回密码code是否一致
	public int valrievePhoneCode(@Param("phone") String phone,
			@Param("code") String code);

	// 找回密码 修改密码
	public int retrievePwdUpdate(@Param("phone") String phone,
			@Param("password") String password, @Param("userid") String userid);

}

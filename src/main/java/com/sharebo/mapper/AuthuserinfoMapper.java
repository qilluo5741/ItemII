package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

public interface AuthuserinfoMapper {
	/**
	 * �û���¼
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public String getAuthloginByuserid(@Param("phone") String phone,
			@Param("password") String password);

	/**
	 * �޸��û�token
	 * 
	 * @param userid
	 * @param token
	 * @return
	 */
	public int updateUserInfoTokenByUserId(@Param("userid") String userid,
			@Param("token") String token);

	// ͨ���ֻ�������֤user�Ƿ��Ѿ����� �����Ƿ�Ϊ��
	public com.sharebo.entity.dto.RegisteredDto getUserInfoIsNullByPhone(
			String phone);

	// ���һ���û� ֻ���ֻ��ţ�����ʱ��
	public int addUserInfo(@Param("phone") String phone,
			@Param("code") String code);

	// �����û�id�޸���֤��
	public int updateRegCodeByUserid(@Param("userid") String userid,
			@Param("code") String code);

	// �����ֻ��������֤����֤�Ƿ�ȶ�
	public int valCodeByphoneAndCode(@Param("phone") String phone,
			@Param("regCode") String code);

	// ��֤���ݿ��е��ֻ������ύ���Ƿ�һ�£��û�ע�ᣩ
	public int valCommitCodeByPhoneAndUserId(
			@Param("commitCode") String commitCode, @Param("phone") String phone);

	// ��֤���ݿ��е��ֻ������ύ���Ƿ�һ��(�һ�����)
	public int valCommitCodeByPhoneAndUserIdToretrievePwd(
			@Param("commitCode") String commitCode, @Param("phone") String phone);

	// �޸��û�����
	public int updateUserPasswordByPhone(@Param("phone") String phone,
			@Param("password") String password,@Param("inviteCode") String inviteCode);

	// �޸������¼״̬
	public int updateInviteState(@Param("phone") String phone);

	// ���û���ʼ��Ǯ��
	public int insertWallet(@Param("phone") String phone);

	// �޸��һ�������֤��
	public int updateretrievePwdCode(@Param("phone") String phone,
			@Param("code") String code);

	// ��֤�ֻ������һ�����code�Ƿ�һ��
	public int valrievePhoneCode(@Param("phone") String phone,
			@Param("code") String code);

	// �һ����� �޸�����
	public int retrievePwdUpdate(@Param("phone") String phone,
			@Param("password") String password, @Param("userid") String userid);

}

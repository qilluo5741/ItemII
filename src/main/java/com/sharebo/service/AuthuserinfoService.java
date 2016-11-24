package com.sharebo.service;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.RegisteredDto;

public interface AuthuserinfoService {
	/**
	 * �û���¼
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	public String getAuthloginByuserid(@Param("phone") String phone,
			@Param("password") String password);

	// �޸�userToken
	public int updateUserInfoTokenByUserId(String userid, String token);

	// ͨ���ֻ�������֤user�Ƿ��Ѿ����� �����Ƿ�Ϊ��
	public RegisteredDto getUserInfoIsNullByPhone(String phone);

	// ���һ���û� ֻ���ֻ��ţ�����ʱ��
	public int addUserInfo(String phone, String code);

	// �����û�id�޸���֤��
	public int updateRegCodeByUserid(String userid, String code);

	// �����ֻ��������֤����֤�Ƿ�ȶ�
	public int valCodeByphoneAndCode(String phone, String code);

	// ��֤���ݿ��е��ֻ������ύ���Ƿ�һ��
	public int valCommitCodeByPhoneAndUserId(String commitCode, String phone);

	// ��֤���ݿ��е��ֻ������ύ���Ƿ�һ��
	public int valCommitCodeByPhoneAndUserIdToretrievePwd(String commitCode,
			String phone);

	// �޸��û�����
	public int updateUserPasswordByPhone(String phone, String password,String inviteCode);

	// �޸������¼״̬
	public int updateInviteState(String phone);

	// ���û���ʼ��Ǯ��
	public int insertWallet(String phone);

	// �޸��һ�������֤��
	public int updateretrievePwdCode(String phone, String code);

	// ��֤�ֻ������һ�����code�Ƿ�һ��
	public int valrievePhoneCode(String phone, String code);

	// �һ����� �޸�����
	public int retrievePwdUpdate(String phone, String password, String userid);
}

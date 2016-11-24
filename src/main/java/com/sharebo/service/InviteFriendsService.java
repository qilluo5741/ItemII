package com.sharebo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.InviteFriends;
import com.sharebo.entity.dto.AuthUserinfo;
import com.sharebo.entity.dto.InviteFriendsDto;
public interface InviteFriendsService {
    /**
     * �����¼��ѯ
     * @param userid
	 * @return
     */
	public List<InviteFriendsDto> selectInviteFriendsInfo(Map<String, Object> map);
	/**
	 * �����¼������ѯ
	 * @param userid
	 * @return
	 */
	public Integer selectCount(@Param("userid")String userid);
    /**
     * �������
     * @param in
     * @return
     */
	public int insertInviteFriends(InviteFriends in);
	/**
	 * ��֤����ĺ����Ƿ��ѱ�����
	 * @param phone
	 * @return
	 */
	public int valueFriends(@Param("tophone")String tophone);
	/**
	 * ��֤����ĺ����Ƿ��Ѿ�ע��
	 * @param tophone
	 * @return
	 */
	public int valueFriendsisRegist(@Param("tophone")String tophone);
	/**
	 * ��ѯ������
	 * @param userid
	 * @return
	 */
	public String selectInviteCode(@Param("userid")String userid);
	/**
	 * ��ѯ�ֻ�����
	 * @param userid
	 * @return
	 */
	public String selectPhone(@Param("userid")String userid);
	//��ѯ�Ѿ�ע����û�
	public List<AuthUserinfo> selectPhoneisexist(@Param("userid")String userid);
	//�޸�״̬����״̬
	public int updateinviteState(@Param("tophone")String tophone);
}

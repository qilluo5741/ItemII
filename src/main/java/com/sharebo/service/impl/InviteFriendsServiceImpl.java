package com.sharebo.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.InviteFriends;
import com.sharebo.entity.dto.AuthUserinfo;
import com.sharebo.entity.dto.InviteFriendsDto;
import com.sharebo.mapper.InviteFriendsMapper;
import com.sharebo.service.InviteFriendsService;
@Service
public class InviteFriendsServiceImpl implements InviteFriendsService {
	@Autowired
	private InviteFriendsMapper mapper;
	 /**
     * �����¼��ѯ
     * @param userid
	 * @return
     */
	@Override
	public List<InviteFriendsDto> selectInviteFriendsInfo(Map<String, Object> map) {
		return mapper.selectInviteFriendsInfo(map);
	}
	/**
	 * �����¼������ѯ
	 * @param userid
	 * @return
	 */
	public Integer selectCount(@Param("userid")String userid){
		return mapper.selectCount(userid);
	}
	/**
     * �������
     * @param in
     * @return
     */
	@Override
	public int insertInviteFriends(InviteFriends in) {
		return mapper.insertInviteFriends(in);
	}
	/**
	 * ��֤����ĺ����Ƿ��ѱ�����
	 * @param phone
	 * @return
	 */
	@Override
	public int valueFriends(String tophone) {
		return mapper.valueFriends(tophone);
	}
	/**
	 * ��֤����ĺ����Ƿ��Ѿ�ע��
	 * @param tophone
	 * @return
	 */
	@Override
	public int valueFriendsisRegist(String tophone) {
		return mapper.valueFriendsisRegist(tophone);
	}
	/**
	 * ��ѯ������
	 * @param userid
	 * @return
	 */
	@Override
	public String selectInviteCode(String userid) {
		return mapper.selectInviteCode(userid);
	}
	/**
	 * ��ѯ�ֻ�����
	 * @param userid
	 * @return
	 */
	@Override
	public String selectPhone(String userid) {
		return mapper.selectPhone(userid);
	}
	@Override
	public List<AuthUserinfo> selectPhoneisexist(String userid) {
		return mapper.selectPhoneisexist(userid);
	}
	@Override
	public int updateinviteState(String tophone) {
		return mapper.updateinviteState(tophone);
	}
}

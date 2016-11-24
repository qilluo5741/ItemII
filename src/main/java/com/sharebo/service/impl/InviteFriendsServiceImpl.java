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
     * 邀请记录查询
     * @param userid
	 * @return
     */
	@Override
	public List<InviteFriendsDto> selectInviteFriendsInfo(Map<String, Object> map) {
		return mapper.selectInviteFriendsInfo(map);
	}
	/**
	 * 邀请记录总数查询
	 * @param userid
	 * @return
	 */
	public Integer selectCount(@Param("userid")String userid){
		return mapper.selectCount(userid);
	}
	/**
     * 邀请好友
     * @param in
     * @return
     */
	@Override
	public int insertInviteFriends(InviteFriends in) {
		return mapper.insertInviteFriends(in);
	}
	/**
	 * 验证邀请的好友是否已被邀请
	 * @param phone
	 * @return
	 */
	@Override
	public int valueFriends(String tophone) {
		return mapper.valueFriends(tophone);
	}
	/**
	 * 验证邀请的好友是否已经注册
	 * @param tophone
	 * @return
	 */
	@Override
	public int valueFriendsisRegist(String tophone) {
		return mapper.valueFriendsisRegist(tophone);
	}
	/**
	 * 查询邀请码
	 * @param userid
	 * @return
	 */
	@Override
	public String selectInviteCode(String userid) {
		return mapper.selectInviteCode(userid);
	}
	/**
	 * 查询手机号码
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

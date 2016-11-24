package com.sharebo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.InviteFriends;
import com.sharebo.entity.dto.AuthUserinfo;
import com.sharebo.entity.dto.InviteFriendsDto;
public interface InviteFriendsService {
    /**
     * 邀请记录查询
     * @param userid
	 * @return
     */
	public List<InviteFriendsDto> selectInviteFriendsInfo(Map<String, Object> map);
	/**
	 * 邀请记录总数查询
	 * @param userid
	 * @return
	 */
	public Integer selectCount(@Param("userid")String userid);
    /**
     * 邀请好友
     * @param in
     * @return
     */
	public int insertInviteFriends(InviteFriends in);
	/**
	 * 验证邀请的好友是否已被邀请
	 * @param phone
	 * @return
	 */
	public int valueFriends(@Param("tophone")String tophone);
	/**
	 * 验证邀请的好友是否已经注册
	 * @param tophone
	 * @return
	 */
	public int valueFriendsisRegist(@Param("tophone")String tophone);
	/**
	 * 查询邀请码
	 * @param userid
	 * @return
	 */
	public String selectInviteCode(@Param("userid")String userid);
	/**
	 * 查询手机号码
	 * @param userid
	 * @return
	 */
	public String selectPhone(@Param("userid")String userid);
	//查询已经注册的用户
	public List<AuthUserinfo> selectPhoneisexist(@Param("userid")String userid);
	//修改状态邀请状态
	public int updateinviteState(@Param("tophone")String tophone);
}

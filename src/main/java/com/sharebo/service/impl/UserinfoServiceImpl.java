package com.sharebo.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.dto.UserDto;
import com.sharebo.entity.dto.Userheadportrait;
import com.sharebo.entity.dto.Userinfodto;
import com.sharebo.mapper.UserinfoMapper;
import com.sharebo.service.UserinfoService;
@Service
public class UserinfoServiceImpl implements UserinfoService {
	@Autowired
	private UserinfoMapper mapper;
	/**
	 * 查询用户基本信息
	 */
	@Override
	public Userinfodto getloadAllUserinfotype(String userid) {
		return mapper.getloadAllUserinfotype(userid);
	}
	/**
	 * 修改头像
	 */
	@Override
	public int updateheadportrait(String headportrait, String userid) {
		return mapper.updateheadportrait(headportrait, userid);
	}
	/**
	 * 修改姓名
	 */
	@Override
	public int updatename(String name, String userid) {
		return mapper.updatename(name, userid);
	}
	/**
	 * 修改性别
	 */
	@Override
	public int updatesex(Integer sex, String userid) {
		return mapper.updatesex(sex, userid);
	}
	/**
	 * 修改年龄
	 */
	@Override
	public int updateage(Integer age, String userid) {
		return mapper.updateage(age, userid);
	}
	/**
	 * 修改驾龄
	 */
	@Override
	public int updatebeenDriv(Integer beenDriv, String userid) {
		return mapper.updatebeenDriv(beenDriv, userid);
	}
	/**
	 * 修改个性签名
	 */
	@Override
	public int updatesignature(String signature, String userid) {
		return mapper.updatesignature(signature, userid);
	}
	/**
	 * 查询RegId
	 * @param userid
	 * @return
	 */
	@Override
	public String getregidByUserid(String userid) {
		return mapper.getregidByUserid(userid);
	}
	/**
	 * 修改regid
	 * @param regid
	 * @param userid
	 * @return
	 */
	@Override
	public int updateRegIdByUserid(String regid, String userid) {
		return mapper.updateRegIdByUserid(regid, userid);
	}
	/**
	 * 修改payNo
	 * @param userid
	 * @param payNo
	 * @return
	 */
	@Override
	public int updatePayNoByUserid(String userid, String payNo) {
		return mapper.updatePayNoByUserid(userid, payNo);
	}
	/**
	 * 查询用户收款账户
	 * @param userid
	 * @return
	 */
	public String getPayNoByUserid(@Param("userid")String userid){
		return mapper.getPayNoByUserid(userid);
	}
	@Override
	public Integer getvalTokenByUserid(String valToken, String userid) {
		return mapper.getvalTokenByUserid(valToken, userid);
	}
	/**
	 * 查询用户支付宝账户，可用余额
	 * @param userid
	 * @return
	 */
	@Override
	public UserDto selectPayNoAndAvailableBalance(String userid) {
		return mapper.selectPayNoAndAvailableBalance(userid);
	}
	@Override
	public List<Userinfodto> selectbyuserid() {
		return mapper.selectbyuserid();
	}
	@Override
	public Integer getdelectvalTokenByUserid(String valToken, String userid) {
		return mapper.getdelectvalTokenByUserid(valToken, userid);
	}
	@Override
	public Userheadportrait getUserheadportraitByUserid(String userid) {
		return mapper.getUserheadportraitByUserid(userid);
	}
	@Override
	public Integer getvalreactivetimeByUserid(String userid) {
		return mapper.getvalreactivetimeByUserid(userid);
	}
}

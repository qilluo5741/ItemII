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
	 * ��ѯ�û�������Ϣ
	 */
	@Override
	public Userinfodto getloadAllUserinfotype(String userid) {
		return mapper.getloadAllUserinfotype(userid);
	}
	/**
	 * �޸�ͷ��
	 */
	@Override
	public int updateheadportrait(String headportrait, String userid) {
		return mapper.updateheadportrait(headportrait, userid);
	}
	/**
	 * �޸�����
	 */
	@Override
	public int updatename(String name, String userid) {
		return mapper.updatename(name, userid);
	}
	/**
	 * �޸��Ա�
	 */
	@Override
	public int updatesex(Integer sex, String userid) {
		return mapper.updatesex(sex, userid);
	}
	/**
	 * �޸�����
	 */
	@Override
	public int updateage(Integer age, String userid) {
		return mapper.updateage(age, userid);
	}
	/**
	 * �޸ļ���
	 */
	@Override
	public int updatebeenDriv(Integer beenDriv, String userid) {
		return mapper.updatebeenDriv(beenDriv, userid);
	}
	/**
	 * �޸ĸ���ǩ��
	 */
	@Override
	public int updatesignature(String signature, String userid) {
		return mapper.updatesignature(signature, userid);
	}
	/**
	 * ��ѯRegId
	 * @param userid
	 * @return
	 */
	@Override
	public String getregidByUserid(String userid) {
		return mapper.getregidByUserid(userid);
	}
	/**
	 * �޸�regid
	 * @param regid
	 * @param userid
	 * @return
	 */
	@Override
	public int updateRegIdByUserid(String regid, String userid) {
		return mapper.updateRegIdByUserid(regid, userid);
	}
	/**
	 * �޸�payNo
	 * @param userid
	 * @param payNo
	 * @return
	 */
	@Override
	public int updatePayNoByUserid(String userid, String payNo) {
		return mapper.updatePayNoByUserid(userid, payNo);
	}
	/**
	 * ��ѯ�û��տ��˻�
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
	 * ��ѯ�û�֧�����˻����������
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

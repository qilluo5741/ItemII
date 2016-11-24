package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.UserDto;
import com.sharebo.entity.dto.Userheadportrait;
import com.sharebo.entity.dto.Userinfodto;

public interface UserinfoMapper {
	/**
	 * ��ѯ������Ϣ
	 * @param userid
	 * @return
	 */
	public Userinfodto getloadAllUserinfotype(@Param("userid")String userid);
	/**
	 * �޸�ͷ��
	 * @param headportrait
	 * @param userid
	 * @return
	 */
	public int updateheadportrait(@Param("headportrait")String headportrait,@Param("userid")String userid);
	/**
	 * �޸�����
	 * @param name
	 * @param userid
	 * @return
	 */
	public int updatename(@Param("name")String name,@Param("userid")String userid);
	/**
	 * �޸��Ա�
	 * @param sex
	 * @param userid
	 * @return
	 */
	public int updatesex(@Param("sex")Integer sex,@Param("userid")String userid);
	/**
	 * �޸�����
	 * @param age
	 * @param userid
	 * @return
	 */
	public int updateage(@Param("age")Integer age,@Param("userid")String userid);
	/**
	 * �޸ļ���
	 * @param beenDriv
	 * @param userid
	 * @return
	 */
	public int updatebeenDriv(@Param("beenDriv")Integer beenDriv,@Param("userid")String userid);
	/**
	 * �޸ĸ���ǩ��
	 * @param signature
	 * @param userid
	 * @return
	 */
	public int updatesignature(@Param("signature")String signature,@Param("userid")String userid);
	/**
	 * ��ѯRegId
	 * @param userid
	 * @return
	 */
	public String getregidByUserid(@Param("userid")String userid);
	/**
	 * �޸�regid
	 * @param regid
	 * @param userid
	 * @return
	 */
	public int updateRegIdByUserid(@Param("regid")String regid,@Param("userid")String userid);
	/**
	 * �޸�payNo
	 * @param userid
	 * @param payNo
	 * @return
	 */
	public int updatePayNoByUserid(@Param("userid")String userid,@Param("payNo")String payNo);
	/**
	 * ��ѯ�û��տ��˻�
	 * @param userid
	 * @return
	 */
	public String getPayNoByUserid(@Param("userid")String userid);
	/**
	 * ��ѯͷ��ͷ��
	 * @param userid
	 * @return
	 */
	public Userheadportrait getUserheadportraitByUserid(@Param("userid")String userid);
	/**
	 * ��֤valToken�Ƿ�ʧЧ
	 * @param valToken
	 * @param userid
	 * @return
	 */
	public Integer getvalTokenByUserid(@Param("valToken")String valToken,@Param("userid")String userid);
	/**
	 * ��ѯ�û�֧�����˻����������
	 * @param userid
	 * @return
	 */
	public UserDto selectPayNoAndAvailableBalance(@Param("userid")String userid);
	public List<Userinfodto> selectbyuserid();
	/**
	 * �˳����valToken
	 * @param valToken
	 * @param userid
	 * @return
	 */
	public Integer getdelectvalTokenByUserid(@Param("valToken")String valToken,@Param("userid")String userid);
	/**
	 * �޸Ļ�Ծʱ��
	 * @param userid
	 * @return
	 */
	public Integer getvalreactivetimeByUserid(@Param("userid")String userid);
}

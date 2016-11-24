package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Carcollection;
import com.sharebo.entity.dto.AltogetherDto;
import com.sharebo.entity.dto.PersonalareaDto;

public interface CarcollectionMapper {
	/**
	 * �û���λ�ղ�
	 * @param carcoll
	 * @return
	 */
	public int addCarcollection(Carcollection carcoll);
	/**
	 * ��ѯʧЧ�Ƿ����
	 * @param carType
	 * @param userid
	 * @param identifying
	 * @return
	 */
	public int getCarcollectionydoesitexist(@Param("carType")Integer carType,@Param("userid")String userid,@Param("identifying")String identifying);
	/**
	 * ɾ���ղ�
	 */
	public int updateCarcollectiony(@Param("isDelete")Integer isDelete,@Param("userid")String userid,@Param("carType")Integer carType,@Param("identifying")String identifying);
	/**
	 * ����ͣ������ѯ
	 * @param userid
	 * @return
	 */
	public List<AltogetherDto> getloadAllAltogether(Map<String, Object> map);
	/**
	 * ����ͣ������ѯ����
	 * @param userid
	 * @return
	 */
	public Integer getAltogetherCount(@Param("userid")String userid);
	/**
	 * ���˳�λ��ѯ
	 * @param userid
	 * @return
	 */
	public List<PersonalareaDto> getloadAllPersonalarea(Map<String, Object> map);
	/**
	 * ���˳�λ��ѯ
	 * @param userid
	 * @return
	 */
	public Integer getloadAllPersonalareaCount(@Param("userid")String userid);
	
}

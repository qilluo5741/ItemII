package com.sharebo.mapper;
import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Carauthentication;

public interface CarauthenticationMapper {
	/**
	 * �����֤��Ϣ
	 * @param Carauth
	 * @return
	 */
	public int addCarauthentication(Carauthentication Carauth);
	/**
	 * �޸ĸ�����֤��Ϣ
	 * @param name
	 * @param userid
	 * @return
	 */
	//public int updateCarauthentication(Carauthentication Carauth);
	/**
	 * ��ѯ�Ƿ���֤
	 * @param userid
	 * @return
	 */
	public int getcarauthenticationIdBydoesitexist(@Param("userid")String userid);
}

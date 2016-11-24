package com.sharebo.service;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Carauthentication;

public interface CarauthenticationService {
	/**
	 * 添加认证信息
	 * @param Carauth
	 * @return
	 */
	public int addCarauthentication(Carauthentication Carauth);
	/**
	 * 修改个人认证信息
	 * @param name
	 * @param userid
	 * @return
	 */
	//public int updateCarauthentication(Carauthentication Carauth);
	/**
	 * 查询是否认证
	 * @param userid
	 * @return
	 */
	public int getcarauthenticationIdBydoesitexist(@Param("userid")String userid);
}

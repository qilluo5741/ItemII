package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.Carauthentication;
import com.sharebo.mapper.CarauthenticationMapper;
import com.sharebo.service.CarauthenticationService;
@Service
public class CarauthenticationServiceImpl implements CarauthenticationService {
	@Autowired
	private CarauthenticationMapper mapper;
	@Override
	public int addCarauthentication(Carauthentication Carauth) {
		return mapper.addCarauthentication(Carauth);
	}
	/*@Override
	public int updateCarauthentication(Carauthentication Carauth) {
		return mapper.updateCarauthentication(Carauth);
	}*/
	@Override
	public int getcarauthenticationIdBydoesitexist(String userid) {
		return mapper.getcarauthenticationIdBydoesitexist(userid);
	}
}

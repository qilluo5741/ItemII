package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.ParkPayment;
import com.sharebo.mapper.ParkPaymentMapper;
import com.sharebo.service.ParkPaymentService;
@Service
public class ParkPaymentServiceImpl implements ParkPaymentService {
	@Autowired
	private ParkPaymentMapper mapper;
	/**
	 * Í£³µ½É·Ñ
	 */
	@Override
	public ParkPayment getParkPayment(String userid) {
		return mapper.getParkPayment(userid);
	}
}

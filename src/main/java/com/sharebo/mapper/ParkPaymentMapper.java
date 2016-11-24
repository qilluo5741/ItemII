package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.ParkPayment;

public interface ParkPaymentMapper {
	/**
	 * Í£³µ½É·Ñ
	 * @param userid
	 * @return
	 */
	public ParkPayment getParkPayment(@Param("userid")String userid);
}

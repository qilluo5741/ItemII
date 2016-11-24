package com.sharebo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.ParkPayment;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.ParkPaymentService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/payment")
public class ParkPaymentController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ParkPaymentService service;
	/**
	 * ͣ���ɷ�
	 * @param userid
	 * @return
	 * payment/getpayment.do?userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="getpayment",method=RequestMethod.POST)
	public ResultDto ParkPayment(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			ParkPayment park=service.getParkPayment(userid);
			if(park!=null){
				return new ResultDto(200,"�ɹ�!",park);
			}
		} catch (Exception e) {
			log.error("ͣ���ɷѲ����쳣��");
			System.out.println("ͣ���ɷѲ����쳣��");
		}
		return new ResultDto(2001,"�������ݣ�");
	}
}

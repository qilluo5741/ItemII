package com.sharebo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Statistical;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.StatisticalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/statis")
public class StatisticalController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StatisticalService service;
	/**
	 * ��Ӹ���ͳ�Ʊ���
	 * @param userid
	 * @return
	 * statis/getStatistical.do?userid=96748217152045057
	 */
	@ResponseBody
	@RequestMapping(value="getStatistical",method=RequestMethod.POST)
	public ResultDto getStatistical(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			Statistical stical=new Statistical();
			stical.setUserid(userid);
			stical.setStatisticalTime(new Date());
			int i=service.addStatistical(stical);
			if(i>0){
				return new ResultDto(200,"�ɹ�");
			}
		} catch (Exception e) {
			log.error("����û��������ݲ����쳣��");
			System.out.println("����û��������ݲ����쳣��");
		}
		return new ResultDto(2001,"�����쳣��");
	}
}

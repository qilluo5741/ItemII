package com.sharebo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.dto.ProprietaryDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.ProprietaryService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/proprie")
public class ProprietaryController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProprietaryService Service;
	/**
	 * ר�г�λ��ѯ
	 * @param carportId
	 * @return
	 * proprie/getproprietary.do?carportId=007dc9054f2e4556a02a02aefd6bb5ea
	 */
	@ResponseBody
	@RequestMapping(value="getproprietary",method=RequestMethod.POST)
	public ResultDto getDetails(String carportId){
		try {
			if(carportId==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			ProprietaryDto pro=Service.getloadAllProprietary(carportId);
			if(pro!=null){
				return new ResultDto(200,"�ɹ�",pro);
			}
		} catch (Exception e) {
			log.error("ר�г�λ��������쳣��");
			System.out.println("ר�г�λ��������쳣��");
		}
		return new ResultDto(2001,"�������ݣ�");
	}
}

package com.sharebo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.dto.Parkcooperation;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.ParkcooperationService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/parkcoop")
public class ParkcooperationController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ParkcooperationService pService;
	@ResponseBody
	/**
	 * ���ͣ��������
	 * @param contactName
	 * @param phone
	 * @param entrance
	 * @param type
	 * @param money
	 * @param feeType
	 * @param beginTime
	 * @param endTime
	 * @param cooperationintention
	 * @param housId
	 * @return
	 * parkcoop/insertParkcoop.do
	 */
	@RequestMapping(value="insertParkcoop",method=RequestMethod.POST)
	public ResultDto insertParkcoop(Parkcooperation park){
		try {
			if(park.getContactName()==null||park.getPhone()==null || park.getHousId()==null||park.getMoney()==null){
				return new ResultDto(3006,"�����������Ϊ��");
			}
			System.out.println(park.getFeeType());
			if(pService.insertParkcooperation(park)>0){
				return new ResultDto(200,"¼��ɹ�");
			}
		} catch (Exception e) {
			log.error("���ͣ�������������쳣��");
			System.out.println("���ͣ�������������쳣");
		}
		 return new ResultDto(3013,"¼��ʧ��");
	}
}


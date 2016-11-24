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
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
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
	 * 添加停车场合作
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
				return new ResultDto(3006,"请求参数不能为空");
			}
			System.out.println(park.getFeeType());
			if(pService.insertParkcooperation(park)>0){
				return new ResultDto(200,"录入成功");
			}
		} catch (Exception e) {
			log.error("添加停车场合作参数异常！");
			System.out.println("添加停车场合作参数异常");
		}
		 return new ResultDto(3013,"录入失败");
	}
}


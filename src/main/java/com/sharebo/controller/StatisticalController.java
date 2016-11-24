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
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
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
	 * 添加更新统计报表
	 * @param userid
	 * @return
	 * statis/getStatistical.do?userid=96748217152045057
	 */
	@ResponseBody
	@RequestMapping(value="getStatistical",method=RequestMethod.POST)
	public ResultDto getStatistical(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			Statistical stical=new Statistical();
			stical.setUserid(userid);
			stical.setStatisticalTime(new Date());
			int i=service.addStatistical(stical);
			if(i>0){
				return new ResultDto(200,"成功");
			}
		} catch (Exception e) {
			log.error("添加用户反馈内容参数异常！");
			System.out.println("添加用户反馈内容参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
}

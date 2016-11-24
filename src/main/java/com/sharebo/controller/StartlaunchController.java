package com.sharebo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.Startlaunch;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.StartlaunchService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/launch")
public class StartlaunchController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StartlaunchService Service;
	/**
	 * launch/launchsement.do?startsize=1&startopinion=c31b32364ce19ca8fcd150a417ecce58
	 * @param startsize
	 * @param startopinion
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="launchsement",method=RequestMethod.POST)
	public ResultDto getStartlaunchAll(int startsize,String startopinion){
		try {
			if(startsize>0 || startopinion==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			Startlaunch launch=Service.getloadAllStartlaunch(startsize, startopinion);
			if(launch!=null){
				return new ResultDto(200,"��ѯ�ɹ�",launch);
			}
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("��������쳣��");
		}
		return new ResultDto(3002,"��������");
	}
}

package com.sharebo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.dto.IconDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.IconService;
import com.sharebo.service.IconversionService;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/icon")
public class IconController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconversionService icoservice;
	@Autowired
	private IconService service;
	/**
	 * 图标icon
	 * icon/getIcontisement.do?opinions=9e304d4e8df1b74cfa009913198428ab
	 * @param opinions
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getIcontisement",method=RequestMethod.POST)
	public ResultDto getIcontisement(String opinions){
		try {
			List<IconDto> icon=service.getloadIconertis(opinions);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("icon",icon);
			map.put("version",icoservice.getloadAllconversion().getIconNo()); 
			return new ResultDto(200,"成功",map);
		} catch (Exception e) {
			log.error("ICON信息参数异常！");
			System.out.println("ICON信息参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
}

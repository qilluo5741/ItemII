package com.sharebo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.AppVersion;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.AppVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/appversion")
public class AppVersionController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppVersionService service;
	/**
	 * 版本更新
	 * @return
	 * appversion/getversion.do
	 */
	@ResponseBody 
	@RequestMapping(value="getversion",method=RequestMethod.POST)
	public ResultDto getversion(){
		try {
			AppVersion vertion=service.getloadAppVersionAll();
			if(vertion!=null){
				return new ResultDto(200,"成功",vertion);
			}
		} catch (Exception e) {
			log.error("查询版本信息参数异常！");
			System.out.println("查询版本信息参数异常！");
		}
		return new ResultDto(2003,"暂无数据！");
	}
}

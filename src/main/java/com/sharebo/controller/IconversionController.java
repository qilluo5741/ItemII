package com.sharebo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.Iconversion;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.IconversionService;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/iconversion")
public class IconversionController{
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconversionService icoService;
	/**
	 * @return
	 * iconversion/getersion.do
	 */
	@ResponseBody
	@RequestMapping(value="getersion",method=RequestMethod.POST)
	public ResultDto getloadAlliconversion(){
		try {
			Iconversion ico=icoService.getloadAllconversion();
			if(ico!=null){
				return new ResultDto(200,"成功",ico);
			}
		} catch (Exception e) {
			log.error("查询版本验证参数异常！");
			System.out.println("查询版本验证参数异常");
		}
		return new ResultDto(2003,"暂无数据！");
	}
}

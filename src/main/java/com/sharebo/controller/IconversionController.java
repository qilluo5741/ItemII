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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
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
				return new ResultDto(200,"�ɹ�",ico);
			}
		} catch (Exception e) {
			log.error("��ѯ�汾��֤�����쳣��");
			System.out.println("��ѯ�汾��֤�����쳣");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
}

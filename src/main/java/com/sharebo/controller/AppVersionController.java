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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/appversion")
public class AppVersionController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AppVersionService service;
	/**
	 * �汾����
	 * @return
	 * appversion/getversion.do
	 */
	@ResponseBody 
	@RequestMapping(value="getversion",method=RequestMethod.POST)
	public ResultDto getversion(){
		try {
			AppVersion vertion=service.getloadAppVersionAll();
			if(vertion!=null){
				return new ResultDto(200,"�ɹ�",vertion);
			}
		} catch (Exception e) {
			log.error("��ѯ�汾��Ϣ�����쳣��");
			System.out.println("��ѯ�汾��Ϣ�����쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
}

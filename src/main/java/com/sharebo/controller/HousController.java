package com.sharebo.controller;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Hous;
import com.sharebo.entity.dto.HousDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.HousService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/hous")
public class HousController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HousService Service;
	/**
	 * ģ����ѯ
	 * @param housName
	 * @return
	 * hous/gethous.do?housName=1
	 */
	@ResponseBody
	@RequestMapping(value="gethous",method=RequestMethod.POST)
	public ResultDto getselectByhousNameorhousAddress(String housName){
		try {
			if(housName==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			List<HousDto> list=Service.selectByhousNameorhousAddress(housName);
			if(list!=null){
				return new ResultDto(200,"�ɹ�",list);
			}
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("��������쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
	/**
	 * ͣ�������
	 * @param housName
	 * @param housAddress
	 * @return
	 * hous/addhous.do?housName=655&housAddress=5645
	 */
	@ResponseBody
	@RequestMapping(value="addhous",method=RequestMethod.POST)
	public ResultDto addhous(String housName,String housAddress){
		try {
			if(housName==null || housAddress==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			String housNames=Service.gethousNameBydoesitexist(housName);
			if(housNames==null){
				Hous hous=new Hous();
				hous.setHousName(housName);
				hous.setHousAddress(housAddress);
				hous.setCreateTime(new Date());
				int i=Service.addHous(hous);
				if(i>0){
					return new ResultDto(200,"��ӳɹ�,��ȴ���ˡ�");
				}
			}else{
				return new ResultDto(2010,"��ͣ�����Ѿ����ڣ�");	
			}
		} catch (Exception e) {            
			log.error("���ͣ������Ϣ�����쳣��");
			System.out.println("���ͣ������Ϣ�����쳣��");
		}
		return new ResultDto(2007,"���ʧ��");
	}
}

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
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/hous")
public class HousController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HousService Service;
	/**
	 * 模糊查询
	 * @param housName
	 * @return
	 * hous/gethous.do?housName=1
	 */
	@ResponseBody
	@RequestMapping(value="gethous",method=RequestMethod.POST)
	public ResultDto getselectByhousNameorhousAddress(String housName){
		try {
			if(housName==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			List<HousDto> list=Service.selectByhousNameorhousAddress(housName);
			if(list!=null){
				return new ResultDto(200,"成功",list);
			}
		} catch (Exception e) {
			log.error("请求参数异常！");
			System.out.println("请求参数异常！");
		}
		return new ResultDto(2003,"暂无数据！");
	}
	/**
	 * 停车场添加
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
				return new ResultDto(2002,"请求参数为空！");	
			}
			String housNames=Service.gethousNameBydoesitexist(housName);
			if(housNames==null){
				Hous hous=new Hous();
				hous.setHousName(housName);
				hous.setHousAddress(housAddress);
				hous.setCreateTime(new Date());
				int i=Service.addHous(hous);
				if(i>0){
					return new ResultDto(200,"添加成功,请等待审核。");
				}
			}else{
				return new ResultDto(2010,"该停车场已经存在！");	
			}
		} catch (Exception e) {            
			log.error("添加停车场信息参数异常！");
			System.out.println("添加停车场信息参数异常！");
		}
		return new ResultDto(2007,"添加失败");
	}
}

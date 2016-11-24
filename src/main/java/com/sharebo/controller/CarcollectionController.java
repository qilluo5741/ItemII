package com.sharebo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Carcollection;
import com.sharebo.entity.Pager;
import com.sharebo.entity.dto.AltogetherDto;
import com.sharebo.entity.dto.PersonalareaDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.CarcollectionService;

/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * 车位收藏
 * @author Administrator
 */
@Controller
@RequestMapping("/collection")
public class CarcollectionController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CarcollectionService Service;
	/**
	 * 车位收藏
	 * @param carType
	 * @param userid
	 * @param identifying
	 * @return
	 * collection/getcollection.do?carType=1&userid=889390526942412800&identifying=1
	 */
	@ResponseBody
	@RequestMapping(value="getcollection",method=RequestMethod.POST)
	public ResultDto getCarcollection(Integer carType,String userid,String identifying){
		try {
			if(carType==null || userid==null || identifying==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			if(Service.getCarcollectionydoesitexist(carType,userid,identifying)>0){
				Service.updateCarcollectiony(1,userid,carType,identifying);
				return new ResultDto(200,"取消收藏");
			}else{
				Carcollection carc=new Carcollection();
				carc.setCarType(carType);
				carc.setIdentifying(identifying);
				carc.setUserid(userid);
				carc.setIsDelete(0);
				carc.setCollectTime(new Date());
				if(Service.addCarcollection(carc)>0){
					return new ResultDto(200,"收藏成功");
				}
			}
		} catch (Exception e) {
			log.error("收藏参数异常！");
			System.out.println("收藏参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * 个人收藏公共停车场
	 * collection/getAltogether.do?userid=889390526942412800&pageIndex=1&pageSize=2
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getAltogether",method=RequestMethod.POST)
	public ResultDto getloadAllAltogether(String userid,Integer pageIndex,Integer pageSize){
		try {
			if(userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			Pager<AltogetherDto> pager=new Pager<AltogetherDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(Service.getloadAllAltogether(map));
			//查询总数
			pager.setTotalNumber(Service.getAltogetherCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"成功",pager);
		} catch (Exception e) {
			log.error("个人收藏参数异常！");
			System.out.println("个人收藏参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * collection/getPersonalarea.do?userid=889390526942412800&pageIndex=1&pageSize=2
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * 个人车位
	 */
	@ResponseBody
	@RequestMapping(value="getPersonalarea",method=RequestMethod.POST)
	public ResultDto getPersonalarea(String userid,Integer pageIndex,Integer pageSize){
		try {
			if(userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			Pager<PersonalareaDto> pager=new Pager<PersonalareaDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(Service.getloadAllPersonalarea(map));
			//查询总数
			pager.setTotalNumber(Service.getloadAllPersonalareaCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"成功",pager);
		} catch (Exception e) {
			log.error("个人车位参数异常！");
			System.out.println("个人车位参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
}

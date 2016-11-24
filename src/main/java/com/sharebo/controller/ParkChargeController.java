package com.sharebo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.Pager;
import com.sharebo.entity.dto.ParkChargeDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.ParkChargeService;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/parkcharge")
public class ParkChargeController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ParkChargeService service;
	/**
	 * 停车缴费记录
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * parkcharge/getParkCharge.do?userid=889390526942412800&pageIndex=1&pageSize=10
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="getParkCharge")
	 public ResultDto seletInviye(String userid,Integer pageIndex,Integer pageSize){
    	try {
    		if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "请求参数不合法");
			}
    		Pager<ParkChargeDto> pager=new Pager<ParkChargeDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.selectParkCharge(map));
			//查询总数
			pager.setTotalNumber(service.selectParkChargeCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"查询成功",pager);
		} catch (Exception e) {
			log.error("请求参数异常！");
			System.out.println("请求参数异常！");
		}
    	return new ResultDto(3002,"查询失败");
    }
	/**
	 * 停车缴费
	 * @param parkcharge
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="getParkChargeInfo")
	public ResultDto getParkChargeInfo(String parkcharge){
		try {
			if(parkcharge==null){
				return new ResultDto(3006,"请求参数为空");
			}
			if(service.selectParkChargeInfo(parkcharge)!=null){
				return new ResultDto(200,"查询成功",service.selectParkChargeInfo(parkcharge));
			}
		} catch (Exception e) {
			log.error("停车缴费异常！");
			System.out.println("停车缴费异常！");
		}
		return new ResultDto(3002,"查询失败");
	}
}

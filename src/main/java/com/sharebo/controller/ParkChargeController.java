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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
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
	 * ͣ���ɷѼ�¼
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
				return new ResultDto(3006, "����������Ϸ�");
			}
    		Pager<ParkChargeDto> pager=new Pager<ParkChargeDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.selectParkCharge(map));
			//��ѯ����
			pager.setTotalNumber(service.selectParkChargeCount(userid));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"��ѯ�ɹ�",pager);
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("��������쳣��");
		}
    	return new ResultDto(3002,"��ѯʧ��");
    }
	/**
	 * ͣ���ɷ�
	 * @param parkcharge
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="getParkChargeInfo")
	public ResultDto getParkChargeInfo(String parkcharge){
		try {
			if(parkcharge==null){
				return new ResultDto(3006,"�������Ϊ��");
			}
			if(service.selectParkChargeInfo(parkcharge)!=null){
				return new ResultDto(200,"��ѯ�ɹ�",service.selectParkChargeInfo(parkcharge));
			}
		} catch (Exception e) {
			log.error("ͣ���ɷ��쳣��");
			System.out.println("ͣ���ɷ��쳣��");
		}
		return new ResultDto(3002,"��ѯʧ��");
	}
}

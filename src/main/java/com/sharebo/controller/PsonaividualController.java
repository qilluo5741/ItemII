package com.sharebo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Pager;
import com.sharebo.entity.dto.PsonaividualDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.PsonaividualService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/psonaiv")
public class PsonaividualController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired	
	private PsonaividualService Service;
	/**
	 * ���˳�λ��Ϣ��ѯ
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * psonaiv/getPsonaividual.do?userid=889394938746241031&pageIndex=1&pageSize=3
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getPsonaividual",method=RequestMethod.POST)
	public ResultDto getloadAllPsonaividual(String userid,Integer pageIndex,Integer pageSize){
		try {
			if(userid==null || pageIndex==null || pageSize==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			Pager<PsonaividualDto> pager=new Pager<PsonaividualDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(Service.getloadPsonaividualAll(map));
			//��ѯ����
			pager.setTotalNumber(Service.getloadPsonaividualAllCount(userid));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"�ɹ�",pager);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("���˳�λ��Ϣ�����쳣��");
			System.out.println("���˳�λ��Ϣ�����쳣��");
		}
		return new ResultDto(2001,"�����쳣��");
	}
}

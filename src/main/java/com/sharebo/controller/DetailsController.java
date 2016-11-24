package com.sharebo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.dto.DetailsDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.DetailsService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/detail")
public class DetailsController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DetailsService Service;
	/**
	 * ���˳�λ�����ѯ
	 * @param userid
	 * @param identifying
	 * @return
	 * detail/getDetails.do?userid=889390526942412800&identifying=8ad30b8c8d2847bab21658ea9dd4b31f
	 */
	@ResponseBody
	@RequestMapping(value="getDetails",method=RequestMethod.POST)
	public ResultDto getDetails(String userid,String identifying){
		try {
			if(userid==null || identifying==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			DetailsDto list=Service.getloadAllDetails(identifying);
			list.setPhone(list.getPhone().substring(0, 3)+"****"+list.getPhone().substring(7, 11));
			int isexit=Service.getloadAllDetailCount(userid,identifying);
			if(isexit>0){
				list.setIsexist(1);
				list.setStatus(0);
				return new ResultDto(200,"�ɹ�",list);
			}else{
				list.setIsexist(0);
				list.setStatus(0);
				return new ResultDto(200,"�ɹ�",list);
			}
		} catch (Exception e) {
			log.error("��λ��������쳣��");
			System.out.println("��λ��������쳣��");
		}
		return new ResultDto(2001,"�����쳣��");
	}
}

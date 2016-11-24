package com.sharebo.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.Pager;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.entity.dto.WithdrawalRecordDto;
import com.sharebo.service.WithdrawalRecordService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/withdrawalRecord")
public class WithdrawalRecordController {
	private org.slf4j.Logger log=org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WithdrawalRecordService wiService;
	/**
	 * ���ּ�¼��ѯ
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * withdrawalRecord/getwithdrawalRecord.do?userid=889390526942412800&pageIndex=1&pageSize=6
	 */
	@ResponseBody
	@RequestMapping(value="getwithdrawalRecord",method=RequestMethod.POST)
	public ResultDto selectwithdrawalRecord(String userid,Integer pageIndex,Integer pageSize){
		try {
			if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "����������Ϸ�");
			}
			Pager<WithdrawalRecordDto> pager=new Pager<WithdrawalRecordDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			//��ѯ����
			List<WithdrawalRecordDto> list=wiService.getWithdrawalRecordDtoByUserid(map);
			for (WithdrawalRecordDto withdrawalRecordDto : list) {
				withdrawalRecordDto.setCashAccount(withdrawalRecordDto.getCashAccount().substring(0, 3)+"****"+withdrawalRecordDto.getCashAccount().substring(7, 11));
			}
			pager.setList(list);
			//��ѯ����
			pager.setTotalNumber(wiService.pagerWithdrawalRecordCount(userid));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"�ɹ�",pager);
		} catch (Exception e) {
			log.error("��ѯ���ּ�¼��Ϣ�����쳣��");
			System.out.println("��ѯ���ּ�¼��Ϣ�����쳣��");
		}
		return new ResultDto(3006,"δ���в���");
	}
	/**
	 * ������ּ�¼
	 * @param userid
	 * @param money
	 * @return
	 * withdrawalRecord/withdrawal.do?money=100&userid=889390526942412800
	 */
	@RequestMapping(value="withdrawal",method=RequestMethod.POST)
	public @ResponseBody ResultDto withdrawal(String userid,Double money){
		//��֤�����Ƿ�Ϊ��
		if(userid==null||money==null){
			return new ResultDto(3007,"�������Ϸ���");
		}
		
		//�����ǲ���������
		if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)!=6){
			return new ResultDto(3008,"���첻�������壡");
			//System.out.println("���첻��������~~~~Ҫ���������");
		}
		//��֤����Ƿ�Ϸ������ڵ���������
		if(wiService.valAvailableBalance(userid, money)!=1){
			return new ResultDto(3008,"�����쳣���밴����·���ƣ�");
		}
		//��֤֧�����˺��Ƿ�Ϊ��
		if(wiService.valuserinfoByPayNo(userid)==1){
			return new ResultDto(3009,"�������տ��˺ţ�"); 
		}
		//��Ӽ�¼
		//�޸����
		try {
			if(wiService.withdrawalRecord(userid, money)){
				return new ResultDto(200,"���ֳɹ���");
			}
		} catch (Exception e) {
			return new ResultDto(3009,"�����쳣��");
		}
		return new ResultDto(3009,"����ʧ�ܣ�");
	}
}

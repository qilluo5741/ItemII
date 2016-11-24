package com.sharebo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Pager;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.entity.dto.TransactionrecordDto;
import com.sharebo.service.TransactionrecordService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/transa")
public class TransactionrecordController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TransactionrecordService Service;
	/**
	 * @param pageIndex
	 * @param pageSize
	 * @param userid
	 * transa/pagetransa.do?pageIndex=1&pageSize=10&userid=889390526942412800
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="pagetransa",method=RequestMethod.POST)
	public ResultDto pagetransa(Integer pageIndex,Integer pageSize,String userid){
		try {
			if(pageIndex==0 || pageSize==0 || userid==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			Pager<TransactionrecordDto> pager=new Pager<TransactionrecordDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			//��ѯ����
			List<TransactionrecordDto> list=Service.getTransactionrecordByPager(map);
			for (int i=0;i<list.size();i++) {
				TransactionrecordDto transactionrecordDto=list.get(i);
				if(transactionrecordDto.getTradeType().equals("1")){
					transactionrecordDto.setTradeType("��������");
				}else if(transactionrecordDto.getTradeType().equals("2")){
					transactionrecordDto.setTradeType("���֧��");
				}else if(transactionrecordDto.getTradeType().equals("3")){
					transactionrecordDto.setTradeType("�˿���");
				}else if(transactionrecordDto.getTradeType().equals("4")){
					transactionrecordDto.setTradeType("֧����֧��");
				}else if(transactionrecordDto.getTradeType().equals("5")){
					transactionrecordDto.setTradeType("΢��֧��");
				}else if(transactionrecordDto.getTradeType().equals("6")){
					transactionrecordDto.setTradeType("...����");
				}
			}
			pager.setList(list);
			//��ѯ����
			pager.setTotalNumber(Service.getTransactionrecordcount(userid));
			pager.setTotalPages();//������ҳ��
			if(pager!=null){
				return new ResultDto(200,"�ɹ�",pager);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��ѯ��Ϣ�����쳣��");
			System.out.println("��ѯ��Ϣ�����쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
}

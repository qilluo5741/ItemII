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
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/withdrawalRecord")
public class WithdrawalRecordController {
	private org.slf4j.Logger log=org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WithdrawalRecordService wiService;
	/**
	 * 提现记录查询
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
				return new ResultDto(3006, "请求参数不合法");
			}
			Pager<WithdrawalRecordDto> pager=new Pager<WithdrawalRecordDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			//查询集合
			List<WithdrawalRecordDto> list=wiService.getWithdrawalRecordDtoByUserid(map);
			for (WithdrawalRecordDto withdrawalRecordDto : list) {
				withdrawalRecordDto.setCashAccount(withdrawalRecordDto.getCashAccount().substring(0, 3)+"****"+withdrawalRecordDto.getCashAccount().substring(7, 11));
			}
			pager.setList(list);
			//查询总数
			pager.setTotalNumber(wiService.pagerWithdrawalRecordCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"成功",pager);
		} catch (Exception e) {
			log.error("查询提现记录信息参数异常！");
			System.out.println("查询提现记录信息参数异常！");
		}
		return new ResultDto(3006,"未进行操作");
	}
	/**
	 * 添加提现记录
	 * @param userid
	 * @param money
	 * @return
	 * withdrawalRecord/withdrawal.do?money=100&userid=889390526942412800
	 */
	@RequestMapping(value="withdrawal",method=RequestMethod.POST)
	public @ResponseBody ResultDto withdrawal(String userid,Double money){
		//验证参数是否为空
		if(userid==null||money==null){
			return new ResultDto(3007,"参数不合法！");
		}
		
		//今天是不是星期五
		if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)!=6){
			return new ResultDto(3008,"今天不是星期五！");
			//System.out.println("今天不是星期五~~~~要做处理哈！");
		}
		//验证金额是否合法（大于等于自有余额）
		if(wiService.valAvailableBalance(userid, money)!=1){
			return new ResultDto(3008,"提现异常！请按照套路出牌！");
		}
		//验证支付宝账号是否为空
		if(wiService.valuserinfoByPayNo(userid)==1){
			return new ResultDto(3009,"请完善收款账号！"); 
		}
		//添加记录
		//修改余额
		try {
			if(wiService.withdrawalRecord(userid, money)){
				return new ResultDto(200,"提现成功！");
			}
		} catch (Exception e) {
			return new ResultDto(3009,"提现异常！");
		}
		return new ResultDto(3009,"提现失败！");
	}
}

package com.sharebo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.Wallet;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.entity.dto.Userinfodto;
import com.sharebo.entity.dto.WalletDto;
import com.sharebo.service.UserinfoService;
import com.sharebo.service.WalletService;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("wallet")
public class WalletController {
	private org.slf4j.Logger log=org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	private WalletService wService;
	@Autowired
	private UserinfoService uService;
	/**
	 * 查询用户可用余额和冻结金额
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="getAvailableBalance",method=RequestMethod.POST)
	public @ResponseBody ResultDto selectAvailableBalance(String userid){
		try {
			if(userid==null){
				return new ResultDto(3009,"请求参数异常");
			}
			WalletDto wallet=wService.getAvailableBalance(userid);
			return new ResultDto(200,"成功",wallet);
		} catch (Exception e) {
			log.error("查询提现记录信息参数异常！");
			System.out.println("查询提现记录信息参数异常！");
		}
		return new ResultDto(3006,"未进行操作");
	}
	/**
	 * 同步数据(已经过期了)
	 * wallet/test.do
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="test",method=RequestMethod.POST)
	public  ResultDto selecttest(){
		List<Userinfodto> user=uService.selectbyuserid();
		for (Userinfodto Userinfodto : user) {
			String userid=Userinfodto.getUserid();
			Wallet w=new Wallet();
			w.setUserid(userid);
			w.setAvailableBalance(0);
			w.setFreezeBalance(0);
			wService.addWalletation(w);
		}
		return new ResultDto(200,"成功！");
	}
}

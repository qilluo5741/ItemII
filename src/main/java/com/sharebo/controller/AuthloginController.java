package com.sharebo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sharebo.entity.dto.RegisteredDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.AuthuserinfoService;
import com.sharebo.util.MD5Util;
import com.sharebo.util.SmsAuthUtil;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@RestController
@RequestMapping("/auth")
public class AuthloginController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthuserinfoService Service;
	/**
	 * 用户登录
	 * @param phone
	 * @param password
	 * @return
	 * auth/login.do?phone=13101089314&password=e10adc3949ba59abbe56e057f20f883e
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ResultDto getAuthloginByuserid(String phone,String password){
		try {
			if(phone==null || password==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			String passwords = MD5Util.encode(password);
			String userid=Service.getAuthloginByuserid(phone, passwords);
			if(userid!=null){
				//生成token
				String token =UUID.randomUUID().toString().replace("-","");
				System.out.println(token+"登录修改的token");
				//token存入数据库
				if(Service.updateUserInfoTokenByUserId(userid, token)<0){
					log.error("登录生成token异常！");
				}
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("userid",userid);
				map.put("userToken", token);
				return new ResultDto(200,"登录成功",map);
			}
		} catch (Exception e) {
			log.error("登录参数异常！");
			System.out.println("登录参数异常！");
		}
		return new ResultDto(2005,"用户名不存在或密码错误！");
	}
	/**
	 * 注册发短信验证码
	 * @param phone 
	 * @param phoneVal
	 * @return
	 */
	@RequestMapping("regSendSms")
	public ResultDto regSendSms(String phone,String phoneVal){
		//验证手机号码是否合法
		if(phone==null||phone.length()!=11){
			return new ResultDto(1001,"手机号码不合法！");
		}
		if(phoneVal==null){
			return new ResultDto(1001,"手机验证失败！");
		}
		if(!phoneVal.equals(MD5Util.encode(phone+"sharebo_"))){
			return new ResultDto(1005,"参数验证不成功！");
		}
		//验证用户是否已经存在
		RegisteredDto rd=Service.getUserInfoIsNullByPhone(phone);
		if(rd==null||rd.getUserid()==null){//用户第一次发送验证码
			String code=RandomStringUtils.randomNumeric(6);
			//添加用户信息
			if(Service.addUserInfo(phone, code)>0){
				//发送短信
				try {
					SmsAuthUtil.SendMessage(phone, code);
					System.out.println("发送手机号码："+phone+"   验证码是："+code);
				} catch (Exception e) {}
				return  new ResultDto(200,"发送成功！");
			}else{
				return new ResultDto(1003,"操作异常！请稍后再试！");
			}
		}else if(rd.getPassword()==null){//用户已经发送过验证码，但是没有注册成功
			//验证验证码是够一分钟之前发送
			 if(!DateUtils.addMinutes(rd.getRegCodeTime(),1).before(new Date())){
				 return new ResultDto(1005,"请一分钟后再试");
			 }
			//发送验证码，并修改验证码
			String code=RandomStringUtils.randomNumeric(6);
			//根据手机号修改验证码
			if(Service.updateRegCodeByUserid(rd.getUserid(), code)>0){
				try {
					SmsAuthUtil.SendMessage(phone, code);
					System.out.println("发送手机号码："+phone+"   验证码是："+code);
				} catch (Exception e) {}
				return  new ResultDto(200,"发送成功！");
			}else{
				return  new ResultDto(200,"发送失败！");
			}
		}else{
			//用户已经注册
			return new ResultDto(1002,"该手机号已经注册！");
		}
	}
	//用户注册-验证验证码（下一步）
	@RequestMapping("valCode")
	public ResultDto valCode(String code,String phone){
		if(code==null||phone==null){
			return new ResultDto(1001,"参数不合法！");
		}
		//首先验证该操作是否有效
		RegisteredDto rd=Service.getUserInfoIsNullByPhone(phone);
		if(rd==null||rd.getPassword()!=null){
			return new ResultDto(1006,"操作无效！");
		}
		Map<String,Object> res=new HashMap<String, Object>();
		//验证是否一致
		if(Service.valCodeByphoneAndCode(phone, code)>0){//验证成功
			res.put("valState", true);//验证状态
			res.put("phone", phone);//手机号
			res.put("commitCode", rd.getUserid());//状态吗
			return new ResultDto(200,"验证成功！",res);
		}else{
			res.put("valState", false);
			return new ResultDto(200,"验证失败！",res);
		}
	}
	/***
	 * 用户注册，提交资料
	 * @param commitCode
	 * @param phone
	 * @param password
	 * @param inviteCode
	 * @return
	 */
	@RequestMapping("regCommit")
	public ResultDto regCommit(String commitCode,String phone,String password,String inviteCode){
		if(commitCode==null||phone==null||password==null){
			return new ResultDto(1001,"参数不合法！");
		}
		//验证commitCode  与手机号是否在数据库中匹配
		if(Service.valCommitCodeByPhoneAndUserId(commitCode, phone)<=0){//验证失败，没有按照套路出牌
			return new ResultDto(1007,"操作异常！请按照套路出牌好么？");
		}
		//验证成功，修改用户其他数据信息
		//密码加密
		password=MD5Util.encode(password);
		//生成邀请码
		String myInviteCode=getRandomChar();
		if(Service.updateUserPasswordByPhone(phone, password,myInviteCode)>0){//注册成功
			//创建一个账户
			Service.insertWallet(phone);
			//根据邀请码 修邀请状态
			if(inviteCode!=null){
				Service.updateInviteState(phone);
			}
			//
			//根据邀请码做相应业务操作
			/**********/
			return new ResultDto(200,"注册成功");
		}else{
			//注册失败！
			return new ResultDto(1008,"注册失败！请稍后再试！");
		}
	}
	//忘记密码-发送验证码
	@RequestMapping("backPwdSendSms")
	public ResultDto backPwdSendSms(String phone,String phoneVal){
		//验证参数是否合法
		if(phone==null||phoneVal==null){
			return new ResultDto(1010,"参数不能为空");
		}
		if(!phoneVal.equals(MD5Util.encode(phone+"sharebo_"))){//02f58e2febf704ee0c2a291692b20580
			return new ResultDto(1005,"参数验证不成功！");
		}
		
		//验证用户是否已经存在
		RegisteredDto rd=Service.getUserInfoIsNullByPhone(phone);
		if(rd==null||rd.getUserid()==null||rd.getPassword()==null){//用户为注册
			return new ResultDto(10011,"该用户没有注册哟！");
		}else{
			 
			 //验证验证码是够一分钟之前发送
			if (rd.getRetrievePwdSmsCodeTime() != null
					&& !DateUtils.addMinutes(rd.getRetrievePwdSmsCodeTime(), 1)
							.before(new Date())) {
				return new ResultDto(1005, "请一分钟后再试！");
			} else {
				// 发送验证码
				String code = RandomStringUtils.randomNumeric(6);
				// 修改验证码
				if(Service.updateretrievePwdCode(phone, code)>0){
					//发送短信
					try {
						SmsAuthUtil.SendMessage(phone, code);
						System.out.println("发送手机号码："+phone+"   验证码是："+code);
					} catch (Exception e) {}
					return new ResultDto(200,"发送成功！");
				}
				return new ResultDto(1012,"发送失败！");
			}
			
		}
	}
	//找回密码验证短信
	@RequestMapping("backPwdValSms")
	public ResultDto backPwdValSms(String phone,String code){
		if(code==null||phone==null){
			return new ResultDto(1001,"参数不合法！");
		}
		//首先验证该操作是否有效
		RegisteredDto rd=Service.getUserInfoIsNullByPhone(phone);
		if(rd==null||rd.getPassword()==null){
			return new ResultDto(1006,"该用户还未注册哟！");
		}
		Map<String, Object> res=new HashMap<String, Object>();
		//验证是否一致
		if(Service.valrievePhoneCode(phone, code)>0){
			res.put("valState", true);//验证状态
			res.put("phone", phone);//手机号
			res.put("commitCode", rd.getUserid());//状态吗
			return new ResultDto(200,"验证成功！",res);
		}else{
			res.put("valState", false);
			return new ResultDto(200,"验证失败！",res);
		}
	}
	//修改密码
	@RequestMapping("updatePwd")
	public ResultDto updatePwd(String commitCode,String phone,String password){
		if (commitCode == null || phone == null || password == null) {
			return new ResultDto(1001, "参数不合法！");
		}
		// 验证commitCode 与手机号是否在数据库中匹配
		if (Service.valCommitCodeByPhoneAndUserIdToretrievePwd(commitCode, phone) <= 0) {// 验证失败，没有按照套路出牌
			return new ResultDto(1007, "操作异常！请按照套路出牌好么？");
		}
		//密码加密
		password=MD5Util.encode(password);
		//修改密码
		if(Service.retrievePwdUpdate(phone, password, commitCode)>0){
			return new ResultDto(200, "修改成功！");
		}
		return new ResultDto(1012, "修改失败！");
	}
	//生成邀请码
	public static String getRandomChar(){
		String randChar = "";
		for (int i = 0; i < 6; i++) {
			int index = (int) Math.round(Math.random() * 1);
			switch (index) {
			case 0:// 大写字符
				randChar += String
						.valueOf((char) Math.round(Math.random() * 25 + 65));
				break;
			default:// 数字
				randChar += String.valueOf(Math.round(Math.random() * 9));
				break;
			}
		}
		return randChar;
	}
}

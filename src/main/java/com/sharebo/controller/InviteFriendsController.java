package com.sharebo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.InviteFriends;
import com.sharebo.entity.Pager;
import com.sharebo.entity.dto.AuthUserinfo;
import com.sharebo.entity.dto.InviteFriendsDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.InviteFriendsService;
import com.sharebo.util.SmsUtil;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/invite")
public class InviteFriendsController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InviteFriendsService inService;
	/**
	 * 邀请好友记录查询
	 * @param userid
	 * @return
	 * invite/getInvite.do?userid=889390526942412800&pageIndex=1&pageSize=5
	 */
    @ResponseBody
    @RequestMapping(value="getInvite",method=RequestMethod.POST)
    public ResultDto seletInviye(String userid,Integer pageIndex,Integer pageSize){
    	try {
    		if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "请求参数不合法");
			}
    		List<AuthUserinfo> list=inService.selectPhoneisexist(userid);
    		for (AuthUserinfo authUserinfo : list) {
    			inService.updateinviteState(authUserinfo.getPhone());
			}
    		Pager<InviteFriendsDto> pager=new Pager<InviteFriendsDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(inService.selectInviteFriendsInfo(map));
			//查询总数
			pager.setTotalNumber(inService.selectCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"成功",pager);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("请求参数异常！");
			System.out.println("请求参数异常！");
		}
    	return new ResultDto(3002,"参数异常");
    }
    /**
     * 邀请好友
     * @param userid
     * @param tophone
     * @param carNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="insertInvite",method=RequestMethod.POST)
    public ResultDto insertFriends(String userid,String tophone,String carNo){
		try {
			if(userid==null||tophone==null||carNo==null){
				return new ResultDto(3001,"请求参数为空");
			}
			Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");//正则表达式验证手机号码是否合法
		    Matcher m = p.matcher(tophone);
		    if(!m.matches()){//手机号验证不成功
		    	return new ResultDto(3009,"手机号码输入不合法！");
		    }
			if(inService.valueFriends(tophone)>0){
				return new ResultDto(3010,"该手机号已经被邀请");
			}
			if(inService.valueFriendsisRegist(tophone)>0){
				return new ResultDto(3011,"该手机号已经注册了");
			}else{
				String phone=inService.selectPhone(userid);
				String inviteCode=inService.selectInviteCode(userid);
				SmsUtil.sendchargeMessages(phone,tophone,inviteCode);
				InviteFriends in=new InviteFriends();
				Date now=new Date();
				in.setTophone(tophone);
				in.setInviteTime(now);
				in.setCarNo(carNo);
				in.setUserid(userid);
				in.setInviteState(0);
				in.setInviteCode(inviteCode);
				if(inService.insertInviteFriends(in)>0){
					return new ResultDto(200,"邀请成功");
				}else{
					return new ResultDto(3012,"邀请失败");
				}
			}
		} catch (Exception e) {
			log.error("邀请好友异常！");
			System.out.println("邀请好友异常！");
		}
    	return new ResultDto(3013,"未进行操作");
	}
    /**
     * 查询用户邀请码
     * @param userid
     * @return
     * invite/selectInviteCode.do
     */
    @ResponseBody
    @RequestMapping(value="selectInviteCode",method=RequestMethod.POST)
    public ResultDto getInviteCode(String userid){
    	try {
			if(userid==null){
				return new ResultDto(3006,"请求参数为空");
			}
			String inviteCode=inService.selectInviteCode(userid);
			Map<String,String> map=new HashMap<String,String>();
			map.put("inviteCode", inviteCode);
			return new ResultDto(200,"成功",map);
		} catch (Exception e) {
			log.error("查询用户邀请码异常！");
			System.out.println("查询用户邀请码异常！");
		}
    	return new ResultDto(3013,"操作失败");
    }
}

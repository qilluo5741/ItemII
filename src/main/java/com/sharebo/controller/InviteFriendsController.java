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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/invite")
public class InviteFriendsController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InviteFriendsService inService;
	/**
	 * ������Ѽ�¼��ѯ
	 * @param userid
	 * @return
	 * invite/getInvite.do?userid=889390526942412800&pageIndex=1&pageSize=5
	 */
    @ResponseBody
    @RequestMapping(value="getInvite",method=RequestMethod.POST)
    public ResultDto seletInviye(String userid,Integer pageIndex,Integer pageSize){
    	try {
    		if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "����������Ϸ�");
			}
    		List<AuthUserinfo> list=inService.selectPhoneisexist(userid);
    		for (AuthUserinfo authUserinfo : list) {
    			inService.updateinviteState(authUserinfo.getPhone());
			}
    		Pager<InviteFriendsDto> pager=new Pager<InviteFriendsDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(inService.selectInviteFriendsInfo(map));
			//��ѯ����
			pager.setTotalNumber(inService.selectCount(userid));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"�ɹ�",pager);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("��������쳣��");
			System.out.println("��������쳣��");
		}
    	return new ResultDto(3002,"�����쳣");
    }
    /**
     * �������
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
				return new ResultDto(3001,"�������Ϊ��");
			}
			Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");//������ʽ��֤�ֻ������Ƿ�Ϸ�
		    Matcher m = p.matcher(tophone);
		    if(!m.matches()){//�ֻ�����֤���ɹ�
		    	return new ResultDto(3009,"�ֻ��������벻�Ϸ���");
		    }
			if(inService.valueFriends(tophone)>0){
				return new ResultDto(3010,"���ֻ����Ѿ�������");
			}
			if(inService.valueFriendsisRegist(tophone)>0){
				return new ResultDto(3011,"���ֻ����Ѿ�ע����");
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
					return new ResultDto(200,"����ɹ�");
				}else{
					return new ResultDto(3012,"����ʧ��");
				}
			}
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("��������쳣��");
		}
    	return new ResultDto(3013,"δ���в���");
	}
    /**
     * ��ѯ�û�������
     * @param userid
     * @return
     * invite/selectInviteCode.do
     */
    @ResponseBody
    @RequestMapping(value="selectInviteCode",method=RequestMethod.POST)
    public ResultDto getInviteCode(String userid){
    	try {
			if(userid==null){
				return new ResultDto(3006,"�������Ϊ��");
			}
			String inviteCode=inService.selectInviteCode(userid);
			Map<String,String> map=new HashMap<String,String>();
			map.put("inviteCode", inviteCode);
			return new ResultDto(200,"�ɹ�",map);
		} catch (Exception e) {
			log.error("��ѯ�û��������쳣��");
			System.out.println("��ѯ�û��������쳣��");
		}
    	return new ResultDto(3013,"����ʧ��");
    }
}

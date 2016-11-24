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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@RestController
@RequestMapping("/auth")
public class AuthloginController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthuserinfoService Service;
	/**
	 * �û���¼
	 * @param phone
	 * @param password
	 * @return
	 * auth/login.do?phone=13101089314&password=e10adc3949ba59abbe56e057f20f883e
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ResultDto getAuthloginByuserid(String phone,String password){
		try {
			if(phone==null || password==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			String passwords = MD5Util.encode(password);
			String userid=Service.getAuthloginByuserid(phone, passwords);
			if(userid!=null){
				//����token
				String token =UUID.randomUUID().toString().replace("-","");
				System.out.println(token+"��¼�޸ĵ�token");
				//token�������ݿ�
				if(Service.updateUserInfoTokenByUserId(userid, token)<0){
					log.error("��¼����token�쳣��");
				}
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("userid",userid);
				map.put("userToken", token);
				return new ResultDto(200,"��¼�ɹ�",map);
			}
		} catch (Exception e) {
			log.error("��¼�����쳣��");
			System.out.println("��¼�����쳣��");
		}
		return new ResultDto(2005,"�û��������ڻ��������");
	}
	/**
	 * ע�ᷢ������֤��
	 * @param phone 
	 * @param phoneVal
	 * @return
	 */
	@RequestMapping("regSendSms")
	public ResultDto regSendSms(String phone,String phoneVal){
		//��֤�ֻ������Ƿ�Ϸ�
		if(phone==null||phone.length()!=11){
			return new ResultDto(1001,"�ֻ����벻�Ϸ���");
		}
		if(phoneVal==null){
			return new ResultDto(1001,"�ֻ���֤ʧ�ܣ�");
		}
		if(!phoneVal.equals(MD5Util.encode(phone+"sharebo_"))){
			return new ResultDto(1005,"������֤���ɹ���");
		}
		//��֤�û��Ƿ��Ѿ�����
		RegisteredDto rd=Service.getUserInfoIsNullByPhone(phone);
		if(rd==null||rd.getUserid()==null){//�û���һ�η�����֤��
			String code=RandomStringUtils.randomNumeric(6);
			//����û���Ϣ
			if(Service.addUserInfo(phone, code)>0){
				//���Ͷ���
				try {
					SmsAuthUtil.SendMessage(phone, code);
					System.out.println("�����ֻ����룺"+phone+"   ��֤���ǣ�"+code);
				} catch (Exception e) {}
				return  new ResultDto(200,"���ͳɹ���");
			}else{
				return new ResultDto(1003,"�����쳣�����Ժ����ԣ�");
			}
		}else if(rd.getPassword()==null){//�û��Ѿ����͹���֤�룬����û��ע��ɹ�
			//��֤��֤���ǹ�һ����֮ǰ����
			 if(!DateUtils.addMinutes(rd.getRegCodeTime(),1).before(new Date())){
				 return new ResultDto(1005,"��һ���Ӻ�����");
			 }
			//������֤�룬���޸���֤��
			String code=RandomStringUtils.randomNumeric(6);
			//�����ֻ����޸���֤��
			if(Service.updateRegCodeByUserid(rd.getUserid(), code)>0){
				try {
					SmsAuthUtil.SendMessage(phone, code);
					System.out.println("�����ֻ����룺"+phone+"   ��֤���ǣ�"+code);
				} catch (Exception e) {}
				return  new ResultDto(200,"���ͳɹ���");
			}else{
				return  new ResultDto(200,"����ʧ�ܣ�");
			}
		}else{
			//�û��Ѿ�ע��
			return new ResultDto(1002,"���ֻ����Ѿ�ע�ᣡ");
		}
	}
	//�û�ע��-��֤��֤�루��һ����
	@RequestMapping("valCode")
	public ResultDto valCode(String code,String phone){
		if(code==null||phone==null){
			return new ResultDto(1001,"�������Ϸ���");
		}
		//������֤�ò����Ƿ���Ч
		RegisteredDto rd=Service.getUserInfoIsNullByPhone(phone);
		if(rd==null||rd.getPassword()!=null){
			return new ResultDto(1006,"������Ч��");
		}
		Map<String,Object> res=new HashMap<String, Object>();
		//��֤�Ƿ�һ��
		if(Service.valCodeByphoneAndCode(phone, code)>0){//��֤�ɹ�
			res.put("valState", true);//��֤״̬
			res.put("phone", phone);//�ֻ���
			res.put("commitCode", rd.getUserid());//״̬��
			return new ResultDto(200,"��֤�ɹ���",res);
		}else{
			res.put("valState", false);
			return new ResultDto(200,"��֤ʧ�ܣ�",res);
		}
	}
	/***
	 * �û�ע�ᣬ�ύ����
	 * @param commitCode
	 * @param phone
	 * @param password
	 * @param inviteCode
	 * @return
	 */
	@RequestMapping("regCommit")
	public ResultDto regCommit(String commitCode,String phone,String password,String inviteCode){
		if(commitCode==null||phone==null||password==null){
			return new ResultDto(1001,"�������Ϸ���");
		}
		//��֤commitCode  ���ֻ����Ƿ������ݿ���ƥ��
		if(Service.valCommitCodeByPhoneAndUserId(commitCode, phone)<=0){//��֤ʧ�ܣ�û�а�����·����
			return new ResultDto(1007,"�����쳣���밴����·���ƺ�ô��");
		}
		//��֤�ɹ����޸��û�����������Ϣ
		//�������
		password=MD5Util.encode(password);
		//����������
		String myInviteCode=getRandomChar();
		if(Service.updateUserPasswordByPhone(phone, password,myInviteCode)>0){//ע��ɹ�
			//����һ���˻�
			Service.insertWallet(phone);
			//���������� ������״̬
			if(inviteCode!=null){
				Service.updateInviteState(phone);
			}
			//
			//��������������Ӧҵ�����
			/**********/
			return new ResultDto(200,"ע��ɹ�");
		}else{
			//ע��ʧ�ܣ�
			return new ResultDto(1008,"ע��ʧ�ܣ����Ժ����ԣ�");
		}
	}
	//��������-������֤��
	@RequestMapping("backPwdSendSms")
	public ResultDto backPwdSendSms(String phone,String phoneVal){
		//��֤�����Ƿ�Ϸ�
		if(phone==null||phoneVal==null){
			return new ResultDto(1010,"��������Ϊ��");
		}
		if(!phoneVal.equals(MD5Util.encode(phone+"sharebo_"))){//02f58e2febf704ee0c2a291692b20580
			return new ResultDto(1005,"������֤���ɹ���");
		}
		
		//��֤�û��Ƿ��Ѿ�����
		RegisteredDto rd=Service.getUserInfoIsNullByPhone(phone);
		if(rd==null||rd.getUserid()==null||rd.getPassword()==null){//�û�Ϊע��
			return new ResultDto(10011,"���û�û��ע��Ӵ��");
		}else{
			 
			 //��֤��֤���ǹ�һ����֮ǰ����
			if (rd.getRetrievePwdSmsCodeTime() != null
					&& !DateUtils.addMinutes(rd.getRetrievePwdSmsCodeTime(), 1)
							.before(new Date())) {
				return new ResultDto(1005, "��һ���Ӻ����ԣ�");
			} else {
				// ������֤��
				String code = RandomStringUtils.randomNumeric(6);
				// �޸���֤��
				if(Service.updateretrievePwdCode(phone, code)>0){
					//���Ͷ���
					try {
						SmsAuthUtil.SendMessage(phone, code);
						System.out.println("�����ֻ����룺"+phone+"   ��֤���ǣ�"+code);
					} catch (Exception e) {}
					return new ResultDto(200,"���ͳɹ���");
				}
				return new ResultDto(1012,"����ʧ�ܣ�");
			}
			
		}
	}
	//�һ�������֤����
	@RequestMapping("backPwdValSms")
	public ResultDto backPwdValSms(String phone,String code){
		if(code==null||phone==null){
			return new ResultDto(1001,"�������Ϸ���");
		}
		//������֤�ò����Ƿ���Ч
		RegisteredDto rd=Service.getUserInfoIsNullByPhone(phone);
		if(rd==null||rd.getPassword()==null){
			return new ResultDto(1006,"���û���δע��Ӵ��");
		}
		Map<String, Object> res=new HashMap<String, Object>();
		//��֤�Ƿ�һ��
		if(Service.valrievePhoneCode(phone, code)>0){
			res.put("valState", true);//��֤״̬
			res.put("phone", phone);//�ֻ���
			res.put("commitCode", rd.getUserid());//״̬��
			return new ResultDto(200,"��֤�ɹ���",res);
		}else{
			res.put("valState", false);
			return new ResultDto(200,"��֤ʧ�ܣ�",res);
		}
	}
	//�޸�����
	@RequestMapping("updatePwd")
	public ResultDto updatePwd(String commitCode,String phone,String password){
		if (commitCode == null || phone == null || password == null) {
			return new ResultDto(1001, "�������Ϸ���");
		}
		// ��֤commitCode ���ֻ����Ƿ������ݿ���ƥ��
		if (Service.valCommitCodeByPhoneAndUserIdToretrievePwd(commitCode, phone) <= 0) {// ��֤ʧ�ܣ�û�а�����·����
			return new ResultDto(1007, "�����쳣���밴����·���ƺ�ô��");
		}
		//�������
		password=MD5Util.encode(password);
		//�޸�����
		if(Service.retrievePwdUpdate(phone, password, commitCode)>0){
			return new ResultDto(200, "�޸ĳɹ���");
		}
		return new ResultDto(1012, "�޸�ʧ�ܣ�");
	}
	//����������
	public static String getRandomChar(){
		String randChar = "";
		for (int i = 0; i < 6; i++) {
			int index = (int) Math.round(Math.random() * 1);
			switch (index) {
			case 0:// ��д�ַ�
				randChar += String
						.valueOf((char) Math.round(Math.random() * 25 + 65));
				break;
			default:// ����
				randChar += String.valueOf(Math.round(Math.random() * 9));
				break;
			}
		}
		return randChar;
	}
}

package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Carauthentication;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.CarauthenticationService;
import com.sharebo.service.FileService;
import com.sharebo.util.security.Base64;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * ������֤
 * @author Administrator
 */
@Controller
@RequestMapping("/authen")
public class CarauthenticationController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CarauthenticationService Service;
	@Autowired
	private FileService fileService;
	/**
	 * ������Ϣ��֤
	 * @param userid
	 * @param email
	 * @param xlicense
	 * @param jlicence
	 * @param carId
	 * @return
	 * authen/getication.do
	 */
	@ResponseBody
	@RequestMapping(value="getication",method=RequestMethod.POST)
	public ResultDto Carauthentication(String userid,String email,String xlicense,String jlicence,String carId){
		try {
			 if(userid==null || email==null || xlicense==null || jlicence==null || carId==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			 }
			 Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");//������ʽ��֤�����Ƿ���ȷ
			 Matcher m = p.matcher(email);
			 if(!m.matches()){//�����ʽ����ȷ
				return new ResultDto(2011,"�����ʽ����ȷ��");
			 }
			 int i=Service.getcarauthenticationIdBydoesitexist(userid);
			 if(i>0){
				 return new ResultDto(2012,"�ó����Ѿ���֤�ˣ�");
			 }
			 //��ͼ����
			 byte[] by = Base64.decode(xlicense);//��ʻ֤ ������
			 ByteArrayInputStream bais = new ByteArrayInputStream(by);
			 String xlicetive = this.fileService.uploadImage(bais);
			 
			 byte[] by1 = Base64.decode(jlicence);//��ʻ֤ ������
			 ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
			 String jlicerse = this.fileService.uploadImage(bais1);
			 Carauthentication car=new Carauthentication();
			 car.setEmail(email);
			 car.setXlicense(xlicetive);
			 car.setJlicence(jlicerse);
			 car.setUserid(userid);
			 car.setCarauthenticationState(3);//��֤״̬(0��null Ϊδ��֤�У�1����֤�ɹ� 2����֤ʧ�� 3�����)
			 car.setCarId(carId);
			 car.setCarauthenticationTime(new Date());
			 if(Service.addCarauthentication(car)>0){
				 return new ResultDto(2013,"�ύ��֤�ɹ�����ȴ����...");
			 }
		} catch (Exception e) {
			log.error("��֤��Ϣ�����쳣��");
			System.out.println("��֤��Ϣ�����쳣��");
		}
		return new ResultDto(2001,"�����쳣��");
	}
}

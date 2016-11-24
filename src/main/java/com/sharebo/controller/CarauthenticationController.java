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
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * 车辆认证
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
	 * 车辆信息认证
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
				return new ResultDto(2002,"请求参数为空！");	
			 }
			 Pattern p = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");//正则表达式验证邮箱是否正确
			 Matcher m = p.matcher(email);
			 if(!m.matches()){//邮箱格式不正确
				return new ResultDto(2011,"邮箱格式不正确！");
			 }
			 int i=Service.getcarauthenticationIdBydoesitexist(userid);
			 if(i>0){
				 return new ResultDto(2012,"该车主已经认证了！");
			 }
			 //视图解密
			 byte[] by = Base64.decode(xlicense);//行驶证 数据流
			 ByteArrayInputStream bais = new ByteArrayInputStream(by);
			 String xlicetive = this.fileService.uploadImage(bais);
			 
			 byte[] by1 = Base64.decode(jlicence);//驾驶证 数据流
			 ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
			 String jlicerse = this.fileService.uploadImage(bais1);
			 Carauthentication car=new Carauthentication();
			 car.setEmail(email);
			 car.setXlicense(xlicetive);
			 car.setJlicence(jlicerse);
			 car.setUserid(userid);
			 car.setCarauthenticationState(3);//认证状态(0和null 为未认证中，1：认证成功 2：认证失败 3审核中)
			 car.setCarId(carId);
			 car.setCarauthenticationTime(new Date());
			 if(Service.addCarauthentication(car)>0){
				 return new ResultDto(2013,"提交认证成功！请等待审核...");
			 }
		} catch (Exception e) {
			log.error("认证信息参数异常！");
			System.out.println("认证信息参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
}

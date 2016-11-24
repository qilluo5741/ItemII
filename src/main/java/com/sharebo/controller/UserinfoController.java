package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.entity.dto.Userinfodto;
import com.sharebo.service.FileService;
import com.sharebo.service.UserinfoService;
import com.sharebo.util.security.Base64;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/user")
public class UserinfoController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserinfoService userService;
	@Autowired
	private FileService fileService;
	/**
	 * 查询个人信息
	 * @param userid
	 * @return
	 * user/getuser.do?userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="getuser",method=RequestMethod.POST)
	public ResultDto getloadAllUserinfotype(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			Userinfodto user=userService.getloadAllUserinfotype(userid);
			if(user!=null){
				return new ResultDto(200,"成功",user);
			}
		} catch (Exception e) {
			log.error("查询用户信息参数异常！");
			System.out.println("查询用户信息参数异常！");
		}
		return new ResultDto(2003,"暂无数据！");
	}
	/**
	 * user/getHeadportrait.do?userid=889390526942412800
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getHeadportrait",method=RequestMethod.POST)
	public ResultDto getloadHeadportrait(String userid){
		if(userid==null){
			return new ResultDto(2002,"请求参数为空！");	
		}
		try {
			if(userService.getUserheadportraitByUserid(userid)!=null){
				return new ResultDto(200,"成功",userService.getUserheadportraitByUserid(userid));
			}
		} catch (Exception e) {
			log.error("用户头像参数异常！");
			System.out.println("用户头像参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * 修改头像
	 * @param headportrait
	 * @param userid
	 * @return
	 * user/updatetrait.do?headportrait=&userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="updatetrait",method=RequestMethod.POST)
	public ResultDto updateheadportrait(String headportrait,String userid){
		try {
			if(headportrait==null || userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			byte[] by = Base64.decode(headportrait);
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			int i=userService.updateheadportrait(headimg,userid);
			if(i>0){
				return new ResultDto(200,"成功");
			}
		} catch (Exception e) {
			log.error("修改用户头像参数异常！");
			System.out.println("修改用户头像参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * 修改姓名
	 * @param name
	 * @param userid
	 * @return
	 * user/updatename.do?name=旧梦丶毁佳人1&userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="updatename",method=RequestMethod.POST)
	public ResultDto updatename(String name,String userid){
		try {
			if(name==null || userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			int i=userService.updatename(name, userid);
			if(i>0){
				return new ResultDto(200,"成功");
			}
		} catch (Exception e) {
			log.error("修改姓名参数异常！");
			System.out.println("修改姓名参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 *  修改性别
	 * @param sex
	 * @param userid
	 * @return
	 * user/updatesex.do?sex=1&userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="updatesex",method=RequestMethod.POST)
	public ResultDto updatesex(Integer sex,String userid){
		try {
			if(sex==null || userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			int i=userService.updatesex(sex,userid);
			if(i>0){
				return new ResultDto(200,"成功");
			}
		} catch (Exception e) {
			log.error("修改性别参数异常！");
			System.out.println("修改性别参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * 修改年龄
	 * @param age
	 * @param userid
	 * @return
	 * user/updateage.do?age=90&userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="updateage",method=RequestMethod.POST)
	public ResultDto updateage(Integer age,String userid){
		try {
			if(age==null || userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			int i=userService.updateage(age,userid);
			if(i>0){
				return new ResultDto(200,"成功");
			}
		} catch (Exception e) {
			log.error("修改年龄参数异常！");
			System.out.println("修改年龄参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * 修改驾龄
	 * @param beenDriv
	 * @param userid
	 * @return
	 * user/updatebeenDriv.do?beenDriv=6&userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="updatebeenDriv",method=RequestMethod.POST)
	public ResultDto updatebeenDriv(Integer beenDriv,String userid){
		try {
			if(beenDriv==null || userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			int i=userService.updatebeenDriv(beenDriv,userid);
			if(i>0){
				return new ResultDto(200,"成功");
			}
		} catch (Exception e) {
			log.error("修改驾龄参数异常！");
			System.out.println("修改驾龄参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * 修改个性签名
	 * @param signature
	 * @param userid
	 * @return
	 * user/updatesignature.do?signature=یبلندوبلندوζั͡ &userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="updatesignature",method=RequestMethod.POST)
	public ResultDto updatesignature(String signature,String userid){
		try {
			if(signature==null || userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			int i=userService.updatesignature(signature,userid);
			if(i>0){
				return new ResultDto(200,"成功");
			}
		} catch (Exception e){
			log.error("修改个性签名参数异常！");
			System.out.println("修改个性签名参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * 修改regid
	 * @param regid
	 * @param userid
	 * @return
	 * user/updateregid.do?regid=null&userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="updateregid",method=RequestMethod.POST)
	public ResultDto updateRegIdByUserid(String regid,String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}else if(regid!=null){
				String regeid=userService.getregidByUserid(userid);
				if(!regid.equals(regeid)){
					int i=userService.updateRegIdByUserid(regid,userid);
					if(i>0){
						return new ResultDto(200,"修改成功！");	
					}else{
						return new ResultDto(200,"修改失败！");	
					}
				}
			}else{
				return new ResultDto(2004,"未执行操作！");
			}
		} catch (Exception e) {
			log.error("修改regid参数异常！");
			System.out.println("修改regid参数异常！");
		}
		return new ResultDto(2004,"未执行操作！");
	}
	/**
	 * 修改用户收款账户
	 * @param userid
	 * @param payNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="updatePayNo",method=RequestMethod.POST)
	public ResultDto updatePayNo(String userid,String payNo){
		try {
			if(userid==null||payNo==null){
				return new ResultDto(3006,"请求参数为空！");
			}
			if(userService.updatePayNoByUserid(userid, payNo)>0){
				Map<String,String> map=new HashMap<String,String>();
				map.put("payNo", payNo);
				return new ResultDto(200,"成功",map);
				}
		} catch (Exception e) {
			log.error("修改payNo参数异常！");
			System.out.println("修改payNo参数异常！");
		}
		return new ResultDto(3004,"未执行操作！");
	}
	/**
	 * 查询用户收款账户
	 * @param userid
	 * @return
	 * user/selectPayNo.do?userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="selectPayNo",method=RequestMethod.POST)
	public ResultDto getPayNo(String userid){
		try {
			if(userid==null){
				return new ResultDto(3006,"请求参数为空！");
			}
			String payNo=userService.getPayNoByUserid(userid);
			Map<String,String> map=new HashMap<String,String>();
			map.put("payNo", payNo);
		    return new ResultDto(200,"成功",map);
		} catch (Exception e) {
			log.error("查询payNo参数异常！");
			System.out.println("查询payNo参数异常！");
		}
		return new ResultDto(3006,"未执行操作！");
	}
	/**
	 * 验证token是否有效
	 * user/gettokenisvalid.do?valToken=b940a146fa374f878cfc64048da4a913&userid=889390526942412800
	 * @param valToken
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="gettokenisvalid",method=RequestMethod.POST)
	public ResultDto gettokenisvalid(String valToken,String userid){
		try {
			//判断请求参数是否为空！
			if(valToken==null || userid==null){
				return new ResultDto(2002,"请求参数为空！");
			}
			//验证token是否有效
			Integer valid=userService.getvalTokenByUserid(valToken,userid);
			if(valid>0){
				 //修改用户活跃时间
				 userService.getvalreactivetimeByUserid(userid);
				 return new ResultDto(200,"token有效",true);
			}else{
				return new ResultDto(200,"token失效",false);
			}
		} catch (Exception e) {
			log.error("查询token参数异常！");
			System.out.println("查询token参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	/**
	 * 查询用户支付宝账户，可用余额
	 * @param userid
	 * @return
	 * user/getPaynoAndAvailableBalance.do?userid=889390526942412800                                                                                                                                            
	 */
	@ResponseBody
	@RequestMapping(value="getPaynoAndAvailableBalance",method=RequestMethod.POST)
	public ResultDto getPaynoAndAvailableBalance(String userid){
		try {
			if(userid==null){
				return new ResultDto(3006,"请求参数为空");
			}
			if(userService.selectPayNoAndAvailableBalance(userid)!=null){
				return new ResultDto(200,"查询成功",userService.selectPayNoAndAvailableBalance(userid));
			}
		} catch (Exception e) {
			log.error("查询用户支付宝账户，可用余额参数异常！");
			System.out.println("查询用户支付宝账户，可用余额参数异常！");
		}
		return new ResultDto(3007,"查询失败");
	}
	/**
	 * user/exit.do?userid=889390526942412800
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="exit",method=RequestMethod.POST)
	public ResultDto updatevalTokenexit(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"请求参数为空！");
			}
			Integer i=userService.getdelectvalTokenByUserid(" ",userid);
			if(i>0){
				return new ResultDto(200,"退出成功");
			}
		} catch (Exception e) {
			log.error("退出异常！");
			System.out.println("退出异常！");
		}
		return new ResultDto(2001,"退出异常");
	}
}

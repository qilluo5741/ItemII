package com.sharebo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Carinfo;
import com.sharebo.entity.dto.CarDto;
import com.sharebo.entity.dto.CarinfoDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.CarinfoService;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/carinfo")
public class CarinfoController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CarinfoService carService;
	/**
	 * 查询车牌信息
	 * @param userid
	 * @return
	 * carinfo/getloadcar.do?userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="getloadcar",method=RequestMethod.POST)
	public ResultDto getloadAllCarinfo(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			List<CarinfoDto> car=carService.getloadAllCarinfo(userid);
			if(car!=null){
				return new ResultDto(200,"成功",car);
			}
		} catch (Exception e) {
			log.error("查询车牌信息参数异常！");
			System.out.println("查询车牌信息参数异常！");
		}
		return new ResultDto(2003,"暂无数据！");
	}
	/**
	 * 删除车牌
	 * @param carNo
	 * @param userid
	 * @return
	 * carinfo/delectloadcar.do?carId=889390658240906806
	 */
	@ResponseBody
	@RequestMapping(value="delectloadcar",method=RequestMethod.POST)
	public ResultDto delectloadcar(String carId){
		try {
			if(carId==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			int i=carService.delectCarinfocarNo(carId);
			if(i>0){
				return new ResultDto(200,"成功");
			}
		} catch (Exception e) {
			log.error("删除车牌信息参数异常！");
			System.out.println("删除车牌信息参数异常！");
		}
		return new ResultDto(2006,"删除失败");
	}
	/**
	 * 添加车牌
	 * carinfo/addloadcar.do?carNo=沪K74119&carType=玛莎拉蒂·总裁&carColor=黄色&userid=889390526942412800
	 * @param carNo
	 * @param carType
	 * @param carColor
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="addloadcar",method=RequestMethod.POST)
	public ResultDto addloadcar(String carNo,String carType,String carColor,String userid){
		try {
			if(carNo==null || carType==null || carColor==null || userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			String carNos=carService.getcarNoBydoesitexist(carNo);
			if(carNos!=null){
				return new ResultDto(2008,"该车牌已经存在！");
			}else{
				Carinfo car=new Carinfo();
				car.setCarNo(carNo);
				car.setCarType(carType);
				car.setCarColor(carColor);
				car.setUserid(userid);
				int i=carService.addCarinfo(car);
				if(i>0){
					return new ResultDto(200,"成功");
				}
			}
		} catch (Exception e) {
			log.error("添加车牌信息参数异常！");
			System.out.println("添加车牌信息参数异常！");
		}
		return new ResultDto(2007,"添加失败");
	}
	/**
	 * 查询车牌专门提供认证使用
	 * @param userid
	 * @return
	 * carinfo/getcarno.do?userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="getcarno",method=RequestMethod.POST)
	public ResultDto getloadCar(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			List<CarDto> car=carService.getloadCar(userid);
			if(car!=null){
				return new ResultDto(200,"成功",car);
			}
		} catch (Exception e) {
			log.error("查询车牌信息参数异常！");
			System.out.println("查询车牌信息参数异常！");
		}
		return new ResultDto(2003,"暂无数据！");
	}
}

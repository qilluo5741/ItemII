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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/carinfo")
public class CarinfoController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CarinfoService carService;
	/**
	 * ��ѯ������Ϣ
	 * @param userid
	 * @return
	 * carinfo/getloadcar.do?userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="getloadcar",method=RequestMethod.POST)
	public ResultDto getloadAllCarinfo(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			List<CarinfoDto> car=carService.getloadAllCarinfo(userid);
			if(car!=null){
				return new ResultDto(200,"�ɹ�",car);
			}
		} catch (Exception e) {
			log.error("��ѯ������Ϣ�����쳣��");
			System.out.println("��ѯ������Ϣ�����쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
	/**
	 * ɾ������
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
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			int i=carService.delectCarinfocarNo(carId);
			if(i>0){
				return new ResultDto(200,"�ɹ�");
			}
		} catch (Exception e) {
			log.error("ɾ��������Ϣ�����쳣��");
			System.out.println("ɾ��������Ϣ�����쳣��");
		}
		return new ResultDto(2006,"ɾ��ʧ��");
	}
	/**
	 * ��ӳ���
	 * carinfo/addloadcar.do?carNo=��K74119&carType=��ɯ���١��ܲ�&carColor=��ɫ&userid=889390526942412800
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
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			String carNos=carService.getcarNoBydoesitexist(carNo);
			if(carNos!=null){
				return new ResultDto(2008,"�ó����Ѿ����ڣ�");
			}else{
				Carinfo car=new Carinfo();
				car.setCarNo(carNo);
				car.setCarType(carType);
				car.setCarColor(carColor);
				car.setUserid(userid);
				int i=carService.addCarinfo(car);
				if(i>0){
					return new ResultDto(200,"�ɹ�");
				}
			}
		} catch (Exception e) {
			log.error("��ӳ�����Ϣ�����쳣��");
			System.out.println("��ӳ�����Ϣ�����쳣��");
		}
		return new ResultDto(2007,"���ʧ��");
	}
	/**
	 * ��ѯ����ר���ṩ��֤ʹ��
	 * @param userid
	 * @return
	 * carinfo/getcarno.do?userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping(value="getcarno",method=RequestMethod.POST)
	public ResultDto getloadCar(String userid){
		try {
			if(userid==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			List<CarDto> car=carService.getloadCar(userid);
			if(car!=null){
				return new ResultDto(200,"�ɹ�",car);
			}
		} catch (Exception e) {
			log.error("��ѯ������Ϣ�����쳣��");
			System.out.println("��ѯ������Ϣ�����쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
}

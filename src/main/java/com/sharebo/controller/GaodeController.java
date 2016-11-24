package com.sharebo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.dto.HousDto;
import com.sharebo.entity.dto.ParkingDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.HousService;
import com.sharebo.service.ParkService;
import com.sharebo.util.GaodUtil;
import net.sf.json.JSONObject;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/parks")
public class GaodeController {
	@Autowired
	private ParkService service;
	@Autowired
	private HousService hservice;
	/**
	 * 同步高德park
	 * parks/Port.do
	 * @return
	 */
	@RequestMapping("Port")
	@ResponseBody
	public ResultDto testPpark() {
		try {
			List<ParkingDto> list=service.selectSynchronizeGaodeId();
			for (ParkingDto parkingDto : list) {
				Map<String,Object> pMap=new HashMap<String,Object>();
				pMap.put("parkState",parkingDto.getParkstate());
				pMap.put("housState",1);
				pMap.put("money", parkingDto.getParkprice());
				pMap.put("parkId",parkingDto.getParkId());
				pMap.put("housId",parkingDto.getHousId());
				String res=GaodUtil.addTableDateOnLoctypeByAddress(parkingDto.getParkName(),parkingDto.getEntryAddress(),pMap);
				System.out.println(res);
				String gaodeid=JSONObject.fromObject(res).getString("_id");
				service.updateparkidbygaodeid(gaodeid,parkingDto.getParkId());
				hservice.updatehousbygaodeid(gaodeid,parkingDto.getHousId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultDto(200,"同步数据完成！",true);
	}
	/**
	 * 同步高德hous
	 * parks/Ports.do
	 * @return
	 */
	@RequestMapping("Ports")
	@ResponseBody
	public ResultDto testPparks() {
		try {
			List<HousDto> list=hservice.selectHousSynchronizeGaodeId();
			for (HousDto HousDto : list) {
				Map<String,Object> pMap=new HashMap<String,Object>();
				pMap.put("parkState",2);
				pMap.put("housState",1);
				pMap.put("housId",HousDto.getHousId());
				String res=GaodUtil.addTableDateOnLoctypeByAddress(HousDto.getHousName(),HousDto.getHousAddress(),pMap);
				String gaodeid=JSONObject.fromObject(res).getString("_id");
				hservice.updatehousbygaodeid(gaodeid, HousDto.getHousId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultDto(200,"同步数据完成！",true);
	}
}

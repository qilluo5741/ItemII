package com.sharebo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.config.GaodeConfig;
import com.sharebo.controller.ParkController;
import com.sharebo.entity.Dayrules;
import com.sharebo.entity.LongrentInfo;
import com.sharebo.entity.MyCarPort;
import com.sharebo.entity.OrderTime;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.HousDto2;
import com.sharebo.entity.dto.LongCarDetailsDto;
import com.sharebo.entity.dto.MyCarportDto;
import com.sharebo.entity.dto.ParkDetailsDto;
import com.sharebo.entity.dto.ParkDto;
import com.sharebo.entity.dto.ParkInfoDto;
import com.sharebo.entity.dto.ParkingDto;
import com.sharebo.mapper.ParkMapper;
import com.sharebo.service.ParkService;
import com.sharebo.util.GaodUtil;
import com.sharebo.util.HttpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class ParkServiceImpl implements ParkService {
	private static final Logger log = LoggerFactory
			.getLogger(ParkController.class);
	@Autowired
	private ParkMapper mapper;

	/**
	 * 添加车位
	 */
	@Override
	public boolean insertParkByLongCarRental(LongrentInfo longrent)throws Exception {
		// 添加车位信息
		longrent.getCarport().setCarportId(UUID.randomUUID().toString().replace("-", ""));
		longrent.getCarport().setRentoutType(0);
		longrent.getCarport().setFeeType(0);//发布长租
		if (mapper.addMyCartInfo(longrent.getCarport()) <= 0) {
			return false;
		}
		// 添加长租信息
		if (mapper.addLongrentInfo(longrent) <= 0) {// 添加失败！
			throw new Exception("添加长租数据失败！");
		}
		//说明：根据 carportId 修改高德地图的状态
		//通过carportId  验证当前小区是否存在车位
		Integer count=mapper.valHousIsExixtsCarPortByCarportId(longrent.getCarport().getCarportId());
		//查询高德地图ID
		String gaodeId=mapper.seleclGaodeIdBycarportId(longrent.getCarport().getCarportId());
		updateGaodState(mapper,gaodeId,count,longrent.getCarport().getMoney(),longrent.getCarport().getCarportId());
		return true;
	}
	//修改高德状态
	public static String updateGaodState(ParkMapper mapper,String gaodeId,Integer count,Double money,String carportId){
		//查询出小区中最低价格
		Double cpMoney=mapper.getHousMinMoneyByCarportId(carportId);
		//查询停车场最低价格
		Double parkMoney=mapper.getParkPriceByCarPortId(carportId);
		System.out.println("小区最低："+cpMoney+"   "+parkMoney);
		//查询高德地图ID
		Map<String, Object> map =new HashMap<String, Object>();
		if(count>0){//有车位哒！
			map.put("housState", 0);
		}else{
			map.put("housState", 1);
		}
		System.out.println("剩余车位数："+count);
		Double minMoney=0.0;
		if(parkMoney!=null){//如果停车场不为空
			minMoney=parkMoney;
		}
		if(cpMoney!=null){
			if(parkMoney!=null){
				minMoney=cpMoney<minMoney?cpMoney:minMoney;
			}else{
				minMoney=cpMoney;
			}
		}
		System.out.println("修改的价格："+minMoney);
		//验证那个money最小
		if(money!=null){
			if(minMoney<money){
				map.put("money",minMoney);
			}else{
				map.put("money",money);
			}
		}else{
			map.put("money",minMoney);
		}
		//修改高德地图滴，状态
		String res=GaodUtil.updateTableData(gaodeId, map);
		System.out.println(res);
		return res;
	}
	@Override
	public int valserialnumberIsExists(String serialnumber, String userid,String housId) {
		return mapper.valserialnumberIsExists(serialnumber, userid,housId);
	}

	@Override
	public String insertIssuedTemporaryParkSpaces(MyCarPort cp)
			throws Exception {
		// 添加车位信息
		cp.setCarportId(UUID.randomUUID().toString().replace("-", ""));
		cp.setRentoutType(1);
		System.out.println(cp.getUserid());
		if (mapper.addMyCartInfo(cp) <= 0) {
			return null;
		}
		// 创建时间规则表
		if (mapper.addweekrules(cp.getCarportId()) <= 0) {
			throw new Exception("添加长租数据失败！");
		}
		//说明：根据 carportId 修改高德地图的状态
		//通过carportId  验证当前小区是否存在车位
		Integer count=mapper.valHousIsExixtsCarPortByCarportId(cp.getCarportId());
		//查询高德地图ID
		String gaodeId=mapper.seleclGaodeIdBycarportId(cp.getCarportId());
		updateGaodState(mapper,gaodeId, count,cp.getMoney(),cp.getCarportId());
		return cp.getCarportId();
	}

	@Override
	public Weekrules getWeekrulesByCarportId(String carportId) {
		return mapper.getWeekrulesByCarportId(carportId);
	}

	@Override
	public int updateWeekrulesByWeekid(Weekrules week) {
		return mapper.updateWeekrulesByWeekid(week);
	}

	@Override
	public LongCarDetailsDto getLongrentInfo(String carportId) {
		return mapper.getLongrentInfo(carportId);
	}

	@Override
	public boolean ParkByLongCarRental(LongrentInfo longrent) throws Exception {
		longrent.getCarport().setRentoutType(0);
		// 先修改长租表详情
		if (mapper.updateLongrent(longrent) <= 0) {
			return false;
		}
		// 然后修改车位信息
		if (mapper.updateMycarPort(longrent.getCarport()) <= 0) {
			throw new Exception("修改长租车位异常！");
		}
		updateGaodState(mapper,mapper.seleclGaodeIdBycarportId(longrent.getCarport().getCarportId()), 1, longrent.getCarport().getMoney(), longrent.getCarport().getCarportId());
		return true;
	}

	@Override
	public int updateTemporaryParkSpaces(MyCarPort cp) {
		cp.setRentoutType(1);
		if(mapper.updateMycarPort(cp)<=0){
			return 0;
		}
		updateGaodState(mapper, mapper.seleclGaodeIdBycarportId(cp.getCarportId()), 1, cp.getMoney(), cp.getCarportId());
		return 1;
	}

	@Override
	public List<String> selectParkMonthAllDayRlus(Map<String, String> map) {
		return mapper.selectParkMonthAllDayRlus(map);
	}

	@Override
	public List<String> selectParkMonthAllNotDayRlus(Map<String, String> map) {
		return mapper.selectparkMenthAllNotDayRlus(map);
	}

	@Override
	public Weekrules weekTime(String carportId) {
		return mapper.weekTime(carportId);
	}
	// 查询一天的时间
	@Override
	public Dayrules getDayrules(Map<String, String> map) throws Exception {
		Dayrules d = mapper.getDayrules(map);
		// 如果当天没有设置时间，就按照星期规则计算
		if (null == d) {
			d = new Dayrules();// 初始化！
			d.setCarportId(map.get("parkid"));
			d.setThisDay(map.get("day"));
			int week = DayToWeek(map.get("day"));// 通过日期得到星期几
			// 通过星期查询可用时间段
			Weekrules w = mapper.weekTime(map.get("parkid"));
			if(w==null){
				throw new Exception("车位不存在");
			}
			// 根据星期取值
			if (week == 1) {// 星期日
				weekToDay(d, w.getSunday_begin(), w.getSunday_end(), 1);
			} else if (week == 2) {// 星期一
				weekToDay(d, w.getMonday_begin(), w.getMonday_end(), 1);
			} else if (week == 3) {// 星期二
				weekToDay(d, w.getTuesday_begin(), w.getTuesday_end(), 1);
			} else if (week == 4) {// 星期三
				weekToDay(d, w.getWednesday_begin(), w.getWednesday_end(), 1);
			} else if (week == 5) {// 星期四
				weekToDay(d, w.getThursday_begin(), w.getThursday_end(), 1);
			} else if (week == 6) {// 星期五
				weekToDay(d, w.getFriday_begin(), w.getFriday_end(), 1);
			} else if (week == 7) {// 星期六
				weekToDay(d, w.getSaturday_begin(), w.getSaturday_end(), 1);
			}
			d.setThisDay(map.get("day"));
		}
		// 查询有没有时间段是否租用 存在的订单 根据时间处理
		List<OrderTime> ot = mapper.getOrdertime(map);
		if (null != ot) {
			// 遍历订单时间表数据
			for (OrderTime orderTime : ot) {
				weekToDay(d, orderTime.getBegin_time(),
						orderTime.getEnd_time(), 2);
			}
		}
		// System.out.println(d.toString());
		return d;
	}

	// 通过星期规则转换时间
	public static Dayrules weekToDay(Dayrules d, String begin, String end, int s) {
		Calendar thisdatC = new GregorianCalendar();
		System.out.println(thisdatC);
		// 截取前面两位成整型
		int b = Integer.parseInt(begin.substring(0, 2));// 开始时间
		int e = Integer.parseInt(end.substring(0, 2));// 结束时间
		if (b != e) {// 判断两个是否相等，相等意味着禁用
			Calendar calendarCountDays = new GregorianCalendar();
			calendarCountDays.setTime(new Date());
			// 把规定时间设为1
			for (int i = b; i < e; i++) {
				if(i<=calendarCountDays.get(Calendar.HOUR_OF_DAY)&&new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(d.getThisDay()))
					continue;
				if (i == 0)
					d.setHours_24(s);
				if (i == 1)
					d.setHours_01(s);
				if (i == 2)
					d.setHours_02(s);
				if (i == 3)
					d.setHours_03(s);
				if (i == 4)
					d.setHours_04(s);
				if (i == 5)
					d.setHours_05(s);
				if (i == 6)
					d.setHours_06(s);
				if (i == 7)
					d.setHours_07(s);
				if (i == 8)
					d.setHours_08(s);
				if (i == 9)
					d.setHours_09(s);
				if (i == 10)
					d.setHours_10(s);
				if (i == 11)
					d.setHours_11(s);
				if (i == 12)
					d.setHours_12(s);
				if (i == 13)
					d.setHours_13(s);
				if (i == 14)
					d.setHours_14(s);
				if (i == 15)
					d.setHours_15(s);
				if (i == 16)
					d.setHours_16(s);
				if (i == 17)
					d.setHours_17(s);
				if (i == 18)
					d.setHours_18(s);
				if (i == 19)
					d.setHours_19(s);
				if (i == 20)
					d.setHours_20(s);
				if (i == 21)
					d.setHours_21(s);
				if (i == 22)
					d.setHours_22(s);
				if (i == 23)
					d.setHours_23(s);
			}
		}
		return d;
	}

	// 传入日期，返回星期几
	public static int DayToWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar();
		try {
			calendarCountDays.setTime(sdf.parse(day));
		} catch (ParseException e) {
			log.error("转换时间失败!");
		}
		// 得到当期那日期是星期几
		return calendarCountDays.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * 查询停车场详情
	 * @param parkId
	 * @return
	 */
	@Override
	public ParkInfoDto selectParkInfo(String parkId,String userid) {
		return mapper.selectParkInfo(parkId, userid);
	}
	@Override
	public boolean isExistxByDayrules(Dayrules d) {
		return mapper.isExistxByDayrules(d)<=0?false:true;
	}
	@Override
	public boolean addDayrules(Dayrules d) {
		return mapper.addDayrules(d);
	}
	@Override
	public boolean updateDayByHours(Dayrules d) {
		return mapper.updateDayByHours(d);
	}

	@Override
	public boolean updateCarPort(String carportId) {
		if(mapper.updateCarPort(carportId)<=0){
			return false;
		}
		//说明：根据 carportId 修改高德地图的状态
		//通过carportId  验证当前小区是否存在车位
		Integer count=mapper.valHousIsExixtsCarPortByCarportId(carportId);
		//查询高德地图ID
		String gaodeId=mapper.seleclGaodeIdBycarportId(carportId);
		updateGaodState(mapper,gaodeId,count,null,carportId);
		return true;
	}
	/**
	 * 查询停车场信息
	 * @param parkId
	 * @return
	 */
	@Override
	public ParkDto selectParkDtoInfo(String parkId) {
		return mapper.selectParkDtoInfo(parkId);
	}
	/**
	 * 根据housId查询停车位
	 * @param housId
	 * @return
	 */
	@Override
	public List<MyCarportDto> selectMyCarportInfo(String housId) {
		return mapper.selectMyCarportInfo(housId);
	}
	//验证小区是否已经存在  如果存在得到高德ID  修改高德停车场数据
			//不存在 同步高德数据     添加小区数据
	@Override
	public boolean addParking(ParkingDto park) throws Exception {
		park.setParkId(UUID.randomUUID().toString().replace("-", ""));
		//通过小区名字查询高德ID
		HousDto2 h=mapper.GetGaodeIdByHousName(park.getParkName());
		if(h==null){//不存在小区
			String housId=UUID.randomUUID().toString().replace("-","");
			//添加高德  得到高德Id
			Map<String,Object> pMap=new HashMap<String,Object>();
			pMap.put("housState", 1);
			pMap.put("parkState", 1);
			pMap.put("housId", housId);
			pMap.put("money", park.getParkprice());
			pMap.put("parkId",park.getParkId());
			String res=GaodUtil.addTableDateOnLoctypeByAddress(park.getParkName(),park.getEntryAddress(),pMap);
			System.out.println(res);
			 String gaodeid=null;
			if(JSONObject.fromObject(res).getString("info").equals("OK")){
				gaodeid=JSONObject.fromObject(res).getString("_id");
				 park.setGaodeId(gaodeid);
			}
			//添加小区
			if(mapper.addHous(housId, park.getParkName(), park.getParkAddress(), gaodeid)<=0){
				return false;
			}
			
		}else{//存在小区
		park.setGaodeId(h.getGaodeIds());
		//修改gaodeID的Park信息
		Map<String,Object> pMap=new HashMap<String,Object>();
		pMap.put("parkState", 1);
		pMap.put("parkId",park.getParkId());
		//验证money
		//根据高德地图Id查询价格
			String url="http://yuntuapi.amap.com/datasearch/id?_id="+h.getGaodeIds()+"&key="+GaodeConfig.getAppKey()+"&tableid="+GaodeConfig.getTableid();
			try {
				double gMoney=JSONObject.fromObject(JSONArray.fromObject(JSONObject.fromObject(HttpClient.get(url)).getString("datas")).get(0)).getDouble("money");
				if(park.getParkprice()!=null){
					if(park.getParkprice()<gMoney){
						pMap.put("money", park.getParkprice());
					}
				}
			} catch (Exception e) {
				System.out.println("一般都是 价格没得。。");
				pMap.put("money", park.getParkprice());
			}
			GaodUtil.updateTableData(h.getGaodeIds(), pMap);
		}
		
		//添加停车场
		if(mapper.addParking(park)<=0){
			return false;
		}
		return true;
	}
	@Override
	public int updateparkidbygaodeid(String gaodeId, String parkid) {
		return mapper.updateparkidbygaodeid(gaodeId,parkid);
	}

	@Override
	public List<ParkingDto> selectSynchronizeGaodeId() {
		return mapper.selectSynchronizeGaodeId();
	}
	@Override
	public List<ParkDetailsDto> getLongRentByHousId(String housIds) {
		return mapper.getLongRentByHousId(housIds);
	}
	//包月停车——价格优先
	@Override
	public List<ParkDetailsDto> getLongRentInfo(Map<String, Object> map) {
		return mapper.getLongRentInfo(map);
	}

	@Override
	public int getLongRentInfoCount() {
		return mapper.getLongRentInfoCount();
	}
	@Override
	public List<MyCarportDto> getloadAllCarport(Map<String, Object> map) {
		return mapper.getloadAllCarport(map);
	}
	@Override
	public Integer getloadAllCarportCount(String housId) {
		return mapper.getloadAllCarportCount(housId);
	}
}

package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.config.GaodeConfig;
import com.sharebo.entity.Dayrules;
import com.sharebo.entity.LongrentInfo;
import com.sharebo.entity.MyCarPort;
import com.sharebo.entity.Pager;
import com.sharebo.entity.ResDate;
import com.sharebo.entity.ResMonth;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.MyCarportDto;
import com.sharebo.entity.dto.ParkDetailsDto;
import com.sharebo.entity.dto.ParkDto;
import com.sharebo.entity.dto.ParkingDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.FileService;
import com.sharebo.service.ParkService;
import com.sharebo.util.HttpClient;
import com.sharebo.util.security.Base64;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 关于车位
 * @author niewei
 */
@Controller
@RequestMapping("/park")
public class ParkController {
	private static final Logger log=LoggerFactory.getLogger(ParkController.class);
	@Autowired
	private ParkService service;
	@Autowired
	private FileService fileService;
	//删除车位   通过车位id  修改isdetete 字段数据为1
	@RequestMapping("deleteCarPort")
	@ResponseBody
	public ResultDto deleteCarPort(String carportId) {
		//重新修改高德费用
		try {
			if (carportId == null) {
				return new ResultDto(3006, "请求参数不能为空");
			}
			if (service.updateCarPort(carportId)) {
				return new ResultDto(200, "删除成功");
			}
		} catch (Exception e) {
			log.error("通过车位id修改isdetete 字段数据为1参数异常！");
			System.out.println("通过车位id修改isdetete 字段数据为1参数异常！");
		}
		return new ResultDto(3007, "删除失败");
	}
	// 修改一天的小时时间
	@RequestMapping("updateHours")
	public @ResponseBody ResultDto updateHours(String day) {
		if (day == null) {
			return new ResultDto(1004, "参数不合法！");
		}
		Dayrules d = null;
		// 解析json
		try {
			d = (Dayrules) JSONObject.toBean(JSONObject.fromObject(day),
					Dayrules.class);
		} catch (Exception e) {
			return new ResultDto(1004, "参数不合法！");
		}
		// 判断dayid是否为空
		if (d.getDayid() == null) {
			// 查询是否存在 存在就说已经添加啦！
			if (service.isExistxByDayrules(d)) {
				return new ResultDto(10015, "已经该数据已经存在！请注意是否修改！请检查dayid是否有误！");
			}
			d.setDayid(UUID.randomUUID().toString().replace("-", ""));
			// 执行添加
			if (service.addDayrules(d)) {
				return new ResultDto(200, "保存成功！");
			}
			return new ResultDto(10016, "操作失败！");
		} else {
			// 执行修改
			if (service.updateDayByHours(d)) {
				return new ResultDto(200, "修改成功！");
			}
			return new ResultDto(10016, "操作失败！");
		}
	}
	//查询一天的时间
	@RequestMapping("getDay")
	public @ResponseBody ResultDto getDay(String day,String carportId){
		if(day==null||carportId==null){
			return new ResultDto(1004,"参数不合法！");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(day));
		} catch (ParseException e) {
			return new ResultDto(10014,"时间格式有误！");
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("day",sdf.format(calendarCountDays.getTime()));
		map.put("parkid",carportId);
		try {
			return new ResultDto(200,service.getDayrules(map));
		} catch (Exception e) {
			log.error("查询一天的时间参数异常！");
			System.out.println("查询一天的时间参数异常！");
			return new ResultDto(200,e.getMessage());
		}
	}
	
	//查询月份时间
	/**
	 *传入日期   车位id 
	 * 用过日期为条件 查询出一个月的时间规则（判断是否当天为禁用）
	 * 通过车位id查询星期规则（当天不禁用，再验证星期是否禁用）
	 * */
	@RequestMapping("getMonth")
	public @ResponseBody ResultDto getMonth(String strDate,String carportId){
		if(strDate==null||carportId==null){
			return new ResultDto(1004,"参数不合法！");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(strDate));
		} catch (ParseException e) {
			return new ResultDto(10014,"时间格式有误！");
		}
		int theYear=calendarCountDays.get(Calendar.YEAR);//传入时间年份
		int theMonth=calendarCountDays.get(Calendar.MONTH)+1;//传入时间的月份
		int days=calendarCountDays.getActualMaximum(Calendar.DAY_OF_MONTH);//传入月的总天数
		//***************************************************************************//
		Calendar calendar= Calendar.getInstance(); 
		int thisYear=calendar.get(Calendar.YEAR);///今天的年份
		int thisMonth=calendar.get(Calendar.MONTH)+1;///今天的月份
		int thisDay=calendar.get(Calendar.DATE);//今天是几号
		ResMonth rm=new ResMonth();//设置年月
		//传入的月份
		//System.out.println(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth));
		System.out.println("传入的年份："+theYear+"现在的年份："+thisYear);
		//判断传入月份是否大于等于当前月份
		if(theYear<thisYear){
			return new ResultDto(1058,"年份不能小于今年哟~");
		}else{
			if(theYear==thisYear)
			if(theMonth<thisMonth){
				return new ResultDto(1058,"月份不能小于当前月哟~");
			}
		}
		/******************************************/
		//查询出车位id和当前月的禁用时间
		Map<String,String> disableDayMap=new HashMap<String, String>();
		disableDayMap.put("parkid",carportId);
		disableDayMap.put("begin",theYear+"-"+theMonth+"-"+"01");
		disableDayMap.put("end",(theMonth==12?theYear+1:theYear)+"-"+(theMonth==12?1:theMonth+1)+"-"+"01");
		//禁用的日期
		List<String> disableDayList=service.selectParkMonthAllDayRlus(disableDayMap);
		for (String string : disableDayList) {
			disableDayMap.put(string, string);//存入集合
		}
		//独立设置的日期（未禁用的日期）
		List<String> disableNotDayList=service.selectParkMonthAllNotDayRlus(disableDayMap);
		for (String string : disableNotDayList) {
			disableDayMap.put(string+"_not", string);//存入集合
		}
		Weekrules weekr=service.weekTime(carportId);
		/******************************************/
		rm.setYear(theYear);
		rm.setMonth(theMonth);
		List<ResDate> rdList=new ArrayList<ResDate>();
		//循环一个月的天数
		for(int i=1;i<=days;i++){
			//如果是当前月 小于当天就不做处理
				if(i<thisDay&&theYear==thisYear&&thisMonth==theMonth){
					rdList.add(new ResDate(i,false,false));
				}else{
					//当前月份  可用时间
					if(disableDayMap.get(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i))==null){
						//------没有针对当天日期禁用
						if(disableDayMap.get(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i)+"_not")==null){
							//没有针对当天日期启用
							//验证用户的星期规则
							boolean isd=WeekIsdisable(weekr,DayToWeek(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i)));
							rdList.add(new ResDate(i,isd,true));
						}else{
							rdList.add(new ResDate(i,true,true));
						}
					}else{
						//存在代表禁用
						rdList.add(new ResDate(i,false,true));
					}
				}
		}
		rm.setRd(rdList);
		/******************************************/
		return new ResultDto(200,rm);
	}
	//传入星期几和星期规则 返回是否禁用
		public static boolean WeekIsdisable(Weekrules w,int week){
			String begin=null;
			String end=null;
			if(week==1){//星期日
				begin=w.getSunday_begin();end=w.getSunday_end();
			}else if(week==2){//星期一
				begin=w.getMonday_begin();end=w.getMonday_end() ;
			}else if(week==3){//星期二
				begin=w.getTuesday_begin();end=w.getTuesday_end() ;
			}else if(week==4){//星期三
				begin=w.getWednesday_begin();end=w.getWednesday_end(); 
			}else if(week==5){//星期四
				begin=w.getThursday_begin();end=w.getThursday_end();
			}else if(week==6){//星期五
				begin=w.getFriday_begin();end=w.getFriday_end();
			}else if(week==7){//星期六
				begin=w.getSaturday_begin();end=w.getSaturday_end();
			}
			if(end.equals("23:59:00")){
				return true;
			}
			//System.out.println(end);
			begin=begin.substring(0,2);
			
			end=end.substring(0,2);
			if(begin.equals(end)){//相等时禁用
				return false;
			}
			return true;
		}
	// 传入日期，返回星期几
	public static int DayToWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar();
			try {
				calendarCountDays.setTime(sdf.parse(day));
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				log.error("转换时间失败!");				
			}
		// 得到当期那日期是星期几
		return calendarCountDays.get(Calendar.DAY_OF_WEEK);
	}

	//删除我的车位  根据CarportId修改isdelete为2
	
	//修改临时车位信息
	@RequestMapping("updateTemporaryParkSpaces")
	@ResponseBody
	public ResultDto updateTemporaryParkSpaces(MyCarPort cp){
		//修改
		if(service.updateTemporaryParkSpaces(cp)==1){
			return new ResultDto(200,"修改成功！");
		}
		return new ResultDto(1013,"修改失败！");
	}
	//修改长租信息
	@RequestMapping("updateLongCarRental")
	@ResponseBody
	public ResultDto updateLongCarRental(LongrentInfo rent){
		//验证参数
		if(rent.getCarport()==null||rent.getCarport().getUserid()==null){
			return new ResultDto(1012,"param is null");
		}
		if(rent.getCarport().getCarportId()==null){
			return new ResultDto(1012,"CarportId nou is null");
		}
		if(rent.getTimetype()!=0&&rent.getTimetype()!=1){
			return new ResultDto(1012,"长租时间类型有误！");
		}
		if(rent.getCarport().getHousId()==null){
			return new ResultDto(1013,"请不要忘记小区哟！");
		}
		//入口
		if(rent.getCarport().getDetailedAddress()==null){
			return new ResultDto(1014,"没有入口地址，车主怎么找车位呢？");
		}
		//车位编号 类型  收费 
		if(rent.getCarport().getSerialnumber()==null||rent.getCarport().getHousType()==null||rent.getCarport().getMoney()==null){
			return new ResultDto(1015,"车位编号 类型  收费  不能为空！");
		}
		//开始时间和结束时间
		if(rent.getBeginmonthTime()==null||rent.getEndmonthTime()==null){
			return new ResultDto(1015,"开始时间和结束时间不能为空！");
		}
		//运营类型
		if(rent.getTimetype()==1){
			if(rent.getBegindayTime()==null||rent.getEnddayTime()==null){
				return new ResultDto(1015,"非全天出租，请设置开始结束时间！");
			}
		}
		try {
			if(service.ParkByLongCarRental(rent)){
				return new ResultDto(200,"修改成功！");		
			}
		} catch (Exception e) {
			log.error("修改车位失败！失败的原因可能是：{}",e.getMessage());
		}
		return new ResultDto(200,"修改失败！请稍后再试！");	
	}
	//根据车位id修改星期规则
	@RequestMapping("updateWeekRules")
	@ResponseBody
	public ResultDto updateWeekRules(String week){
		Weekrules wd=null;
		try {
			//解析json
			wd=(Weekrules) JSONObject.toBean(JSONObject.fromObject(week), Weekrules.class);
			//进行修改
			if(service.updateWeekrulesByWeekid(wd)<=0)//修改
			return  new ResultDto(1024,"修改失败！");
		} catch (Exception e) {
			log.error("解析参数异常！请重试！");
		}
		return  new ResultDto(200,"修改成功！");
	}

	// 根据车位id修改星期规则2
	@RequestMapping("updateWeekRules2")
	@ResponseBody
	public ResultDto updateWeekRules2(Weekrules wd) {
		System.out.println(wd);
		try {
			// 进行修改
			if (service.updateWeekrulesByWeekid(wd) <= 0)// 修改
				return new ResultDto(1024, "修改失败！");
		} catch (Exception e) {
			log.error("解析参数异常！请重试！");
		}
		return new ResultDto(200, "修改成功！");
	}
	//根据车位id查询星期规则时间
	@RequestMapping("getWeekrules")
	public @ResponseBody ResultDto getWeekrules(String carportId){
		//验证参数
		if(carportId==null){
			return new ResultDto(1021,"参数不合法！");
		}
		//查询
		return new ResultDto(200,service.getWeekrulesByCarportId(carportId));
	}
	//发布临时车位
	@RequestMapping("issuedTemporaryParkSpaces")
	public @ResponseBody ResultDto issuedTemporaryParkSpaces(MyCarPort cp,String invimg){
		System.out.println(cp.toString());
		//验证参数
		if(cp.getUserid()==null||cp.getHousId()==null||cp.getDetailedAddress()==null||cp.getSerialnumber()==null||cp.getHousType()==null||cp.getMoney()==null || cp.getInvidualimg()==null || invimg==null){
			return new ResultDto(1021,"参数不合法！");
		}
		//判断图片的传值
		if(cp.getInvidualimg().equals("1") && invimg.equals("1")){
			cp.setInvidualimg("1"+","+"1");
		}else if(!cp.getInvidualimg().equals("1") && invimg.equals("1")){
			byte[] by = Base64.decode(cp.getInvidualimg());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			cp.setInvidualimg(headimg+","+"1");
		}else{
			byte[] by = Base64.decode(cp.getInvidualimg());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			byte[] by1 = Base64.decode(invimg);
			ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
			String headimgs = this.fileService.uploadImage(bais1);
			cp.setInvidualimg(headimg+","+headimgs);
		}
		//验证车位是否存在
		// 验证车位编号是否存在
		if (service.valserialnumberIsExists(cp.getSerialnumber(),cp.getUserid(),cp.getHousId()) > 0) {
			return new ResultDto(2016, "该车位编号已经存在！无需重复添加！");
		}
		String parkid=null;
		//添加车位
		try {
			parkid=service.insertIssuedTemporaryParkSpaces(cp);
			if(parkid==null){
				return new ResultDto(1022,"发布车位失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("发布临时停车位异常！"+e.getMessage());
		}
		Map<String, String> map=new HashMap<String, String>();
		map.put("carportId", parkid);
		return new ResultDto(200,"发布车位成功！",map);
	}
	//根据车位id查询长租详情
	@RequestMapping("getLongCarRental")
	@ResponseBody
	public ResultDto getLongCarRental(String carportId){
		return new ResultDto(200,service.getLongrentInfo(carportId));
	}
	//发布长租车位
	@RequestMapping("releaseLongCarRental")
	public @ResponseBody ResultDto releaseLongCarRental(LongrentInfo rent,String parkimg){
		System.out.println(rent.getCarport().getParkremark());
		if(rent.getPersonalphoto()==null || parkimg==null){
			return new ResultDto(1011,"请求参数为空！");
		}
		//判断图片的传值
		if(rent.getPersonalphoto().equals("1") && parkimg.equals("1")){
			rent.setPersonalphoto("1"+","+"1");
		}else if(!rent.getPersonalphoto().equals("1") && parkimg.equals("1")){
			byte[] by = Base64.decode(rent.getPersonalphoto());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			rent.setPersonalphoto(headimg+","+"1");
		}else{
			byte[] by = Base64.decode(rent.getPersonalphoto());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			byte[] by1 = Base64.decode(parkimg);
			ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
			String headimgs = this.fileService.uploadImage(bais1);
			rent.setPersonalphoto(headimg+","+headimgs);
		}
		//验证参数
		if(rent.getCarport().getUserid()==null){
			return new ResultDto(1012,"userid nou is null");
		}
		if(rent.getCarport().getHousId()==null){
			return new ResultDto(1013,"请不要忘记小区哟！");
		}
		//入口
		if(rent.getCarport().getDetailedAddress()==null){
			return new ResultDto(1014,"没有入口地址，车主怎么找车位呢？");
		}
		//车位编号 类型  收费 
		if(rent.getCarport().getSerialnumber()==null||rent.getCarport().getHousType()==null||rent.getCarport().getMoney()==null){
			return new ResultDto(1015,"车位编号 类型  收费  不能为空！");
		}
		//开始时间和结束时间
		if(rent.getBeginmonthTime()==null||rent.getEndmonthTime()==null){
			return new ResultDto(1015,"开始时间和结束时间不能为空！");
		}
		//运营类型
		if(rent.getTimetype()==1){
			if(rent.getBegindayTime()==null||rent.getEnddayTime()==null){
				return new ResultDto(1015,"非全天出租，请设置开始结束时间！");
			}
		}
		//验证车位编号是否存在
		if(service.valserialnumberIsExists(rent.getCarport().getSerialnumber(), rent.getCarport().getUserid(),rent.getCarport().getHousId())>0){
			return new ResultDto(2016,"该车位编号已经存在！无需重复添加！");
		}
		try {
			if(service.insertParkByLongCarRental(rent)){
				return new ResultDto(200,"发布成功！");		
			}
		} catch (Exception e) {
			log.error("发布车位失败！失败的原因可能是：{}",e.getMessage());
		}
		return new ResultDto(200,"发布失败！请稍后再试！");		
	}
	/**
	 * 停车场详情查询
	 * @param parkId
	 * @return
	 * park/getParkInfo.do?parkId=889394938746241519&userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping("getParkInfo")
	public ResultDto getParkInfo(String parkId,String userid){
		try {
			if(parkId==null||userid==null){
				return new ResultDto(3006,"请求参数为空");
			}
			if(service.selectParkInfo(parkId, userid)!=null){
				return new ResultDto(200,"成功",service.selectParkInfo(parkId, userid));
			}
		} catch (Exception e) {
			log.error("停车场详情查询查询异常！");
			System.out.println("停车场详情查询查询异常！");
		}
		return new ResultDto(3010,"暂无数据！");
	}
	/**
	 * 根据parkId查询停车场信息
	 * @param parkId
	 * @return
	 * park/getParkDtoInfo.do?parkId=889394938746242682
	 */
	@ResponseBody
	@RequestMapping("getParkDtoInfo")
	public ResultDto getParkDtoInfo(String parkId){
		try {
			if(parkId==null){
				return new ResultDto(3006,"请求参数为空");
			}
			if(service.selectParkDtoInfo(parkId)!=null){
				return new ResultDto(200,"成功",service.selectParkDtoInfo(parkId));
			}
		} catch (Exception e) {
			log.error("根据parkId查询停车场信息异常！");
			System.out.println("根据parkId查询停车场信息异常！");
		}
		return new ResultDto(3010,"暂无数据！");
	}
	/**
	 * 根据housId查询停车位
	 * @param housId
	 * @return
	 * park/getMyCarportInfo.do?housId=24534087746194205
	 */
	@ResponseBody
	@RequestMapping("getMyCarportInfo")
	public ResultDto getMyCarportInfo(String housId){
		try {
			if(housId==null){
				return new ResultDto(3006,"请求参数为空");
			}
			if(service.selectMyCarportInfo(housId)!=null){
				List<MyCarportDto> list=service.selectMyCarportInfo(housId);
				for (MyCarportDto myCarportDto : list) {
					myCarportDto.setPhone(myCarportDto.getPhone().substring(0, 3)+"****"+myCarportDto.getPhone().substring(7, 11));
				}
				return new ResultDto(200,"查询成功",list);
			}
		} catch (Exception e) {
			log.error("根据housId查询停车位异常！");
			System.out.println("根据housId查询停车位信息异常！");
		}
		return new ResultDto(3010,"暂无数据！");
	}
	/**
	 * 所有车位详情
	 * @param housId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * park/getinformation.do?housId=24534087746194210&pageIndex=1&pageSize=10
	 */
	@ResponseBody
	@RequestMapping(value="getinformation",method=RequestMethod.POST)
	public ResultDto getloadAllparkinginformation(String housId,Integer pageIndex,Integer pageSize){
		try {
			if(housId==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			Pager<MyCarportDto> pager=new Pager<MyCarportDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("housId", housId);
			List<MyCarportDto> list=service.getloadAllCarport(map);
			for (MyCarportDto myCarportDto : list) {
				myCarportDto.setPhone(myCarportDto.getPhone().substring(0, 3)+"****"+myCarportDto.getPhone().substring(7, 11));
			}
			pager.setList(list);
			//查询总数
			pager.setTotalNumber(service.getloadAllCarportCount(housId));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"成功",pager);
		} catch (Exception e) {
			log.error("所有车位详情参数异常！");
			System.out.println("所有车位详情参数异常！");
		}
		return new ResultDto(2001,"暂无数据！");
	}
	/**
	 * park/getaddpark.do?userid=889390526942412800&parkName=1&parkAddress=1&parkbusinessType=1&entryAddress=1&beBegin=2016-03-16 10:07:56&beEnd=2016-03-16 10:07:56&parkSum=1&parkType=1&parkprice=1&chargeType=1&reservationfee=1&reservationfeedivide=1&parkingisdivided=1&iswhether=1&parkstate=1&feeModel=1
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getaddpark",method=RequestMethod.POST)
	public ResultDto Carauthentication(String userid,String parkName,String parkAddress,String parkbusinessType,String entryAddress,String beBegin,String beEnd,Integer parkSum,String parkType,Double parkprice,Integer chargeType,Double reservationfee,Double reservationfeedivide,Double parkingisdivided,Integer iswhether,Integer parkstate, String feeModel){
		try {
			if(userid==null || parkName==null || parkAddress==null|| parkbusinessType==null || entryAddress==null|| beBegin==null|| beEnd==null|| parkSum==null|| parkType==null|| parkprice==null|| chargeType==null|| reservationfee==null|| reservationfeedivide==null|| parkingisdivided==null|| iswhether==null|| parkstate==null|| feeModel==null){
				return new ResultDto(2002,"请求参数为空！");	
			}
			ParkingDto p=new ParkingDto();
			p.setParkName(parkName);
			p.setParkAddress(parkAddress);
			p.setParkbusinessType(parkbusinessType);
			p.setEntryAddress(entryAddress);
			p.setBeBegin(beBegin);
			p.setBeEnd(beEnd);
			p.setParkSum(parkSum);
			p.setParkType(parkType);
			p.setParkprice(parkprice);
			p.setChargeType(chargeType);
			p.setReservationfee(reservationfeedivide);
			p.setReservationfeedivide(reservationfeedivide);
			p.setParkingisdivided(parkingisdivided);
			p.setCash(parkprice);
			p.setIswhether(iswhether);
			p.setPartner("xb");
			p.setUserid(userid);
			p.setCreatetime(new Date());
			p.setFeeModel(feeModel);
			if(service.addParking(p)){
				return new ResultDto(200,"添加成功！");
			}
		} catch (Exception e) {
			log.error("添加参数异常！");
			System.out.println("添加参数异常！");
			return new ResultDto(2001,"操作貌似有点问题！请稍后再试试看！");
		}
		return new ResultDto(2001,"参数异常！");
	}
	//包月停车-距离优先
	@RequestMapping("getLongRentList")
	@ResponseBody
	public ResultDto getLongRentList(String center){
		String url="http://yuntuapi.amap.com/datasearch/around?"
				+ "key="+GaodeConfig.getAppKey()
				+ "&tableid="+GaodeConfig.getTableid()
				+ "&center="+center
				+"&filter=hostState:0"
				+"&limit=100"
				+"&radius=3000";
		//请求参数
		String res = HttpClient.get(url);
		// 得到结果集JSON集合
		String datas = JSONObject.fromObject(res).getString("datas");
		JsonConfig jc=new JsonConfig();
		@SuppressWarnings("unchecked")
		List<ParkDetailsDto> list = JSONArray.toList(JSONArray.fromObject(datas), new ParkDetailsDto(),jc);
		List<ParkDetailsDto> resList=null;
		if(list.size()==0){
			return new ResultDto(200,"暂无车位",resList);
		}
		// 存放值
		Map<String, ParkDetailsDto> parkMap = new HashMap<String, ParkDetailsDto>();
		// 拼接where 参数
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			ParkDetailsDto pd = list.get(i);
			if(pd.getHousId()==null){
				continue;
			}
			parkMap.put(pd.getHousId(), pd);//添加临时值
			sb.append("'"+pd.getHousId()+"'");//添加条件where
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		//查询数据库
		resList=service.getLongRentByHousId(sb.toString());
		for (int i = 0; i < resList.size(); i++) {
			ParkDetailsDto p=resList.get(i);
			//取得其他值
			ParkDetailsDto mapPd=parkMap.get(p.getHousId());
			//摄入其他值
			p.set_address(mapPd.get_address());
			p.set_name(mapPd.get_name());
			p.set_distance(mapPd.get_distance());
			if(p!=null){
				resList.set(i,p);
			}
		}
		return new ResultDto(200,resList);
	}
	//包月停车  价格优先
	@ResponseBody
	@RequestMapping("getLongRentListOrderByMoney")
	public ResultDto getLongRentListOrderByMoney(Integer pageIndex,Integer pageSize){
		try {
    		if(pageIndex==null||pageSize==null){
				return new ResultDto(3006, "请求参数不合法");
			}
    		Pager<ParkDetailsDto> pager=new Pager<ParkDetailsDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			pager.setList(service.getLongRentInfo(map));
			//查询总数
			pager.setTotalNumber(service.getLongRentInfoCount());
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"成功",pager);
		} catch (Exception e) {
			log.error("请求参数异常！");
			System.out.println("请求参数异常！");
		}
    	return new ResultDto(3002,"参数异常");
    }
	
	/**
	 * park/getParkdistance.do?parkId=889394938746241770&center=121.429449,31.137204
	 * 根据parkId查询停车场信息
	 * @param parkId
	 * @param center
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("getParkdistance")
	public ResultDto getParkdistance(String parkId,String center){
		try {
			if(parkId==null){
				return new ResultDto(3006,"请求参数为空");
			}
			String url="http://yuntuapi.amap.com/datasearch/around?"
					+ "key="+GaodeConfig.getAppKey()
					+ "&tableid="+GaodeConfig.getTableid()
					+ "&center="+center;
			//请求参数
			System.out.println(HttpClient.get(url));
			String datas=JSONObject.fromObject(HttpClient.get(url)).getString("datas");
			JsonConfig jsonc=new JsonConfig();
			List<ParkDetailsDto> list = JSONArray.toList(JSONArray.fromObject(datas),new ParkDetailsDto(),jsonc);
			ParkDto park=service.selectParkDtoInfo(parkId);
			if(park!=null){
				for (int i = 0; i < list.size(); i++) {
					park.set_distance(list.get(i).get_distance());
				}
				return new ResultDto(200,"成功",park);
			}
		} catch (Exception e) {
			log.error("根据parkId查询停车场信息异常！");
			System.out.println("根据parkId查询停车场信息异常！");
		}
		return new ResultDto(3010,"暂无数据！");
	}
	/**
	 * hostid
	 * @param housId
	 * @return
	 * park/getCarportInfo.do?housId=889394938746241532&center=121.429449,31.137204
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("getCarportInfo")
	public ResultDto getCarportInfo(String housId,String center){
		try {
			if(housId==null){
				return new ResultDto(3006,"请求参数为空");
			}
			String url="http://yuntuapi.amap.com/datasearch/around?"
					+ "key="+GaodeConfig.getAppKey()
					+ "&tableid="+GaodeConfig.getTableid()
					+ "&center="+center;
			//请求参数
			String datas=JSONObject.fromObject(HttpClient.get(url)).getString("datas");
			JsonConfig jsonc=new JsonConfig();
			List<ParkDetailsDto> parkddt = JSONArray.toList(JSONArray.fromObject(datas),new ParkDetailsDto(),jsonc);
			if(service.selectMyCarportInfo(housId)!=null){
				List<MyCarportDto> list=service.selectMyCarportInfo(housId);
				for (MyCarportDto myCarportDto : list) {
					myCarportDto.setPhone(myCarportDto.getPhone().substring(0, 3)+"****"+myCarportDto.getPhone().substring(7, 11));
				}
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("list",list);
				try {
					map.put("_distance",parkddt.get(0).get_distance());
				} catch (Exception e) {
					map.put("_distance",null);
				}
				return new ResultDto(200,"查询成功",map);
			}
		} catch (Exception e) {
			log.error("根据housId查询停车位异常！");
			System.out.println("根据housId查询停车位信息异常！");
		}
		return new ResultDto(3010,"暂无数据！");
	}
}


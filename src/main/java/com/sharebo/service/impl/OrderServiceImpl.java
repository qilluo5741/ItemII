package com.sharebo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.entity.LeaseOrderDetailInfo;
import com.sharebo.entity.MyCarOrderTime;
import com.sharebo.entity.ParkOrderDetailsInfo;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.CallBackRefuceInfo;
import com.sharebo.entity.dto.CarportOrderInfo;
import com.sharebo.entity.dto.DetailsDto;
import com.sharebo.entity.dto.LongCarDetailsDto;
import com.sharebo.entity.dto.LongRentDto;
import com.sharebo.entity.dto.LongRentOrderDto;
import com.sharebo.entity.dto.OrderStateDto;
import com.sharebo.entity.dto.ParkOrderDetails;
import com.sharebo.entity.dto.ParkOrderFeeDto;
import com.sharebo.entity.dto.ParkOrderInfo;
import com.sharebo.entity.dto.SupplierOrder;
import com.sharebo.entity.dto.TempOrderDto;
import com.sharebo.entity.dto.TemporaryRentDto;
import com.sharebo.mapper.DetailsMapper;
import com.sharebo.mapper.OrderMapper;
import com.sharebo.mapper.ParkMapper;
import com.sharebo.mapper.PayMapper;
import com.sharebo.service.OrderService;

/**
 * 订单
 * 
 * @author niewei
 *
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper mapper;
	@Autowired
	private ParkMapper pmapper;
	@Autowired
	private DetailsMapper dmapper;
	@Autowired
	private PayMapper paymapper;

	@Override
	public ParkOrderFeeDto getParkFeeByParkId(String parkId) {
		return mapper.getParkFeeByParkId(parkId);
	}

	@Override
	public boolean addParkOrder(ParkOrderDetailsInfo podi) throws Exception {
		//1. 添加订单
		if(mapper.addOrderInfo(podi.getOrder())<=0){
			return false;
		}
		//2.添加订单详情
		if(mapper.addParkOrderDetails(podi)<=0){
			throw new Exception("添加公共停车场订单详情失败！");
		}
		return true;
	}

	@Override
	public ParkOrderDetails getParkOrderDetail(String orderId) {
		return mapper.getParkOrderDetail(orderId);
	}
	@Override
	public boolean valCarPortIsLongRentByCarportId(String carportId) {
		return mapper.valCarPortIsLongRentByCarportId(carportId)<=0?false:true;
	}
	@Override
	public int valCarPortIsRentByCarportId(String carportId) {
		return mapper.valCarPortIsRentByCarportId(carportId);
	}
	@Override
	public LongCarDetailsDto getLongrentInfo(String carportId) {
		return pmapper.getLongrentInfo(carportId);
	}
	@Override
	public boolean addLongRentOrder(LeaseOrderDetailInfo lodi) throws Exception {
		//添加主订单
		if(mapper.addOrderInfo(lodi.getCpod().getOrder())<=0){
			return false;
		}
		//添加个人车位订单详情
		if(mapper.addCarportorDerdetails(lodi.getCpod())<=0){
			throw new Exception("添加个人车位订单详情异常！");
		}
		//添加长租信息
		try {
			if(mapper.addLeaseorderdetails(lodi)<=0){
				throw new Exception("添加个人车位长租订单详情异常！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("添加个人车位长租订单详情异常！");
		}
		return true;
	}
	//修改订单状态
	@Override
	public int CallBack_updateOrderState(String orderNum) {
		return mapper.CallBack_updateOrderState(orderNum);
	}
	@Override
	public boolean valThisDayIsExists(String day, String carportId,Integer begin,Integer end) {
		//验证小时规则是否存在
		if(mapper.valHoursIsExists(carportId, day)>0){//存在
			//验证天  等于0代表没有禁用  相反已经禁用
			String sql=val_sql(carportId, day, begin, end);
			if(mapper.valDayisExists(sql)==0){
				return false; 
			}
		}else{//不存在
			//验证星期规则
			if(mapper.valdisableWeek(DayToWeek(day), carportId)>0){//已经禁用
				return false;
			}else{
				//验证是否超过小时段
				//查询车位星期规则
				Weekrules wr=mapper.weekTime(carportId);
				if(wr==null){
					return false;
				}
				//处理
				Map<String,String> map=new HashMap<String, String>();
				map=toBedinEnd(map, DayToWeek(day), wr);
				///判断两个时间是否存在交集
				int b=Integer.valueOf(map.get("begin").substring(0,map.get("begin").indexOf(":")));//星期时间
				int e=Integer.valueOf(map.get("end").substring(0,map.get("end").indexOf(":")));//星期时间
				//判断开始和结束时间是否包含在设置时间内
				if(!(b<=begin&&e>=end)){
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public boolean valOrderIsExists(String day, String carPortId,
			Integer begin, Integer end) {
		/*****验证订单中是否存在已经预约的时间******/
		//1.查询是否有当天存在的当前车位的时间 并且订单状态为已经取消
		//2.判断两个时间是否有交集
		return false;
	}
	//传入星期，开始时间，结束时间，时间对象 处理对象
		public static Map<String,String> toBedinEnd(Map<String,String> map,int week,Weekrules w){
			String begin=null;
			String end=null;
			if(week==1){//星期日
				begin=w.getSunday_begin();end=w.getSunday_end();
			}else if(week==2){//星期一
				begin=w.getMonday_begin();end=w.getMonday_end();
			}else if(week==3){//星期二
				begin=w.getTuesday_begin();end=w.getTuesday_end();		
			}else if(week==4){//星期三
				begin=w.getWednesday_begin();end=w.getWednesday_end();
			}else if(week==5){//星期四
				begin=w.getThursday_begin();end=w.getThursday_end();
			}else if(week==6){//星期五
				begin=w.getFriday_begin();end=w.getFriday_end();
			}else if(week==7){//星期六
				begin=w.getSaturday_begin();end=w.getSaturday_end();
			}
			map.put("begin", begin);
			map.put("end", end);
			return map;
		}
	@Override
	public DetailsDto getloadAllDetails(String carportId) {
	
		return dmapper.getloadAllDetails(carportId);
	}
	//根据开始时间，结束时间 parkid生成sql语句   目的：查询结果为空
		public static String val_sql(String carportId,String thisDate,Integer begin,Integer end){
			StringBuffer sb=new StringBuffer();
			sb.append("carportId='"+carportId+"' and thisDay='"+thisDate+"'");
			if(begin<0||end>24){//判断小时是否合法
				return  null;
			}
			for(int i=begin;i<end;i++){
				//编写sql
				if(i==0){
					sb.append(" and hours_24=1");
				}
				else{
					sb.append(" and hours_"+(i<10?"0"+i:i)+"=1");
				}
			}
			return sb.toString();
		}

	// 传入日期，返回星期几
	public static int DayToWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar();
		try {
			calendarCountDays.setTime(sdf.parse(day));
		} catch (ParseException e) {
		}
		// 得到当期那日期是星期几
		return calendarCountDays.get(Calendar.DAY_OF_WEEK);
	}
    //专位订单记录查询
	@Override
	public List<CarportOrderInfo> getCarportOrderInfoByUserid(Map<String, Object> map) {
	
		return mapper.getCarportOrderInfoByUserid(map);
	}
    //专位订单记录总数
	@Override
	public int getCarportOrderInfoCount(String userid) {
	
		return mapper.getCarportOrderInfoCount(userid);
	}
	@Override
	public boolean addTempOrder(MyCarOrderTime cot) throws Exception {
		//添加订单
		if(mapper.addOrderInfo(cot.getCpod().getOrder())<=0){
			return false;
		}
		//添加订单详情
		if(mapper.addCarportorDerdetailsByTemp(cot.getCpod())<=0){
			throw new Exception("添加个人临时订单详情异常！");
		}
		//添加临时
		if(mapper.addMyCarOrderTime(cot)<=0){
			throw new Exception("添加个人临时订单详情异常！");
		}
		return true;
	}
	@Override
	public boolean valCarportTempOrderIsTemp(String carportId, String thisdate,
			Integer begin, Integer end) {
		List<MyCarOrderTime> cto=mapper.getMyCarOrderTime(carportId, thisdate);
		if(cto==null){
			return false;
		}
		for (MyCarOrderTime myCarOrderTime : cto) {
			//取出时间判断是否有交集
			int b=Integer.valueOf(myCarOrderTime.getBeginTime().substring(0,2));//星期时间
			int e=Integer.valueOf(myCarOrderTime.getEndTime().substring(0,2));//星期时间
			//判断开始和结束时间是否包含在设置时间内
			System.out.println(begin+":"+end);
			System.out.println(b+":"+e);
			System.out.println((b<=begin&&e>=end));
			if((b<=begin&&e>=end)){
				return true;
			}
		}
		return false;
	}
    //公共停车场订单记录查询
	@Override
	public List<ParkOrderInfo> getParkOrderInfoByUserid(Map<String,Object> map) {
		return mapper.getParkOrderInfoByUserid(map);
	}
    //公共停车场订单记录总数
	@Override
	public int getParkOrderInfoCount(String userid) {
	
		return mapper.getParkOrderInfoCount(userid);
	}
	//长租订单详情查询
	@Override
	public LongRentOrderDto getLongRentOrderDetails(String orderId) {
		return mapper.getLongRentOrderDetails(orderId);
	}
    //临时停车订单详情查询
	@Override
	public TempOrderDto getTempOrderDetail(String orderId) {
		return mapper.getTempOrderDetail(orderId);
	}
    //用户评价
	@Override
	public int updateEvaluationByOrderId(String orderId, Integer evaluation) {
	
		return mapper.updateEvaluationByOrderId(orderId, evaluation);
	}
	//通过orderId查询订单状态
	@Override
	public OrderStateDto selectOrderStateByOrderId(String orderNum) {
	
		return mapper.selectOrderStateByOrderId(orderNum);
	}
	//供方接受订单 
	@Override
	public boolean acceptOrder(String orderNum) throws Exception {
		OrderStateDto osd=mapper.selectOrderStateByOrderId(orderNum);
		if(osd==null){
			return false;//没有找到相关订单的信息 ,或者是已经处理
		}
		// 修改订单的状态
		if (mapper.updateOrderStateByOrderId(orderNum,1) <= 0) {
			return false;
		}
		
		//给供方添加余额
		if(paymapper.updateAvailableBalance(osd.getUserid(), osd.getCountMoney())<=0){
			throw new Exception("用户退款失败！");
		}
		//添加记录
		if(paymapper.addtransactionrecord(osd.getUserid(), 1, osd.getCountMoney())<=0){
			throw new Exception("添加记录失败！");
		}
		return true;
	}
   //拒绝订单处理
	@Override
	public boolean refuseOrder(String orderNum) throws Exception {
		CallBackRefuceInfo cbr=paymapper.getCountMoneyAndUseridByorderNum(orderNum);
		if(cbr==null){
			return false;//没有找到相关的订单，或者是已经处理
		}
		// 修改订单的状态
		if (mapper.updateOrderStateByOrderId(orderNum,2) <= 0) {
			return false;
		}
		//给用户退现
		if(paymapper.updateAvailableBalance(cbr.getUserid(), cbr.getCountMoney())<=0){
			throw new Exception("用户退款失败！");
		}
		//添加记录
		if(paymapper.addtransactionrecord(cbr.getUserid(), 3, cbr.getCountMoney())<=0){
			throw new Exception("添加记录失败！");
		}
		return true;
	}
	//供方我的订单（长租）
	@Override
	public List<SupplierOrder> getLongRentInfo(Map<String, Object> map) {
		return mapper.getLongRentInfo(map);
	}
	//供方我的订单（长租）总数
	@Override
	public int getLongRentInfoCount(String userid) {
		return mapper.getCarportOrderInfoCount(userid);
	}
	//供方我的订单（临时出租）
	@Override
	public List<SupplierOrder> getTemporaryRentInfo(Map<String, Object> map) {
		return mapper.getTemporaryRentInfo(map);
	}
	//供方我的订单（临时出租）总数
	@Override
	public int getTemporaryRentInfoCount(String userid) {
		return mapper.getTemporaryRentInfoCount(userid);
	}
    //供方我的订单临时出租详情
	@Override
	public TemporaryRentDto getTemporaryRentDetails(String orderId) {
		return mapper.getTemporaryRentDetails(orderId);
	}
    //供方我的订单长租详情
	@Override
	public LongRentDto getLongRentDetails(String orderId) {
		return mapper.getLongRentDetails(orderId);
	}
}

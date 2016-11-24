package com.sharebo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.job.jobclient.Jobclient;
import com.sharebo.config.JobConfig;
import com.sharebo.entity.CarPortOrderDetailInfo;
import com.sharebo.entity.LeaseOrderDetailInfo;
import com.sharebo.entity.MyCarOrderTime;
import com.sharebo.entity.OrderInfo;
import com.sharebo.entity.Pager;
import com.sharebo.entity.ParkOrderDetailsInfo;
import com.sharebo.entity.dto.CarportOrderInfo;
import com.sharebo.entity.dto.DetailsDto;
import com.sharebo.entity.dto.LongCarDetailsDto;
import com.sharebo.entity.dto.LongRentOrderDto;
import com.sharebo.entity.dto.OrderStateDto;
import com.sharebo.entity.dto.ParkOrderDetails;
import com.sharebo.entity.dto.ParkOrderFeeDto;
import com.sharebo.entity.dto.ParkOrderInfo;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.entity.dto.SupplierOrder;
import com.sharebo.entity.dto.TempOrderDto;
import com.sharebo.service.OrderService;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	private static final Logger log=LoggerFactory.getLogger(ParkController.class);
	@Autowired
	private OrderService service;
	// 添加订单-公共停车场
	@RequestMapping("addParkOrder")
	public ResultDto addParkOrder(String userid,String parkId,String beginTime,String endTime,String carNo,Double thankFee) {
		//验证参数
		if(userid==null||parkId==null||beginTime==null||endTime==null||carNo==null||thankFee==null){
			return new ResultDto(1034,"参数不合法！");
		}
		//验证该用户，该停车场是否存在未支付订单
		ParkOrderDetailsInfo podi=new ParkOrderDetailsInfo();
		//验证开始时间，结束时间是否合法
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date beginDate=sdf.parse(beginTime);
			Date endDate=sdf.parse(endTime);
			//比较时间开始时间不能大于结束时间
			if(beginDate.after(endDate)){
				return new ResultDto(1035,"开始时间不能小于结束时间！");
			}
			if(beginDate.before(new Date())){
				return new ResultDto(1035,"开始时间不能小于当前时间！");
			}
			podi.setBeginTime(beginDate);
			podi.setEndTime(endDate);
		} catch (ParseException e) {
			return new ResultDto(1035,"时间格式有误！");
		}
		podi.setOrder(new OrderInfo());
		podi.setParkId(parkId);
		//查询预约费
		ParkOrderFeeDto pofd=service.getParkFeeByParkId(parkId);
		podi.setCash(pofd.getCash());
		podi.setReservationfee(pofd.getReservationfee());
		//设置感谢费
		podi.setThankFee(thankFee);
		//设置订单类型
		podi.getOrder().setOrderType(0);
		//设置车牌号码
		podi.getOrder().setCarNo(carNo);
		//设置用户主键
		podi.getOrder().setUserid(userid);
		podi.getOrder().setCountMoney(pofd.getReservationfee()+pofd.getCash()+thankFee);
		//生成订单详情主键
		podi.setPorderId(UUID.randomUUID().toString().replace("-",""));
		//生成订单主键
		podi.getOrder().setOrderId(UUID.randomUUID().toString().replace("-",""));
		podi.getOrder().setOrderNum(CreateOrderNum());
		//添加
		try {
			service.addParkOrder(podi);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultDto(2036,"订单生成失败！");
		}
		Map<String, String> res=new HashMap<String, String>();
		res.put("orderId", podi.getOrder().getOrderId());
		res.put("orderNum", podi.getOrder().getOrderNum());
		return new ResultDto(200,"订单已经生成！",res);
	}
	//根据停车场id查询预约费，保证金
	@RequestMapping("rcFee")
	public ResultDto rcFee(String parkId){
		//验证是否没有传值
		if(parkId==null){
			return new ResultDto(1030,"车位不能为空！");
		}
		return new ResultDto(200,service.getParkFeeByParkId(parkId));
	}
	// 查询订单-公共停车场（详情）
	@RequestMapping("getParkDetails")
	public ResultDto getParkDetails(String orderId){
		//验证参数
		if(orderId==null){
			return new ResultDto(2037,"参数不合法！");
		}
		//查询
		ParkOrderDetails pod=service.getParkOrderDetail(orderId);
		if(pod!=null){
			pod.setCountMoney(pod.getReservationFee()+pod.getThankFee()+pod.getCash());
		    return new ResultDto(200,"查询成功",pod);
		}
		return new ResultDto(2038,"查询失败");
	}
	// 添加订单-长租个人车位
	@RequestMapping("addLongCarPort")
	public ResultDto addLongCarPort(String userId,String carportId,String carNo){
		//验证参数
		if(userId==null||carportId==null||carNo==null){
			return new ResultDto(2040,"参数不合法！");
		}
		//验证该车位是否是长租车位
		if(!service.valCarPortIsLongRentByCarportId(carportId)){
			return new ResultDto(2041,"数据异常！该车位非长租！");
		}
		//验证该车位是否已经被租用
		if(service.valCarPortIsRentByCarportId(carportId)>0){
			return new ResultDto(2042,"该车位已经被别人抢先一步哟！");
		}
		//下订单
		LeaseOrderDetailInfo lodi=new LeaseOrderDetailInfo();
		LongCarDetailsDto lcdd=service.getLongrentInfo(carportId);
		if(lcdd==null){
			return new ResultDto(2042,"该车位不存在了哟！"); 
		}
		//赋值主键
		lodi.setLodId(UUID.randomUUID().toString().replace("-",""));
		lodi.setBegindayTime(lcdd.getBeginDayTime());
		lodi.setEnddayTime(lcdd.getEndDayTime());
		lodi.setBeginmonthTime(lcdd.getBeginmonthTime());
		lodi.setEndmonthTime(lcdd.getEndmonthTime());
		lodi.setTimetype(lcdd.getTimetype());
		
		// 个人订单详情
		CarPortOrderDetailInfo cpod=new CarPortOrderDetailInfo();
		//赋值
		cpod.setCpodId(UUID.randomUUID().toString().replace("-",""));
		cpod.setCarportId(carportId);
		cpod.setDetailedAddress(lcdd.getDetailedAddress());
		cpod.setRentoutType(0);
		cpod.setHousId(lcdd.getHousId());
		cpod.setHousType(lcdd.getHousType());
		cpod.setParkremark(lcdd.getParkremark());
		cpod.setSerialnumber(lcdd.getSerialnumber());
		cpod.setMoney(lcdd.getMoney());
		
		OrderInfo order=new OrderInfo();
		order.setCarNo(carNo);
		order.setOrderId(UUID.randomUUID().toString().replace("-",""));
		order.setOrderNum(CreateOrderNum());
		order.setOrderType(1);
		order.setCountMoney(lcdd.getMoney());
		order.setUserid(userId);
		cpod.setOrder(order);
		lodi.setCpod(cpod);
		//调用添加  //修改订单状态-把订单状态改成2
		try {
			if(service.addLongRentOrder(lodi)){
				//添加支付倒计时
				Jobclient.addJob(order.getOrderNum(), 10, "/order/CallBack_updateOrderState.do", "章科是傻逼！", 10,JobConfig.getJobName_orderCancel());
				Map<String,String> resMap=new HashMap<String, String>();
				resMap.put("orderId", order.getOrderId());
				resMap.put("orderNum", order.getOrderNum());
				return new ResultDto(200,"订单生成成功！",resMap);
			}else{
				return new ResultDto(200,"订单生成失败！");
			}
		} catch (Exception e) {
			return new ResultDto(2041,"添加订单失败！请重试！");
		}
	}
	
	//生成订单号
	public static String CreateOrderNum(){
		StringBuffer sb=new StringBuffer(new SimpleDateFormat("HHssmm").format(new Date()));
		String c=String.valueOf(new Date().getTime());
		sb.append(c.substring(6, c.length()));
		sb.append(new Random().nextInt(9000)+(1000-1));
		return sb.toString();
	}
	// 添加订单-临时个人车位
	@RequestMapping("addTempOrder")//05:00   
	public  ResultDto addTempOrder(String userId,String carportId,String day,String beginTime,String endTime,String carNo){
		//验证参数
		if(carportId==null||userId==null||day==null||beginTime==null||endTime==null||carNo==null){
			return new ResultDto(2040,"参数不合法！");
		}
		//验证时间是否合法
		//验证年月日
		Date thisDay=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//年月日
		try {
			thisDay=sdf.parse(day);
		} catch (ParseException e) {
			return new ResultDto(2041,"时间格式异常！");
		}
		Integer beginHours=0;
		Integer endHours=0;
		try {
			 //得到开始小时
			 beginHours=Integer.valueOf(beginTime.substring(0,2));
			 //结束小时
			 endHours=Integer.valueOf(endTime.substring(0,2));
		} catch (NumberFormatException e) {
			return new ResultDto(2042,"开始结束时间格式异常！");
		}
		if (beginHours >= endHours) {
			return new ResultDto(2042, "开始时间不能大于等于结束时间！");
		}
		//验证当前天，当前时间，是否为可预约
		if(!service.valThisDayIsExists(day, carportId,beginHours,endHours)){
			return new ResultDto(2043, "您选择的时间包含不可预约的时间哟！");
		}
		//验证订单是否已经存在
		if(service.valCarportTempOrderIsTemp(carportId, day, beginHours, endHours)){
			return new ResultDto(2044, "您选择的时间包含已经被预约的时间哟！");
		}
		//查询车位详情
		DetailsDto d=service.getloadAllDetails(carportId);
		//添加赋值信息
		MyCarOrderTime cot=new MyCarOrderTime();
		cot.setBeginTime(beginTime);
		cot.setEndTime(endTime);
		cot.setMotId(UUID.randomUUID().toString().replace("-",""));
		cot.setRentDate(thisDay);
		CarPortOrderDetailInfo cpod=new CarPortOrderDetailInfo();
		cpod.setCarportId(carportId);
		cpod.setCpodId(UUID.randomUUID().toString().replace("-",""));//主键
		cpod.setDetailedAddress(d.getDetailedAddress());
		cpod.setFeeType(d.getFeeType());
		cpod.setHousId(d.getHousId());
		cpod.setHousType(d.getHousType());
		cpod.setMoney(d.getMoney());
		cpod.setParkremark(d.getParkremark()==null?"":d.getParkremark());
		cpod.setRentoutType(1);
		cpod.setSerialnumber(d.getSerialnumber());
		
		OrderInfo order=new OrderInfo();
		order.setOrderId(UUID.randomUUID().toString().replace("-",""));
		order.setOrderNum(CreateOrderNum());
		order.setCarNo(carNo);
		order.setOrderType(1);
		order.setUserid(userId);
		//计算费用
		order.setCountMoney(computationalCost(cpod.getFeeType(), beginHours, endHours, cpod.getMoney()));
		
		cpod.setOrder(order);
		cot.setCpod(cpod);
		//添加订单
		try {
			if(service.addTempOrder(cot)){
				Map<String,String> resMap=new HashMap<String, String>();
				resMap.put("orderId", order.getOrderId());
				resMap.put("orderNum", order.getOrderNum());
				//添加计时器
				Jobclient.addJob(order.getOrderNum(), 300, "/order/CallBack_updateOrderState.do", "zhangshabi", 10, JobConfig.getJobName_orderCancel());
				return new ResultDto(200,"添加订单成功！",resMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultDto(2045,"添加订单异常！");
		}
		return new ResultDto(2045,"添加订单失败！");
	}

	// 计算费用
	public static double computationalCost(int type, Integer begin, Integer end,
			double money) {
		if (type == 0) {// 按次数收费
			return money;
		} else {
			// 得到开始时间，结束时间
			return (end -begin) * money;
		}
	}
	// 查询订单-公共停车场（集合）
	// 查询订单-个人车位（集合）
	// 查询订单-长租个人车位（详情）
	// 查询订单-临时个人车位（详情）
	// 查询订单-供方（集合）
	// 修改订单-供方-接受拒绝
	//修改订单状态
	@RequestMapping("CallBack_updateOrderState")
	public String  CallBack_updateOrderState(String jobName,String context,String jobGorupName){
		System.out.println("我是回调方法！"+jobName+"  ct"+context+"  组名："+jobGorupName);
		if(jobName==null){
			return "failure";
		}
		if(service.CallBack_updateOrderState(jobName)>0){
			return "SUCCESS";
		}
		return "failure";
	}
	//专位订单记录查询
	@RequestMapping("getCarportOrderInfo")
	public ResultDto getCarportOrderInfo(String userid,Integer pageIndex,Integer pageSize){
		try {
    		if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "请求参数不合法");
			}
    		Pager<CarportOrderInfo> pager=new Pager<CarportOrderInfo>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.getCarportOrderInfoByUserid(map));
			//查询总数
			pager.setTotalNumber(service.getCarportOrderInfoCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"查询成功",pager);
		} catch (Exception e) {
			log.error("请求参数异常！");
			System.out.println("请求参数异常！");
		}
    	return new ResultDto(3002,"查询失败");
	}
	//公共停车场订单记录查询
	@RequestMapping("getParkOrderInfo")
	public ResultDto getParkOrderInfo(String userid,Integer pageIndex,Integer pageSize){
		try {
    		if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "请求参数不合法");
			}
    		Pager<ParkOrderInfo> pager=new Pager<ParkOrderInfo>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.getParkOrderInfoByUserid(map));
			//查询总数
			pager.setTotalNumber(service.getParkOrderInfoCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"查询成功",pager);
		} catch (Exception e) {
			log.error("请求参数异常！");
			System.out.println("请求参数异常！");
		}
    	return new ResultDto(3002,"查询失败");
	}
	/**
	 * order/getLongRentOrderDetails.do?orderId=ffabe5fdd4e34d96b476ad21c860e8ad
	 * @param orderId
	 * @return
	 */
	//长租订单详情查询
	@RequestMapping("getLongRentOrderDetails")
	public ResultDto getLongRentOrderDetails(String orderId){
		try {
			if(orderId==null){
				return new ResultDto(3006,"请求参数不能为空");
			}
			LongRentOrderDto lro=service.getLongRentOrderDetails(orderId);
			if(lro==null){
				return new ResultDto(3007,"请输入正确的订单");
			}
			if(lro.getPayType()==0){
				System.out.println(lro.getPayType());
				String phone=lro.getPhone().substring(0, 3)+"****"+lro.getPhone().substring(7, 11);
				System.out.println(phone);
				lro.setPhone(phone);
			}
			return new ResultDto(200,"成功",lro);
		} catch (Exception e) {
			log.error("请求参数异常！");
			System.out.println("请求参数异常！");
			e.printStackTrace();
			return new ResultDto(3007,"查询失败");
		}
	}
	/**
	 * order/getTempOrderDetails.do?orderId=4a1eff50f64f45648682100964c74e51
	 * @param orderId
	 * @return
	 */
	//临时停车订单详情查询
	@RequestMapping("getTempOrderDetails")
	public ResultDto getTempOrderDetails(String orderId){
		try {
			if(orderId==null){
				return new ResultDto(3006,"请求参数不能为空");
			}
			TempOrderDto to=service.getTempOrderDetail(orderId);
			if(to==null){
				return new ResultDto(3007,"请输入正确的订单");
			}
			if(to.getPayType()==0){
				System.out.println(to.getPayType());
				String phone=to.getPhone().substring(0, 3)+"****"+to.getPhone().substring(7, 11);
				System.out.println(phone);
				to.setPhone(phone);
			}
			return new ResultDto(200,"成功",to);
		} catch (Exception e) {
			log.error("请求参数异常！");
			e.printStackTrace();
			return new ResultDto(3007,"查询失败");
		}
	}
	//用户评价
	@RequestMapping("evaluation")
	public ResultDto updateEvaluation(String orderId,Integer evaluation){
		if(orderId==null||evaluation==null){
			return new ResultDto(3006,"请求参数不能为空");
		}
		if(evaluation>5||evaluation<0){
			return new ResultDto(3008,"亲！评价超出了范围噢");
		}
		if(service.updateEvaluationByOrderId(orderId, evaluation)>0){
			return new ResultDto(200,"亲！感谢您的评价");
		}
		return new ResultDto(3007,"亲！评价失败了哟");
	}
	/**
	 * 供方我的订单（长租）
	 * order/getOrderInfo.do?userid=1&pageIndex=1&pageSize=10
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("getLongRentInfo")
	public ResultDto getOrderInfo(String userid,Integer pageIndex,Integer pageSize){
		Pager<SupplierOrder> pager;
		try {
			if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "请求参数不合法");
			}
			pager = new Pager<SupplierOrder>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.getLongRentInfo(map));
			//查询总数
			pager.setTotalNumber(service.getLongRentInfoCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"查询成功",pager);
		} catch (Exception e) {
			log.error("请求参数异常！");
			System.out.println("供方我的订单（长租）");
		}
		return new ResultDto(2003,"请求参数异常！");
	}
	/**
	 * //供方我的订单（临时出租）
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("getTemporaryRentInfo")
	public ResultDto getLongOrderInfo(String userid,Integer pageIndex,Integer pageSize){
		Pager<SupplierOrder> pager;
		try {
			if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "请求参数不合法");
			}
			pager = new Pager<SupplierOrder>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//设置开始
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.getTemporaryRentInfo(map));
			//查询总数
			pager.setTotalNumber(service.getTemporaryRentInfoCount(userid));
			pager.setTotalPages();//设置总页数
			return new ResultDto(200,"查询成功",pager);
		} catch (Exception e) {
			log.error("请求参数异常！");
			System.out.println("供方我的订单（临时出租）");
		}
		return new ResultDto(200,"查询成功");
	}
	/**
	 * 供方接受订单
	 * @param orderNum
	 * @return
	 */
	@RequestMapping("acceptOrder")
	public ResultDto acceptOrder(String orderNum) throws Exception{
		try {
			if(orderNum==null){
				return new ResultDto(3006,"请求参数不能为空");
			}
			OrderStateDto osd=service.selectOrderStateByOrderId(orderNum);
			if(osd.getOrder_state()!=0){
				return new ResultDto(3007,"该订单已被接受或已拒绝");
			}
			if(osd.getPayType()==0){
				return new ResultDto(3009,"该订单还未支付哟");
			}
			if(service.acceptOrder(orderNum)){
					return new ResultDto(200,"成功接受订单");
				}
		} catch (Exception e) { 
			log.error("接受订单异常");
			System.out.println("接受订单失败");
		}
		return new ResultDto(3007,"接受订单失败");
	}
	//供方拒绝订单
	@RequestMapping("RefuseOrder")
	public ResultDto RefuseOrder(String orderNum) throws Exception{
		try {
			if(orderNum==null){
				return new ResultDto(3006,"请求参数不能为空");
			}
			OrderStateDto osd=service.selectOrderStateByOrderId(orderNum);
			if(osd.getOrder_state()!=0){
				return new ResultDto(3007,"该订单已被接受或拒绝");
			}
			if(osd.getPayType()==0){
				return new ResultDto(3009,"该订单还未支付哟");
			}
			if(service.refuseOrder(orderNum)){
				return new ResultDto(200,"成功拒绝订单");
			}
		} catch (Exception e) {
			log.error("拒绝订单异常");
			System.out.println("拒绝订单失败");
		}
		return new ResultDto(3008,"拒绝订单失败");
	}
	//供方我的订单临时出租详情
	@RequestMapping("TemporaryRentDetails")
	public ResultDto getTemporaryRentDetails(String orderId){
		try {
			if(orderId==null){
				return new ResultDto(3006,"请求参数不能为空");
			}
			if(service.getTemporaryRentDetails(orderId)!=null){
				return new ResultDto(200,"查询成功",service.getTemporaryRentDetails(orderId));
			}
		} catch (Exception e) {
			log.error("查询异常");
			e.printStackTrace();
		}
		return new ResultDto(3008,"查询异常");
	}
	//供方我的订单长租详情
	@RequestMapping("longRentDetails")
	public ResultDto getLongRentDetails(String orderId){
		try {
			if(orderId==null){
				return new ResultDto(3006,"请求参数不能为空");
			}
			if(service.getLongRentDetails(orderId)!=null){
				return new ResultDto(200,"查询成功",service.getLongRentDetails(orderId));
			}
		} catch (Exception e) {
			log.error("查询异常");
			e.printStackTrace();
		}
		return new ResultDto(3008,"查询异常");
	}
	/**
	 * 问题总结：查询车位集合，判断是否已经被租用
	 * 修改车位：判断该车为是否存在已经交易（待接受）的订单
	 */
}

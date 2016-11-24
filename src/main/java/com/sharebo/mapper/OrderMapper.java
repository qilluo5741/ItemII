package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.MyCarOrderTime;
import com.sharebo.entity.OrderInfo;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.CarportOrderInfo;
import com.sharebo.entity.dto.LongRentDto;
import com.sharebo.entity.dto.LongRentOrderDto;
import com.sharebo.entity.dto.OrderStateDto;
import com.sharebo.entity.dto.ParkOrderDetails;
import com.sharebo.entity.dto.ParkOrderFeeDto;
import com.sharebo.entity.dto.ParkOrderInfo;
import com.sharebo.entity.dto.SupplierOrder;
import com.sharebo.entity.dto.TempOrderDto;
import com.sharebo.entity.dto.TemporaryRentDto;

public interface OrderMapper {
	// 通过parkID查询预约费用和保证金
	public ParkOrderFeeDto getParkFeeByParkId(@Param("parkId") String parkId);

	// 添加订单
	public int addOrderInfo(OrderInfo order);

	// 添加公共停车场订单详情
	public int addParkOrderDetails(com.sharebo.entity.ParkOrderDetailsInfo podi);

	/**
	 * 公共停车场订单详情查询
	 * 
	 * @param orderId
	 * @return
	 */
	public ParkOrderDetails getParkOrderDetail(@Param("orderId") String orderId);

	// 验证该车位是否是长租车位
	public int valCarPortIsLongRentByCarportId(
			@Param("carportId") String carportId);

	// 验证车位是否已经被租用
	public int valCarPortIsRentByCarportId(@Param("carportId") String carportId);

	// 添加个人车位详情
	public int addCarportorDerdetails(
			com.sharebo.entity.CarPortOrderDetailInfo podi);

	// 添加个人车位长租详情
	public int addLeaseorderdetails(com.sharebo.entity.LeaseOrderDetailInfo lodi);

	// 修改订单状态
	public int CallBack_updateOrderState(@Param("orderNum") String orderNum);

	// 查询星期是否为禁用
	public int valdisableWeek(@Param("week") Integer week,
			@Param("carportId") String carportId);

	// 验证天（小时是否合法）
	public int valDayisExists(@Param("sql") String sql);

	// 验证小时是否存啊在
	public int valHoursIsExists(@Param("carportId") String carportId,
			@Param("thisDay") String thisDay);

	// 查询星期规则
	public Weekrules weekTime(@Param("carportId") String carportId);

	// 专位订单记录查询
	public List<CarportOrderInfo> getCarportOrderInfoByUserid(
			Map<String, Object> map);

	// 专位订单记录总数
	public int getCarportOrderInfoCount(@Param("userid") String userid);

	// 添加个人车位长租详情
	public int addCarportorDerdetailsByTemp(com.sharebo.entity.CarPortOrderDetailInfo lodi);
	//添加个人临时订单时间
	public int addMyCarOrderTime(com.sharebo.entity.MyCarOrderTime cot);
	//查询当前时间，车位存在的订单
	public List<MyCarOrderTime> getMyCarOrderTime(@Param("carportId")String carportId,@Param("rentDate")String rentDate);
	
	//公共停车场订单记录查询
	public List<ParkOrderInfo> getParkOrderInfoByUserid(Map<String,Object> map);
	//公共停车场订单记录总数
	public int getParkOrderInfoCount(@Param("userid")String userid);
	//长租订单详情查询
	public LongRentOrderDto getLongRentOrderDetails(@Param("orderId")String orderId);
	//临时停车订单详情查询
	public TempOrderDto getTempOrderDetail(@Param("orderId")String orderId);
	//用户评价
	public int updateEvaluationByOrderId(@Param("orderId")String orderId,@Param("evaluation")Integer evaluation);
	//通过orderId查询订单状态
	public OrderStateDto selectOrderStateByOrderId(@Param("orderNum")String orderNum);
	//(供方)修改订单状态
	public int updateOrderStateByOrderId(@Param("orderNum")String orderNum,@Param("order_state")Integer order_state);
	//供方我的订单（长租）
	public List<SupplierOrder> getLongRentInfo(Map<String,Object> map);
	//供方我的订单（长租）总数
	public int getLongRentInfoCount(@Param("userid")String userid);
	//供方我的订单（临时出租）
	public List<SupplierOrder> getTemporaryRentInfo(Map<String,Object> map);
	//供方我的订单（临时出租）总数
	public int getTemporaryRentInfoCount(@Param("userid")String userid);
	//供方我的订单临时出租详情
	public TemporaryRentDto getTemporaryRentDetails(@Param("orderId")String orderId);
	//供方我的订单长租详情
	public LongRentDto getLongRentDetails(@Param("orderId")String orderId);
}

package com.sharebo.service;

import java.util.List;
import java.util.Map;
import com.sharebo.entity.LeaseOrderDetailInfo;
import com.sharebo.entity.MyCarOrderTime;
import com.sharebo.entity.ParkOrderDetailsInfo;
import com.sharebo.entity.dto.DetailsDto;
import com.sharebo.entity.dto.CarportOrderInfo;
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

public interface OrderService {
	// 通过parkID查询预约费用和保证金
	public ParkOrderFeeDto getParkFeeByParkId(String parkId);

	// 添加公共停车场订单
	public boolean addParkOrder(ParkOrderDetailsInfo podi) throws Exception;

	/**
	 * 公共停车场订单详情查询
	 * 
	 * @param orderId
	 * @return
	 */
	public ParkOrderDetails getParkOrderDetail( String orderId);

	// 验证该车位是否是长租车位
	public boolean valCarPortIsLongRentByCarportId(String carportId);

	// 验证车位是否已经被租用
	public int valCarPortIsRentByCarportId(String carportId);

	// 查询个人长租详情
	public LongCarDetailsDto getLongrentInfo(String carportId);
	//下长租订单
	public boolean addLongRentOrder(LeaseOrderDetailInfo lodi) throws Exception;
	//修改订单状态
	public int CallBack_updateOrderState(String orderNum);
	
	//验证当天时间是否可预约
	public boolean valThisDayIsExists(String day,String carPortId,Integer begin,Integer end);
	//验证订单是否已经存在预约的时间段
	public boolean valOrderIsExists(String day,String carPortId,Integer begin,Integer end);
	//查询临时订单详情
	public DetailsDto getloadAllDetails(String carportId);
	//专位订单记录查询
	public List<CarportOrderInfo> getCarportOrderInfoByUserid(Map<String,Object> map);
	//专位订单记录总数
	public int getCarportOrderInfoCount(String userid);
	//添加临时订单
	public boolean addTempOrder(MyCarOrderTime cot) throws Exception;
	//验证车位是否已经预约或者合法
	public boolean valCarportTempOrderIsTemp(String carportId,String thisdate,Integer begin,Integer end);
	//公共停车场订单记录查询
	public List<ParkOrderInfo> getParkOrderInfoByUserid(Map<String,Object> map);
	//公共停车场订单记录总数
	public int getParkOrderInfoCount(String userid);
	//长租订单详情查询
	public LongRentOrderDto getLongRentOrderDetails(String orderId);
	//临时停车订单详情查询
	public TempOrderDto getTempOrderDetail(String orderId);
	//用户评价
	public int updateEvaluationByOrderId(String orderId,Integer evaluation);
	//通过orderNum查询订单状态
	public OrderStateDto selectOrderStateByOrderId(String orderNum);
	//供方接受订单 
	public boolean acceptOrder(String orderNum) throws Exception;
	//拒绝订单处理
	public boolean refuseOrder(String orderNum) throws Exception;
	//供方我的订单（长租）
	public List<SupplierOrder> getLongRentInfo(Map<String,Object> map);
	//供方我的订单（长租）总数
	public int getLongRentInfoCount(String userid);
	//供方我的订单（临时出租）
	public List<SupplierOrder> getTemporaryRentInfo(Map<String,Object> map);
	//供方我的订单（临时出租）总数
	public int getTemporaryRentInfoCount(String userid);
	//供方我的订单临时出租详情
	public TemporaryRentDto getTemporaryRentDetails(String orderId);
	//供方我的订单长租详情
	public LongRentDto getLongRentDetails(String orderId);
}

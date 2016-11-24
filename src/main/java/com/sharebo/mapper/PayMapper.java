package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.CallBackRefuceInfo;
import com.sharebo.entity.dto.UserorregidDto;

public interface PayMapper {
	// 验证订单是否存在，并且得到费用
	public Double getCountMoneyByOrderNum(@Param("orderNum") String orderNum);

	// 根据用户Id查询剩余的余额
	public Double getAvailableBalanceByuserid(@Param("userid") String userid);

	// 修改余额
	public int updateAvailableBalanceByUserid(
			@Param("countMoney") Double countMoney,
			@Param("userid") String userid);

	// 修改订单状态
	public int updateOrderinfo(@Param("payType") Integer payType,
			@Param("orderNum") String orderNum);

	// 添加交易记录
	public int addtransactionrecord(@Param("userid") String userid,
			@Param("tradeType") Integer tradeType,
			@Param("tradeMoney") Double tradeMoney);

	// 验证是否是个人车位
	public Integer valOrderTypeByOrderNum(@Param("orderNum") String orderNum);

	// 判断该公共停车场保安是否自动接单
	public Integer valParkAutomaticOrder(@Param("orderNum") String orderNum);

	// 修改订单状态
	public int updateOrderStateByOrderNum(@Param("orderNum") String orderNum,
			@Param("order_state") Integer order_state);

	// 通过订单号码查询退款金额 以及userid
	public CallBackRefuceInfo getCountMoneyAndUseridByorderNum(
			@Param("orderNum") String orderNum);

	// 给用户修改余额
	public int updateAvailableBalance(@Param("userid") String userid,
			@Param("availableBalance") Double availableBalance);
	//查询userid
	public String valParkOrderByuserid(@Param("orderNum") String orderNum);
	//查询推送的regid(
	public UserorregidDto getloadAllcarNoandregid(@Param("orderNum") String orderNum);
	// 验证订单是否存在，并且得到需要支付的费用
	public Double getpayMoneyBypayidentifying(@Param("payidentifying") String payidentifying);
	//修改状态
	public int updateparkcharge(@Param("payType") Integer payType,@Param("payidentifying") String payidentifying);
	//根据查询停车缴费userid
	public String getvalparkchargeByuserid(@Param("payidentifying") String payidentifying);
}

package com.sharebo.service;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.UserorregidDto;

public interface PayService {
	// 验证订单是否存在，并且得到费用
	public Double getCountMoneyByOrderNum(String orderNum);

	// 根据用户Id查询剩余的余额
	public Double getAvailableBalanceByuserid(String userid);

	// 操作订单状态
	public boolean updateOrder(String orderNum, Double countMoney, String userid)
			throws Exception;
	// 验证是否是个人车位
	public int valOrderTypeByOrderNum(String orderNum);

	// 判断该公共停车场保安是否自动接单
	public Integer valParkAutomaticOrder(String orderNum);
	// 修改订单状态
	public int updateOrderStateByOrderNum(String orderNum, Integer order_state);
	//拒绝订单处理
	public boolean callBackRefuce(String orderNum) throws Exception;
	// 支付宝操作订单状态
	public boolean updateOrderalipay(String orderNum, Double countMoney, String userid) throws Exception;
	//支付宝查询userid
	public String valParkOrderByuserid(String orderNum);
	//微信
	public boolean updateOrderweiixn(String orderNum, Double countMoney, String userid) throws Exception;
	//查询推送的regid(
	public UserorregidDto getloadAllcarNoandregid(String orderNum);
	//操作停车订单状态
	public boolean updateparkcharge(String payidentifying, Double payMoney, String userid)throws Exception;
	// 验证订单是否存在，并且得到需要支付的费用
	public Double getpayMoneyBypayidentifying(String payidentifying);
	//根据查询停车缴费userid
	public String getvalparkchargeByuserid(@Param("payidentifying") String payidentifying);
	//支付宝操作订单状态
	public boolean updateparkchargealipay(String payidentifying, Double payMoney, String userid) throws Exception;
	//支付宝操作订单状态
	public boolean updateparkchargeweiixn(String payidentifying, Double payMoney, String userid) throws Exception;
}

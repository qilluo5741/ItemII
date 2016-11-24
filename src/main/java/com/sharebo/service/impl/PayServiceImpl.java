package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sharebo.entity.dto.CallBackRefuceInfo;
import com.sharebo.entity.dto.UserorregidDto;
import com.sharebo.mapper.PayMapper;
import com.sharebo.service.PayService;
@Service
@Transactional(rollbackFor=Exception.class)
public class PayServiceImpl implements PayService {
	@Autowired
	private PayMapper mapper;

	@Override
	public Double getCountMoneyByOrderNum(String orderNum) {
		return mapper.getCountMoneyByOrderNum(orderNum);
	}
	@Override
	public Double getAvailableBalanceByuserid(String userid) {
		return mapper.getAvailableBalanceByuserid(userid);
	}
	@Override
	public boolean updateOrder(String orderNum, Double countMoney,String userid)throws Exception {
		// 减去余额
		if(mapper.updateAvailableBalanceByUserid(countMoney, userid)<=0){
			return false;
		}
		// 修改订单状态
		if(mapper.updateOrderinfo(3, orderNum)<=0){
			throw new Exception("修改订单状态失败！");
		}
		// 插入交易记录
		if(mapper.addtransactionrecord(userid, 2, -countMoney)<=0){
			throw new Exception("插入交易记录失败！");
		}
		return true;
	}
	//验证是否是个人车位
	@Override
	public int valOrderTypeByOrderNum(String orderNum) {
		return mapper.valOrderTypeByOrderNum(orderNum);
	}
	//判断该公共停车场保安是否自动接单
	@Override
	public Integer valParkAutomaticOrder(String orderNum) {
		return mapper.valParkAutomaticOrder(orderNum);
	}
	//修改订单的状态 已完成--------自动接单
	@Override
	public int updateOrderStateByOrderNum(String orderNum, Integer order_state) {
		return mapper.updateOrderStateByOrderNum(orderNum, order_state);
	}
	@Override
	public boolean callBackRefuce(String orderNum) throws Exception {
		//通过订单NUM找到用户 ,并得到总费用，
		CallBackRefuceInfo cbr=mapper.getCountMoneyAndUseridByorderNum(orderNum);
		if(cbr==null){
			return false;//没有找到相关的订单，或者是已经处理
		}
		// 修改订单的状态
		if (mapper.updateOrderStateByOrderNum(orderNum, 2) <= 0) {
			return false;
		}
		//给用户退现
		if(mapper.updateAvailableBalance(cbr.getUserid(), cbr.getCountMoney())<=0){
			throw new Exception("用户退款失败！");
		}
		//添加记录
		if(mapper.addtransactionrecord(cbr.getUserid(), 3, cbr.getCountMoney())<=0){
			throw new Exception("添加记录失败！");
		}
		return true;
	}
	//支付宝支付
	@Override
	public boolean updateOrderalipay(String orderNum, Double countMoney, String userid) throws Exception {
		// 修改订单状态
		if(mapper.updateOrderinfo(1,orderNum)<=0){
			throw new Exception("修改订单状态失败！");
		}
		// 插入交易记录
		if(mapper.addtransactionrecord(userid, 4,-countMoney)<=0){
			throw new Exception("插入交易记录失败！");
		}
		return true;
	}
	//根据订单号查询userid
	@Override
	public String valParkOrderByuserid(String orderNum) {
		return mapper.valParkOrderByuserid(orderNum);
	}
	@Override
	public boolean updateOrderweiixn(String orderNum, Double countMoney, String userid) throws Exception {
		//微信修改订单状态
		if(mapper.updateOrderinfo(2,orderNum)<=0){
			throw new Exception("修改订单状态失败！");
		}
		//微信插入交易记录
		if(mapper.addtransactionrecord(userid,5,-countMoney)<=0){
			throw new Exception("插入交易记录失败！");
		}
		return true;
	}
	@Override
	public UserorregidDto getloadAllcarNoandregid(String orderNum) {
		return mapper.getloadAllcarNoandregid(orderNum);
	}
	@Override
	public Double getpayMoneyBypayidentifying(String payidentifying) {
		return mapper.getpayMoneyBypayidentifying(payidentifying);
	}
	@Override
	public boolean updateparkcharge(String payidentifying, Double payMoney, String userid) throws Exception {
		// 减去余额
		if(mapper.updateAvailableBalanceByUserid(payMoney, userid)<=0){
			return false;
		}
		// 修改订单状态
		if(mapper.updateparkcharge(3,payidentifying)<=0){
			throw new Exception("修改订单状态失败！");
		}
		// 插入交易记录
		if(mapper.addtransactionrecord(userid, 2, -payMoney)<=0){
			throw new Exception("插入交易记录失败！");
		}
		return true;
	}
	//停车缴费查询userid给用户插入记录使用
	@Override
	public String getvalparkchargeByuserid(String payidentifying) {
		return mapper.getvalparkchargeByuserid(payidentifying);
	}
	@Override
	public boolean updateparkchargealipay(String payidentifying, Double payMoney, String userid) throws Exception {
		// 修改订单状态
		if(mapper.updateparkcharge(1,payidentifying)<=0){
			throw new Exception("修改订单状态失败！");
		}
		// 插入交易记录
		if(mapper.addtransactionrecord(userid, 4,-payMoney)<=0){
			throw new Exception("插入交易记录失败！");
		}
		return true;
	}
	@Override
	public boolean updateparkchargeweiixn(String payidentifying, Double payMoney, String userid) throws Exception {
		// 修改订单状态
		if(mapper.updateparkcharge(2,payidentifying)<=0){
			throw new Exception("修改订单状态失败！");
		}
		// 插入交易记录
		if(mapper.addtransactionrecord(userid,5,-payMoney)<=0){
			throw new Exception("插入交易记录失败！");
		}
		return true;
	}
}

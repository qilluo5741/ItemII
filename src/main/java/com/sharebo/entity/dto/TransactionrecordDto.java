package com.sharebo.entity.dto;

import java.io.Serializable;

public class TransactionrecordDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tradeType;//交易类型(1:订单收益，2：余额支付，3：退款金额  ,4:支付宝支付,5:微信支付，6...待定)',
	private double tradeMoney;//'交易金额(负数就是支出，正数就是收益 不能为0)',
	private String tradeTime;//'交易时间',
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public double getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(double tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
}

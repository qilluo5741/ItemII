package com.sharebo.entity.dto;

import java.io.Serializable;

public class TransactionrecordDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tradeType;//��������(1:�������棬2�����֧����3���˿���  ,4:֧����֧��,5:΢��֧����6...����)',
	private double tradeMoney;//'���׽��(��������֧���������������� ����Ϊ0)',
	private String tradeTime;//'����ʱ��',
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

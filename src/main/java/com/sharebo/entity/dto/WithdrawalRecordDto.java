package com.sharebo.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class WithdrawalRecordDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private double wrMoney;//              double(8,2) not null comment '提现金额(必须大于100)',
	private Date wrtime;//               datetime not null,
	private String cashAccount;
	private int wrstate;//提现状态(0:审核中  1:已经成功)
	public double getWrMoney() {
		return wrMoney;
	}
	public void setWrMoney(double wrMoney) {
		this.wrMoney = wrMoney;
	}
	public Date getWrtime() {
		return wrtime;
	}
	public void setWrtime(Date wrtime) {
		this.wrtime = wrtime;
	}
	public String getCashAccount() {
		return cashAccount;
	}
	public void setCashAccount(String cashAccount) {
		this.cashAccount = cashAccount;
	}
	public int getWrstate() {
		return wrstate;
	}
	public void setWrstate(int wrstate) {
		this.wrstate = wrstate;
	}
}

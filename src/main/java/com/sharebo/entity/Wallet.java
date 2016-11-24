package com.sharebo.entity;

import java.io.Serializable;

public class Wallet implements Serializable {
	private static final long serialVersionUID = 1L;
	private String walletId;
	private double availableBalance;
	private double freezeBalance;
	private String userid;
	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}
	public double getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}
	public double getFreezeBalance() {
		return freezeBalance;
	}
	public void setFreezeBalance(double freezeBalance) {
		this.freezeBalance = freezeBalance;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}

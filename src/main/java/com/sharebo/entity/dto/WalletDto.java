package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * Ǯ����
 * @author Administrator
 *
 */

public class WalletDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private double availableBalance;//     double(10,2) not null comment '�������',
	private double freezeBalance;//        double(10,2) not null comment '�������',
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
}

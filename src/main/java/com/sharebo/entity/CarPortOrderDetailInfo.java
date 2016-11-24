package com.sharebo.entity;

import java.io.Serializable;

/**
 * 个人订单详情
 * 
 * @author niewei
 *
 */
public class CarPortOrderDetailInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpodId;// 个人订单详情主键
	private String housId;// '小区外键(对应小区)',
	private String detailedAddress;// 入口详细地址',
	private Integer rentoutType;// '出租类型(0:长租：1:临时出租)
								// 数据来自车位表 如果为长租 数据对应长租时间表 临时出租 数据对应临时时间表',
	private String housType;// '车位类型(地面；室内；充电桩)',
	private String serialnumber;// '车位编号',
	private String parkremark;// '停车说明',
	private OrderInfo order;// '订单外键',
	private Double money;// '长租单价费用',
	private Integer feeType;// '收费类型(1:按次收费 2：小时收费)',
	private String carportId;// '车位外键',
	public String getCpodId() {
		return cpodId;
	}
	public void setCpodId(String cpodId) {
		this.cpodId = cpodId;
	}
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public Integer getRentoutType() {
		return rentoutType;
	}
	public void setRentoutType(Integer rentoutType) {
		this.rentoutType = rentoutType;
	}
	public String getHousType() {
		return housType;
	}
	public void setHousType(String housType) {
		this.housType = housType;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getParkremark() {
		return parkremark;
	}
	public void setParkremark(String parkremark) {
		this.parkremark = parkremark;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public String getCarportId() {
		return carportId;
	}
	public void setCarportId(String carportId) {
		this.carportId = carportId;
	}
	
}

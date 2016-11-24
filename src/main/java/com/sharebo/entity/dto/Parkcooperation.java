package com.sharebo.entity.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 停车场合作
 * @author Administrator
 *
 */
public class Parkcooperation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String pcId;
	private String housId;//小区
	private String contactName;//联系人名字
	private String phone;//联系人号码
	private String entrance;//入口
	private String type;//停车场类型
	private Double money;//收费
	private String  beginTime;//开始运营时间
	private String  endTime;//结束运营时间
	private Integer cooperationintention;//合作意向(1:免费上线，2：关联物业 3：关联保安)
	private Integer feeType;//收费模式(1:按次收费  2:按小时收费)
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getHousId() {
		return housId;
	}
	public void setHousId(String housId) {
		this.housId = housId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getCooperationintention() {
		return cooperationintention;
	}
	public void setCooperationintention(Integer cooperationintention) {
		this.cooperationintention = cooperationintention;
	}
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public static void main(String[] args) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String time=dateFormat.format(now);
		System.out.println(time);
	}
}

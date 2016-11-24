package com.sharebo.entity;

import java.util.Date;

/**
 * 个人车位长租订单详情
 * 
 * @author niewei
 *
 */
public class LeaseOrderDetailInfo {

	private String lodId;// 个人车位长租订单详情主键
	private Integer timetype;// '长租时间类型(0：全天出租，1：参考天时间段 ) 如果为全天出租
							// 就参照可出租月开始结束时间，其相反',
	private String enddayTime;// '长租可租天结束时间（一天中，什么时候结束）',
	private String begindayTime;// '长租可租天开始时间（一天中什么时候开始，在长租时间段类型为非全天可租可用）',
	private Date endmonthTime;// '长租可租月结束时间',
	private Date beginmonthTime;// '长租可租月开始时间',
	private CarPortOrderDetailInfo cpod;// '个人订单外键',
	public String getLodId() {
		return lodId;
	}
	public void setLodId(String lodId) {
		this.lodId = lodId;
	}
	public Integer getTimetype() {
		return timetype;
	}
	public void setTimetype(Integer timetype) {
		this.timetype = timetype;
	}
	public String getEnddayTime() {
		return enddayTime;
	}
	public void setEnddayTime(String enddayTime) {
		this.enddayTime = enddayTime;
	}
	public String getBegindayTime() {
		return begindayTime;
	}
	public void setBegindayTime(String begindayTime) {
		this.begindayTime = begindayTime;
	}
	public Date getEndmonthTime() {
		return endmonthTime;
	}
	public void setEndmonthTime(Date endmonthTime) {
		this.endmonthTime = endmonthTime;
	}
	public Date getBeginmonthTime() {
		return beginmonthTime;
	}
	public void setBeginmonthTime(Date beginmonthTime) {
		this.beginmonthTime = beginmonthTime;
	}
	public CarPortOrderDetailInfo getCpod() {
		return cpod;
	}
	public void setCpod(CarPortOrderDetailInfo cpod) {
		this.cpod = cpod;
	}
	
}

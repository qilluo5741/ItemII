package com.sharebo.entity.dto;

import java.io.Serializable;
/**
 * �û�������Ϣ
 * @author Administrator
 *
 */
public class Userinfodto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userid;
	private String name;
	private int sex;//�Ա�
	private int age;//����
	private String headportrait;//ͷ��
	private int beenDriv;//����
	private String signature;//����ǩ��
	private int number;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHeadportrait() {
		return headportrait;
	}
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	public int getBeenDriv() {
		return beenDriv;
	}
	public void setBeenDriv(int beenDriv) {
		this.beenDriv = beenDriv;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}

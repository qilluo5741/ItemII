package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.UserDto;
import com.sharebo.entity.dto.Userheadportrait;
import com.sharebo.entity.dto.Userinfodto;

public interface UserinfoMapper {
	/**
	 * 查询个人信息
	 * @param userid
	 * @return
	 */
	public Userinfodto getloadAllUserinfotype(@Param("userid")String userid);
	/**
	 * 修改头像
	 * @param headportrait
	 * @param userid
	 * @return
	 */
	public int updateheadportrait(@Param("headportrait")String headportrait,@Param("userid")String userid);
	/**
	 * 修改姓名
	 * @param name
	 * @param userid
	 * @return
	 */
	public int updatename(@Param("name")String name,@Param("userid")String userid);
	/**
	 * 修改性别
	 * @param sex
	 * @param userid
	 * @return
	 */
	public int updatesex(@Param("sex")Integer sex,@Param("userid")String userid);
	/**
	 * 修改年龄
	 * @param age
	 * @param userid
	 * @return
	 */
	public int updateage(@Param("age")Integer age,@Param("userid")String userid);
	/**
	 * 修改驾龄
	 * @param beenDriv
	 * @param userid
	 * @return
	 */
	public int updatebeenDriv(@Param("beenDriv")Integer beenDriv,@Param("userid")String userid);
	/**
	 * 修改个性签名
	 * @param signature
	 * @param userid
	 * @return
	 */
	public int updatesignature(@Param("signature")String signature,@Param("userid")String userid);
	/**
	 * 查询RegId
	 * @param userid
	 * @return
	 */
	public String getregidByUserid(@Param("userid")String userid);
	/**
	 * 修改regid
	 * @param regid
	 * @param userid
	 * @return
	 */
	public int updateRegIdByUserid(@Param("regid")String regid,@Param("userid")String userid);
	/**
	 * 修改payNo
	 * @param userid
	 * @param payNo
	 * @return
	 */
	public int updatePayNoByUserid(@Param("userid")String userid,@Param("payNo")String payNo);
	/**
	 * 查询用户收款账户
	 * @param userid
	 * @return
	 */
	public String getPayNoByUserid(@Param("userid")String userid);
	/**
	 * 查询头像头像
	 * @param userid
	 * @return
	 */
	public Userheadportrait getUserheadportraitByUserid(@Param("userid")String userid);
	/**
	 * 验证valToken是否失效
	 * @param valToken
	 * @param userid
	 * @return
	 */
	public Integer getvalTokenByUserid(@Param("valToken")String valToken,@Param("userid")String userid);
	/**
	 * 查询用户支付宝账户，可用余额
	 * @param userid
	 * @return
	 */
	public UserDto selectPayNoAndAvailableBalance(@Param("userid")String userid);
	public List<Userinfodto> selectbyuserid();
	/**
	 * 退出清空valToken
	 * @param valToken
	 * @param userid
	 * @return
	 */
	public Integer getdelectvalTokenByUserid(@Param("valToken")String valToken,@Param("userid")String userid);
	/**
	 * 修改活跃时间
	 * @param userid
	 * @return
	 */
	public Integer getvalreactivetimeByUserid(@Param("userid")String userid);
}

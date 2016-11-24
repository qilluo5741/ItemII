package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Carcollection;
import com.sharebo.entity.dto.AltogetherDto;
import com.sharebo.entity.dto.PersonalareaDto;

public interface CarcollectionMapper {
	/**
	 * 用户车位收藏
	 * @param carcoll
	 * @return
	 */
	public int addCarcollection(Carcollection carcoll);
	/**
	 * 查询失效是否存在
	 * @param carType
	 * @param userid
	 * @param identifying
	 * @return
	 */
	public int getCarcollectionydoesitexist(@Param("carType")Integer carType,@Param("userid")String userid,@Param("identifying")String identifying);
	/**
	 * 删除收藏
	 */
	public int updateCarcollectiony(@Param("isDelete")Integer isDelete,@Param("userid")String userid,@Param("carType")Integer carType,@Param("identifying")String identifying);
	/**
	 * 公共停车场查询
	 * @param userid
	 * @return
	 */
	public List<AltogetherDto> getloadAllAltogether(Map<String, Object> map);
	/**
	 * 公共停车场查询总数
	 * @param userid
	 * @return
	 */
	public Integer getAltogetherCount(@Param("userid")String userid);
	/**
	 * 个人车位查询
	 * @param userid
	 * @return
	 */
	public List<PersonalareaDto> getloadAllPersonalarea(Map<String, Object> map);
	/**
	 * 个人车位查询
	 * @param userid
	 * @return
	 */
	public Integer getloadAllPersonalareaCount(@Param("userid")String userid);
	
}

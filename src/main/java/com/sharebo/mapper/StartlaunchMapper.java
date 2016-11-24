package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Startlaunch;

public interface StartlaunchMapper {
	/**
	 * Æô¶¯Ò³
	 * @param startsize
	 * @param startopinion
	 * @return
	 */
	public Startlaunch getloadAllStartlaunch(@Param("startsize")int startsize,@Param("startopinion")String startopinion);
}

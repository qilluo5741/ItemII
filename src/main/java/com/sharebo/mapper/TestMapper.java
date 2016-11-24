package com.sharebo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.TestInfo;
/**
 * mapper
 * @author niewei
 *
 */
public interface TestMapper {
	List<TestInfo> loadAllTest(@Param("name")String name,@Param("id") Integer id);
	void testupdate(@Param("name")String name);
}

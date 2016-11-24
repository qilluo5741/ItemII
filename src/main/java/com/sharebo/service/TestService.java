package com.sharebo.service;

import java.util.List;

import com.sharebo.entity.TestInfo;

/**
 * 环境搭建测试接口
 * @author niewei
 *
 */
public interface TestService {
	List<TestInfo> loadAllTest();
	void testupdate();
}

package com.sharebo.service;

import java.util.List;

import com.sharebo.entity.TestInfo;

/**
 * ��������Խӿ�
 * @author niewei
 *
 */
public interface TestService {
	List<TestInfo> loadAllTest();
	void testupdate();
}

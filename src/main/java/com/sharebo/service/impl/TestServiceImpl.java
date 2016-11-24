package com.sharebo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharebo.entity.TestInfo;
import com.sharebo.mapper.TestMapper;
import com.sharebo.service.TestService;
@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestMapper mapper;

	public List<TestInfo> loadAllTest() {
		//Auto-generated method stub
		return mapper.loadAllTest("уб©ф",2);
	}

	public void testupdate() {
		mapper.testupdate("zhangke"+new Date().getTime());
	}
	
}

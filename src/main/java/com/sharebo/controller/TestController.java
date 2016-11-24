package com.sharebo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.dto.ResultDto;
import com.sharebo.entity.dto.UserorregidDto;
import com.sharebo.service.PayService;
import com.sharebo.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private PayService service1;
	@Autowired
	private TestService service;
	/**
	 * test/test1.do?out_trade_no=16344708543457223
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("test1")
	public @ResponseBody ResultDto test1(String out_trade_no) throws Exception{
		/*List<TestInfo> tests=service.loadAllTest();
		System.out.println(Jobclient.addJob("zsb",10, "test/testupdate.do", "zhangkeshishabi", 2));
		//Jobclient.removeJob("6546");
		System.out.println(Jobclient.monitoringJob(JobConfig.getJobGorupName()));*/
		//System.out.println(SmsAuthUtil.SendMessage("18581343206", "123456"));
		UserorregidDto user=service1.getloadAllcarNoandregid(out_trade_no);
		return new ResultDto(200,"³É¹¦",user);
	}
	@RequestMapping("test")
	public @ResponseBody ResultDto test(String name) throws Exception{
		/*List<TestInfo> tests=service.loadAllTest();
		System.out.println(Jobclient.addJob("zsb",10, "test/testupdate.do", "zhangkeshishabi", 2));
		//Jobclient.removeJob("6546");
		System.out.println(Jobclient.monitoringJob(JobConfig.getJobGorupName()));*/
		//System.out.println(SmsAuthUtil.SendMessage("18581343206", "123456"));
		return new ResultDto(1,"my!"+name);
	}
	@RequestMapping("testupdate")
	public @ResponseBody String testupdate(HttpServletRequest request){
		 //service.testupdate();
		Map<String, String[]> map=request.getParameterMap();
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String key=iter.next();
			System.out.println("key£º"+key+" value:"+map.get(key)[0]);
		} 
		return "SUCCESS";
	}
	@RequestMapping("aa")
	public @ResponseBody String aa(HttpServletRequest request) throws IOException{
		System.out.println(service);
		StringBuffer sb=new StringBuffer();
		BufferedReader br=request.getReader();
		String line=null;
		while((line=br.readLine())!=null){
			sb.append(line);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		return "SUCCESS";
	}
}

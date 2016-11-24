package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Feedback;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.FeedbackService;
import com.sharebo.service.FileService;
import com.sharebo.util.security.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 */
@Controller
@RequestMapping("/feed")
public class FeedbackController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FeedbackService feedService;
	@Autowired
	private FileService fileService;
	/**
	 * 添加用户反馈内容
	 * @param feedbackText
	 * @param feedbackImage
	 * @param userid
	 * feed/getFeedback.do?feedbackText=1&userid=889390526942412800
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getFeedback",method=RequestMethod.POST)
	public ResultDto getaddFeedback(String feedbackText,String feedbackImage,String userid){
		try {
			if(feedbackText==null){
				return new ResultDto(2002,"请求参数为空！");	
			}else if(feedbackImage==null || feedbackImage.equals("")){
				Feedback feed=new Feedback();
				feed.setFeedbackText(feedbackText);
				feed.setFeedbackImage(feedbackImage);
				feed.setUserid(userid);
				feed.setFeedbackTime(new Date());
				int i=feedService.addFeedback(feed);
				if(i>0){
					return new ResultDto(200,"反馈成功!");
				}
			}else{
				byte[] by = Base64.decode(feedbackImage);
				ByteArrayInputStream bais = new ByteArrayInputStream(by);
				String headimg = this.fileService.uploadImage(bais);
				Feedback feed=new Feedback();
				feed.setFeedbackText(feedbackText);
				feed.setFeedbackImage(headimg);
				feed.setUserid(userid);
				feed.setFeedbackTime(new Date());
				int i=feedService.addFeedback(feed);
				if(i>0){
					return new ResultDto(200,"反馈成功！");
				}
			}
		} catch (Exception e) {
			log.error("添加用户反馈内容参数异常！");
			System.out.println("添加用户反馈内容参数异常！");
		}
		return new ResultDto(2001,"反馈异常！");
	}
}

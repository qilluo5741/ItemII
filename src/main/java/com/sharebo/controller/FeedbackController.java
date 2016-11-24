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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
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
	 * ����û���������
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
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}else if(feedbackImage==null || feedbackImage.equals("")){
				Feedback feed=new Feedback();
				feed.setFeedbackText(feedbackText);
				feed.setFeedbackImage(feedbackImage);
				feed.setUserid(userid);
				feed.setFeedbackTime(new Date());
				int i=feedService.addFeedback(feed);
				if(i>0){
					return new ResultDto(200,"�����ɹ�!");
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
					return new ResultDto(200,"�����ɹ���");
				}
			}
		} catch (Exception e) {
			log.error("����û��������ݲ����쳣��");
			System.out.println("����û��������ݲ����쳣��");
		}
		return new ResultDto(2001,"�����쳣��");
	}
}

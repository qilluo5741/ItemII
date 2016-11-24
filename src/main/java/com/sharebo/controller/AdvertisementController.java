package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;
import com.sharebo.entity.dto.AdvertisDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.AdvertisService;
import com.sharebo.service.FileService;
import com.sharebo.service.IconversionService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * ����ϴ�
 * @author Administrator
 */
@Controller
@RequestMapping("/advert")
public class AdvertisementController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FileService fileService;
	@Autowired
	private IconversionService icoservice;
	@Autowired
	private AdvertisService service;
	/**
	 * ���ҳ��ѯ
	 * @return
	 * advert/advertisement.do?opinion=9e304d4e8df1b74cfa009913198428ab
	 */
	@ResponseBody
	@RequestMapping(value="advertisement",method=RequestMethod.POST)
	public ResultDto GetAdvertisementAll(String opinion){
		try {
			List<AdvertisDto> adver=service.getloadAdvertis(opinion);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("adver",adver);
			map.put("version",icoservice.getloadAllconversion().getPno()); 
			return new ResultDto(200,"�ɹ�",map);
		} catch (Exception e) {
			log.error("�����Ϣ�����쳣��");
			System.out.println("�����Ϣ�����쳣��");
		}
		return new ResultDto(2001,"�����쳣��");
	}
	/**
	 * ����ϴ�
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * advert/upload.do
	 */
	@ResponseBody
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public ResultDto fileUpload() throws IOException, ParserConfigurationException, SAXException{
		try {
			File fileisE = new File("D:\\Android\\7.png");
			FileInputStream inputFile = new FileInputStream(fileisE);
			byte[] buffer = new byte[(int)fileisE.length()];
			inputFile.read(buffer);
			inputFile.close();
			String file = new Base64().encodeToString(buffer);
			byte[] oc = new Base64().decode(file);
			ByteArrayInputStream org = new ByteArrayInputStream(oc);
			//д�뵽ָ����Ŀ¼
			String newFileName =this.fileService.uploadImage(org);
			System.out.println(newFileName);
			return new ResultDto(200,"�ɹ�",newFileName);
		} catch (Exception e) {
			log.error("�����쳣��");
			System.out.println("�����쳣��");
		}
		return new ResultDto(200,"�����쳣");
	}
}

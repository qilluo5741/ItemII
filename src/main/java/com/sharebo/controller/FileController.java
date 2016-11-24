package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.FileService;
/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/file")
public class FileController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FileService fileService;
	/**
	 * 图片上传
	 * @return
	 * file/getfile.do
	 */
	@ResponseBody
	@RequestMapping(value="getfile",method=RequestMethod.POST)
	public ResultDto getfile(){
		try {
			File fileisE = new File("D:\\Android\\4.png");
			FileInputStream inputFile = new FileInputStream(fileisE);
			byte[] buffer = new byte[(int)fileisE.length()];
			inputFile.read(buffer);
			inputFile.close();
			String file = new Base64().encodeToString(buffer);
			System.out.println(file);
			byte[] oc = new Base64().decode(file);
			System.out.println(oc);
			ByteArrayInputStream bais = new ByteArrayInputStream(oc);
			System.out.println(bais);
			String headimg = this.fileService.uploadImage(bais);
			System.out.println(headimg);
			return new ResultDto(200,"成功",headimg);
		} catch (IOException e) {
			log.error("参数异常！");
			System.out.println("参数异常！");
		}
		return new ResultDto(2001,"参数异常！");
	}
}

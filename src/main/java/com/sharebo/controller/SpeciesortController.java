package com.sharebo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sharebo.entity.Patterntype;
import com.sharebo.entity.Speciesort;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.entity.dto.SpDto;
import com.sharebo.entity.dto.SpeciesortDto;
import com.sharebo.service.IconversionService;
import com.sharebo.service.SpeciesortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * ��ѯ������
 * @author Administrator
 */
@Controller
@RequestMapping("/specie")
public class SpeciesortController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SpeciesortService sortService;
	@Autowired
	private IconversionService icoservice;
	/**
	 * ��ѯ������
	 * @return
	 * /specie/sort.do
	 */
	@ResponseBody
	@RequestMapping(value="sort",method=RequestMethod.POST)
	public  ResultDto SpeciesortServiceloadSpeciesortAll(){
		try {
			List<Speciesort> sort=sortService.loadSpeciesortAll();
			if(sort!=null){
				return new ResultDto(200,"�ɹ�",sort);
			}
		} catch (Exception e) {
			log.error("��ѯ�����Ͳ����쳣��");
			System.out.println("��ѯ�����Ͳ����쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
	/**
	 * ��ѯ���ͺ�
	 * @param specieid
	 * @return
	 * /specie/pattern.do?specieid=96736370927599616
	 */
	@ResponseBody
	@RequestMapping(value="pattern",method=RequestMethod.POST)
	public ResultDto loadAllPatterntype(String specieid){
		try {
			if(specieid==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			List<Patterntype> Pattern=sortService.loadAllPatterntype(specieid);
			if(Pattern!=null){
				return new ResultDto(200,"�ɹ�",Pattern);
			}
		} catch (Exception e) {
			log.error(" ��ѯ���������쳣��");
			System.out.println(" ��ѯ��������쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
	/**
	 * ��ѯ���г���Ʒ������Ϣ
	 * specie/sortpattern.do
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="sortpattern",method=RequestMethod.POST)
	public ResultDto sortpattern(){
		try {
			List<SpDto> sps=new ArrayList<SpDto>(); 
			String ch=null;
			List<SpeciesortDto> list=sortService.loadAllPatterntype2();
			SpDto s=new SpDto();
			for (SpeciesortDto speciesortDto : list) {
				if(ch==null){
					ch=speciesortDto.getSpecietype();
				}
				if(ch.equals(speciesortDto.getSpecietype())){
					if(s.getSpds()==null){
						s.setSpds(new ArrayList<SpeciesortDto>());
					}
					s.getSpds().add(speciesortDto);
				}else{
					s.setCh(ch);
					sps.add(s);
					s=new SpDto(); 
					s.setSpds(new ArrayList<SpeciesortDto>());
					s.getSpds().add(speciesortDto);
					ch=speciesortDto.getSpecietype();
				}
			}
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("sps",sps);
			map.put("version",icoservice.getloadAllconversion().getCarNo()); 
			return new ResultDto(200,"�ɹ�",map);
		} catch (Exception e) {
			log.error("���ز����쳣��");
			System.out.println("���ز����쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
	/**
	 * ��ѯ���г���Ʒ������Ϣ
	 * specie/spattern.do
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="spattern",method=RequestMethod.POST)
	public ResultDto spattern() {
		try {
			// ���صĽ����
			List<Map<String, List<SpeciesortDto>>> res = new ArrayList<Map<String, List<SpeciesortDto>>>();
			// ��ѯ�����Ľ����
			List<SpeciesortDto> list = sortService.loadAllPatterntype2();
			String ch=null;
			Map<String, List<SpeciesortDto>> map=new HashMap<String, List<SpeciesortDto>>();
			List<SpeciesortDto> li=new ArrayList<SpeciesortDto>();
			for (SpeciesortDto speciesortDto : list) {
				if (ch == null) {
					ch = speciesortDto.getSpecietype();
				}
				if (ch.equals(speciesortDto.getSpecietype())) {
					li.add(speciesortDto);
				} else {
					map.put(ch, li);
					res.add(map);
					li=new ArrayList<SpeciesortDto>();
					li.add(speciesortDto);
					map=new HashMap<String, List<SpeciesortDto>>();
					ch = speciesortDto.getSpecietype();
				}
			}
			map.put(ch, li);
			res.add(map);
			Map<String,Object> resmap=new HashMap<String, Object>();
			resmap.put("version",icoservice.getloadAllconversion().getCarNo());
			resmap.put("res",res);
			return new ResultDto(200,"�ɹ�",resmap);
		} catch (Exception e) {
			log.error("���ز����쳣��");
			System.out.println("���ز����쳣��");
		}
		return new ResultDto(2003,"�������ݣ�");
	}
}

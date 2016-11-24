package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.entity.Hous;
import com.sharebo.entity.Monthly;
import com.sharebo.entity.Pager;
import com.sharebo.entity.dto.MonthDto;
import com.sharebo.entity.dto.MonthlyDto;
import com.sharebo.entity.dto.ParkMonth;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.FileService;
import com.sharebo.service.MonthlyService;
import com.sharebo.util.security.Base64;

/**
 * 作者：weimeilayer@163.com
 * 时间：2016-10-19
 * @author Administrator
 */
@Controller
@RequestMapping("/monthly")
public class MonthlyController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MonthlyService service;
	@Autowired
	private FileService fileService;

	/**
	 * 模糊查询宜停还是包月
	 * 
	 * @param parkname
	 * @return monthly/getvague.do?parkname=
	 */
	@ResponseBody
	@RequestMapping(value = "getvague", method = RequestMethod.POST)
	public ResultDto getselectByparknameorparkAddress(String parkname) {
		if (parkname == null) {
			return new ResultDto(2002, "请求参数为空！");
		}
		try {
			List<MonthlyDto> list = service.selectByparknameorparkAddress(parkname);
			if (list != null) {
				return new ResultDto(200, "成功", list);
			}
		} catch (Exception e) {
			log.error("模糊查询宜停还是包月异常！");
			System.out.println("模糊查询宜停还是包月异常！");
		}
		return new ResultDto(2003, "暂无数据！");
	}

	/**
	 * 添加包月信息
	 * monthly/addmonthly.do?parkname=1&parkAddress=1&entryaddress=1&parkprice=1
	 * &source=1&parktype=1&parktips=1&business=1&contact=1&informa=1&parkimage=
	 * 1&primages=1
	 * 
	 * @param parkname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addmonthly", method = RequestMethod.POST)
	public ResultDto getaddMonthlyation(Monthly monthly, String primages) {
		if (monthly.getParkname() == null || monthly.getParkAddress() == null || monthly.getEntryaddress() == null
				|| monthly.getParkprice() == null || monthly.getSource() == null || monthly.getParktype() == null
				|| monthly.getParktips() == null || monthly.getBusiness() == null || monthly.getContact() == null
				|| monthly.getInforma() == null || monthly.getParkimage() == null || primages == null) {
			return new ResultDto(2002, "请求参数为空！");
		}
		if (service.valMonthlyIsExixtsByparkname(monthly.getParkname()) > 0) {
			return new ResultDto(2003, "该停车场已经存在！");
		}
		try {
			if (monthly.getParkimage().equals("1") && primages.equals("1")) {
				monthly.setParkimage("1" + "," + "1");
			} else if (!monthly.getParkimage().equals("1") && primages.equals("1")) {
				byte[] by = Base64.decode(monthly.getParkimage());
				ByteArrayInputStream bais = new ByteArrayInputStream(by);
				String headimg = this.fileService.uploadImage(bais);
				monthly.setParkimage(headimg + "," + "1");
			} else if (!primages.equals("1") && monthly.getParkimage().equals("1")) {
				byte[] by1 = Base64.decode(primages);
				ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
				String headimgs = this.fileService.uploadImage(bais1);
				monthly.setParkimage("1" + "," + headimgs);
			} else {
				byte[] by = Base64.decode(monthly.getParkimage());
				ByteArrayInputStream bais = new ByteArrayInputStream(by);
				String headimg = this.fileService.uploadImage(bais);
				byte[] by1 = Base64.decode(primages);
				ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
				String headimgs = this.fileService.uploadImage(bais1);
				monthly.setParkimage(headimg + "," + headimgs);
			}
			if (service.addMonthlyation(monthly) > 0) {
				return new ResultDto(200, "添加包月成功");
			}
		} catch (Exception e) {
			log.error("添加包月异常！");
			System.out.println("添加包月异常！");
		}
		return new ResultDto(2001, "添加包月失败！");
	}

	/**
	 * 添加宜停信息
	 * monthly/addShouldMonthlyation.do?parkname=1&parkAddress=1&entryaddress=1&
	 * parkprice=1&source=1&parktype=1&parktips=1&business=1&parkimage=1&
	 * primages=1
	 * 
	 * @param parkname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addShouldMonthlyation", method = RequestMethod.POST)
	public ResultDto addShouldMonthlyation(Monthly monthly, String primages) {
		if (monthly.getParkname() == null || monthly.getParkAddress() == null || monthly.getEntryaddress() == null
				|| monthly.getParkprice() == null || monthly.getSource() == null || monthly.getParktype() == null
				|| monthly.getParktips() == null || monthly.getBusiness() == null || monthly.getParkimage() == null
				|| primages == null) {
			return new ResultDto(2002, "请求参数为空！");
		}
		ParkMonth parkmonth = new ParkMonth();
		if (service.valMonthlyIsExixtsByparkname(monthly.getParkname()) > 0) {
			return new ResultDto(2003, "该停车场已经存在！");
		}
		try {
			if (monthly.getParkimage().equals("1") && primages.equals("1")) {
				monthly.setParkimage("1" + "," + "1");
				parkmonth.setParkpicture("1" + "," + "1");
			} else if (!monthly.getParkimage().equals("1") && primages.equals("1")) {
				byte[] by = Base64.decode(monthly.getParkimage());
				ByteArrayInputStream bais = new ByteArrayInputStream(by);
				String headimg = this.fileService.uploadImage(bais);
				monthly.setParkimage(headimg + "," + "1");
				parkmonth.setParkpicture(headimg + "," + "1");
			} else if (!primages.equals("1") && monthly.getParkimage().equals("1")) {
				byte[] by1 = Base64.decode(primages);
				ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
				String headimgs = this.fileService.uploadImage(bais1);
				monthly.setParkimage("1" + "," + headimgs);
				parkmonth.setParkpicture("1" + "," + headimgs);
			} else {
				byte[] by = Base64.decode(monthly.getParkimage());
				ByteArrayInputStream bais = new ByteArrayInputStream(by);
				String headimg = this.fileService.uploadImage(bais);
				byte[] by1 = Base64.decode(primages);
				ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
				String headimgs = this.fileService.uploadImage(bais1);
				monthly.setParkimage(headimg + "," + headimgs);
				parkmonth.setParkpicture(headimg + "," + headimgs);
			}
			if (service.addShouldMonthlyation(monthly) > 0) {
				parkmonth.setParkName(monthly.getParkname());
				parkmonth.setParkAddress(monthly.getParkAddress());
				parkmonth.setEntryAddress(monthly.getParkAddress());
				parkmonth.setParkbusinessType(monthly.getSource());
				parkmonth.setParkType(monthly.getParktype());
				parkmonth.setFeeModel(monthly.getParkprice() + monthly.getParktips());
				parkmonth.setShouldstop(monthly.getBusiness());
				
				//根据收费方式与价格，存PARK表内的parkPrice与chargeType
				if (!monthly.getParkprice().contains("月")) {
					// 有小数，用DOUBLE
					String priceExist = monthly.getParkprice().substring(0, monthly.getParkprice().indexOf("/")).replace("元", "");
					double price = 0;
					if(priceExist.length() != 0){
						price = Double.parseDouble(priceExist);
					}
					// 加对小数点后的处理，因为可能存在4.5元的情况，如果是0的话，就直接去除小数点。
					double priceDecimal  = price % 1;
					// 存Price在价格上
					if (priceDecimal == 0) {
						int a = (int) price / 1;
						parkmonth.setParkPrice(String.valueOf(a));
					} else {
						parkmonth.setParkPrice(String.valueOf(price));
					}
					// 根据类别存那个
					if (monthly.getParkprice().contains("小时")) {
						// 类型存1
						parkmonth.setChargeType("1");
					} else if (monthly.getParkprice().contains("次")) {
						// 类型存0
						parkmonth.setChargeType("0");
					}
				}
				try {
					service.addParkMonthation(parkmonth);
					Hous hous = new Hous();
					hous.setHousName(monthly.getParkname());
					hous.setHousAddress(monthly.getParkAddress());
					service.addHous(hous);
				} catch (Exception e) {
					System.out.println("添加宜停同步失败");
				}
				return new ResultDto(200, "添加宜停成功");
			}
		} catch (Exception e) {
			log.error("添加宜停异常！");
			System.out.println("添加宜停异常！");
		}
		return new ResultDto(2001, "添加宜停失败！该停车场已经存在！");
	}

	/**
	 * 查询 monthly/getMonthlybymonthId.do?monthId=889435252953449083
	 * 
	 * @param monthId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getMonthlybymonthId",method = RequestMethod.POST)
	public ResultDto getMonthlybymonthId(String monthId) {
		if (monthId == null) {
			return new ResultDto(2002, "请求参数为空！");
		}
		try {
			Monthly monthly = service.getMonthlybymonthId(monthId);
			monthly.setInforma("未知");
			monthly.setContact("未知");
			if (monthly != null) {
				return new ResultDto(200, "成功", monthly);
			}
		} catch (Exception e) {
			log.error("查询宜停包月异常！");
			System.out.println("查询宜停包月异常！");
		}
		return new ResultDto(2001, "暂无数据！");
	}

	/**
	 * 修改包月信息
	 * monthly/updateMonthlyation.do?parkname=2&parkAddress=2&entryaddress=3&
	 * parkprice=2&source=2&parktype=1&parktips=1&business=1&contact=1&informa=1
	 * &parkimage=1&monthId=889435252953449083
	 * 
	 * @param parkname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateMonthlyation", method = RequestMethod.POST)
	public ResultDto updateMonthlyation(Monthly monthly, String primages) {
		if (monthly.getMonthId() == null || monthly.getParkname() == null || monthly.getParkAddress() == null
				|| monthly.getEntryaddress() == null || monthly.getParkprice() == null || monthly.getSource() == null
				|| monthly.getParktype() == null || monthly.getParktips() == null || monthly.getBusiness() == null
				|| monthly.getContact() == null || monthly.getInforma() == null || primages == null) {
			return new ResultDto(2002, "请求参数为空！");
		}
		try {
			if (monthly.getParkimage().equals("1") && primages.equals("1")) {
				monthly.setParkimage("1" + "," + "1");
			} else if (!monthly.getParkimage().equals("1") && primages.equals("1")) {
				byte[] by = Base64.decode(monthly.getParkimage());
				ByteArrayInputStream bais = new ByteArrayInputStream(by);
				String headimg = this.fileService.uploadImage(bais);
				monthly.setParkimage(headimg + "," + "1");
			} else if (!primages.equals("1") && monthly.getParkimage().equals("1")) {
				byte[] by1 = Base64.decode(primages);
				ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
				String headimgs = this.fileService.uploadImage(bais1);
				monthly.setParkimage("1" + "," + headimgs);
			} else {
				byte[] by = Base64.decode(monthly.getParkimage());
				ByteArrayInputStream bais = new ByteArrayInputStream(by);
				String headimg = this.fileService.uploadImage(bais);
				byte[] by1 = Base64.decode(primages);
				ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
				String headimgs = this.fileService.uploadImage(bais1);
				monthly.setParkimage(headimg + "," + headimgs);
			}
			if (service.updateMonthlyation(monthly) >= 0) {
				return new ResultDto(200, "修改包月成功");
			}
		} catch (Exception e) {
			log.error("修改包月异常！");
			System.out.println("修改包月异常！");
		}
		return new ResultDto(2001, "修改包月失败！");
	}

	/**
	 * 修改宜停信息
	 * monthly/updateShouldMonthlyation.do?parkname=2&parkAddress=2&entryaddress
	 * =3&parkprice=2&source=2&parktype=1&parktips=1&business=1&parkimage=1&
	 * monthId=889435252953449083
	 * 
	 * @param parkname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateShouldMonthlyation", method = RequestMethod.POST)
	public ResultDto updateShouldMonthlyation(Monthly monthly, String primages) {
		if (monthly.getMonthId() == null || monthly.getParkname() == null || monthly.getParkAddress() == null
				|| monthly.getEntryaddress() == null || monthly.getParkprice() == null || monthly.getSource() == null
				|| monthly.getParktype() == null || monthly.getParktips() == null || monthly.getBusiness() == null
				|| monthly.getParkimage() == null || primages == null) {
			return new ResultDto(2002, "请求参数为空！");
		}

		if (monthly.getParkimage().equals("1") && primages.equals("1")) {
			monthly.setParkimage("1" + "," + "1");
		} else if (!monthly.getParkimage().equals("1") && primages.equals("1")) {
			byte[] by = Base64.decode(monthly.getParkimage());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			monthly.setParkimage(headimg + "," + "1");
		} else if (!primages.equals("1") && monthly.getParkimage().equals("1")) {
			byte[] by1 = Base64.decode(primages);
			ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
			String headimgs = this.fileService.uploadImage(bais1);
			monthly.setParkimage("1" + "," + headimgs);
		} else {
			byte[] by = Base64.decode(monthly.getParkimage());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			byte[] by1 = Base64.decode(primages);
			ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
			String headimgs = this.fileService.uploadImage(bais1);
			monthly.setParkimage(headimg + "," + headimgs);
		}
		try {
			if (service.updateShouldMonthlyation(monthly) >= 0) {
				return new ResultDto(200, "修改宜停成功");
			}
		} catch (Exception e) {
			log.error("修改宜停异常！");
			System.out.println("修改宜停异常！");
		}
		return new ResultDto(2001, "修改宜停失败！");
	}

	/**
	 * 分页查询包月
	 * 
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return monthly/getloadAllMonthtoer.do?pageIndex=1&pageSize=10
	 */
	@ResponseBody
	@RequestMapping(value = "getloadAllMonthtoer", method = RequestMethod.POST)
	public ResultDto getloadAllMonthDtoer(Integer pageIndex, Integer pageSize) {
		try {
			Pager<MonthDto> pager = new Pager<MonthDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map = new HashMap<String, Object>();
			// 设置开始
			int pageBegin = (pageIndex - 1) * pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			pager.setList(service.getloadAllMonthDtoer(map));
			// 查询总数
			pager.setTotalNumber(service.getMonthDtoerCount());
			pager.setTotalPages();// 设置总页数
			return new ResultDto(200, "成功", pager);
		} catch (Exception e) {
			log.error("个人收藏参数异常！");
			System.out.println("个人收藏参数异常！");
		}
		return new ResultDto(2001, "参数异常！");
	}

	/**
	 * 详情查询包月
	 * 
	 * @param monthId
	 * @return monthly/getMonthlydetailsbymonthId.do?monthId=889435252953448988
	 */
	@ResponseBody
	@RequestMapping(value = "getMonthlydetailsbymonthId", method = RequestMethod.POST)
	public ResultDto getMonthlydetailsbymonthId(String monthId) {
		if (monthId == null) {
			return new ResultDto(2001, "请求参数不能为空！");
		}
		// 判断是否为空
		try {
			Monthly monthly=service.getMonthlybymonthId(monthId);
			if (monthly != null) {
				monthly.setInforma("未知");
				monthly.setContact("未知");
				return new ResultDto(200, "成功！",monthly);
			}
		} catch (Exception e) {
			log.error("详情查询包月异常！");
			System.out.println("详情查询包月异常！");
		}
		return new ResultDto(2001, "详情查询包月失败！");
	}

	/**
	 * 已经失效！
	 * @ResponseBody
	 * @return monthly/getMonthly.do
	 */
	@RequestMapping(value = "getMonthly", method = RequestMethod.POST)
	public ResultDto getMonthly() {
		List<Monthly> monthlys = service.getMonthly();
		ParkMonth parkmonth = new ParkMonth();
		for (Monthly monthly : monthlys) {
			parkmonth.setParkName(monthly.getParkname());
			parkmonth.setParkAddress(monthly.getParkAddress());
			parkmonth.setEntryAddress(monthly.getParkAddress());
			parkmonth.setParkbusinessType(monthly.getSource());
			parkmonth.setParkType(monthly.getParktype());
			parkmonth.setFeeModel(monthly.getParktips());
			parkmonth.setShouldstop(monthly.getBusiness());
			parkmonth.setParkpicture(monthly.getParkimage());
			service.addParkMonthation(parkmonth);
			Hous hous = new Hous();
			hous.setHousName(monthly.getParkname());
			hous.setHousAddress(monthly.getParkAddress());
			service.addHous(hous);
		}
		return new ResultDto(200, "成功！");
	}
}

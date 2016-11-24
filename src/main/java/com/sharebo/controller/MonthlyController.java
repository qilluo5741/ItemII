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
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-19
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
	 * ģ����ѯ��ͣ���ǰ���
	 * 
	 * @param parkname
	 * @return monthly/getvague.do?parkname=
	 */
	@ResponseBody
	@RequestMapping(value = "getvague", method = RequestMethod.POST)
	public ResultDto getselectByparknameorparkAddress(String parkname) {
		if (parkname == null) {
			return new ResultDto(2002, "�������Ϊ�գ�");
		}
		try {
			List<MonthlyDto> list = service.selectByparknameorparkAddress(parkname);
			if (list != null) {
				return new ResultDto(200, "�ɹ�", list);
			}
		} catch (Exception e) {
			log.error("ģ����ѯ��ͣ���ǰ����쳣��");
			System.out.println("ģ����ѯ��ͣ���ǰ����쳣��");
		}
		return new ResultDto(2003, "�������ݣ�");
	}

	/**
	 * ��Ӱ�����Ϣ
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
			return new ResultDto(2002, "�������Ϊ�գ�");
		}
		if (service.valMonthlyIsExixtsByparkname(monthly.getParkname()) > 0) {
			return new ResultDto(2003, "��ͣ�����Ѿ����ڣ�");
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
				return new ResultDto(200, "��Ӱ��³ɹ�");
			}
		} catch (Exception e) {
			log.error("��Ӱ����쳣��");
			System.out.println("��Ӱ����쳣��");
		}
		return new ResultDto(2001, "��Ӱ���ʧ�ܣ�");
	}

	/**
	 * �����ͣ��Ϣ
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
			return new ResultDto(2002, "�������Ϊ�գ�");
		}
		ParkMonth parkmonth = new ParkMonth();
		if (service.valMonthlyIsExixtsByparkname(monthly.getParkname()) > 0) {
			return new ResultDto(2003, "��ͣ�����Ѿ����ڣ�");
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
				
				//�����շѷ�ʽ��۸񣬴�PARK���ڵ�parkPrice��chargeType
				if (!monthly.getParkprice().contains("��")) {
					// ��С������DOUBLE
					String priceExist = monthly.getParkprice().substring(0, monthly.getParkprice().indexOf("/")).replace("Ԫ", "");
					double price = 0;
					if(priceExist.length() != 0){
						price = Double.parseDouble(priceExist);
					}
					// �Ӷ�С�����Ĵ�����Ϊ���ܴ���4.5Ԫ������������0�Ļ�����ֱ��ȥ��С���㡣
					double priceDecimal  = price % 1;
					// ��Price�ڼ۸���
					if (priceDecimal == 0) {
						int a = (int) price / 1;
						parkmonth.setParkPrice(String.valueOf(a));
					} else {
						parkmonth.setParkPrice(String.valueOf(price));
					}
					// ���������Ǹ�
					if (monthly.getParkprice().contains("Сʱ")) {
						// ���ʹ�1
						parkmonth.setChargeType("1");
					} else if (monthly.getParkprice().contains("��")) {
						// ���ʹ�0
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
					System.out.println("�����ͣͬ��ʧ��");
				}
				return new ResultDto(200, "�����ͣ�ɹ�");
			}
		} catch (Exception e) {
			log.error("�����ͣ�쳣��");
			System.out.println("�����ͣ�쳣��");
		}
		return new ResultDto(2001, "�����ͣʧ�ܣ���ͣ�����Ѿ����ڣ�");
	}

	/**
	 * ��ѯ monthly/getMonthlybymonthId.do?monthId=889435252953449083
	 * 
	 * @param monthId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getMonthlybymonthId",method = RequestMethod.POST)
	public ResultDto getMonthlybymonthId(String monthId) {
		if (monthId == null) {
			return new ResultDto(2002, "�������Ϊ�գ�");
		}
		try {
			Monthly monthly = service.getMonthlybymonthId(monthId);
			monthly.setInforma("δ֪");
			monthly.setContact("δ֪");
			if (monthly != null) {
				return new ResultDto(200, "�ɹ�", monthly);
			}
		} catch (Exception e) {
			log.error("��ѯ��ͣ�����쳣��");
			System.out.println("��ѯ��ͣ�����쳣��");
		}
		return new ResultDto(2001, "�������ݣ�");
	}

	/**
	 * �޸İ�����Ϣ
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
			return new ResultDto(2002, "�������Ϊ�գ�");
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
				return new ResultDto(200, "�޸İ��³ɹ�");
			}
		} catch (Exception e) {
			log.error("�޸İ����쳣��");
			System.out.println("�޸İ����쳣��");
		}
		return new ResultDto(2001, "�޸İ���ʧ�ܣ�");
	}

	/**
	 * �޸���ͣ��Ϣ
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
			return new ResultDto(2002, "�������Ϊ�գ�");
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
				return new ResultDto(200, "�޸���ͣ�ɹ�");
			}
		} catch (Exception e) {
			log.error("�޸���ͣ�쳣��");
			System.out.println("�޸���ͣ�쳣��");
		}
		return new ResultDto(2001, "�޸���ͣʧ�ܣ�");
	}

	/**
	 * ��ҳ��ѯ����
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
			// ���ÿ�ʼ
			int pageBegin = (pageIndex - 1) * pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			pager.setList(service.getloadAllMonthDtoer(map));
			// ��ѯ����
			pager.setTotalNumber(service.getMonthDtoerCount());
			pager.setTotalPages();// ������ҳ��
			return new ResultDto(200, "�ɹ�", pager);
		} catch (Exception e) {
			log.error("�����ղز����쳣��");
			System.out.println("�����ղز����쳣��");
		}
		return new ResultDto(2001, "�����쳣��");
	}

	/**
	 * �����ѯ����
	 * 
	 * @param monthId
	 * @return monthly/getMonthlydetailsbymonthId.do?monthId=889435252953448988
	 */
	@ResponseBody
	@RequestMapping(value = "getMonthlydetailsbymonthId", method = RequestMethod.POST)
	public ResultDto getMonthlydetailsbymonthId(String monthId) {
		if (monthId == null) {
			return new ResultDto(2001, "�����������Ϊ�գ�");
		}
		// �ж��Ƿ�Ϊ��
		try {
			Monthly monthly=service.getMonthlybymonthId(monthId);
			if (monthly != null) {
				monthly.setInforma("δ֪");
				monthly.setContact("δ֪");
				return new ResultDto(200, "�ɹ���",monthly);
			}
		} catch (Exception e) {
			log.error("�����ѯ�����쳣��");
			System.out.println("�����ѯ�����쳣��");
		}
		return new ResultDto(2001, "�����ѯ����ʧ�ܣ�");
	}

	/**
	 * �Ѿ�ʧЧ��
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
		return new ResultDto(200, "�ɹ���");
	}
}

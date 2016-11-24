package com.sharebo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.config.GaodeConfig;
import com.sharebo.controller.ParkController;
import com.sharebo.entity.Dayrules;
import com.sharebo.entity.LongrentInfo;
import com.sharebo.entity.MyCarPort;
import com.sharebo.entity.OrderTime;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.HousDto2;
import com.sharebo.entity.dto.LongCarDetailsDto;
import com.sharebo.entity.dto.MyCarportDto;
import com.sharebo.entity.dto.ParkDetailsDto;
import com.sharebo.entity.dto.ParkDto;
import com.sharebo.entity.dto.ParkInfoDto;
import com.sharebo.entity.dto.ParkingDto;
import com.sharebo.mapper.ParkMapper;
import com.sharebo.service.ParkService;
import com.sharebo.util.GaodUtil;
import com.sharebo.util.HttpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class ParkServiceImpl implements ParkService {
	private static final Logger log = LoggerFactory
			.getLogger(ParkController.class);
	@Autowired
	private ParkMapper mapper;

	/**
	 * ��ӳ�λ
	 */
	@Override
	public boolean insertParkByLongCarRental(LongrentInfo longrent)throws Exception {
		// ��ӳ�λ��Ϣ
		longrent.getCarport().setCarportId(UUID.randomUUID().toString().replace("-", ""));
		longrent.getCarport().setRentoutType(0);
		longrent.getCarport().setFeeType(0);//��������
		if (mapper.addMyCartInfo(longrent.getCarport()) <= 0) {
			return false;
		}
		// ��ӳ�����Ϣ
		if (mapper.addLongrentInfo(longrent) <= 0) {// ���ʧ�ܣ�
			throw new Exception("��ӳ�������ʧ�ܣ�");
		}
		//˵�������� carportId �޸ĸߵµ�ͼ��״̬
		//ͨ��carportId  ��֤��ǰС���Ƿ���ڳ�λ
		Integer count=mapper.valHousIsExixtsCarPortByCarportId(longrent.getCarport().getCarportId());
		//��ѯ�ߵµ�ͼID
		String gaodeId=mapper.seleclGaodeIdBycarportId(longrent.getCarport().getCarportId());
		updateGaodState(mapper,gaodeId,count,longrent.getCarport().getMoney(),longrent.getCarport().getCarportId());
		return true;
	}
	//�޸ĸߵ�״̬
	public static String updateGaodState(ParkMapper mapper,String gaodeId,Integer count,Double money,String carportId){
		//��ѯ��С������ͼ۸�
		Double cpMoney=mapper.getHousMinMoneyByCarportId(carportId);
		//��ѯͣ������ͼ۸�
		Double parkMoney=mapper.getParkPriceByCarPortId(carportId);
		System.out.println("С����ͣ�"+cpMoney+"   "+parkMoney);
		//��ѯ�ߵµ�ͼID
		Map<String, Object> map =new HashMap<String, Object>();
		if(count>0){//�г�λ�գ�
			map.put("housState", 0);
		}else{
			map.put("housState", 1);
		}
		System.out.println("ʣ�೵λ����"+count);
		Double minMoney=0.0;
		if(parkMoney!=null){//���ͣ������Ϊ��
			minMoney=parkMoney;
		}
		if(cpMoney!=null){
			if(parkMoney!=null){
				minMoney=cpMoney<minMoney?cpMoney:minMoney;
			}else{
				minMoney=cpMoney;
			}
		}
		System.out.println("�޸ĵļ۸�"+minMoney);
		//��֤�Ǹ�money��С
		if(money!=null){
			if(minMoney<money){
				map.put("money",minMoney);
			}else{
				map.put("money",money);
			}
		}else{
			map.put("money",minMoney);
		}
		//�޸ĸߵµ�ͼ�Σ�״̬
		String res=GaodUtil.updateTableData(gaodeId, map);
		System.out.println(res);
		return res;
	}
	@Override
	public int valserialnumberIsExists(String serialnumber, String userid,String housId) {
		return mapper.valserialnumberIsExists(serialnumber, userid,housId);
	}

	@Override
	public String insertIssuedTemporaryParkSpaces(MyCarPort cp)
			throws Exception {
		// ��ӳ�λ��Ϣ
		cp.setCarportId(UUID.randomUUID().toString().replace("-", ""));
		cp.setRentoutType(1);
		System.out.println(cp.getUserid());
		if (mapper.addMyCartInfo(cp) <= 0) {
			return null;
		}
		// ����ʱ������
		if (mapper.addweekrules(cp.getCarportId()) <= 0) {
			throw new Exception("��ӳ�������ʧ�ܣ�");
		}
		//˵�������� carportId �޸ĸߵµ�ͼ��״̬
		//ͨ��carportId  ��֤��ǰС���Ƿ���ڳ�λ
		Integer count=mapper.valHousIsExixtsCarPortByCarportId(cp.getCarportId());
		//��ѯ�ߵµ�ͼID
		String gaodeId=mapper.seleclGaodeIdBycarportId(cp.getCarportId());
		updateGaodState(mapper,gaodeId, count,cp.getMoney(),cp.getCarportId());
		return cp.getCarportId();
	}

	@Override
	public Weekrules getWeekrulesByCarportId(String carportId) {
		return mapper.getWeekrulesByCarportId(carportId);
	}

	@Override
	public int updateWeekrulesByWeekid(Weekrules week) {
		return mapper.updateWeekrulesByWeekid(week);
	}

	@Override
	public LongCarDetailsDto getLongrentInfo(String carportId) {
		return mapper.getLongrentInfo(carportId);
	}

	@Override
	public boolean ParkByLongCarRental(LongrentInfo longrent) throws Exception {
		longrent.getCarport().setRentoutType(0);
		// ���޸ĳ��������
		if (mapper.updateLongrent(longrent) <= 0) {
			return false;
		}
		// Ȼ���޸ĳ�λ��Ϣ
		if (mapper.updateMycarPort(longrent.getCarport()) <= 0) {
			throw new Exception("�޸ĳ��⳵λ�쳣��");
		}
		updateGaodState(mapper,mapper.seleclGaodeIdBycarportId(longrent.getCarport().getCarportId()), 1, longrent.getCarport().getMoney(), longrent.getCarport().getCarportId());
		return true;
	}

	@Override
	public int updateTemporaryParkSpaces(MyCarPort cp) {
		cp.setRentoutType(1);
		if(mapper.updateMycarPort(cp)<=0){
			return 0;
		}
		updateGaodState(mapper, mapper.seleclGaodeIdBycarportId(cp.getCarportId()), 1, cp.getMoney(), cp.getCarportId());
		return 1;
	}

	@Override
	public List<String> selectParkMonthAllDayRlus(Map<String, String> map) {
		return mapper.selectParkMonthAllDayRlus(map);
	}

	@Override
	public List<String> selectParkMonthAllNotDayRlus(Map<String, String> map) {
		return mapper.selectparkMenthAllNotDayRlus(map);
	}

	@Override
	public Weekrules weekTime(String carportId) {
		return mapper.weekTime(carportId);
	}
	// ��ѯһ���ʱ��
	@Override
	public Dayrules getDayrules(Map<String, String> map) throws Exception {
		Dayrules d = mapper.getDayrules(map);
		// �������û������ʱ�䣬�Ͱ������ڹ������
		if (null == d) {
			d = new Dayrules();// ��ʼ����
			d.setCarportId(map.get("parkid"));
			d.setThisDay(map.get("day"));
			int week = DayToWeek(map.get("day"));// ͨ�����ڵõ����ڼ�
			// ͨ�����ڲ�ѯ����ʱ���
			Weekrules w = mapper.weekTime(map.get("parkid"));
			if(w==null){
				throw new Exception("��λ������");
			}
			// ��������ȡֵ
			if (week == 1) {// ������
				weekToDay(d, w.getSunday_begin(), w.getSunday_end(), 1);
			} else if (week == 2) {// ����һ
				weekToDay(d, w.getMonday_begin(), w.getMonday_end(), 1);
			} else if (week == 3) {// ���ڶ�
				weekToDay(d, w.getTuesday_begin(), w.getTuesday_end(), 1);
			} else if (week == 4) {// ������
				weekToDay(d, w.getWednesday_begin(), w.getWednesday_end(), 1);
			} else if (week == 5) {// ������
				weekToDay(d, w.getThursday_begin(), w.getThursday_end(), 1);
			} else if (week == 6) {// ������
				weekToDay(d, w.getFriday_begin(), w.getFriday_end(), 1);
			} else if (week == 7) {// ������
				weekToDay(d, w.getSaturday_begin(), w.getSaturday_end(), 1);
			}
			d.setThisDay(map.get("day"));
		}
		// ��ѯ��û��ʱ����Ƿ����� ���ڵĶ��� ����ʱ�䴦��
		List<OrderTime> ot = mapper.getOrdertime(map);
		if (null != ot) {
			// ��������ʱ�������
			for (OrderTime orderTime : ot) {
				weekToDay(d, orderTime.getBegin_time(),
						orderTime.getEnd_time(), 2);
			}
		}
		// System.out.println(d.toString());
		return d;
	}

	// ͨ�����ڹ���ת��ʱ��
	public static Dayrules weekToDay(Dayrules d, String begin, String end, int s) {
		Calendar thisdatC = new GregorianCalendar();
		System.out.println(thisdatC);
		// ��ȡǰ����λ������
		int b = Integer.parseInt(begin.substring(0, 2));// ��ʼʱ��
		int e = Integer.parseInt(end.substring(0, 2));// ����ʱ��
		if (b != e) {// �ж������Ƿ���ȣ������ζ�Ž���
			Calendar calendarCountDays = new GregorianCalendar();
			calendarCountDays.setTime(new Date());
			// �ѹ涨ʱ����Ϊ1
			for (int i = b; i < e; i++) {
				if(i<=calendarCountDays.get(Calendar.HOUR_OF_DAY)&&new SimpleDateFormat("yyyy-MM-dd").format(new Date()).equals(d.getThisDay()))
					continue;
				if (i == 0)
					d.setHours_24(s);
				if (i == 1)
					d.setHours_01(s);
				if (i == 2)
					d.setHours_02(s);
				if (i == 3)
					d.setHours_03(s);
				if (i == 4)
					d.setHours_04(s);
				if (i == 5)
					d.setHours_05(s);
				if (i == 6)
					d.setHours_06(s);
				if (i == 7)
					d.setHours_07(s);
				if (i == 8)
					d.setHours_08(s);
				if (i == 9)
					d.setHours_09(s);
				if (i == 10)
					d.setHours_10(s);
				if (i == 11)
					d.setHours_11(s);
				if (i == 12)
					d.setHours_12(s);
				if (i == 13)
					d.setHours_13(s);
				if (i == 14)
					d.setHours_14(s);
				if (i == 15)
					d.setHours_15(s);
				if (i == 16)
					d.setHours_16(s);
				if (i == 17)
					d.setHours_17(s);
				if (i == 18)
					d.setHours_18(s);
				if (i == 19)
					d.setHours_19(s);
				if (i == 20)
					d.setHours_20(s);
				if (i == 21)
					d.setHours_21(s);
				if (i == 22)
					d.setHours_22(s);
				if (i == 23)
					d.setHours_23(s);
			}
		}
		return d;
	}

	// �������ڣ��������ڼ�
	public static int DayToWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar();
		try {
			calendarCountDays.setTime(sdf.parse(day));
		} catch (ParseException e) {
			log.error("ת��ʱ��ʧ��!");
		}
		// �õ����������������ڼ�
		return calendarCountDays.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * ��ѯͣ��������
	 * @param parkId
	 * @return
	 */
	@Override
	public ParkInfoDto selectParkInfo(String parkId,String userid) {
		return mapper.selectParkInfo(parkId, userid);
	}
	@Override
	public boolean isExistxByDayrules(Dayrules d) {
		return mapper.isExistxByDayrules(d)<=0?false:true;
	}
	@Override
	public boolean addDayrules(Dayrules d) {
		return mapper.addDayrules(d);
	}
	@Override
	public boolean updateDayByHours(Dayrules d) {
		return mapper.updateDayByHours(d);
	}

	@Override
	public boolean updateCarPort(String carportId) {
		if(mapper.updateCarPort(carportId)<=0){
			return false;
		}
		//˵�������� carportId �޸ĸߵµ�ͼ��״̬
		//ͨ��carportId  ��֤��ǰС���Ƿ���ڳ�λ
		Integer count=mapper.valHousIsExixtsCarPortByCarportId(carportId);
		//��ѯ�ߵµ�ͼID
		String gaodeId=mapper.seleclGaodeIdBycarportId(carportId);
		updateGaodState(mapper,gaodeId,count,null,carportId);
		return true;
	}
	/**
	 * ��ѯͣ������Ϣ
	 * @param parkId
	 * @return
	 */
	@Override
	public ParkDto selectParkDtoInfo(String parkId) {
		return mapper.selectParkDtoInfo(parkId);
	}
	/**
	 * ����housId��ѯͣ��λ
	 * @param housId
	 * @return
	 */
	@Override
	public List<MyCarportDto> selectMyCarportInfo(String housId) {
		return mapper.selectMyCarportInfo(housId);
	}
	//��֤С���Ƿ��Ѿ�����  ������ڵõ��ߵ�ID  �޸ĸߵ�ͣ��������
			//������ ͬ���ߵ�����     ���С������
	@Override
	public boolean addParking(ParkingDto park) throws Exception {
		park.setParkId(UUID.randomUUID().toString().replace("-", ""));
		//ͨ��С�����ֲ�ѯ�ߵ�ID
		HousDto2 h=mapper.GetGaodeIdByHousName(park.getParkName());
		if(h==null){//������С��
			String housId=UUID.randomUUID().toString().replace("-","");
			//��Ӹߵ�  �õ��ߵ�Id
			Map<String,Object> pMap=new HashMap<String,Object>();
			pMap.put("housState", 1);
			pMap.put("parkState", 1);
			pMap.put("housId", housId);
			pMap.put("money", park.getParkprice());
			pMap.put("parkId",park.getParkId());
			String res=GaodUtil.addTableDateOnLoctypeByAddress(park.getParkName(),park.getEntryAddress(),pMap);
			System.out.println(res);
			 String gaodeid=null;
			if(JSONObject.fromObject(res).getString("info").equals("OK")){
				gaodeid=JSONObject.fromObject(res).getString("_id");
				 park.setGaodeId(gaodeid);
			}
			//���С��
			if(mapper.addHous(housId, park.getParkName(), park.getParkAddress(), gaodeid)<=0){
				return false;
			}
			
		}else{//����С��
		park.setGaodeId(h.getGaodeIds());
		//�޸�gaodeID��Park��Ϣ
		Map<String,Object> pMap=new HashMap<String,Object>();
		pMap.put("parkState", 1);
		pMap.put("parkId",park.getParkId());
		//��֤money
		//���ݸߵµ�ͼId��ѯ�۸�
			String url="http://yuntuapi.amap.com/datasearch/id?_id="+h.getGaodeIds()+"&key="+GaodeConfig.getAppKey()+"&tableid="+GaodeConfig.getTableid();
			try {
				double gMoney=JSONObject.fromObject(JSONArray.fromObject(JSONObject.fromObject(HttpClient.get(url)).getString("datas")).get(0)).getDouble("money");
				if(park.getParkprice()!=null){
					if(park.getParkprice()<gMoney){
						pMap.put("money", park.getParkprice());
					}
				}
			} catch (Exception e) {
				System.out.println("һ�㶼�� �۸�û�á���");
				pMap.put("money", park.getParkprice());
			}
			GaodUtil.updateTableData(h.getGaodeIds(), pMap);
		}
		
		//���ͣ����
		if(mapper.addParking(park)<=0){
			return false;
		}
		return true;
	}
	@Override
	public int updateparkidbygaodeid(String gaodeId, String parkid) {
		return mapper.updateparkidbygaodeid(gaodeId,parkid);
	}

	@Override
	public List<ParkingDto> selectSynchronizeGaodeId() {
		return mapper.selectSynchronizeGaodeId();
	}
	@Override
	public List<ParkDetailsDto> getLongRentByHousId(String housIds) {
		return mapper.getLongRentByHousId(housIds);
	}
	//����ͣ�������۸�����
	@Override
	public List<ParkDetailsDto> getLongRentInfo(Map<String, Object> map) {
		return mapper.getLongRentInfo(map);
	}

	@Override
	public int getLongRentInfoCount() {
		return mapper.getLongRentInfoCount();
	}
	@Override
	public List<MyCarportDto> getloadAllCarport(Map<String, Object> map) {
		return mapper.getloadAllCarport(map);
	}
	@Override
	public Integer getloadAllCarportCount(String housId) {
		return mapper.getloadAllCarportCount(housId);
	}
}

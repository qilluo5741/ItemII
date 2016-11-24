package com.sharebo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sharebo.entity.LeaseOrderDetailInfo;
import com.sharebo.entity.MyCarOrderTime;
import com.sharebo.entity.ParkOrderDetailsInfo;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.CallBackRefuceInfo;
import com.sharebo.entity.dto.CarportOrderInfo;
import com.sharebo.entity.dto.DetailsDto;
import com.sharebo.entity.dto.LongCarDetailsDto;
import com.sharebo.entity.dto.LongRentDto;
import com.sharebo.entity.dto.LongRentOrderDto;
import com.sharebo.entity.dto.OrderStateDto;
import com.sharebo.entity.dto.ParkOrderDetails;
import com.sharebo.entity.dto.ParkOrderFeeDto;
import com.sharebo.entity.dto.ParkOrderInfo;
import com.sharebo.entity.dto.SupplierOrder;
import com.sharebo.entity.dto.TempOrderDto;
import com.sharebo.entity.dto.TemporaryRentDto;
import com.sharebo.mapper.DetailsMapper;
import com.sharebo.mapper.OrderMapper;
import com.sharebo.mapper.ParkMapper;
import com.sharebo.mapper.PayMapper;
import com.sharebo.service.OrderService;

/**
 * ����
 * 
 * @author niewei
 *
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper mapper;
	@Autowired
	private ParkMapper pmapper;
	@Autowired
	private DetailsMapper dmapper;
	@Autowired
	private PayMapper paymapper;

	@Override
	public ParkOrderFeeDto getParkFeeByParkId(String parkId) {
		return mapper.getParkFeeByParkId(parkId);
	}

	@Override
	public boolean addParkOrder(ParkOrderDetailsInfo podi) throws Exception {
		//1. ��Ӷ���
		if(mapper.addOrderInfo(podi.getOrder())<=0){
			return false;
		}
		//2.��Ӷ�������
		if(mapper.addParkOrderDetails(podi)<=0){
			throw new Exception("��ӹ���ͣ������������ʧ�ܣ�");
		}
		return true;
	}

	@Override
	public ParkOrderDetails getParkOrderDetail(String orderId) {
		return mapper.getParkOrderDetail(orderId);
	}
	@Override
	public boolean valCarPortIsLongRentByCarportId(String carportId) {
		return mapper.valCarPortIsLongRentByCarportId(carportId)<=0?false:true;
	}
	@Override
	public int valCarPortIsRentByCarportId(String carportId) {
		return mapper.valCarPortIsRentByCarportId(carportId);
	}
	@Override
	public LongCarDetailsDto getLongrentInfo(String carportId) {
		return pmapper.getLongrentInfo(carportId);
	}
	@Override
	public boolean addLongRentOrder(LeaseOrderDetailInfo lodi) throws Exception {
		//���������
		if(mapper.addOrderInfo(lodi.getCpod().getOrder())<=0){
			return false;
		}
		//��Ӹ��˳�λ��������
		if(mapper.addCarportorDerdetails(lodi.getCpod())<=0){
			throw new Exception("��Ӹ��˳�λ���������쳣��");
		}
		//��ӳ�����Ϣ
		try {
			if(mapper.addLeaseorderdetails(lodi)<=0){
				throw new Exception("��Ӹ��˳�λ���ⶩ�������쳣��");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("��Ӹ��˳�λ���ⶩ�������쳣��");
		}
		return true;
	}
	//�޸Ķ���״̬
	@Override
	public int CallBack_updateOrderState(String orderNum) {
		return mapper.CallBack_updateOrderState(orderNum);
	}
	@Override
	public boolean valThisDayIsExists(String day, String carportId,Integer begin,Integer end) {
		//��֤Сʱ�����Ƿ����
		if(mapper.valHoursIsExists(carportId, day)>0){//����
			//��֤��  ����0����û�н���  �෴�Ѿ�����
			String sql=val_sql(carportId, day, begin, end);
			if(mapper.valDayisExists(sql)==0){
				return false; 
			}
		}else{//������
			//��֤���ڹ���
			if(mapper.valdisableWeek(DayToWeek(day), carportId)>0){//�Ѿ�����
				return false;
			}else{
				//��֤�Ƿ񳬹�Сʱ��
				//��ѯ��λ���ڹ���
				Weekrules wr=mapper.weekTime(carportId);
				if(wr==null){
					return false;
				}
				//����
				Map<String,String> map=new HashMap<String, String>();
				map=toBedinEnd(map, DayToWeek(day), wr);
				///�ж�����ʱ���Ƿ���ڽ���
				int b=Integer.valueOf(map.get("begin").substring(0,map.get("begin").indexOf(":")));//����ʱ��
				int e=Integer.valueOf(map.get("end").substring(0,map.get("end").indexOf(":")));//����ʱ��
				//�жϿ�ʼ�ͽ���ʱ���Ƿ����������ʱ����
				if(!(b<=begin&&e>=end)){
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public boolean valOrderIsExists(String day, String carPortId,
			Integer begin, Integer end) {
		/*****��֤�������Ƿ�����Ѿ�ԤԼ��ʱ��******/
		//1.��ѯ�Ƿ��е�����ڵĵ�ǰ��λ��ʱ�� ���Ҷ���״̬Ϊ�Ѿ�ȡ��
		//2.�ж�����ʱ���Ƿ��н���
		return false;
	}
	//�������ڣ���ʼʱ�䣬����ʱ�䣬ʱ����� �������
		public static Map<String,String> toBedinEnd(Map<String,String> map,int week,Weekrules w){
			String begin=null;
			String end=null;
			if(week==1){//������
				begin=w.getSunday_begin();end=w.getSunday_end();
			}else if(week==2){//����һ
				begin=w.getMonday_begin();end=w.getMonday_end();
			}else if(week==3){//���ڶ�
				begin=w.getTuesday_begin();end=w.getTuesday_end();		
			}else if(week==4){//������
				begin=w.getWednesday_begin();end=w.getWednesday_end();
			}else if(week==5){//������
				begin=w.getThursday_begin();end=w.getThursday_end();
			}else if(week==6){//������
				begin=w.getFriday_begin();end=w.getFriday_end();
			}else if(week==7){//������
				begin=w.getSaturday_begin();end=w.getSaturday_end();
			}
			map.put("begin", begin);
			map.put("end", end);
			return map;
		}
	@Override
	public DetailsDto getloadAllDetails(String carportId) {
	
		return dmapper.getloadAllDetails(carportId);
	}
	//���ݿ�ʼʱ�䣬����ʱ�� parkid����sql���   Ŀ�ģ���ѯ���Ϊ��
		public static String val_sql(String carportId,String thisDate,Integer begin,Integer end){
			StringBuffer sb=new StringBuffer();
			sb.append("carportId='"+carportId+"' and thisDay='"+thisDate+"'");
			if(begin<0||end>24){//�ж�Сʱ�Ƿ�Ϸ�
				return  null;
			}
			for(int i=begin;i<end;i++){
				//��дsql
				if(i==0){
					sb.append(" and hours_24=1");
				}
				else{
					sb.append(" and hours_"+(i<10?"0"+i:i)+"=1");
				}
			}
			return sb.toString();
		}

	// �������ڣ��������ڼ�
	public static int DayToWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar();
		try {
			calendarCountDays.setTime(sdf.parse(day));
		} catch (ParseException e) {
		}
		// �õ����������������ڼ�
		return calendarCountDays.get(Calendar.DAY_OF_WEEK);
	}
    //רλ������¼��ѯ
	@Override
	public List<CarportOrderInfo> getCarportOrderInfoByUserid(Map<String, Object> map) {
	
		return mapper.getCarportOrderInfoByUserid(map);
	}
    //רλ������¼����
	@Override
	public int getCarportOrderInfoCount(String userid) {
	
		return mapper.getCarportOrderInfoCount(userid);
	}
	@Override
	public boolean addTempOrder(MyCarOrderTime cot) throws Exception {
		//��Ӷ���
		if(mapper.addOrderInfo(cot.getCpod().getOrder())<=0){
			return false;
		}
		//��Ӷ�������
		if(mapper.addCarportorDerdetailsByTemp(cot.getCpod())<=0){
			throw new Exception("��Ӹ�����ʱ���������쳣��");
		}
		//�����ʱ
		if(mapper.addMyCarOrderTime(cot)<=0){
			throw new Exception("��Ӹ�����ʱ���������쳣��");
		}
		return true;
	}
	@Override
	public boolean valCarportTempOrderIsTemp(String carportId, String thisdate,
			Integer begin, Integer end) {
		List<MyCarOrderTime> cto=mapper.getMyCarOrderTime(carportId, thisdate);
		if(cto==null){
			return false;
		}
		for (MyCarOrderTime myCarOrderTime : cto) {
			//ȡ��ʱ���ж��Ƿ��н���
			int b=Integer.valueOf(myCarOrderTime.getBeginTime().substring(0,2));//����ʱ��
			int e=Integer.valueOf(myCarOrderTime.getEndTime().substring(0,2));//����ʱ��
			//�жϿ�ʼ�ͽ���ʱ���Ƿ����������ʱ����
			System.out.println(begin+":"+end);
			System.out.println(b+":"+e);
			System.out.println((b<=begin&&e>=end));
			if((b<=begin&&e>=end)){
				return true;
			}
		}
		return false;
	}
    //����ͣ����������¼��ѯ
	@Override
	public List<ParkOrderInfo> getParkOrderInfoByUserid(Map<String,Object> map) {
		return mapper.getParkOrderInfoByUserid(map);
	}
    //����ͣ����������¼����
	@Override
	public int getParkOrderInfoCount(String userid) {
	
		return mapper.getParkOrderInfoCount(userid);
	}
	//���ⶩ�������ѯ
	@Override
	public LongRentOrderDto getLongRentOrderDetails(String orderId) {
		return mapper.getLongRentOrderDetails(orderId);
	}
    //��ʱͣ�����������ѯ
	@Override
	public TempOrderDto getTempOrderDetail(String orderId) {
		return mapper.getTempOrderDetail(orderId);
	}
    //�û�����
	@Override
	public int updateEvaluationByOrderId(String orderId, Integer evaluation) {
	
		return mapper.updateEvaluationByOrderId(orderId, evaluation);
	}
	//ͨ��orderId��ѯ����״̬
	@Override
	public OrderStateDto selectOrderStateByOrderId(String orderNum) {
	
		return mapper.selectOrderStateByOrderId(orderNum);
	}
	//�������ܶ��� 
	@Override
	public boolean acceptOrder(String orderNum) throws Exception {
		OrderStateDto osd=mapper.selectOrderStateByOrderId(orderNum);
		if(osd==null){
			return false;//û���ҵ���ض�������Ϣ ,�������Ѿ�����
		}
		// �޸Ķ�����״̬
		if (mapper.updateOrderStateByOrderId(orderNum,1) <= 0) {
			return false;
		}
		
		//������������
		if(paymapper.updateAvailableBalance(osd.getUserid(), osd.getCountMoney())<=0){
			throw new Exception("�û��˿�ʧ�ܣ�");
		}
		//��Ӽ�¼
		if(paymapper.addtransactionrecord(osd.getUserid(), 1, osd.getCountMoney())<=0){
			throw new Exception("��Ӽ�¼ʧ�ܣ�");
		}
		return true;
	}
   //�ܾ���������
	@Override
	public boolean refuseOrder(String orderNum) throws Exception {
		CallBackRefuceInfo cbr=paymapper.getCountMoneyAndUseridByorderNum(orderNum);
		if(cbr==null){
			return false;//û���ҵ���صĶ������������Ѿ�����
		}
		// �޸Ķ�����״̬
		if (mapper.updateOrderStateByOrderId(orderNum,2) <= 0) {
			return false;
		}
		//���û�����
		if(paymapper.updateAvailableBalance(cbr.getUserid(), cbr.getCountMoney())<=0){
			throw new Exception("�û��˿�ʧ�ܣ�");
		}
		//��Ӽ�¼
		if(paymapper.addtransactionrecord(cbr.getUserid(), 3, cbr.getCountMoney())<=0){
			throw new Exception("��Ӽ�¼ʧ�ܣ�");
		}
		return true;
	}
	//�����ҵĶ��������⣩
	@Override
	public List<SupplierOrder> getLongRentInfo(Map<String, Object> map) {
		return mapper.getLongRentInfo(map);
	}
	//�����ҵĶ��������⣩����
	@Override
	public int getLongRentInfoCount(String userid) {
		return mapper.getCarportOrderInfoCount(userid);
	}
	//�����ҵĶ�������ʱ���⣩
	@Override
	public List<SupplierOrder> getTemporaryRentInfo(Map<String, Object> map) {
		return mapper.getTemporaryRentInfo(map);
	}
	//�����ҵĶ�������ʱ���⣩����
	@Override
	public int getTemporaryRentInfoCount(String userid) {
		return mapper.getTemporaryRentInfoCount(userid);
	}
    //�����ҵĶ�����ʱ��������
	@Override
	public TemporaryRentDto getTemporaryRentDetails(String orderId) {
		return mapper.getTemporaryRentDetails(orderId);
	}
    //�����ҵĶ�����������
	@Override
	public LongRentDto getLongRentDetails(String orderId) {
		return mapper.getLongRentDetails(orderId);
	}
}

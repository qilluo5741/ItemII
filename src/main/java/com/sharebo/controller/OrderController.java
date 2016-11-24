package com.sharebo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.job.jobclient.Jobclient;
import com.sharebo.config.JobConfig;
import com.sharebo.entity.CarPortOrderDetailInfo;
import com.sharebo.entity.LeaseOrderDetailInfo;
import com.sharebo.entity.MyCarOrderTime;
import com.sharebo.entity.OrderInfo;
import com.sharebo.entity.Pager;
import com.sharebo.entity.ParkOrderDetailsInfo;
import com.sharebo.entity.dto.CarportOrderInfo;
import com.sharebo.entity.dto.DetailsDto;
import com.sharebo.entity.dto.LongCarDetailsDto;
import com.sharebo.entity.dto.LongRentOrderDto;
import com.sharebo.entity.dto.OrderStateDto;
import com.sharebo.entity.dto.ParkOrderDetails;
import com.sharebo.entity.dto.ParkOrderFeeDto;
import com.sharebo.entity.dto.ParkOrderInfo;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.entity.dto.SupplierOrder;
import com.sharebo.entity.dto.TempOrderDto;
import com.sharebo.service.OrderService;
/**
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 */
@RestController
@RequestMapping("/order")
public class OrderController {
	private static final Logger log=LoggerFactory.getLogger(ParkController.class);
	@Autowired
	private OrderService service;
	// ��Ӷ���-����ͣ����
	@RequestMapping("addParkOrder")
	public ResultDto addParkOrder(String userid,String parkId,String beginTime,String endTime,String carNo,Double thankFee) {
		//��֤����
		if(userid==null||parkId==null||beginTime==null||endTime==null||carNo==null||thankFee==null){
			return new ResultDto(1034,"�������Ϸ���");
		}
		//��֤���û�����ͣ�����Ƿ����δ֧������
		ParkOrderDetailsInfo podi=new ParkOrderDetailsInfo();
		//��֤��ʼʱ�䣬����ʱ���Ƿ�Ϸ�
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date beginDate=sdf.parse(beginTime);
			Date endDate=sdf.parse(endTime);
			//�Ƚ�ʱ�俪ʼʱ�䲻�ܴ��ڽ���ʱ��
			if(beginDate.after(endDate)){
				return new ResultDto(1035,"��ʼʱ�䲻��С�ڽ���ʱ�䣡");
			}
			if(beginDate.before(new Date())){
				return new ResultDto(1035,"��ʼʱ�䲻��С�ڵ�ǰʱ�䣡");
			}
			podi.setBeginTime(beginDate);
			podi.setEndTime(endDate);
		} catch (ParseException e) {
			return new ResultDto(1035,"ʱ���ʽ����");
		}
		podi.setOrder(new OrderInfo());
		podi.setParkId(parkId);
		//��ѯԤԼ��
		ParkOrderFeeDto pofd=service.getParkFeeByParkId(parkId);
		podi.setCash(pofd.getCash());
		podi.setReservationfee(pofd.getReservationfee());
		//���ø�л��
		podi.setThankFee(thankFee);
		//���ö�������
		podi.getOrder().setOrderType(0);
		//���ó��ƺ���
		podi.getOrder().setCarNo(carNo);
		//�����û�����
		podi.getOrder().setUserid(userid);
		podi.getOrder().setCountMoney(pofd.getReservationfee()+pofd.getCash()+thankFee);
		//���ɶ�����������
		podi.setPorderId(UUID.randomUUID().toString().replace("-",""));
		//���ɶ�������
		podi.getOrder().setOrderId(UUID.randomUUID().toString().replace("-",""));
		podi.getOrder().setOrderNum(CreateOrderNum());
		//���
		try {
			service.addParkOrder(podi);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResultDto(2036,"��������ʧ�ܣ�");
		}
		Map<String, String> res=new HashMap<String, String>();
		res.put("orderId", podi.getOrder().getOrderId());
		res.put("orderNum", podi.getOrder().getOrderNum());
		return new ResultDto(200,"�����Ѿ����ɣ�",res);
	}
	//����ͣ����id��ѯԤԼ�ѣ���֤��
	@RequestMapping("rcFee")
	public ResultDto rcFee(String parkId){
		//��֤�Ƿ�û�д�ֵ
		if(parkId==null){
			return new ResultDto(1030,"��λ����Ϊ�գ�");
		}
		return new ResultDto(200,service.getParkFeeByParkId(parkId));
	}
	// ��ѯ����-����ͣ���������飩
	@RequestMapping("getParkDetails")
	public ResultDto getParkDetails(String orderId){
		//��֤����
		if(orderId==null){
			return new ResultDto(2037,"�������Ϸ���");
		}
		//��ѯ
		ParkOrderDetails pod=service.getParkOrderDetail(orderId);
		if(pod!=null){
			pod.setCountMoney(pod.getReservationFee()+pod.getThankFee()+pod.getCash());
		    return new ResultDto(200,"��ѯ�ɹ�",pod);
		}
		return new ResultDto(2038,"��ѯʧ��");
	}
	// ��Ӷ���-������˳�λ
	@RequestMapping("addLongCarPort")
	public ResultDto addLongCarPort(String userId,String carportId,String carNo){
		//��֤����
		if(userId==null||carportId==null||carNo==null){
			return new ResultDto(2040,"�������Ϸ���");
		}
		//��֤�ó�λ�Ƿ��ǳ��⳵λ
		if(!service.valCarPortIsLongRentByCarportId(carportId)){
			return new ResultDto(2041,"�����쳣���ó�λ�ǳ��⣡");
		}
		//��֤�ó�λ�Ƿ��Ѿ�������
		if(service.valCarPortIsRentByCarportId(carportId)>0){
			return new ResultDto(2042,"�ó�λ�Ѿ�����������һ��Ӵ��");
		}
		//�¶���
		LeaseOrderDetailInfo lodi=new LeaseOrderDetailInfo();
		LongCarDetailsDto lcdd=service.getLongrentInfo(carportId);
		if(lcdd==null){
			return new ResultDto(2042,"�ó�λ��������Ӵ��"); 
		}
		//��ֵ����
		lodi.setLodId(UUID.randomUUID().toString().replace("-",""));
		lodi.setBegindayTime(lcdd.getBeginDayTime());
		lodi.setEnddayTime(lcdd.getEndDayTime());
		lodi.setBeginmonthTime(lcdd.getBeginmonthTime());
		lodi.setEndmonthTime(lcdd.getEndmonthTime());
		lodi.setTimetype(lcdd.getTimetype());
		
		// ���˶�������
		CarPortOrderDetailInfo cpod=new CarPortOrderDetailInfo();
		//��ֵ
		cpod.setCpodId(UUID.randomUUID().toString().replace("-",""));
		cpod.setCarportId(carportId);
		cpod.setDetailedAddress(lcdd.getDetailedAddress());
		cpod.setRentoutType(0);
		cpod.setHousId(lcdd.getHousId());
		cpod.setHousType(lcdd.getHousType());
		cpod.setParkremark(lcdd.getParkremark());
		cpod.setSerialnumber(lcdd.getSerialnumber());
		cpod.setMoney(lcdd.getMoney());
		
		OrderInfo order=new OrderInfo();
		order.setCarNo(carNo);
		order.setOrderId(UUID.randomUUID().toString().replace("-",""));
		order.setOrderNum(CreateOrderNum());
		order.setOrderType(1);
		order.setCountMoney(lcdd.getMoney());
		order.setUserid(userId);
		cpod.setOrder(order);
		lodi.setCpod(cpod);
		//�������  //�޸Ķ���״̬-�Ѷ���״̬�ĳ�2
		try {
			if(service.addLongRentOrder(lodi)){
				//���֧������ʱ
				Jobclient.addJob(order.getOrderNum(), 10, "/order/CallBack_updateOrderState.do", "�¿���ɵ�ƣ�", 10,JobConfig.getJobName_orderCancel());
				Map<String,String> resMap=new HashMap<String, String>();
				resMap.put("orderId", order.getOrderId());
				resMap.put("orderNum", order.getOrderNum());
				return new ResultDto(200,"�������ɳɹ���",resMap);
			}else{
				return new ResultDto(200,"��������ʧ�ܣ�");
			}
		} catch (Exception e) {
			return new ResultDto(2041,"��Ӷ���ʧ�ܣ������ԣ�");
		}
	}
	
	//���ɶ�����
	public static String CreateOrderNum(){
		StringBuffer sb=new StringBuffer(new SimpleDateFormat("HHssmm").format(new Date()));
		String c=String.valueOf(new Date().getTime());
		sb.append(c.substring(6, c.length()));
		sb.append(new Random().nextInt(9000)+(1000-1));
		return sb.toString();
	}
	// ��Ӷ���-��ʱ���˳�λ
	@RequestMapping("addTempOrder")//05:00   
	public  ResultDto addTempOrder(String userId,String carportId,String day,String beginTime,String endTime,String carNo){
		//��֤����
		if(carportId==null||userId==null||day==null||beginTime==null||endTime==null||carNo==null){
			return new ResultDto(2040,"�������Ϸ���");
		}
		//��֤ʱ���Ƿ�Ϸ�
		//��֤������
		Date thisDay=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//������
		try {
			thisDay=sdf.parse(day);
		} catch (ParseException e) {
			return new ResultDto(2041,"ʱ���ʽ�쳣��");
		}
		Integer beginHours=0;
		Integer endHours=0;
		try {
			 //�õ���ʼСʱ
			 beginHours=Integer.valueOf(beginTime.substring(0,2));
			 //����Сʱ
			 endHours=Integer.valueOf(endTime.substring(0,2));
		} catch (NumberFormatException e) {
			return new ResultDto(2042,"��ʼ����ʱ���ʽ�쳣��");
		}
		if (beginHours >= endHours) {
			return new ResultDto(2042, "��ʼʱ�䲻�ܴ��ڵ��ڽ���ʱ�䣡");
		}
		//��֤��ǰ�죬��ǰʱ�䣬�Ƿ�Ϊ��ԤԼ
		if(!service.valThisDayIsExists(day, carportId,beginHours,endHours)){
			return new ResultDto(2043, "��ѡ���ʱ���������ԤԼ��ʱ��Ӵ��");
		}
		//��֤�����Ƿ��Ѿ�����
		if(service.valCarportTempOrderIsTemp(carportId, day, beginHours, endHours)){
			return new ResultDto(2044, "��ѡ���ʱ������Ѿ���ԤԼ��ʱ��Ӵ��");
		}
		//��ѯ��λ����
		DetailsDto d=service.getloadAllDetails(carportId);
		//��Ӹ�ֵ��Ϣ
		MyCarOrderTime cot=new MyCarOrderTime();
		cot.setBeginTime(beginTime);
		cot.setEndTime(endTime);
		cot.setMotId(UUID.randomUUID().toString().replace("-",""));
		cot.setRentDate(thisDay);
		CarPortOrderDetailInfo cpod=new CarPortOrderDetailInfo();
		cpod.setCarportId(carportId);
		cpod.setCpodId(UUID.randomUUID().toString().replace("-",""));//����
		cpod.setDetailedAddress(d.getDetailedAddress());
		cpod.setFeeType(d.getFeeType());
		cpod.setHousId(d.getHousId());
		cpod.setHousType(d.getHousType());
		cpod.setMoney(d.getMoney());
		cpod.setParkremark(d.getParkremark()==null?"":d.getParkremark());
		cpod.setRentoutType(1);
		cpod.setSerialnumber(d.getSerialnumber());
		
		OrderInfo order=new OrderInfo();
		order.setOrderId(UUID.randomUUID().toString().replace("-",""));
		order.setOrderNum(CreateOrderNum());
		order.setCarNo(carNo);
		order.setOrderType(1);
		order.setUserid(userId);
		//�������
		order.setCountMoney(computationalCost(cpod.getFeeType(), beginHours, endHours, cpod.getMoney()));
		
		cpod.setOrder(order);
		cot.setCpod(cpod);
		//��Ӷ���
		try {
			if(service.addTempOrder(cot)){
				Map<String,String> resMap=new HashMap<String, String>();
				resMap.put("orderId", order.getOrderId());
				resMap.put("orderNum", order.getOrderNum());
				//��Ӽ�ʱ��
				Jobclient.addJob(order.getOrderNum(), 300, "/order/CallBack_updateOrderState.do", "zhangshabi", 10, JobConfig.getJobName_orderCancel());
				return new ResultDto(200,"��Ӷ����ɹ���",resMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultDto(2045,"��Ӷ����쳣��");
		}
		return new ResultDto(2045,"��Ӷ���ʧ�ܣ�");
	}

	// �������
	public static double computationalCost(int type, Integer begin, Integer end,
			double money) {
		if (type == 0) {// �������շ�
			return money;
		} else {
			// �õ���ʼʱ�䣬����ʱ��
			return (end -begin) * money;
		}
	}
	// ��ѯ����-����ͣ���������ϣ�
	// ��ѯ����-���˳�λ�����ϣ�
	// ��ѯ����-������˳�λ�����飩
	// ��ѯ����-��ʱ���˳�λ�����飩
	// ��ѯ����-���������ϣ�
	// �޸Ķ���-����-���ܾܾ�
	//�޸Ķ���״̬
	@RequestMapping("CallBack_updateOrderState")
	public String  CallBack_updateOrderState(String jobName,String context,String jobGorupName){
		System.out.println("���ǻص�������"+jobName+"  ct"+context+"  ������"+jobGorupName);
		if(jobName==null){
			return "failure";
		}
		if(service.CallBack_updateOrderState(jobName)>0){
			return "SUCCESS";
		}
		return "failure";
	}
	//רλ������¼��ѯ
	@RequestMapping("getCarportOrderInfo")
	public ResultDto getCarportOrderInfo(String userid,Integer pageIndex,Integer pageSize){
		try {
    		if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "����������Ϸ�");
			}
    		Pager<CarportOrderInfo> pager=new Pager<CarportOrderInfo>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.getCarportOrderInfoByUserid(map));
			//��ѯ����
			pager.setTotalNumber(service.getCarportOrderInfoCount(userid));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"��ѯ�ɹ�",pager);
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("��������쳣��");
		}
    	return new ResultDto(3002,"��ѯʧ��");
	}
	//����ͣ����������¼��ѯ
	@RequestMapping("getParkOrderInfo")
	public ResultDto getParkOrderInfo(String userid,Integer pageIndex,Integer pageSize){
		try {
    		if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "����������Ϸ�");
			}
    		Pager<ParkOrderInfo> pager=new Pager<ParkOrderInfo>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.getParkOrderInfoByUserid(map));
			//��ѯ����
			pager.setTotalNumber(service.getParkOrderInfoCount(userid));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"��ѯ�ɹ�",pager);
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("��������쳣��");
		}
    	return new ResultDto(3002,"��ѯʧ��");
	}
	/**
	 * order/getLongRentOrderDetails.do?orderId=ffabe5fdd4e34d96b476ad21c860e8ad
	 * @param orderId
	 * @return
	 */
	//���ⶩ�������ѯ
	@RequestMapping("getLongRentOrderDetails")
	public ResultDto getLongRentOrderDetails(String orderId){
		try {
			if(orderId==null){
				return new ResultDto(3006,"�����������Ϊ��");
			}
			LongRentOrderDto lro=service.getLongRentOrderDetails(orderId);
			if(lro==null){
				return new ResultDto(3007,"��������ȷ�Ķ���");
			}
			if(lro.getPayType()==0){
				System.out.println(lro.getPayType());
				String phone=lro.getPhone().substring(0, 3)+"****"+lro.getPhone().substring(7, 11);
				System.out.println(phone);
				lro.setPhone(phone);
			}
			return new ResultDto(200,"�ɹ�",lro);
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("��������쳣��");
			e.printStackTrace();
			return new ResultDto(3007,"��ѯʧ��");
		}
	}
	/**
	 * order/getTempOrderDetails.do?orderId=4a1eff50f64f45648682100964c74e51
	 * @param orderId
	 * @return
	 */
	//��ʱͣ�����������ѯ
	@RequestMapping("getTempOrderDetails")
	public ResultDto getTempOrderDetails(String orderId){
		try {
			if(orderId==null){
				return new ResultDto(3006,"�����������Ϊ��");
			}
			TempOrderDto to=service.getTempOrderDetail(orderId);
			if(to==null){
				return new ResultDto(3007,"��������ȷ�Ķ���");
			}
			if(to.getPayType()==0){
				System.out.println(to.getPayType());
				String phone=to.getPhone().substring(0, 3)+"****"+to.getPhone().substring(7, 11);
				System.out.println(phone);
				to.setPhone(phone);
			}
			return new ResultDto(200,"�ɹ�",to);
		} catch (Exception e) {
			log.error("��������쳣��");
			e.printStackTrace();
			return new ResultDto(3007,"��ѯʧ��");
		}
	}
	//�û�����
	@RequestMapping("evaluation")
	public ResultDto updateEvaluation(String orderId,Integer evaluation){
		if(orderId==null||evaluation==null){
			return new ResultDto(3006,"�����������Ϊ��");
		}
		if(evaluation>5||evaluation<0){
			return new ResultDto(3008,"�ף����۳����˷�Χ��");
		}
		if(service.updateEvaluationByOrderId(orderId, evaluation)>0){
			return new ResultDto(200,"�ף���л��������");
		}
		return new ResultDto(3007,"�ף�����ʧ����Ӵ");
	}
	/**
	 * �����ҵĶ��������⣩
	 * order/getOrderInfo.do?userid=1&pageIndex=1&pageSize=10
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("getLongRentInfo")
	public ResultDto getOrderInfo(String userid,Integer pageIndex,Integer pageSize){
		Pager<SupplierOrder> pager;
		try {
			if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "����������Ϸ�");
			}
			pager = new Pager<SupplierOrder>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.getLongRentInfo(map));
			//��ѯ����
			pager.setTotalNumber(service.getLongRentInfoCount(userid));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"��ѯ�ɹ�",pager);
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("�����ҵĶ��������⣩");
		}
		return new ResultDto(2003,"��������쳣��");
	}
	/**
	 * //�����ҵĶ�������ʱ���⣩
	 * @param userid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("getTemporaryRentInfo")
	public ResultDto getLongOrderInfo(String userid,Integer pageIndex,Integer pageSize){
		Pager<SupplierOrder> pager;
		try {
			if(pageIndex==null||pageSize==null||userid==null){
				return new ResultDto(3006, "����������Ϸ�");
			}
			pager = new Pager<SupplierOrder>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("userid", userid);
			pager.setList(service.getTemporaryRentInfo(map));
			//��ѯ����
			pager.setTotalNumber(service.getTemporaryRentInfoCount(userid));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"��ѯ�ɹ�",pager);
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("�����ҵĶ�������ʱ���⣩");
		}
		return new ResultDto(200,"��ѯ�ɹ�");
	}
	/**
	 * �������ܶ���
	 * @param orderNum
	 * @return
	 */
	@RequestMapping("acceptOrder")
	public ResultDto acceptOrder(String orderNum) throws Exception{
		try {
			if(orderNum==null){
				return new ResultDto(3006,"�����������Ϊ��");
			}
			OrderStateDto osd=service.selectOrderStateByOrderId(orderNum);
			if(osd.getOrder_state()!=0){
				return new ResultDto(3007,"�ö����ѱ����ܻ��Ѿܾ�");
			}
			if(osd.getPayType()==0){
				return new ResultDto(3009,"�ö�����δ֧��Ӵ");
			}
			if(service.acceptOrder(orderNum)){
					return new ResultDto(200,"�ɹ����ܶ���");
				}
		} catch (Exception e) { 
			log.error("���ܶ����쳣");
			System.out.println("���ܶ���ʧ��");
		}
		return new ResultDto(3007,"���ܶ���ʧ��");
	}
	//�����ܾ�����
	@RequestMapping("RefuseOrder")
	public ResultDto RefuseOrder(String orderNum) throws Exception{
		try {
			if(orderNum==null){
				return new ResultDto(3006,"�����������Ϊ��");
			}
			OrderStateDto osd=service.selectOrderStateByOrderId(orderNum);
			if(osd.getOrder_state()!=0){
				return new ResultDto(3007,"�ö����ѱ����ܻ�ܾ�");
			}
			if(osd.getPayType()==0){
				return new ResultDto(3009,"�ö�����δ֧��Ӵ");
			}
			if(service.refuseOrder(orderNum)){
				return new ResultDto(200,"�ɹ��ܾ�����");
			}
		} catch (Exception e) {
			log.error("�ܾ������쳣");
			System.out.println("�ܾ�����ʧ��");
		}
		return new ResultDto(3008,"�ܾ�����ʧ��");
	}
	//�����ҵĶ�����ʱ��������
	@RequestMapping("TemporaryRentDetails")
	public ResultDto getTemporaryRentDetails(String orderId){
		try {
			if(orderId==null){
				return new ResultDto(3006,"�����������Ϊ��");
			}
			if(service.getTemporaryRentDetails(orderId)!=null){
				return new ResultDto(200,"��ѯ�ɹ�",service.getTemporaryRentDetails(orderId));
			}
		} catch (Exception e) {
			log.error("��ѯ�쳣");
			e.printStackTrace();
		}
		return new ResultDto(3008,"��ѯ�쳣");
	}
	//�����ҵĶ�����������
	@RequestMapping("longRentDetails")
	public ResultDto getLongRentDetails(String orderId){
		try {
			if(orderId==null){
				return new ResultDto(3006,"�����������Ϊ��");
			}
			if(service.getLongRentDetails(orderId)!=null){
				return new ResultDto(200,"��ѯ�ɹ�",service.getLongRentDetails(orderId));
			}
		} catch (Exception e) {
			log.error("��ѯ�쳣");
			e.printStackTrace();
		}
		return new ResultDto(3008,"��ѯ�쳣");
	}
	/**
	 * �����ܽ᣺��ѯ��λ���ϣ��ж��Ƿ��Ѿ�������
	 * �޸ĳ�λ���жϸó�Ϊ�Ƿ�����Ѿ����ף������ܣ��Ķ���
	 */
}

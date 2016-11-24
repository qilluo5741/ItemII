package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.MyCarOrderTime;
import com.sharebo.entity.OrderInfo;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.CarportOrderInfo;
import com.sharebo.entity.dto.LongRentDto;
import com.sharebo.entity.dto.LongRentOrderDto;
import com.sharebo.entity.dto.OrderStateDto;
import com.sharebo.entity.dto.ParkOrderDetails;
import com.sharebo.entity.dto.ParkOrderFeeDto;
import com.sharebo.entity.dto.ParkOrderInfo;
import com.sharebo.entity.dto.SupplierOrder;
import com.sharebo.entity.dto.TempOrderDto;
import com.sharebo.entity.dto.TemporaryRentDto;

public interface OrderMapper {
	// ͨ��parkID��ѯԤԼ���úͱ�֤��
	public ParkOrderFeeDto getParkFeeByParkId(@Param("parkId") String parkId);

	// ��Ӷ���
	public int addOrderInfo(OrderInfo order);

	// ��ӹ���ͣ������������
	public int addParkOrderDetails(com.sharebo.entity.ParkOrderDetailsInfo podi);

	/**
	 * ����ͣ�������������ѯ
	 * 
	 * @param orderId
	 * @return
	 */
	public ParkOrderDetails getParkOrderDetail(@Param("orderId") String orderId);

	// ��֤�ó�λ�Ƿ��ǳ��⳵λ
	public int valCarPortIsLongRentByCarportId(
			@Param("carportId") String carportId);

	// ��֤��λ�Ƿ��Ѿ�������
	public int valCarPortIsRentByCarportId(@Param("carportId") String carportId);

	// ��Ӹ��˳�λ����
	public int addCarportorDerdetails(
			com.sharebo.entity.CarPortOrderDetailInfo podi);

	// ��Ӹ��˳�λ��������
	public int addLeaseorderdetails(com.sharebo.entity.LeaseOrderDetailInfo lodi);

	// �޸Ķ���״̬
	public int CallBack_updateOrderState(@Param("orderNum") String orderNum);

	// ��ѯ�����Ƿ�Ϊ����
	public int valdisableWeek(@Param("week") Integer week,
			@Param("carportId") String carportId);

	// ��֤�죨Сʱ�Ƿ�Ϸ���
	public int valDayisExists(@Param("sql") String sql);

	// ��֤Сʱ�Ƿ�氡��
	public int valHoursIsExists(@Param("carportId") String carportId,
			@Param("thisDay") String thisDay);

	// ��ѯ���ڹ���
	public Weekrules weekTime(@Param("carportId") String carportId);

	// רλ������¼��ѯ
	public List<CarportOrderInfo> getCarportOrderInfoByUserid(
			Map<String, Object> map);

	// רλ������¼����
	public int getCarportOrderInfoCount(@Param("userid") String userid);

	// ��Ӹ��˳�λ��������
	public int addCarportorDerdetailsByTemp(com.sharebo.entity.CarPortOrderDetailInfo lodi);
	//��Ӹ�����ʱ����ʱ��
	public int addMyCarOrderTime(com.sharebo.entity.MyCarOrderTime cot);
	//��ѯ��ǰʱ�䣬��λ���ڵĶ���
	public List<MyCarOrderTime> getMyCarOrderTime(@Param("carportId")String carportId,@Param("rentDate")String rentDate);
	
	//����ͣ����������¼��ѯ
	public List<ParkOrderInfo> getParkOrderInfoByUserid(Map<String,Object> map);
	//����ͣ����������¼����
	public int getParkOrderInfoCount(@Param("userid")String userid);
	//���ⶩ�������ѯ
	public LongRentOrderDto getLongRentOrderDetails(@Param("orderId")String orderId);
	//��ʱͣ�����������ѯ
	public TempOrderDto getTempOrderDetail(@Param("orderId")String orderId);
	//�û�����
	public int updateEvaluationByOrderId(@Param("orderId")String orderId,@Param("evaluation")Integer evaluation);
	//ͨ��orderId��ѯ����״̬
	public OrderStateDto selectOrderStateByOrderId(@Param("orderNum")String orderNum);
	//(����)�޸Ķ���״̬
	public int updateOrderStateByOrderId(@Param("orderNum")String orderNum,@Param("order_state")Integer order_state);
	//�����ҵĶ��������⣩
	public List<SupplierOrder> getLongRentInfo(Map<String,Object> map);
	//�����ҵĶ��������⣩����
	public int getLongRentInfoCount(@Param("userid")String userid);
	//�����ҵĶ�������ʱ���⣩
	public List<SupplierOrder> getTemporaryRentInfo(Map<String,Object> map);
	//�����ҵĶ�������ʱ���⣩����
	public int getTemporaryRentInfoCount(@Param("userid")String userid);
	//�����ҵĶ�����ʱ��������
	public TemporaryRentDto getTemporaryRentDetails(@Param("orderId")String orderId);
	//�����ҵĶ�����������
	public LongRentDto getLongRentDetails(@Param("orderId")String orderId);
}

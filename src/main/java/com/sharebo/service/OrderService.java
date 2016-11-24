package com.sharebo.service;

import java.util.List;
import java.util.Map;
import com.sharebo.entity.LeaseOrderDetailInfo;
import com.sharebo.entity.MyCarOrderTime;
import com.sharebo.entity.ParkOrderDetailsInfo;
import com.sharebo.entity.dto.DetailsDto;
import com.sharebo.entity.dto.CarportOrderInfo;
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

public interface OrderService {
	// ͨ��parkID��ѯԤԼ���úͱ�֤��
	public ParkOrderFeeDto getParkFeeByParkId(String parkId);

	// ��ӹ���ͣ��������
	public boolean addParkOrder(ParkOrderDetailsInfo podi) throws Exception;

	/**
	 * ����ͣ�������������ѯ
	 * 
	 * @param orderId
	 * @return
	 */
	public ParkOrderDetails getParkOrderDetail( String orderId);

	// ��֤�ó�λ�Ƿ��ǳ��⳵λ
	public boolean valCarPortIsLongRentByCarportId(String carportId);

	// ��֤��λ�Ƿ��Ѿ�������
	public int valCarPortIsRentByCarportId(String carportId);

	// ��ѯ���˳�������
	public LongCarDetailsDto getLongrentInfo(String carportId);
	//�³��ⶩ��
	public boolean addLongRentOrder(LeaseOrderDetailInfo lodi) throws Exception;
	//�޸Ķ���״̬
	public int CallBack_updateOrderState(String orderNum);
	
	//��֤����ʱ���Ƿ��ԤԼ
	public boolean valThisDayIsExists(String day,String carPortId,Integer begin,Integer end);
	//��֤�����Ƿ��Ѿ�����ԤԼ��ʱ���
	public boolean valOrderIsExists(String day,String carPortId,Integer begin,Integer end);
	//��ѯ��ʱ��������
	public DetailsDto getloadAllDetails(String carportId);
	//רλ������¼��ѯ
	public List<CarportOrderInfo> getCarportOrderInfoByUserid(Map<String,Object> map);
	//רλ������¼����
	public int getCarportOrderInfoCount(String userid);
	//�����ʱ����
	public boolean addTempOrder(MyCarOrderTime cot) throws Exception;
	//��֤��λ�Ƿ��Ѿ�ԤԼ���ߺϷ�
	public boolean valCarportTempOrderIsTemp(String carportId,String thisdate,Integer begin,Integer end);
	//����ͣ����������¼��ѯ
	public List<ParkOrderInfo> getParkOrderInfoByUserid(Map<String,Object> map);
	//����ͣ����������¼����
	public int getParkOrderInfoCount(String userid);
	//���ⶩ�������ѯ
	public LongRentOrderDto getLongRentOrderDetails(String orderId);
	//��ʱͣ�����������ѯ
	public TempOrderDto getTempOrderDetail(String orderId);
	//�û�����
	public int updateEvaluationByOrderId(String orderId,Integer evaluation);
	//ͨ��orderNum��ѯ����״̬
	public OrderStateDto selectOrderStateByOrderId(String orderNum);
	//�������ܶ��� 
	public boolean acceptOrder(String orderNum) throws Exception;
	//�ܾ���������
	public boolean refuseOrder(String orderNum) throws Exception;
	//�����ҵĶ��������⣩
	public List<SupplierOrder> getLongRentInfo(Map<String,Object> map);
	//�����ҵĶ��������⣩����
	public int getLongRentInfoCount(String userid);
	//�����ҵĶ�������ʱ���⣩
	public List<SupplierOrder> getTemporaryRentInfo(Map<String,Object> map);
	//�����ҵĶ�������ʱ���⣩����
	public int getTemporaryRentInfoCount(String userid);
	//�����ҵĶ�����ʱ��������
	public TemporaryRentDto getTemporaryRentDetails(String orderId);
	//�����ҵĶ�����������
	public LongRentDto getLongRentDetails(String orderId);
}

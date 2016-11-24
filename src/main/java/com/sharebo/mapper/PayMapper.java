package com.sharebo.mapper;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.CallBackRefuceInfo;
import com.sharebo.entity.dto.UserorregidDto;

public interface PayMapper {
	// ��֤�����Ƿ���ڣ����ҵõ�����
	public Double getCountMoneyByOrderNum(@Param("orderNum") String orderNum);

	// �����û�Id��ѯʣ������
	public Double getAvailableBalanceByuserid(@Param("userid") String userid);

	// �޸����
	public int updateAvailableBalanceByUserid(
			@Param("countMoney") Double countMoney,
			@Param("userid") String userid);

	// �޸Ķ���״̬
	public int updateOrderinfo(@Param("payType") Integer payType,
			@Param("orderNum") String orderNum);

	// ��ӽ��׼�¼
	public int addtransactionrecord(@Param("userid") String userid,
			@Param("tradeType") Integer tradeType,
			@Param("tradeMoney") Double tradeMoney);

	// ��֤�Ƿ��Ǹ��˳�λ
	public Integer valOrderTypeByOrderNum(@Param("orderNum") String orderNum);

	// �жϸù���ͣ���������Ƿ��Զ��ӵ�
	public Integer valParkAutomaticOrder(@Param("orderNum") String orderNum);

	// �޸Ķ���״̬
	public int updateOrderStateByOrderNum(@Param("orderNum") String orderNum,
			@Param("order_state") Integer order_state);

	// ͨ�����������ѯ�˿��� �Լ�userid
	public CallBackRefuceInfo getCountMoneyAndUseridByorderNum(
			@Param("orderNum") String orderNum);

	// ���û��޸����
	public int updateAvailableBalance(@Param("userid") String userid,
			@Param("availableBalance") Double availableBalance);
	//��ѯuserid
	public String valParkOrderByuserid(@Param("orderNum") String orderNum);
	//��ѯ���͵�regid(
	public UserorregidDto getloadAllcarNoandregid(@Param("orderNum") String orderNum);
	// ��֤�����Ƿ���ڣ����ҵõ���Ҫ֧���ķ���
	public Double getpayMoneyBypayidentifying(@Param("payidentifying") String payidentifying);
	//�޸�״̬
	public int updateparkcharge(@Param("payType") Integer payType,@Param("payidentifying") String payidentifying);
	//���ݲ�ѯͣ���ɷ�userid
	public String getvalparkchargeByuserid(@Param("payidentifying") String payidentifying);
}

package com.sharebo.service;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.dto.UserorregidDto;

public interface PayService {
	// ��֤�����Ƿ���ڣ����ҵõ�����
	public Double getCountMoneyByOrderNum(String orderNum);

	// �����û�Id��ѯʣ������
	public Double getAvailableBalanceByuserid(String userid);

	// ��������״̬
	public boolean updateOrder(String orderNum, Double countMoney, String userid)
			throws Exception;
	// ��֤�Ƿ��Ǹ��˳�λ
	public int valOrderTypeByOrderNum(String orderNum);

	// �жϸù���ͣ���������Ƿ��Զ��ӵ�
	public Integer valParkAutomaticOrder(String orderNum);
	// �޸Ķ���״̬
	public int updateOrderStateByOrderNum(String orderNum, Integer order_state);
	//�ܾ���������
	public boolean callBackRefuce(String orderNum) throws Exception;
	// ֧������������״̬
	public boolean updateOrderalipay(String orderNum, Double countMoney, String userid) throws Exception;
	//֧������ѯuserid
	public String valParkOrderByuserid(String orderNum);
	//΢��
	public boolean updateOrderweiixn(String orderNum, Double countMoney, String userid) throws Exception;
	//��ѯ���͵�regid(
	public UserorregidDto getloadAllcarNoandregid(String orderNum);
	//����ͣ������״̬
	public boolean updateparkcharge(String payidentifying, Double payMoney, String userid)throws Exception;
	// ��֤�����Ƿ���ڣ����ҵõ���Ҫ֧���ķ���
	public Double getpayMoneyBypayidentifying(String payidentifying);
	//���ݲ�ѯͣ���ɷ�userid
	public String getvalparkchargeByuserid(@Param("payidentifying") String payidentifying);
	//֧������������״̬
	public boolean updateparkchargealipay(String payidentifying, Double payMoney, String userid) throws Exception;
	//֧������������״̬
	public boolean updateparkchargeweiixn(String payidentifying, Double payMoney, String userid) throws Exception;
}

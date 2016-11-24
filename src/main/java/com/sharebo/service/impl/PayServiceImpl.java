package com.sharebo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sharebo.entity.dto.CallBackRefuceInfo;
import com.sharebo.entity.dto.UserorregidDto;
import com.sharebo.mapper.PayMapper;
import com.sharebo.service.PayService;
@Service
@Transactional(rollbackFor=Exception.class)
public class PayServiceImpl implements PayService {
	@Autowired
	private PayMapper mapper;

	@Override
	public Double getCountMoneyByOrderNum(String orderNum) {
		return mapper.getCountMoneyByOrderNum(orderNum);
	}
	@Override
	public Double getAvailableBalanceByuserid(String userid) {
		return mapper.getAvailableBalanceByuserid(userid);
	}
	@Override
	public boolean updateOrder(String orderNum, Double countMoney,String userid)throws Exception {
		// ��ȥ���
		if(mapper.updateAvailableBalanceByUserid(countMoney, userid)<=0){
			return false;
		}
		// �޸Ķ���״̬
		if(mapper.updateOrderinfo(3, orderNum)<=0){
			throw new Exception("�޸Ķ���״̬ʧ�ܣ�");
		}
		// ���뽻�׼�¼
		if(mapper.addtransactionrecord(userid, 2, -countMoney)<=0){
			throw new Exception("���뽻�׼�¼ʧ�ܣ�");
		}
		return true;
	}
	//��֤�Ƿ��Ǹ��˳�λ
	@Override
	public int valOrderTypeByOrderNum(String orderNum) {
		return mapper.valOrderTypeByOrderNum(orderNum);
	}
	//�жϸù���ͣ���������Ƿ��Զ��ӵ�
	@Override
	public Integer valParkAutomaticOrder(String orderNum) {
		return mapper.valParkAutomaticOrder(orderNum);
	}
	//�޸Ķ�����״̬ �����--------�Զ��ӵ�
	@Override
	public int updateOrderStateByOrderNum(String orderNum, Integer order_state) {
		return mapper.updateOrderStateByOrderNum(orderNum, order_state);
	}
	@Override
	public boolean callBackRefuce(String orderNum) throws Exception {
		//ͨ������NUM�ҵ��û� ,���õ��ܷ��ã�
		CallBackRefuceInfo cbr=mapper.getCountMoneyAndUseridByorderNum(orderNum);
		if(cbr==null){
			return false;//û���ҵ���صĶ������������Ѿ�����
		}
		// �޸Ķ�����״̬
		if (mapper.updateOrderStateByOrderNum(orderNum, 2) <= 0) {
			return false;
		}
		//���û�����
		if(mapper.updateAvailableBalance(cbr.getUserid(), cbr.getCountMoney())<=0){
			throw new Exception("�û��˿�ʧ�ܣ�");
		}
		//��Ӽ�¼
		if(mapper.addtransactionrecord(cbr.getUserid(), 3, cbr.getCountMoney())<=0){
			throw new Exception("��Ӽ�¼ʧ�ܣ�");
		}
		return true;
	}
	//֧����֧��
	@Override
	public boolean updateOrderalipay(String orderNum, Double countMoney, String userid) throws Exception {
		// �޸Ķ���״̬
		if(mapper.updateOrderinfo(1,orderNum)<=0){
			throw new Exception("�޸Ķ���״̬ʧ�ܣ�");
		}
		// ���뽻�׼�¼
		if(mapper.addtransactionrecord(userid, 4,-countMoney)<=0){
			throw new Exception("���뽻�׼�¼ʧ�ܣ�");
		}
		return true;
	}
	//���ݶ����Ų�ѯuserid
	@Override
	public String valParkOrderByuserid(String orderNum) {
		return mapper.valParkOrderByuserid(orderNum);
	}
	@Override
	public boolean updateOrderweiixn(String orderNum, Double countMoney, String userid) throws Exception {
		//΢���޸Ķ���״̬
		if(mapper.updateOrderinfo(2,orderNum)<=0){
			throw new Exception("�޸Ķ���״̬ʧ�ܣ�");
		}
		//΢�Ų��뽻�׼�¼
		if(mapper.addtransactionrecord(userid,5,-countMoney)<=0){
			throw new Exception("���뽻�׼�¼ʧ�ܣ�");
		}
		return true;
	}
	@Override
	public UserorregidDto getloadAllcarNoandregid(String orderNum) {
		return mapper.getloadAllcarNoandregid(orderNum);
	}
	@Override
	public Double getpayMoneyBypayidentifying(String payidentifying) {
		return mapper.getpayMoneyBypayidentifying(payidentifying);
	}
	@Override
	public boolean updateparkcharge(String payidentifying, Double payMoney, String userid) throws Exception {
		// ��ȥ���
		if(mapper.updateAvailableBalanceByUserid(payMoney, userid)<=0){
			return false;
		}
		// �޸Ķ���״̬
		if(mapper.updateparkcharge(3,payidentifying)<=0){
			throw new Exception("�޸Ķ���״̬ʧ�ܣ�");
		}
		// ���뽻�׼�¼
		if(mapper.addtransactionrecord(userid, 2, -payMoney)<=0){
			throw new Exception("���뽻�׼�¼ʧ�ܣ�");
		}
		return true;
	}
	//ͣ���ɷѲ�ѯuserid���û������¼ʹ��
	@Override
	public String getvalparkchargeByuserid(String payidentifying) {
		return mapper.getvalparkchargeByuserid(payidentifying);
	}
	@Override
	public boolean updateparkchargealipay(String payidentifying, Double payMoney, String userid) throws Exception {
		// �޸Ķ���״̬
		if(mapper.updateparkcharge(1,payidentifying)<=0){
			throw new Exception("�޸Ķ���״̬ʧ�ܣ�");
		}
		// ���뽻�׼�¼
		if(mapper.addtransactionrecord(userid, 4,-payMoney)<=0){
			throw new Exception("���뽻�׼�¼ʧ�ܣ�");
		}
		return true;
	}
	@Override
	public boolean updateparkchargeweiixn(String payidentifying, Double payMoney, String userid) throws Exception {
		// �޸Ķ���״̬
		if(mapper.updateparkcharge(2,payidentifying)<=0){
			throw new Exception("�޸Ķ���״̬ʧ�ܣ�");
		}
		// ���뽻�׼�¼
		if(mapper.addtransactionrecord(userid,5,-payMoney)<=0){
			throw new Exception("���뽻�׼�¼ʧ�ܣ�");
		}
		return true;
	}
}

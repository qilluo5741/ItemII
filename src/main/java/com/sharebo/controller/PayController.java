package com.sharebo.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.job.jobclient.Jobclient;
import com.sharebo.config.JobConfig;
import com.sharebo.config.WeiXinConfig;
import com.sharebo.entity.WeixinResult;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.PayService;
import com.sharebo.util.AlipayNotify;
import com.sharebo.util.Log;
import com.sharebo.util.MD5Util;
import com.sharebo.util.WeixinUtil;
/**
 * ֧��
 * ���ߣ�weimeilayer@163.com
 * ʱ�䣺2016-10-14
 * @author Administrator
 *
 */
@RestController
@RequestMapping("pay")
public class PayController {
	private Logger log=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PayService service;
	@Autowired
	private HttpServletRequest request;
	//�ص��ܾ�����
	@RequestMapping("callBackRefuce")
	public String callBackRefuce(String jobName){
		//��Ǯ
		try {
			if(service.callBackRefuce(jobName)){
				System.out.println("��Ǯ�ɹ���");
				return "SUCCESS";
			}
		} catch (Exception e) {
			e.printStackTrace();
			//������һ����ʱ
			try {
				Jobclient.addJob(jobName, 120,"/pay/callBackRefuce.do", "�ȿ�", 2, JobConfig.getJobName_orderRefused());
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("JOB����ʧ��..");
			}
			return "SUCCESS";
		}
		return "ERROR";
	}
	/**
	 * pay/balancePay.do?orderNum=05072619674457523&userid=889390526942412800
	 * @param orderNum
	 * @param userid
	 * @return
	 */
	//���֧��
	@RequestMapping("balancePay")
	public ResultDto balancePay(String orderNum,String userid){
		if(orderNum==null||userid==null){
			return new ResultDto(30045,"��������Ϊ��Ӵ��");
		}
		//��֤�����Ƿ���� ���õ�֧���ķ���
		Double countMoney=service.getCountMoneyByOrderNum(orderNum);
		if(countMoney==null){
			return new ResultDto(30046,"�Ҳ����ö���Ӵ��");
		}
		// �����û�Id��ѯʣ������
		Double availableBalance=service.getAvailableBalanceByuserid(userid);
		//��֤Ǯ���ļ۸��Ƿ���ڵ�����Ҫ֧���ķ���
		if(availableBalance<countMoney){
			return new ResultDto(2049,"�������㣡");
		}
		//��ȥ���
		//�޸Ķ���״̬
		//���뽻�׼�¼
		try {
			if(!service.updateOrder(orderNum, countMoney, userid)){
				return new ResultDto(30050,"֧��ʧ�ܣ�");
			}else{
				//ȡ��֧������ʱ
				Jobclient.removeJob(orderNum, JobConfig.getJobName_orderCancel());
				//��֤�Ƿ��Ǹ��˳�λ
				//����Ǹ��˳�λ��ӵ���ʱ
				if(service.valOrderTypeByOrderNum(orderNum)==1){//�Ǹ��˳�λ
					System.out.println("���˳�λ��ʱ��ʼ");
					//��Ӽ�ʱ���ݶ���
					Jobclient.addJob(orderNum,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
				}else{//ͣ����
					Integer automatic =service.valParkAutomaticOrder(orderNum);
					if(automatic==null||automatic!=1){//��ͣ�����ֶ��ӵ�
						System.out.println("ͣ������λ��ʱ��ʼ");
						//��Ӽ�ʱ
						Jobclient.addJob(orderNum,300,"/pay/callBackRefuce.do", "��ʱ",5, JobConfig.getJobName_orderRefused());
					}else{
						//�Զ��ӵ�
						//�޸Ķ�����״̬ �����
						service.updateOrderStateByOrderNum(orderNum,1);
					}
					//�����ͣ������ѯ��֤�Ƿ������Զ��ӵ�  �����Զ��ӵ�����Ӽ�ʱ
				}
				return new ResultDto(200,"֧���ɹ���");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return new ResultDto(30050,"֧��ʧ�ܣ�");
		}
	}
	//֧����֧��
	/**
	 * pay/alipayPay.do
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="alipayPay",method=RequestMethod.POST)
	public String alipayPay(ModelMap map) throws UnsupportedEncodingException, InterruptedException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
				: valueStr + values[i] + ",";
			}
			//����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//��ȡ֧������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
		//�̻�������
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//֧�������׺�
		//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//�������
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//����״̬
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//��ȡ֧������֪ͨ���ز���
		if(AlipayNotify.verify(params)){
			//��֤�ɹ�
			//////////////////////////////////////////////////////////////////////////////////////////
			//������������̻���ҵ���߼��������
			if(trade_status.equals("TRADE_FINISHED")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				return "fail";
				//ע�⣺
				//�˿����ڳ������˿����޺��������¿��˿��֧����ϵͳ���͸ý���״̬֪ͨ
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				//ע��
				//��֤�����Ƿ���� ���õ�֧���ķ���
				Double countMoney=service.getCountMoneyByOrderNum(out_trade_no);
				System.out.println("֧�����õ�֧���ķ���"+countMoney);
				if(countMoney==null){
					return "fail";
				}
				//�õ�userid
				String userid=service.valParkOrderByuserid(out_trade_no);
				System.out.println("֧�����õ�userid"+userid);
				//ת���������
				double total_feel=Double.valueOf(total_fee);
				//�ж�֧������Ƿ�����Ҫ֧�����һ��
				if(countMoney!=total_feel){
					return "fail";
				}
				try{
					if(!service.updateOrderalipay(out_trade_no,countMoney,userid)){
						return "fail";
					}else{
						//ȡ��֧������ʱ
						Jobclient.removeJob(out_trade_no, JobConfig.getJobName_orderCancel());
						//��֤�Ƿ��Ǹ��˳�λ
						//����Ǹ��˳�λ��ӵ���ʱ
						if(service.valOrderTypeByOrderNum(out_trade_no)==1){//�Ǹ��˳�λ
							System.out.println("���˳�λ��ʱ��ʼ");
							//��Ӽ�ʱ���ݶ���
							Jobclient.addJob(out_trade_no,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
						}else{
							//ͣ����
							Integer automatic =service.valParkAutomaticOrder(out_trade_no);
							if(automatic==null||automatic!=1){//��ͣ�����ֶ��ӵ�
								System.out.println("ͣ������λ��ʱ��ʼ");
								//��Ӽ�ʱ
								Jobclient.addJob(out_trade_no,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
							}else{
								//�Զ��ӵ�
								//�޸Ķ�����״̬ �����
								service.updateOrderStateByOrderNum(out_trade_no,1);//0�ȴ� 1��ʾ �����2�ܾ�
							}
						}
					}
				} catch (Exception e) {
					log.error("֧���쳣");
					return "fail";
				}
				//�����ͣ������ѯ��֤�Ƿ������Զ��ӵ�  �����Զ��ӵ�����Ӽ�ʱ
				//���������������ִ���̻���ҵ�����
				//ע�⣺
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}
			return "success";//�벻Ҫ�޸Ļ�ɾ��
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//��֤ʧ��
			return "fail";
		}
	}
	/**
	 * ΢��֧��
	 * @return
	 * pay/weixinNotice.do
	 */
	@ResponseBody 
	@RequestMapping(value="weixinNotice",method=RequestMethod.POST)
	public String wxNotice(){
		//���ܴ���������Ϣ ��ת���ɼ�ֵ�Եļ���
		Map<String, String> map;
		map = WeixinUtil.xmlToMap(request);
		String weixinSign=map.get("sign");
		String mySign = createSign(map);
		WeixinResult outRes=null;
			//��֤ǩ��
			if(weixinSign.equals(mySign)){//��֤�ɹ�
				////////////////////////////////////������Ҫ�Ĳ���///////////////////////////////////////////
				//ҵ����	result_code	��	String(16)	SUCCESS	SUCCESS/FAIL
				//�������	err_code	��	String(32)	SYSTEMERROR	���󷵻ص���Ϣ����
				//�ܽ��	total_fee	��	Int	100	�����ܽ���λΪ��
				//�ֽ�֧�����	cash_fee	��	Int	100	�ֽ�֧�������ֽ�֧�������֧�����
				//΢��֧��������	transaction_id	��	String(32)	1217752501201407033233368018	΢��֧��������
				//�̻�������	out_trade_no	��	String(32)	1212321211201407033568112322	�̻�ϵͳ�Ķ����ţ�������һ�¡�
				//�̼����ݰ�	attach	��	String(128)	123456	�̼����ݰ���ԭ������
				//֧�����ʱ��	time_end	��	String(14)	20141030133525	֧�����ʱ�䣬��ʽΪyyyyMMddHHmmss����2009��12��25��9��10��10���ʾΪ20091225091010���������ʱ�����
				if(map.get("result_code").equals("SUCCESS")){
					//////////////////////////////////����ҵ��/////////////////////////////////////
					String out_trade_no=map.get("out_trade_no");//�̻�������
					System.out.println("�̻�������"+out_trade_no);
					String total_fee=map.get("total_fee");//�������
					System.out.println("�������"+total_fee);
					/////////////////////////////////////////////////////////////////////////////
					//��֤�����Ƿ���� ���õ�֧���ķ���
					Double countMoney=service.getCountMoneyByOrderNum(out_trade_no);
					System.out.println("΢�ŵõ�֧���ķ���"+countMoney);
					if(countMoney==null){
						return "fail";
					}
					//�õ�userid
					String userid=service.valParkOrderByuserid(out_trade_no);
					System.out.println("΢��userid"+userid);
					//ת���������
					double total_feel=Double.valueOf(total_fee);
					//�ж�֧������Ƿ�����Ҫ֧�����һ��
					if(countMoney*100!=total_feel){
						System.out.println(countMoney*100+"��֤���");
						return "fail";
					}
					try{
						//΢���޸Ķ���״̬
						if(!service.updateOrderweiixn(out_trade_no,countMoney,userid)){
							return "fail";
						}else{
							//ȡ��֧������ʱ
							Jobclient.removeJob(out_trade_no, JobConfig.getJobName_orderCancel());
							//��֤�Ƿ��Ǹ��˳�λ
							//����Ǹ��˳�λ��ӵ���ʱ
							if(service.valOrderTypeByOrderNum(out_trade_no)==1){//�Ǹ��˳�λ
								System.out.println("���˳�λ��ʱ��ʼ");
								//��Ӽ�ʱ���ݶ���
								Jobclient.addJob(out_trade_no,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
							}else{
								//ͣ����
								Integer automatic =service.valParkAutomaticOrder(out_trade_no);
								if(automatic==null||automatic!=1){//��ͣ�����ֶ��ӵ�
									System.out.println("ͣ������λ��ʱ��ʼ");
									//��Ӽ�ʱ
									Jobclient.addJob(out_trade_no,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
								}else{
									//�Զ��ӵ�
									//�޸Ķ�����״̬ �����
									service.updateOrderStateByOrderNum(out_trade_no,1);//0�ȴ� 1��ʾ �����2�ܾ�
								}
							}
						}
					} catch (Exception e) {
						log.error("֧���쳣");
						return "fail";
					}
					/////////////////////////////////////////////////////////////////////////////
					System.out.println(out_trade_no);
					outRes=new WeixinResult("SUCCESS","ǩ����֤�ɹ���");
				}
				else{
					Log.getInstance().debug("΢��֧��֪ͨ��������룺"+map.get("err_code")+"�������������"+map.get("err_code_des"));
					outRes=new WeixinResult("SUCCESS","ҵ������֤Ϊʧ�ܣ�");
				}
			}
			else{
				//��֤ǩ��ʧ�ܣ�
				Log.getInstance().error("ǩ����֤ʧ�ܣ�");
				outRes=new WeixinResult("FAIL","ǩ����֤ʧ�ܣ�");
			}
			System.out.println(WeixinUtil.toXml(outRes));
			//��Ӧ΢���Ƿ���ܳɹ�
			return WeixinUtil.toXml(outRes);
		}
		
	@SuppressWarnings("rawtypes")
	public static String createSign(Map<String,String> parameters){ 	
		StringBuffer sb = new StringBuffer(); 		
		Set es = parameters.entrySet();
		//���в��봫�εĲ�������accsii�������� 		
		Iterator it = es.iterator(); 	
		while(it.hasNext()) { 		
			Map.Entry entry = (Map.Entry)it.next(); 	
			String k = (String)entry.getKey(); 		
			Object v = entry.getValue(); 		
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) { 
				sb.append(k + "=" + v + "&"); 	
				} 	
		} 		
		sb.append("key=" + WeiXinConfig.aap_key); 	
		String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase(); 
		return sign; 	
	}
	/**
	 * pay/balancePaycost.do?payidentifying=14492632095895311&userid=889390526942412800
	 * ���֧��ͣ���ɷ�
	 * @param payidentifying
	 * @param userid
	 * @return
	 */
	@RequestMapping("balancePaycost")
	public ResultDto balancePaycost(String payidentifying,String userid){
		if(payidentifying==null||userid==null){
			return new ResultDto(30045,"��������Ϊ��Ӵ��");
		}
		//��֤�����Ƿ���� ���õ�֧���ķ���
		Double payMoney=service.getpayMoneyBypayidentifying(payidentifying);
		if(payMoney==null){
			return new ResultDto(2036,"�Ҳ����ö���Ӵ��");
		}
		// �����û�Id��ѯʣ������
		Double availableBalance=service.getAvailableBalanceByuserid(userid);
		//��֤Ǯ���ļ۸��Ƿ���ڵ�����Ҫ֧���ķ���
		if(availableBalance<payMoney){
			return new ResultDto(2049,"�������㣡");
		}
		try {
			if(!service.updateparkcharge(payidentifying, payMoney,userid)){
				return new ResultDto(30050,"֧��ʧ�ܣ�");
			}
			return new ResultDto(200,"֧���ɹ���");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return new ResultDto(3050,"֧��ʧ�ܣ�");
		}
	}
	/**
	 * pay/alipayPaycost.do
	 * ֧����֧��
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="alipayPaycost",method=RequestMethod.POST)
	public String getByUserid(ModelMap map) throws UnsupportedEncodingException, InterruptedException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
				: valueStr + values[i] + ",";
			}
			//����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//��ȡ֧������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
		//�̻�������
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//֧�������׺�
		//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//�������
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//����״̬
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//��ȡ֧������֪ͨ���ز���
		if(AlipayNotify.verify(params)){
			//��֤�ɹ�
			//////////////////////////////////////////////////////////////////////////////////////////
			//������������̻���ҵ���߼��������
			if(trade_status.equals("TRADE_FINISHED")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				return "fail";
				//ע�⣺
				//�˿����ڳ������˿����޺��������¿��˿��֧����ϵͳ���͸ý���״̬֪ͨ
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				//ע��
				//��֤�����Ƿ���� ���õ�֧���ķ���
				Double payMoney=service.getpayMoneyBypayidentifying(out_trade_no);
				if(payMoney==null){
					return "fail";
				}
				//�õ�userid
				String userid=service.getvalparkchargeByuserid(out_trade_no);
				System.out.println("֧�����õ�userid"+userid);
				//ת���������
				double total_feel=Double.valueOf(total_fee);
				//�ж�֧������Ƿ�����Ҫ֧�����һ��
				if(payMoney!=total_feel){
					return "fail";
				}
				try{
					if(!service.updateparkchargealipay(out_trade_no, payMoney, userid)){
						return "fail";
					}
				} catch (Exception e) {
					log.error("֧����֧���쳣");
					return "fail";
				}
				//�����ͣ������ѯ��֤�Ƿ������Զ��ӵ�  �����Զ��ӵ�����Ӽ�ʱ
				//���������������ִ���̻���ҵ�����
				//ע�⣺
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}
			return "success";//�벻Ҫ�޸Ļ�ɾ��
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//��֤ʧ��
			return "fail";
		}
	}
	/**
	 * ΢��֧��
	 * @return
	 * pay/weixinPaycost.do
	 */
	@ResponseBody 
	@RequestMapping(value="weixinPaycost",method=RequestMethod.POST)
	public String wxNoticePaycost(){
		//���ܴ���������Ϣ ��ת���ɼ�ֵ�Եļ���
		Map<String, String> map;
		map = WeixinUtil.xmlToMap(request);
		String weixinSign=map.get("sign");
		String mySign = createSign(map);
		WeixinResult outRes=null;
			//��֤ǩ��
			if(weixinSign.equals(mySign)){//��֤�ɹ�
				////////////////////////////////////������Ҫ�Ĳ���///////////////////////////////////////////
				//ҵ����	result_code	��	String(16)	SUCCESS	SUCCESS/FAIL
				//�������	err_code	��	String(32)	SYSTEMERROR	���󷵻ص���Ϣ����
				//�ܽ��	total_fee	��	Int	100	�����ܽ���λΪ��
				//�ֽ�֧�����	cash_fee	��	Int	100	�ֽ�֧�������ֽ�֧�������֧�����
				//΢��֧��������	transaction_id	��	String(32)	1217752501201407033233368018	΢��֧��������
				//�̻�������	out_trade_no	��	String(32)	1212321211201407033568112322	�̻�ϵͳ�Ķ����ţ�������һ�¡�
				//�̼����ݰ�	attach	��	String(128)	123456	�̼����ݰ���ԭ������
				//֧�����ʱ��	time_end	��	String(14)	20141030133525	֧�����ʱ�䣬��ʽΪyyyyMMddHHmmss����2009��12��25��9��10��10���ʾΪ20091225091010���������ʱ�����
				if(map.get("result_code").equals("SUCCESS")){
					//////////////////////////////////����ҵ��/////////////////////////////////////
					String out_trade_no=map.get("out_trade_no");//�̻�������
					System.out.println("�̻�������"+out_trade_no);
					String total_fee=map.get("total_fee");//�������
					System.out.println("�������"+total_fee);
					/////////////////////////////////////////////////////////////////////////////
					//��֤�����Ƿ���� ���õ�֧���ķ���
					Double payMoney=service.getpayMoneyBypayidentifying(out_trade_no);
					if(payMoney==null){
						return "fail";
					}
					//�õ�userid
					String userid=service.getvalparkchargeByuserid(out_trade_no);
					System.out.println("΢�ŵõ�userid"+userid);
					//ת���������
					double total_feel=Double.valueOf(total_fee);
					//�ж�֧������Ƿ�����Ҫ֧�����һ��
					if(payMoney*100!=total_feel){
						return "fail";
					}
					try{
						if(!service.updateparkchargeweiixn(out_trade_no,payMoney,userid)){
							return "fail";
						}
					} catch (Exception e) {
						log.error("΢��֧���쳣");
						return "fail";
					}
					/////////////////////////////////////////////////////////////////////////////
					outRes=new WeixinResult("SUCCESS","ǩ����֤�ɹ���");
				}else{
					Log.getInstance().debug("΢��֧��֪ͨ��������룺"+map.get("err_code")+"�������������"+map.get("err_code_des"));
					outRes=new WeixinResult("SUCCESS","ҵ������֤Ϊʧ�ܣ�");
				}
			}else{
				//��֤ǩ��ʧ�ܣ�
				Log.getInstance().error("ǩ����֤ʧ�ܣ�");
				outRes=new WeixinResult("FAIL","ǩ����֤ʧ�ܣ�");
			}
			System.out.println(WeixinUtil.toXml(outRes));
			//��Ӧ΢���Ƿ���ܳɹ�
			return WeixinUtil.toXml(outRes);
		}
}

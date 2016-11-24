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
 * 支付
 * 作者：weimeilayer@163.com
 * 时间：2016-10-14
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
	//回调拒绝订单
	@RequestMapping("callBackRefuce")
	public String callBackRefuce(String jobName){
		//退钱
		try {
			if(service.callBackRefuce(jobName)){
				System.out.println("退钱成功！");
				return "SUCCESS";
			}
		} catch (Exception e) {
			e.printStackTrace();
			//进行下一步计时
			try {
				Jobclient.addJob(jobName, 120,"/pay/callBackRefuce.do", "咳咳", 2, JobConfig.getJobName_orderRefused());
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error("JOB连接失败..");
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
	//余额支付
	@RequestMapping("balancePay")
	public ResultDto balancePay(String orderNum,String userid){
		if(orderNum==null||userid==null){
			return new ResultDto(30045,"参数不能为空哟！");
		}
		//验证订单是否存在 并得到支付的费用
		Double countMoney=service.getCountMoneyByOrderNum(orderNum);
		if(countMoney==null){
			return new ResultDto(30046,"找不到该订单哟！");
		}
		// 根据用户Id查询剩余的余额
		Double availableBalance=service.getAvailableBalanceByuserid(userid);
		//验证钱包的价格是否大于等于需要支付的费用
		if(availableBalance<countMoney){
			return new ResultDto(2049,"您的余额不足！");
		}
		//减去余额
		//修改订单状态
		//插入交易记录
		try {
			if(!service.updateOrder(orderNum, countMoney, userid)){
				return new ResultDto(30050,"支付失败！");
			}else{
				//取消支付倒计时
				Jobclient.removeJob(orderNum, JobConfig.getJobName_orderCancel());
				//验证是否是个人车位
				//如果是个人车位添加倒计时
				if(service.valOrderTypeByOrderNum(orderNum)==1){//是个人车位
					System.out.println("个人车位计时开始");
					//添加计时根据订单
					Jobclient.addJob(orderNum,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
				}else{//停车场
					Integer automatic =service.valParkAutomaticOrder(orderNum);
					if(automatic==null||automatic!=1){//是停车场手动接单
						System.out.println("停车场车位计时开始");
						//添加计时
						Jobclient.addJob(orderNum,300,"/pay/callBackRefuce.do", "计时",5, JobConfig.getJobName_orderRefused());
					}else{
						//自动接单
						//修改订单的状态 已完成
						service.updateOrderStateByOrderNum(orderNum,1);
					}
					//如果是停车场查询验证是否是最自动接单  不是自动接单就添加计时
				}
				return new ResultDto(200,"支付成功！");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return new ResultDto(30050,"支付失败！");
		}
	}
	//支付宝支付
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
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//订单金额
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数
		if(AlipayNotify.verify(params)){
			//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				return "fail";
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				//注意
				//验证订单是否存在 并得到支付的费用
				Double countMoney=service.getCountMoneyByOrderNum(out_trade_no);
				System.out.println("支付宝得到支付的费用"+countMoney);
				if(countMoney==null){
					return "fail";
				}
				//得到userid
				String userid=service.valParkOrderByuserid(out_trade_no);
				System.out.println("支付宝得到userid"+userid);
				//转换金额类型
				double total_feel=Double.valueOf(total_fee);
				//判断支付金额是否与需要支付金额一样
				if(countMoney!=total_feel){
					return "fail";
				}
				try{
					if(!service.updateOrderalipay(out_trade_no,countMoney,userid)){
						return "fail";
					}else{
						//取消支付倒计时
						Jobclient.removeJob(out_trade_no, JobConfig.getJobName_orderCancel());
						//验证是否是个人车位
						//如果是个人车位添加倒计时
						if(service.valOrderTypeByOrderNum(out_trade_no)==1){//是个人车位
							System.out.println("个人车位计时开始");
							//添加计时根据订单
							Jobclient.addJob(out_trade_no,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
						}else{
							//停车场
							Integer automatic =service.valParkAutomaticOrder(out_trade_no);
							if(automatic==null||automatic!=1){//是停车场手动接单
								System.out.println("停车场车位计时开始");
								//添加计时
								Jobclient.addJob(out_trade_no,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
							}else{
								//自动接单
								//修改订单的状态 已完成
								service.updateOrderStateByOrderNum(out_trade_no,1);//0等待 1表示 已完成2拒绝
							}
						}
					}
				} catch (Exception e) {
					log.error("支付异常");
					return "fail";
				}
				//如果是停车场查询验证是否是最自动接单  不是自动接单就添加计时
				//如果有做过处理，不执行商户的业务程序
				//注意：
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}
			return "success";//请不要修改或删除
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			return "fail";
		}
	}
	/**
	 * 微信支付
	 * @return
	 * pay/weixinNotice.do
	 */
	@ResponseBody 
	@RequestMapping(value="weixinNotice",method=RequestMethod.POST)
	public String wxNotice(){
		//接受传过来的信息 并转换成键值对的集合
		Map<String, String> map;
		map = WeixinUtil.xmlToMap(request);
		String weixinSign=map.get("sign");
		String mySign = createSign(map);
		WeixinResult outRes=null;
			//验证签名
			if(weixinSign.equals(mySign)){//验证成功
				////////////////////////////////////可能需要的参数///////////////////////////////////////////
				//业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
				//错误代码	err_code	否	String(32)	SYSTEMERROR	错误返回的信息描述
				//总金额	total_fee	是	Int	100	订单总金额，单位为分
				//现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
				//微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
				//商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统的订单号，与请求一致。
				//商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
				//支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
				if(map.get("result_code").equals("SUCCESS")){
					//////////////////////////////////处理业务/////////////////////////////////////
					String out_trade_no=map.get("out_trade_no");//商户订单号
					System.out.println("商户订单号"+out_trade_no);
					String total_fee=map.get("total_fee");//订单金额
					System.out.println("订单金额"+total_fee);
					/////////////////////////////////////////////////////////////////////////////
					//验证订单是否存在 并得到支付的费用
					Double countMoney=service.getCountMoneyByOrderNum(out_trade_no);
					System.out.println("微信得到支付的费用"+countMoney);
					if(countMoney==null){
						return "fail";
					}
					//得到userid
					String userid=service.valParkOrderByuserid(out_trade_no);
					System.out.println("微信userid"+userid);
					//转换金额类型
					double total_feel=Double.valueOf(total_fee);
					//判断支付金额是否与需要支付金额一样
					if(countMoney*100!=total_feel){
						System.out.println(countMoney*100+"验证金额");
						return "fail";
					}
					try{
						//微信修改订单状态
						if(!service.updateOrderweiixn(out_trade_no,countMoney,userid)){
							return "fail";
						}else{
							//取消支付倒计时
							Jobclient.removeJob(out_trade_no, JobConfig.getJobName_orderCancel());
							//验证是否是个人车位
							//如果是个人车位添加倒计时
							if(service.valOrderTypeByOrderNum(out_trade_no)==1){//是个人车位
								System.out.println("个人车位计时开始");
								//添加计时根据订单
								Jobclient.addJob(out_trade_no,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
							}else{
								//停车场
								Integer automatic =service.valParkAutomaticOrder(out_trade_no);
								if(automatic==null||automatic!=1){//是停车场手动接单
									System.out.println("停车场车位计时开始");
									//添加计时
									Jobclient.addJob(out_trade_no,300, "/pay/callBackRefuce.do", "", 5, JobConfig.getJobName_orderRefused());
								}else{
									//自动接单
									//修改订单的状态 已完成
									service.updateOrderStateByOrderNum(out_trade_no,1);//0等待 1表示 已完成2拒绝
								}
							}
						}
					} catch (Exception e) {
						log.error("支付异常");
						return "fail";
					}
					/////////////////////////////////////////////////////////////////////////////
					System.out.println(out_trade_no);
					outRes=new WeixinResult("SUCCESS","签名验证成功！");
				}
				else{
					Log.getInstance().debug("微信支付通知：错误代码："+map.get("err_code")+"错误代码描述："+map.get("err_code_des"));
					outRes=new WeixinResult("SUCCESS","业务结果验证为失败！");
				}
			}
			else{
				//验证签名失败！
				Log.getInstance().error("签名验证失败！");
				outRes=new WeixinResult("FAIL","签名验证失败！");
			}
			System.out.println(WeixinUtil.toXml(outRes));
			//相应微信是否接受成功
			return WeixinUtil.toXml(outRes);
		}
		
	@SuppressWarnings("rawtypes")
	public static String createSign(Map<String,String> parameters){ 	
		StringBuffer sb = new StringBuffer(); 		
		Set es = parameters.entrySet();
		//所有参与传参的参数按照accsii排序（升序） 		
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
	 * 余额支付停车缴费
	 * @param payidentifying
	 * @param userid
	 * @return
	 */
	@RequestMapping("balancePaycost")
	public ResultDto balancePaycost(String payidentifying,String userid){
		if(payidentifying==null||userid==null){
			return new ResultDto(30045,"参数不能为空哟！");
		}
		//验证订单是否存在 并得到支付的费用
		Double payMoney=service.getpayMoneyBypayidentifying(payidentifying);
		if(payMoney==null){
			return new ResultDto(2036,"找不到该订单哟！");
		}
		// 根据用户Id查询剩余的余额
		Double availableBalance=service.getAvailableBalanceByuserid(userid);
		//验证钱包的价格是否大于等于需要支付的费用
		if(availableBalance<payMoney){
			return new ResultDto(2049,"您的余额不足！");
		}
		try {
			if(!service.updateparkcharge(payidentifying, payMoney,userid)){
				return new ResultDto(30050,"支付失败！");
			}
			return new ResultDto(200,"支付成功！");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return new ResultDto(3050,"支付失败！");
		}
	}
	/**
	 * pay/alipayPaycost.do
	 * 支付宝支付
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
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//订单金额
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数
		if(AlipayNotify.verify(params)){
			//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				return "fail";
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				//注意
				//验证订单是否存在 并得到支付的费用
				Double payMoney=service.getpayMoneyBypayidentifying(out_trade_no);
				if(payMoney==null){
					return "fail";
				}
				//得到userid
				String userid=service.getvalparkchargeByuserid(out_trade_no);
				System.out.println("支付宝得到userid"+userid);
				//转换金额类型
				double total_feel=Double.valueOf(total_fee);
				//判断支付金额是否与需要支付金额一样
				if(payMoney!=total_feel){
					return "fail";
				}
				try{
					if(!service.updateparkchargealipay(out_trade_no, payMoney, userid)){
						return "fail";
					}
				} catch (Exception e) {
					log.error("支付宝支付异常");
					return "fail";
				}
				//如果是停车场查询验证是否是最自动接单  不是自动接单就添加计时
				//如果有做过处理，不执行商户的业务程序
				//注意：
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}
			return "success";//请不要修改或删除
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			return "fail";
		}
	}
	/**
	 * 微信支付
	 * @return
	 * pay/weixinPaycost.do
	 */
	@ResponseBody 
	@RequestMapping(value="weixinPaycost",method=RequestMethod.POST)
	public String wxNoticePaycost(){
		//接受传过来的信息 并转换成键值对的集合
		Map<String, String> map;
		map = WeixinUtil.xmlToMap(request);
		String weixinSign=map.get("sign");
		String mySign = createSign(map);
		WeixinResult outRes=null;
			//验证签名
			if(weixinSign.equals(mySign)){//验证成功
				////////////////////////////////////可能需要的参数///////////////////////////////////////////
				//业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
				//错误代码	err_code	否	String(32)	SYSTEMERROR	错误返回的信息描述
				//总金额	total_fee	是	Int	100	订单总金额，单位为分
				//现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
				//微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
				//商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统的订单号，与请求一致。
				//商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
				//支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
				if(map.get("result_code").equals("SUCCESS")){
					//////////////////////////////////处理业务/////////////////////////////////////
					String out_trade_no=map.get("out_trade_no");//商户订单号
					System.out.println("商户订单号"+out_trade_no);
					String total_fee=map.get("total_fee");//订单金额
					System.out.println("订单金额"+total_fee);
					/////////////////////////////////////////////////////////////////////////////
					//验证订单是否存在 并得到支付的费用
					Double payMoney=service.getpayMoneyBypayidentifying(out_trade_no);
					if(payMoney==null){
						return "fail";
					}
					//得到userid
					String userid=service.getvalparkchargeByuserid(out_trade_no);
					System.out.println("微信得到userid"+userid);
					//转换金额类型
					double total_feel=Double.valueOf(total_fee);
					//判断支付金额是否与需要支付金额一样
					if(payMoney*100!=total_feel){
						return "fail";
					}
					try{
						if(!service.updateparkchargeweiixn(out_trade_no,payMoney,userid)){
							return "fail";
						}
					} catch (Exception e) {
						log.error("微信支付异常");
						return "fail";
					}
					/////////////////////////////////////////////////////////////////////////////
					outRes=new WeixinResult("SUCCESS","签名验证成功！");
				}else{
					Log.getInstance().debug("微信支付通知：错误代码："+map.get("err_code")+"错误代码描述："+map.get("err_code_des"));
					outRes=new WeixinResult("SUCCESS","业务结果验证为失败！");
				}
			}else{
				//验证签名失败！
				Log.getInstance().error("签名验证失败！");
				outRes=new WeixinResult("FAIL","签名验证失败！");
			}
			System.out.println(WeixinUtil.toXml(outRes));
			//相应微信是否接受成功
			return WeixinUtil.toXml(outRes);
		}
}

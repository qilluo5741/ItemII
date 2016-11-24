package com.sharebo.controller;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sharebo.config.GaodeConfig;
import com.sharebo.entity.Dayrules;
import com.sharebo.entity.LongrentInfo;
import com.sharebo.entity.MyCarPort;
import com.sharebo.entity.Pager;
import com.sharebo.entity.ResDate;
import com.sharebo.entity.ResMonth;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.MyCarportDto;
import com.sharebo.entity.dto.ParkDetailsDto;
import com.sharebo.entity.dto.ParkDto;
import com.sharebo.entity.dto.ParkingDto;
import com.sharebo.entity.dto.ResultDto;
import com.sharebo.service.FileService;
import com.sharebo.service.ParkService;
import com.sharebo.util.HttpClient;
import com.sharebo.util.security.Base64;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * ���ڳ�λ
 * @author niewei
 */
@Controller
@RequestMapping("/park")
public class ParkController {
	private static final Logger log=LoggerFactory.getLogger(ParkController.class);
	@Autowired
	private ParkService service;
	@Autowired
	private FileService fileService;
	//ɾ����λ   ͨ����λid  �޸�isdetete �ֶ�����Ϊ1
	@RequestMapping("deleteCarPort")
	@ResponseBody
	public ResultDto deleteCarPort(String carportId) {
		//�����޸ĸߵ·���
		try {
			if (carportId == null) {
				return new ResultDto(3006, "�����������Ϊ��");
			}
			if (service.updateCarPort(carportId)) {
				return new ResultDto(200, "ɾ���ɹ�");
			}
		} catch (Exception e) {
			log.error("ͨ����λid�޸�isdetete �ֶ�����Ϊ1�����쳣��");
			System.out.println("ͨ����λid�޸�isdetete �ֶ�����Ϊ1�����쳣��");
		}
		return new ResultDto(3007, "ɾ��ʧ��");
	}
	// �޸�һ���Сʱʱ��
	@RequestMapping("updateHours")
	public @ResponseBody ResultDto updateHours(String day) {
		if (day == null) {
			return new ResultDto(1004, "�������Ϸ���");
		}
		Dayrules d = null;
		// ����json
		try {
			d = (Dayrules) JSONObject.toBean(JSONObject.fromObject(day),
					Dayrules.class);
		} catch (Exception e) {
			return new ResultDto(1004, "�������Ϸ���");
		}
		// �ж�dayid�Ƿ�Ϊ��
		if (d.getDayid() == null) {
			// ��ѯ�Ƿ���� ���ھ�˵�Ѿ��������
			if (service.isExistxByDayrules(d)) {
				return new ResultDto(10015, "�Ѿ��������Ѿ����ڣ���ע���Ƿ��޸ģ�����dayid�Ƿ�����");
			}
			d.setDayid(UUID.randomUUID().toString().replace("-", ""));
			// ִ�����
			if (service.addDayrules(d)) {
				return new ResultDto(200, "����ɹ���");
			}
			return new ResultDto(10016, "����ʧ�ܣ�");
		} else {
			// ִ���޸�
			if (service.updateDayByHours(d)) {
				return new ResultDto(200, "�޸ĳɹ���");
			}
			return new ResultDto(10016, "����ʧ�ܣ�");
		}
	}
	//��ѯһ���ʱ��
	@RequestMapping("getDay")
	public @ResponseBody ResultDto getDay(String day,String carportId){
		if(day==null||carportId==null){
			return new ResultDto(1004,"�������Ϸ���");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(day));
		} catch (ParseException e) {
			return new ResultDto(10014,"ʱ���ʽ����");
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("day",sdf.format(calendarCountDays.getTime()));
		map.put("parkid",carportId);
		try {
			return new ResultDto(200,service.getDayrules(map));
		} catch (Exception e) {
			log.error("��ѯһ���ʱ������쳣��");
			System.out.println("��ѯһ���ʱ������쳣��");
			return new ResultDto(200,e.getMessage());
		}
	}
	
	//��ѯ�·�ʱ��
	/**
	 *��������   ��λid 
	 * �ù�����Ϊ���� ��ѯ��һ���µ�ʱ������ж��Ƿ���Ϊ���ã�
	 * ͨ����λid��ѯ���ڹ��򣨵��첻���ã�����֤�����Ƿ���ã�
	 * */
	@RequestMapping("getMonth")
	public @ResponseBody ResultDto getMonth(String strDate,String carportId){
		if(strDate==null||carportId==null){
			return new ResultDto(1004,"�������Ϸ���");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar calendarCountDays = new GregorianCalendar(); 
		try {
			calendarCountDays.setTime(sdf.parse(strDate));
		} catch (ParseException e) {
			return new ResultDto(10014,"ʱ���ʽ����");
		}
		int theYear=calendarCountDays.get(Calendar.YEAR);//����ʱ�����
		int theMonth=calendarCountDays.get(Calendar.MONTH)+1;//����ʱ����·�
		int days=calendarCountDays.getActualMaximum(Calendar.DAY_OF_MONTH);//�����µ�������
		//***************************************************************************//
		Calendar calendar= Calendar.getInstance(); 
		int thisYear=calendar.get(Calendar.YEAR);///��������
		int thisMonth=calendar.get(Calendar.MONTH)+1;///������·�
		int thisDay=calendar.get(Calendar.DATE);//�����Ǽ���
		ResMonth rm=new ResMonth();//��������
		//������·�
		//System.out.println(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth));
		System.out.println("�������ݣ�"+theYear+"���ڵ���ݣ�"+thisYear);
		//�жϴ����·��Ƿ���ڵ��ڵ�ǰ�·�
		if(theYear<thisYear){
			return new ResultDto(1058,"��ݲ���С�ڽ���Ӵ~");
		}else{
			if(theYear==thisYear)
			if(theMonth<thisMonth){
				return new ResultDto(1058,"�·ݲ���С�ڵ�ǰ��Ӵ~");
			}
		}
		/******************************************/
		//��ѯ����λid�͵�ǰ�µĽ���ʱ��
		Map<String,String> disableDayMap=new HashMap<String, String>();
		disableDayMap.put("parkid",carportId);
		disableDayMap.put("begin",theYear+"-"+theMonth+"-"+"01");
		disableDayMap.put("end",(theMonth==12?theYear+1:theYear)+"-"+(theMonth==12?1:theMonth+1)+"-"+"01");
		//���õ�����
		List<String> disableDayList=service.selectParkMonthAllDayRlus(disableDayMap);
		for (String string : disableDayList) {
			disableDayMap.put(string, string);//���뼯��
		}
		//�������õ����ڣ�δ���õ����ڣ�
		List<String> disableNotDayList=service.selectParkMonthAllNotDayRlus(disableDayMap);
		for (String string : disableNotDayList) {
			disableDayMap.put(string+"_not", string);//���뼯��
		}
		Weekrules weekr=service.weekTime(carportId);
		/******************************************/
		rm.setYear(theYear);
		rm.setMonth(theMonth);
		List<ResDate> rdList=new ArrayList<ResDate>();
		//ѭ��һ���µ�����
		for(int i=1;i<=days;i++){
			//����ǵ�ǰ�� С�ڵ���Ͳ�������
				if(i<thisDay&&theYear==thisYear&&thisMonth==theMonth){
					rdList.add(new ResDate(i,false,false));
				}else{
					//��ǰ�·�  ����ʱ��
					if(disableDayMap.get(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i))==null){
						//------û����Ե������ڽ���
						if(disableDayMap.get(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i)+"_not")==null){
							//û����Ե�����������
							//��֤�û������ڹ���
							boolean isd=WeekIsdisable(weekr,DayToWeek(theYear+"-"+(theMonth<10?"0"+theMonth:theMonth)+"-"+(i<10?"0"+i:i)));
							rdList.add(new ResDate(i,isd,true));
						}else{
							rdList.add(new ResDate(i,true,true));
						}
					}else{
						//���ڴ������
						rdList.add(new ResDate(i,false,true));
					}
				}
		}
		rm.setRd(rdList);
		/******************************************/
		return new ResultDto(200,rm);
	}
	//�������ڼ������ڹ��� �����Ƿ����
		public static boolean WeekIsdisable(Weekrules w,int week){
			String begin=null;
			String end=null;
			if(week==1){//������
				begin=w.getSunday_begin();end=w.getSunday_end();
			}else if(week==2){//����һ
				begin=w.getMonday_begin();end=w.getMonday_end() ;
			}else if(week==3){//���ڶ�
				begin=w.getTuesday_begin();end=w.getTuesday_end() ;
			}else if(week==4){//������
				begin=w.getWednesday_begin();end=w.getWednesday_end(); 
			}else if(week==5){//������
				begin=w.getThursday_begin();end=w.getThursday_end();
			}else if(week==6){//������
				begin=w.getFriday_begin();end=w.getFriday_end();
			}else if(week==7){//������
				begin=w.getSaturday_begin();end=w.getSaturday_end();
			}
			if(end.equals("23:59:00")){
				return true;
			}
			//System.out.println(end);
			begin=begin.substring(0,2);
			
			end=end.substring(0,2);
			if(begin.equals(end)){//���ʱ����
				return false;
			}
			return true;
		}
	// �������ڣ��������ڼ�
	public static int DayToWeek(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendarCountDays = new GregorianCalendar();
			try {
				calendarCountDays.setTime(sdf.parse(day));
			} catch (java.text.ParseException e) {
				e.printStackTrace();
				log.error("ת��ʱ��ʧ��!");				
			}
		// �õ����������������ڼ�
		return calendarCountDays.get(Calendar.DAY_OF_WEEK);
	}

	//ɾ���ҵĳ�λ  ����CarportId�޸�isdeleteΪ2
	
	//�޸���ʱ��λ��Ϣ
	@RequestMapping("updateTemporaryParkSpaces")
	@ResponseBody
	public ResultDto updateTemporaryParkSpaces(MyCarPort cp){
		//�޸�
		if(service.updateTemporaryParkSpaces(cp)==1){
			return new ResultDto(200,"�޸ĳɹ���");
		}
		return new ResultDto(1013,"�޸�ʧ�ܣ�");
	}
	//�޸ĳ�����Ϣ
	@RequestMapping("updateLongCarRental")
	@ResponseBody
	public ResultDto updateLongCarRental(LongrentInfo rent){
		//��֤����
		if(rent.getCarport()==null||rent.getCarport().getUserid()==null){
			return new ResultDto(1012,"param is null");
		}
		if(rent.getCarport().getCarportId()==null){
			return new ResultDto(1012,"CarportId nou is null");
		}
		if(rent.getTimetype()!=0&&rent.getTimetype()!=1){
			return new ResultDto(1012,"����ʱ����������");
		}
		if(rent.getCarport().getHousId()==null){
			return new ResultDto(1013,"�벻Ҫ����С��Ӵ��");
		}
		//���
		if(rent.getCarport().getDetailedAddress()==null){
			return new ResultDto(1014,"û����ڵ�ַ��������ô�ҳ�λ�أ�");
		}
		//��λ��� ����  �շ� 
		if(rent.getCarport().getSerialnumber()==null||rent.getCarport().getHousType()==null||rent.getCarport().getMoney()==null){
			return new ResultDto(1015,"��λ��� ����  �շ�  ����Ϊ�գ�");
		}
		//��ʼʱ��ͽ���ʱ��
		if(rent.getBeginmonthTime()==null||rent.getEndmonthTime()==null){
			return new ResultDto(1015,"��ʼʱ��ͽ���ʱ�䲻��Ϊ�գ�");
		}
		//��Ӫ����
		if(rent.getTimetype()==1){
			if(rent.getBegindayTime()==null||rent.getEnddayTime()==null){
				return new ResultDto(1015,"��ȫ����⣬�����ÿ�ʼ����ʱ�䣡");
			}
		}
		try {
			if(service.ParkByLongCarRental(rent)){
				return new ResultDto(200,"�޸ĳɹ���");		
			}
		} catch (Exception e) {
			log.error("�޸ĳ�λʧ�ܣ�ʧ�ܵ�ԭ������ǣ�{}",e.getMessage());
		}
		return new ResultDto(200,"�޸�ʧ�ܣ����Ժ����ԣ�");	
	}
	//���ݳ�λid�޸����ڹ���
	@RequestMapping("updateWeekRules")
	@ResponseBody
	public ResultDto updateWeekRules(String week){
		Weekrules wd=null;
		try {
			//����json
			wd=(Weekrules) JSONObject.toBean(JSONObject.fromObject(week), Weekrules.class);
			//�����޸�
			if(service.updateWeekrulesByWeekid(wd)<=0)//�޸�
			return  new ResultDto(1024,"�޸�ʧ�ܣ�");
		} catch (Exception e) {
			log.error("���������쳣�������ԣ�");
		}
		return  new ResultDto(200,"�޸ĳɹ���");
	}

	// ���ݳ�λid�޸����ڹ���2
	@RequestMapping("updateWeekRules2")
	@ResponseBody
	public ResultDto updateWeekRules2(Weekrules wd) {
		System.out.println(wd);
		try {
			// �����޸�
			if (service.updateWeekrulesByWeekid(wd) <= 0)// �޸�
				return new ResultDto(1024, "�޸�ʧ�ܣ�");
		} catch (Exception e) {
			log.error("���������쳣�������ԣ�");
		}
		return new ResultDto(200, "�޸ĳɹ���");
	}
	//���ݳ�λid��ѯ���ڹ���ʱ��
	@RequestMapping("getWeekrules")
	public @ResponseBody ResultDto getWeekrules(String carportId){
		//��֤����
		if(carportId==null){
			return new ResultDto(1021,"�������Ϸ���");
		}
		//��ѯ
		return new ResultDto(200,service.getWeekrulesByCarportId(carportId));
	}
	//������ʱ��λ
	@RequestMapping("issuedTemporaryParkSpaces")
	public @ResponseBody ResultDto issuedTemporaryParkSpaces(MyCarPort cp,String invimg){
		System.out.println(cp.toString());
		//��֤����
		if(cp.getUserid()==null||cp.getHousId()==null||cp.getDetailedAddress()==null||cp.getSerialnumber()==null||cp.getHousType()==null||cp.getMoney()==null || cp.getInvidualimg()==null || invimg==null){
			return new ResultDto(1021,"�������Ϸ���");
		}
		//�ж�ͼƬ�Ĵ�ֵ
		if(cp.getInvidualimg().equals("1") && invimg.equals("1")){
			cp.setInvidualimg("1"+","+"1");
		}else if(!cp.getInvidualimg().equals("1") && invimg.equals("1")){
			byte[] by = Base64.decode(cp.getInvidualimg());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			cp.setInvidualimg(headimg+","+"1");
		}else{
			byte[] by = Base64.decode(cp.getInvidualimg());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			byte[] by1 = Base64.decode(invimg);
			ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
			String headimgs = this.fileService.uploadImage(bais1);
			cp.setInvidualimg(headimg+","+headimgs);
		}
		//��֤��λ�Ƿ����
		// ��֤��λ����Ƿ����
		if (service.valserialnumberIsExists(cp.getSerialnumber(),cp.getUserid(),cp.getHousId()) > 0) {
			return new ResultDto(2016, "�ó�λ����Ѿ����ڣ������ظ���ӣ�");
		}
		String parkid=null;
		//��ӳ�λ
		try {
			parkid=service.insertIssuedTemporaryParkSpaces(cp);
			if(parkid==null){
				return new ResultDto(1022,"������λʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("������ʱͣ��λ�쳣��"+e.getMessage());
		}
		Map<String, String> map=new HashMap<String, String>();
		map.put("carportId", parkid);
		return new ResultDto(200,"������λ�ɹ���",map);
	}
	//���ݳ�λid��ѯ��������
	@RequestMapping("getLongCarRental")
	@ResponseBody
	public ResultDto getLongCarRental(String carportId){
		return new ResultDto(200,service.getLongrentInfo(carportId));
	}
	//�������⳵λ
	@RequestMapping("releaseLongCarRental")
	public @ResponseBody ResultDto releaseLongCarRental(LongrentInfo rent,String parkimg){
		System.out.println(rent.getCarport().getParkremark());
		if(rent.getPersonalphoto()==null || parkimg==null){
			return new ResultDto(1011,"�������Ϊ�գ�");
		}
		//�ж�ͼƬ�Ĵ�ֵ
		if(rent.getPersonalphoto().equals("1") && parkimg.equals("1")){
			rent.setPersonalphoto("1"+","+"1");
		}else if(!rent.getPersonalphoto().equals("1") && parkimg.equals("1")){
			byte[] by = Base64.decode(rent.getPersonalphoto());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			rent.setPersonalphoto(headimg+","+"1");
		}else{
			byte[] by = Base64.decode(rent.getPersonalphoto());
			ByteArrayInputStream bais = new ByteArrayInputStream(by);
			String headimg = this.fileService.uploadImage(bais);
			byte[] by1 = Base64.decode(parkimg);
			ByteArrayInputStream bais1 = new ByteArrayInputStream(by1);
			String headimgs = this.fileService.uploadImage(bais1);
			rent.setPersonalphoto(headimg+","+headimgs);
		}
		//��֤����
		if(rent.getCarport().getUserid()==null){
			return new ResultDto(1012,"userid nou is null");
		}
		if(rent.getCarport().getHousId()==null){
			return new ResultDto(1013,"�벻Ҫ����С��Ӵ��");
		}
		//���
		if(rent.getCarport().getDetailedAddress()==null){
			return new ResultDto(1014,"û����ڵ�ַ��������ô�ҳ�λ�أ�");
		}
		//��λ��� ����  �շ� 
		if(rent.getCarport().getSerialnumber()==null||rent.getCarport().getHousType()==null||rent.getCarport().getMoney()==null){
			return new ResultDto(1015,"��λ��� ����  �շ�  ����Ϊ�գ�");
		}
		//��ʼʱ��ͽ���ʱ��
		if(rent.getBeginmonthTime()==null||rent.getEndmonthTime()==null){
			return new ResultDto(1015,"��ʼʱ��ͽ���ʱ�䲻��Ϊ�գ�");
		}
		//��Ӫ����
		if(rent.getTimetype()==1){
			if(rent.getBegindayTime()==null||rent.getEnddayTime()==null){
				return new ResultDto(1015,"��ȫ����⣬�����ÿ�ʼ����ʱ�䣡");
			}
		}
		//��֤��λ����Ƿ����
		if(service.valserialnumberIsExists(rent.getCarport().getSerialnumber(), rent.getCarport().getUserid(),rent.getCarport().getHousId())>0){
			return new ResultDto(2016,"�ó�λ����Ѿ����ڣ������ظ���ӣ�");
		}
		try {
			if(service.insertParkByLongCarRental(rent)){
				return new ResultDto(200,"�����ɹ���");		
			}
		} catch (Exception e) {
			log.error("������λʧ�ܣ�ʧ�ܵ�ԭ������ǣ�{}",e.getMessage());
		}
		return new ResultDto(200,"����ʧ�ܣ����Ժ����ԣ�");		
	}
	/**
	 * ͣ���������ѯ
	 * @param parkId
	 * @return
	 * park/getParkInfo.do?parkId=889394938746241519&userid=889390526942412800
	 */
	@ResponseBody
	@RequestMapping("getParkInfo")
	public ResultDto getParkInfo(String parkId,String userid){
		try {
			if(parkId==null||userid==null){
				return new ResultDto(3006,"�������Ϊ��");
			}
			if(service.selectParkInfo(parkId, userid)!=null){
				return new ResultDto(200,"�ɹ�",service.selectParkInfo(parkId, userid));
			}
		} catch (Exception e) {
			log.error("ͣ���������ѯ��ѯ�쳣��");
			System.out.println("ͣ���������ѯ��ѯ�쳣��");
		}
		return new ResultDto(3010,"�������ݣ�");
	}
	/**
	 * ����parkId��ѯͣ������Ϣ
	 * @param parkId
	 * @return
	 * park/getParkDtoInfo.do?parkId=889394938746242682
	 */
	@ResponseBody
	@RequestMapping("getParkDtoInfo")
	public ResultDto getParkDtoInfo(String parkId){
		try {
			if(parkId==null){
				return new ResultDto(3006,"�������Ϊ��");
			}
			if(service.selectParkDtoInfo(parkId)!=null){
				return new ResultDto(200,"�ɹ�",service.selectParkDtoInfo(parkId));
			}
		} catch (Exception e) {
			log.error("����parkId��ѯͣ������Ϣ�쳣��");
			System.out.println("����parkId��ѯͣ������Ϣ�쳣��");
		}
		return new ResultDto(3010,"�������ݣ�");
	}
	/**
	 * ����housId��ѯͣ��λ
	 * @param housId
	 * @return
	 * park/getMyCarportInfo.do?housId=24534087746194205
	 */
	@ResponseBody
	@RequestMapping("getMyCarportInfo")
	public ResultDto getMyCarportInfo(String housId){
		try {
			if(housId==null){
				return new ResultDto(3006,"�������Ϊ��");
			}
			if(service.selectMyCarportInfo(housId)!=null){
				List<MyCarportDto> list=service.selectMyCarportInfo(housId);
				for (MyCarportDto myCarportDto : list) {
					myCarportDto.setPhone(myCarportDto.getPhone().substring(0, 3)+"****"+myCarportDto.getPhone().substring(7, 11));
				}
				return new ResultDto(200,"��ѯ�ɹ�",list);
			}
		} catch (Exception e) {
			log.error("����housId��ѯͣ��λ�쳣��");
			System.out.println("����housId��ѯͣ��λ��Ϣ�쳣��");
		}
		return new ResultDto(3010,"�������ݣ�");
	}
	/**
	 * ���г�λ����
	 * @param housId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * park/getinformation.do?housId=24534087746194210&pageIndex=1&pageSize=10
	 */
	@ResponseBody
	@RequestMapping(value="getinformation",method=RequestMethod.POST)
	public ResultDto getloadAllparkinginformation(String housId,Integer pageIndex,Integer pageSize){
		try {
			if(housId==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			Pager<MyCarportDto> pager=new Pager<MyCarportDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			map.put("housId", housId);
			List<MyCarportDto> list=service.getloadAllCarport(map);
			for (MyCarportDto myCarportDto : list) {
				myCarportDto.setPhone(myCarportDto.getPhone().substring(0, 3)+"****"+myCarportDto.getPhone().substring(7, 11));
			}
			pager.setList(list);
			//��ѯ����
			pager.setTotalNumber(service.getloadAllCarportCount(housId));
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"�ɹ�",pager);
		} catch (Exception e) {
			log.error("���г�λ��������쳣��");
			System.out.println("���г�λ��������쳣��");
		}
		return new ResultDto(2001,"�������ݣ�");
	}
	/**
	 * park/getaddpark.do?userid=889390526942412800&parkName=1&parkAddress=1&parkbusinessType=1&entryAddress=1&beBegin=2016-03-16 10:07:56&beEnd=2016-03-16 10:07:56&parkSum=1&parkType=1&parkprice=1&chargeType=1&reservationfee=1&reservationfeedivide=1&parkingisdivided=1&iswhether=1&parkstate=1&feeModel=1
	 * @param userid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getaddpark",method=RequestMethod.POST)
	public ResultDto Carauthentication(String userid,String parkName,String parkAddress,String parkbusinessType,String entryAddress,String beBegin,String beEnd,Integer parkSum,String parkType,Double parkprice,Integer chargeType,Double reservationfee,Double reservationfeedivide,Double parkingisdivided,Integer iswhether,Integer parkstate, String feeModel){
		try {
			if(userid==null || parkName==null || parkAddress==null|| parkbusinessType==null || entryAddress==null|| beBegin==null|| beEnd==null|| parkSum==null|| parkType==null|| parkprice==null|| chargeType==null|| reservationfee==null|| reservationfeedivide==null|| parkingisdivided==null|| iswhether==null|| parkstate==null|| feeModel==null){
				return new ResultDto(2002,"�������Ϊ�գ�");	
			}
			ParkingDto p=new ParkingDto();
			p.setParkName(parkName);
			p.setParkAddress(parkAddress);
			p.setParkbusinessType(parkbusinessType);
			p.setEntryAddress(entryAddress);
			p.setBeBegin(beBegin);
			p.setBeEnd(beEnd);
			p.setParkSum(parkSum);
			p.setParkType(parkType);
			p.setParkprice(parkprice);
			p.setChargeType(chargeType);
			p.setReservationfee(reservationfeedivide);
			p.setReservationfeedivide(reservationfeedivide);
			p.setParkingisdivided(parkingisdivided);
			p.setCash(parkprice);
			p.setIswhether(iswhether);
			p.setPartner("xb");
			p.setUserid(userid);
			p.setCreatetime(new Date());
			p.setFeeModel(feeModel);
			if(service.addParking(p)){
				return new ResultDto(200,"��ӳɹ���");
			}
		} catch (Exception e) {
			log.error("��Ӳ����쳣��");
			System.out.println("��Ӳ����쳣��");
			return new ResultDto(2001,"����ò���е����⣡���Ժ������Կ���");
		}
		return new ResultDto(2001,"�����쳣��");
	}
	//����ͣ��-��������
	@RequestMapping("getLongRentList")
	@ResponseBody
	public ResultDto getLongRentList(String center){
		String url="http://yuntuapi.amap.com/datasearch/around?"
				+ "key="+GaodeConfig.getAppKey()
				+ "&tableid="+GaodeConfig.getTableid()
				+ "&center="+center
				+"&filter=hostState:0"
				+"&limit=100"
				+"&radius=3000";
		//�������
		String res = HttpClient.get(url);
		// �õ������JSON����
		String datas = JSONObject.fromObject(res).getString("datas");
		JsonConfig jc=new JsonConfig();
		@SuppressWarnings("unchecked")
		List<ParkDetailsDto> list = JSONArray.toList(JSONArray.fromObject(datas), new ParkDetailsDto(),jc);
		List<ParkDetailsDto> resList=null;
		if(list.size()==0){
			return new ResultDto(200,"���޳�λ",resList);
		}
		// ���ֵ
		Map<String, ParkDetailsDto> parkMap = new HashMap<String, ParkDetailsDto>();
		// ƴ��where ����
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			ParkDetailsDto pd = list.get(i);
			if(pd.getHousId()==null){
				continue;
			}
			parkMap.put(pd.getHousId(), pd);//�����ʱֵ
			sb.append("'"+pd.getHousId()+"'");//�������where
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		//��ѯ���ݿ�
		resList=service.getLongRentByHousId(sb.toString());
		for (int i = 0; i < resList.size(); i++) {
			ParkDetailsDto p=resList.get(i);
			//ȡ������ֵ
			ParkDetailsDto mapPd=parkMap.get(p.getHousId());
			//��������ֵ
			p.set_address(mapPd.get_address());
			p.set_name(mapPd.get_name());
			p.set_distance(mapPd.get_distance());
			if(p!=null){
				resList.set(i,p);
			}
		}
		return new ResultDto(200,resList);
	}
	//����ͣ��  �۸�����
	@ResponseBody
	@RequestMapping("getLongRentListOrderByMoney")
	public ResultDto getLongRentListOrderByMoney(Integer pageIndex,Integer pageSize){
		try {
    		if(pageIndex==null||pageSize==null){
				return new ResultDto(3006, "����������Ϸ�");
			}
    		Pager<ParkDetailsDto> pager=new Pager<ParkDetailsDto>();
			pager.setPageIndex(pageIndex);
			pager.setPageSize(pageSize);
			Map<String, Object> map=new HashMap<String, Object>();
			//���ÿ�ʼ
			int pageBegin=(pageIndex-1)*pageSize;
			map.put("pageBegin", pageBegin);
			map.put("pageSize", pageSize);
			pager.setList(service.getLongRentInfo(map));
			//��ѯ����
			pager.setTotalNumber(service.getLongRentInfoCount());
			pager.setTotalPages();//������ҳ��
			return new ResultDto(200,"�ɹ�",pager);
		} catch (Exception e) {
			log.error("��������쳣��");
			System.out.println("��������쳣��");
		}
    	return new ResultDto(3002,"�����쳣");
    }
	
	/**
	 * park/getParkdistance.do?parkId=889394938746241770&center=121.429449,31.137204
	 * ����parkId��ѯͣ������Ϣ
	 * @param parkId
	 * @param center
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("getParkdistance")
	public ResultDto getParkdistance(String parkId,String center){
		try {
			if(parkId==null){
				return new ResultDto(3006,"�������Ϊ��");
			}
			String url="http://yuntuapi.amap.com/datasearch/around?"
					+ "key="+GaodeConfig.getAppKey()
					+ "&tableid="+GaodeConfig.getTableid()
					+ "&center="+center;
			//�������
			System.out.println(HttpClient.get(url));
			String datas=JSONObject.fromObject(HttpClient.get(url)).getString("datas");
			JsonConfig jsonc=new JsonConfig();
			List<ParkDetailsDto> list = JSONArray.toList(JSONArray.fromObject(datas),new ParkDetailsDto(),jsonc);
			ParkDto park=service.selectParkDtoInfo(parkId);
			if(park!=null){
				for (int i = 0; i < list.size(); i++) {
					park.set_distance(list.get(i).get_distance());
				}
				return new ResultDto(200,"�ɹ�",park);
			}
		} catch (Exception e) {
			log.error("����parkId��ѯͣ������Ϣ�쳣��");
			System.out.println("����parkId��ѯͣ������Ϣ�쳣��");
		}
		return new ResultDto(3010,"�������ݣ�");
	}
	/**
	 * hostid
	 * @param housId
	 * @return
	 * park/getCarportInfo.do?housId=889394938746241532&center=121.429449,31.137204
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("getCarportInfo")
	public ResultDto getCarportInfo(String housId,String center){
		try {
			if(housId==null){
				return new ResultDto(3006,"�������Ϊ��");
			}
			String url="http://yuntuapi.amap.com/datasearch/around?"
					+ "key="+GaodeConfig.getAppKey()
					+ "&tableid="+GaodeConfig.getTableid()
					+ "&center="+center;
			//�������
			String datas=JSONObject.fromObject(HttpClient.get(url)).getString("datas");
			JsonConfig jsonc=new JsonConfig();
			List<ParkDetailsDto> parkddt = JSONArray.toList(JSONArray.fromObject(datas),new ParkDetailsDto(),jsonc);
			if(service.selectMyCarportInfo(housId)!=null){
				List<MyCarportDto> list=service.selectMyCarportInfo(housId);
				for (MyCarportDto myCarportDto : list) {
					myCarportDto.setPhone(myCarportDto.getPhone().substring(0, 3)+"****"+myCarportDto.getPhone().substring(7, 11));
				}
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("list",list);
				try {
					map.put("_distance",parkddt.get(0).get_distance());
				} catch (Exception e) {
					map.put("_distance",null);
				}
				return new ResultDto(200,"��ѯ�ɹ�",map);
			}
		} catch (Exception e) {
			log.error("����housId��ѯͣ��λ�쳣��");
			System.out.println("����housId��ѯͣ��λ��Ϣ�쳣��");
		}
		return new ResultDto(3010,"�������ݣ�");
	}
}


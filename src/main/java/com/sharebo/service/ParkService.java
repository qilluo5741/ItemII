package com.sharebo.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Dayrules;
import com.sharebo.entity.LongrentInfo;
import com.sharebo.entity.MyCarPort;
import com.sharebo.entity.Weekrules;
import com.sharebo.entity.dto.LongCarDetailsDto;
import com.sharebo.entity.dto.MyCarportDto;
import com.sharebo.entity.dto.ParkDetailsDto;
import com.sharebo.entity.dto.ParkDto;
import com.sharebo.entity.dto.ParkInfoDto;
import com.sharebo.entity.dto.ParkingDto;

/**
 * 
 * @author niewei
 *
 */
public interface ParkService {
	// ������ʱ��λ
	public String insertIssuedTemporaryParkSpaces(MyCarPort cp)
			throws Exception;

	// �������⳵λ
	public boolean insertParkByLongCarRental(LongrentInfo longrent)
			throws Exception;

	// ��֤��λ����Ƿ����
	public int valserialnumberIsExists(String serialnumber, String userid,
			String housId);

	// ��ѯ���ڹ���
	public Weekrules getWeekrulesByCarportId(
			@Param("carportId") String carportId);

	// �޸ĳ�λ���ڹ���ʱ��
	public int updateWeekrulesByWeekid(Weekrules week);

	// ��ѯ��������
	public LongCarDetailsDto getLongrentInfo(
			@Param("carportId") String carportId);

	// �޸ĳ�λ
	public boolean ParkByLongCarRental(LongrentInfo longrent) throws Exception;

	// �޸���ʱ��λ
	public int updateTemporaryParkSpaces(MyCarPort cp);

	// ��ѯ���õ�����
	public List<String> selectParkMonthAllDayRlus(Map<String, String> map);

	// ��ѯһ����λ����ȫ���ǽ��õ�ʱ��
	public List<String> selectParkMonthAllNotDayRlus(Map<String, String> map);

	// ͨ����λid�õ���λ���ڹ���
	public Weekrules weekTime(String carportId);

	// ��ѯһ��Ŀ���ʱ��
	public Dayrules getDayrules(Map<String, String> map) throws Exception;

	// ѯ���ڹ����Ƿ����
	public boolean isExistxByDayrules(com.sharebo.entity.Dayrules d);

	// ���һ��ʱ��
	public boolean addDayrules(com.sharebo.entity.Dayrules d);

	// �޸ĵ���ʱ�� ͨ��parkid h�� ����
	public boolean updateDayByHours(com.sharebo.entity.Dayrules d);

	/**
	 * ��ѯͣ��������
	 * 
	 * @param parkId
	 * @return
	 */
	public ParkInfoDto selectParkInfo(String parkId,String userid);

	// ɾ����λ ͨ����λid
	public boolean updateCarPort(@Param("carportId") String carportId);

	/**
	 * ����parkId��ѯͣ������Ϣ
	 * 
	 * @param parkId
	 * @return
	 */
	public ParkDto selectParkDtoInfo(@Param("parkId") String parkId);

	/**
	 * ����housId��ѯͣ��λ
	 * 
	 * @param housId
	 * @return
	 */
	public List<MyCarportDto> selectMyCarportInfo(@Param("housId") String housId);

	/**
	 * ���ͣ����
	 * 
	 * @param Parking
	 * @return
	 */
	public boolean addParking(ParkingDto park) throws Exception;

	/**
	 * ����ͣ����id�޸ߵ�id
	 * 
	 * @param parkid
	 * @param gaodeId
	 * @return
	 */
	public int updateparkidbygaodeid(String gaodeId, String parkId);

	/**
	 * ͬ���ߵ�id
	 * 
	 * @return
	 */
	public List<ParkingDto> selectSynchronizeGaodeId();

	// ��ѯ������Ϣ-����housid
	public List<ParkDetailsDto> getLongRentByHousId(String housIds);
	//����ͣ�������۸�����
	public List<ParkDetailsDto> getLongRentInfo(Map<String, Object> map);
	//�õ�����ͣ�������۸����ȵ�����
	public int getLongRentInfoCount();
	
	/**
	 * ��ҳ��ѯС����λ
	 * @param map
	 * @return
	 */
	public List<MyCarportDto> getloadAllCarport(Map<String, Object> map);
	public Integer getloadAllCarportCount(@Param("housId")String housId);
}

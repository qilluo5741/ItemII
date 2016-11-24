package com.sharebo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sharebo.entity.Dayrules;
import com.sharebo.entity.OrderTime;
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
public interface ParkMapper {
	// ��ӳ�����Ϣ
	public int addLongrentInfo(com.sharebo.entity.LongrentInfo longrent);

	// ��ӳ�λ��Ϣ
	public int addMyCartInfo(com.sharebo.entity.MyCarPort mycar);

	// ��֤��λ����Ƿ����
	public int valserialnumberIsExists(
			@Param("serialnumber") String serialnumber,
			@Param("userid") String userid, @Param("housId") String housId);

	// ��ʼ��������ڹ���
	public int addweekrules(@Param("carportId") String carportId);

	// ��ѯ���ڹ���
	public Weekrules getWeekrulesByCarportId(
			@Param("carportId") String carportId);

	// �޸ĳ�λ���ڹ���ʱ��
	public int updateWeekrulesByWeekid(Weekrules week);

	// ��ѯ��������
	public LongCarDetailsDto getLongrentInfo(@Param("carportId") String carportId);

	// �޸ĳ�������
	public int updateLongrent(com.sharebo.entity.LongrentInfo longrent);

	// �޸ĳ�λ��Ϣ
	public int updateMycarPort(com.sharebo.entity.MyCarPort mycar);

	// ��ѯһ����λ����ȫ�����õ�ʱ��
	public List<String> selectParkMonthAllDayRlus(Map<String, String> map);

	// ��ѯһ����λ����ȫ���ǽ��õ�ʱ��
	public List<String> selectparkMenthAllNotDayRlus(Map<String, String> map);

	// ��ѯ���ڹ���
	public Weekrules weekTime(String carportId);

	// ��ѯһ��Ŀ���ʱ��
	public Dayrules getDayrules(Map<String, String> map);

	/**
	 * ��ѯͣ��������
	 * 
	 * @param parkId
	 * @return
	 */
	public ParkInfoDto selectParkInfo(@Param("parkId") String parkId,
			@Param("userid") String userid);

	// ѯ���ڹ����Ƿ����
	public int isExistxByDayrules(com.sharebo.entity.Dayrules d);

	// ���һ��ʱ��
	public boolean addDayrules(com.sharebo.entity.Dayrules d);

	// �޸ĵ���ʱ�� ͨ��parkid h�� ����
	public boolean updateDayByHours(com.sharebo.entity.Dayrules d);

	// ��ѯ����ʱ��� ͨ������ �ͳ�λId
	public List<OrderTime> getOrdertime(Map<String, String> map);

	// ɾ����λ ͨ����λid
	public int updateCarPort(@Param("carportId") String carportId);

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

	// �鵱ǰС����λ����
	public Integer valHousIsExixtsCarPortByCarportId(String carportId);

	// ��ѯ�ߵµ�ͼID
	public String seleclGaodeIdBycarportId(String carportId);

	/**
	 * ���ͣ����
	 * 
	 * @param Parking
	 * @return
	 */
	public int addParking(ParkingDto park);

	/**
	 * ����ͣ����id�޸ߵ�id
	 * 
	 * @param parkid
	 * @param gaodeId
	 * @return
	 */
	public int updateparkidbygaodeid(@Param("gaodeId") String gaodeId,
			@Param("parkId") String parkId);

	/**
	 * ͬ���ߵ�id
	 * 
	 * @return
	 */
	public List<ParkingDto> selectSynchronizeGaodeId();

	// ��ѯ������Ϣ-����housid
	public List<ParkDetailsDto> getLongRentByHousId(
			@Param("housIds") String housIds);

	// ����ͣ�������۸�����
	public List<ParkDetailsDto> getLongRentInfo(Map<String, Object> map);

	// �õ�����ͣ�������۸����ȵ�����
	public int getLongRentInfoCount();

	/**
	 * ��ҳ��ѯС����λ
	 * 
	 * @param map
	 * @return
	 */
	public List<MyCarportDto> getloadAllCarport(Map<String, Object> map);

	public Integer getloadAllCarportCount(@Param("housId") String housId);

	// ͨ��С�����ֲ�ѯ�ߵ�Id
	public com.sharebo.entity.dto.HousDto2 GetGaodeIdByHousName(
			@Param("housName") String housName);

	// ���С��
	public int addHous(@Param("housId") String housId,
			@Param("housName") String housName,
			@Param("housAddress") String housAddress,
			@Param("gaodeIds") String gaodeIds);

	// ͨ���ߵ�iD��ѯͣ���۸�
	public Double getParkPricebygaodeId(@Param("gaodeId") String gaodeId);

	// ��ѯС����λ����ͼۺ�ͣ������ͼ۸� ͨ��carportId
	public Map<String, Object> getParkMoneyAndCapPortMoneyBycarportId(
			@Param("carportId") String carportId);

	// ��ѯ��С����ͼ۸�
	public Double getHousMinMoneyByCarportId(
			@Param("carportId") String carportId);

	// ��ѯͣ������ͼ۸�
	public Double getParkPriceByCarPortId(@Param("carportId") String carportId);
}

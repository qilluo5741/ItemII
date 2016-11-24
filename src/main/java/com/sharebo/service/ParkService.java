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
	// 发布临时车位
	public String insertIssuedTemporaryParkSpaces(MyCarPort cp)
			throws Exception;

	// 发布长租车位
	public boolean insertParkByLongCarRental(LongrentInfo longrent)
			throws Exception;

	// 验证车位编号是否存在
	public int valserialnumberIsExists(String serialnumber, String userid,
			String housId);

	// 查询星期规则
	public Weekrules getWeekrulesByCarportId(
			@Param("carportId") String carportId);

	// 修改车位星期规则时间
	public int updateWeekrulesByWeekid(Weekrules week);

	// 查询长租详情
	public LongCarDetailsDto getLongrentInfo(
			@Param("carportId") String carportId);

	// 修改车位
	public boolean ParkByLongCarRental(LongrentInfo longrent) throws Exception;

	// 修改临时车位
	public int updateTemporaryParkSpaces(MyCarPort cp);

	// 查询禁用的日期
	public List<String> selectParkMonthAllDayRlus(Map<String, String> map);

	// 查询一个车位当月全部非禁用的时间
	public List<String> selectParkMonthAllNotDayRlus(Map<String, String> map);

	// 通过车位id得到车位星期规则
	public Weekrules weekTime(String carportId);

	// 查询一天的可用时间
	public Dayrules getDayrules(Map<String, String> map) throws Exception;

	// 询日期规则是否存在
	public boolean isExistxByDayrules(com.sharebo.entity.Dayrules d);

	// 添加一天时间
	public boolean addDayrules(com.sharebo.entity.Dayrules d);

	// 修改当天时间 通过parkid h和 日期
	public boolean updateDayByHours(com.sharebo.entity.Dayrules d);

	/**
	 * 查询停车场详情
	 * 
	 * @param parkId
	 * @return
	 */
	public ParkInfoDto selectParkInfo(String parkId,String userid);

	// 删除车位 通过车位id
	public boolean updateCarPort(@Param("carportId") String carportId);

	/**
	 * 根据parkId查询停车场信息
	 * 
	 * @param parkId
	 * @return
	 */
	public ParkDto selectParkDtoInfo(@Param("parkId") String parkId);

	/**
	 * 根据housId查询停车位
	 * 
	 * @param housId
	 * @return
	 */
	public List<MyCarportDto> selectMyCarportInfo(@Param("housId") String housId);

	/**
	 * 添加停车场
	 * 
	 * @param Parking
	 * @return
	 */
	public boolean addParking(ParkingDto park) throws Exception;

	/**
	 * 根据停车场id修高德id
	 * 
	 * @param parkid
	 * @param gaodeId
	 * @return
	 */
	public int updateparkidbygaodeid(String gaodeId, String parkId);

	/**
	 * 同步高德id
	 * 
	 * @return
	 */
	public List<ParkingDto> selectSynchronizeGaodeId();

	// 查询长租信息-根据housid
	public List<ParkDetailsDto> getLongRentByHousId(String housIds);
	//包月停车——价格优先
	public List<ParkDetailsDto> getLongRentInfo(Map<String, Object> map);
	//得到包月停车——价格优先的总数
	public int getLongRentInfoCount();
	
	/**
	 * 分页查询小区车位
	 * @param map
	 * @return
	 */
	public List<MyCarportDto> getloadAllCarport(Map<String, Object> map);
	public Integer getloadAllCarportCount(@Param("housId")String housId);
}

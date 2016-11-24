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
	// 添加长租信息
	public int addLongrentInfo(com.sharebo.entity.LongrentInfo longrent);

	// 添加车位信息
	public int addMyCartInfo(com.sharebo.entity.MyCarPort mycar);

	// 验证车位编号是否存在
	public int valserialnumberIsExists(
			@Param("serialnumber") String serialnumber,
			@Param("userid") String userid, @Param("housId") String housId);

	// 初始化添加星期规则
	public int addweekrules(@Param("carportId") String carportId);

	// 查询星期规则
	public Weekrules getWeekrulesByCarportId(
			@Param("carportId") String carportId);

	// 修改车位星期规则时间
	public int updateWeekrulesByWeekid(Weekrules week);

	// 查询长租详情
	public LongCarDetailsDto getLongrentInfo(@Param("carportId") String carportId);

	// 修改长租详情
	public int updateLongrent(com.sharebo.entity.LongrentInfo longrent);

	// 修改车位信息
	public int updateMycarPort(com.sharebo.entity.MyCarPort mycar);

	// 查询一个车位当月全部禁用的时间
	public List<String> selectParkMonthAllDayRlus(Map<String, String> map);

	// 查询一个车位当月全部非禁用的时间
	public List<String> selectparkMenthAllNotDayRlus(Map<String, String> map);

	// 查询星期规则
	public Weekrules weekTime(String carportId);

	// 查询一天的可用时间
	public Dayrules getDayrules(Map<String, String> map);

	/**
	 * 查询停车场详情
	 * 
	 * @param parkId
	 * @return
	 */
	public ParkInfoDto selectParkInfo(@Param("parkId") String parkId,
			@Param("userid") String userid);

	// 询日期规则是否存在
	public int isExistxByDayrules(com.sharebo.entity.Dayrules d);

	// 添加一天时间
	public boolean addDayrules(com.sharebo.entity.Dayrules d);

	// 修改当天时间 通过parkid h和 日期
	public boolean updateDayByHours(com.sharebo.entity.Dayrules d);

	// 查询订单时间表 通过日期 和车位Id
	public List<OrderTime> getOrdertime(Map<String, String> map);

	// 删除车位 通过车位id
	public int updateCarPort(@Param("carportId") String carportId);

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

	// 查当前小区车位总数
	public Integer valHousIsExixtsCarPortByCarportId(String carportId);

	// 查询高德地图ID
	public String seleclGaodeIdBycarportId(String carportId);

	/**
	 * 添加停车场
	 * 
	 * @param Parking
	 * @return
	 */
	public int addParking(ParkingDto park);

	/**
	 * 根据停车场id修高德id
	 * 
	 * @param parkid
	 * @param gaodeId
	 * @return
	 */
	public int updateparkidbygaodeid(@Param("gaodeId") String gaodeId,
			@Param("parkId") String parkId);

	/**
	 * 同步高德id
	 * 
	 * @return
	 */
	public List<ParkingDto> selectSynchronizeGaodeId();

	// 查询长租信息-根据housid
	public List<ParkDetailsDto> getLongRentByHousId(
			@Param("housIds") String housIds);

	// 包月停车——价格优先
	public List<ParkDetailsDto> getLongRentInfo(Map<String, Object> map);

	// 得到包月停车——价格优先的总数
	public int getLongRentInfoCount();

	/**
	 * 分页查询小区车位
	 * 
	 * @param map
	 * @return
	 */
	public List<MyCarportDto> getloadAllCarport(Map<String, Object> map);

	public Integer getloadAllCarportCount(@Param("housId") String housId);

	// 通过小区名字查询高德Id
	public com.sharebo.entity.dto.HousDto2 GetGaodeIdByHousName(
			@Param("housName") String housName);

	// 添加小区
	public int addHous(@Param("housId") String housId,
			@Param("housName") String housName,
			@Param("housAddress") String housAddress,
			@Param("gaodeIds") String gaodeIds);

	// 通过高德iD查询停车价格
	public Double getParkPricebygaodeId(@Param("gaodeId") String gaodeId);

	// 查询小区车位的最低价和停车场最低价格 通过carportId
	public Map<String, Object> getParkMoneyAndCapPortMoneyBycarportId(
			@Param("carportId") String carportId);

	// 查询出小区最低价格
	public Double getHousMinMoneyByCarportId(
			@Param("carportId") String carportId);

	// 查询停车场最低价格
	public Double getParkPriceByCarPortId(@Param("carportId") String carportId);
}

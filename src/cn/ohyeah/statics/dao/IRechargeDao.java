package cn.ohyeah.statics.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IRechargeDao {
	
	/**
	 * 获取该时间段内单款游戏某一天的充值总额
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getSubscribeDetail(String startTime, String endTime);
	
	/**
	 * 获取该时间段内单款游戏充值总额
	 * @param startTime
	 * @param endTime
	 * @return Map
	 */
	public Map<String,Integer> getAllSubscribeDetail(String startTime, String endTime);

	/**
	 * 获得该时间段内的行数
	 * @param startTime
	 * @param endTime
	 * @throws ParseException 
	 */
	public List<String> getTableRows(String startTime, String endTime);
	
	/**
	 * 获取已经充值的游戏(列数)
	 * @param startTime
	 * @param endTime
	 * @return 
	 */
	public Map<String,String> getTableColumns(String startTime, String endTime);
	
}

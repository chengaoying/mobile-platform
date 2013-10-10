package cn.ohyeah.mobile.platform.dao;

import java.util.List;

import cn.ohyeah.mobile.platform.model.UserPrizeRecord;

public interface IUserPrizeRecordDao {

	/**
	 * 保存用户中奖记录
	 * @param record
	 */
	public void save(UserPrizeRecord record);
	
	/**
	 * 读取用户中奖记录列表
	 * @param userid
	 */
	public List<UserPrizeRecord> loadRecordList(int userid);
	
	/**
	 * 读取一个用户中奖记录
	 * @param id
	 * @return
	 */
	public UserPrizeRecord load(int id);
	
	/**
	 * 删除一条记录
	 * @param id
	 */
	public void delete(int id);
}

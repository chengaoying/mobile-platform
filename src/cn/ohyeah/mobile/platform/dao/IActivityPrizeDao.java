package cn.ohyeah.mobile.platform.dao;

import java.util.List;

import cn.ohyeah.mobile.platform.model.ActivityPrize;

public interface IActivityPrizeDao {

	/**
	 * 添加一条活动
	 * @param activityPrize
	 */
	public void save(ActivityPrize activityPrize);
	
	/**
	 * 读取一条活动
	 * @param id
	 * @return
	 */
	public ActivityPrize load(int id);
	
	/**
	 * 删除一条活动
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 更改活动
	 * @param activityPrize
	 */
	public void update(ActivityPrize activityPrize);
	
	/**
	 * 查询活动列表
	 * @return
	 */
	public List<ActivityPrize> queryList();
}

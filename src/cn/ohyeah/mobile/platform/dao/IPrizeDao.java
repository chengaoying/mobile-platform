package cn.ohyeah.mobile.platform.dao;

import java.util.List;

import cn.ohyeah.mobile.platform.model.Prize;

public interface IPrizeDao {
	
	/**
	 * 保存
	 * @param resource
	 */
	public void save(Prize resource);
	
	/**
	 * 通过资源id删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 通过资源id加载一个资源
	 * @param id
	 * @return
	 */
	public Prize loadById(int id);
	
	/**
	 * 通过资源名加载一个资源
	 * @param name
	 * @return
	 */
	public Prize loadByName(String name);
	
	/**
	 * 加载某一活动下所有的奖品
	 * @param type
	 * @return
	 */
	public List<Prize> loadByActivityid(int activityid);
}

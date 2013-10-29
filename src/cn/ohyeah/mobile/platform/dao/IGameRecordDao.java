package cn.ohyeah.mobile.platform.dao;

import java.util.List;

import cn.ohyeah.mobile.platform.model.GameRecord;

public interface IGameRecordDao {
	
	/**
	 * 保存一条游戏记录
	 * @param gamerecord
	 */
	public void save(GameRecord gamerecord);
	
	/**
	 * 通过记录id查找一条记录
	 * @param id
	 * @return
	 */
	public GameRecord loadById(int id);
	
	/**
	 * 通过游戏记录索引查找记录
	 * @param recordindex
	 * @return
	 */
	public GameRecord loadByRecordindex(int recordindex);
	
	/**
	 * 查找记录列表
	 * @return
	 */
	public List<GameRecord> queryList(int productid);

}

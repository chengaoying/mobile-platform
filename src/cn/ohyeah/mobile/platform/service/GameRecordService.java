package cn.ohyeah.mobile.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.mobile.platform.dao.IGameRecordDao;
import cn.ohyeah.mobile.platform.model.GameRecord;

@Service("gameRecordService")
public class GameRecordService {

	@Autowired
	@Qualifier("gameRecordDao")
	private IGameRecordDao gameRecordDao;
	
	public void save(GameRecord gameRecord){
		gameRecordDao.save(gameRecord);
	}
	
	public GameRecord loadById(int id){
		return gameRecordDao.loadById(id);
	}
	
	public GameRecord loadByRecordindex(int recordindex){
		return gameRecordDao.loadByRecordindex(recordindex);
	}
	
	public List<GameRecord> queryList(int productid){
		return gameRecordDao.queryList(productid);
	}
}

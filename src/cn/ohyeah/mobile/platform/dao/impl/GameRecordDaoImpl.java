package cn.ohyeah.mobile.platform.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ohyeah.mobile.platform.dao.IGameRecordDao;
import cn.ohyeah.mobile.platform.model.GameRecord;

@Repository("gameRecordDao")
public class GameRecordDaoImpl  extends BaseDaoImpl implements IGameRecordDao{

	@Override
	public void save(GameRecord gamerecord) {
		GameRecord gr = loadByRecordindex(gamerecord.getRecordindex());
		if(gr != null){
			gr.setData(gamerecord.getData());
			gr.setTime(gamerecord.getTime());
			getSession().update(gr);
		}else{
			getSession().save(gamerecord);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameRecord loadById(int id) {
		String sql = "from GameRecord where id=:id";
		Query query = getSession().createQuery(sql);
		query.setParameter("id", id); 
		List<GameRecord> list = query.list();
		if(list.size() > 0){
			GameRecord record = (GameRecord)query.list().get(0);
			return record;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameRecord loadByRecordindex(int recordindex) {
		String sql = "from GameRecord where recordindex=:recordindex";
		Query query = getSession().createQuery(sql);
		query.setParameter("recordindex", recordindex); 
		List<GameRecord> list = query.list();
		if(list.size() > 0){
			GameRecord record = (GameRecord)query.list().get(0);
			return record;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GameRecord> queryList(int productid) {
		String sql = "from GameRecord where productid=:productid";
		Query query = getSession().createQuery(sql);
		query.setParameter("productid", productid); 
		List<GameRecord> list = query.list();
		return list;
	} 

}

package cn.ohyeah.mobile.platform.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.ohyeah.mobile.platform.dao.IPrizeDao;
import cn.ohyeah.mobile.platform.model.Prize;

@Repository("prizeDao")
public class PrizeDaoImpl extends BaseDaoImpl implements IPrizeDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public void save(Prize prize) {
		getSession().save(prize);
		LOGGER.info("save user");
	}

	@Override
	public void delete(int id) {
		getSession().delete(loadById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Prize loadById(int id) {
		String sql = "from Prize where prizeid=:prizeid";
		Query query = getSession().createQuery(sql);
		query.setParameter("prizeid", id);
		List<Prize> list = query.list();
		if(list.size() > 0){
			Prize resource = (Prize)query.list().get(0);
			return resource;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Prize> loadByActivityid(int activityid) {
		String sql = "from Prize where activityid=:activityid";
		Query query = getSession().createQuery(sql);
		query.setParameter("activityid", activityid);
		List<Prize> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Prize loadByName(String name) {
		String sql = "from Prize where name=:name";
		Query query = getSession().createQuery(sql);
		query.setParameter("name", name);
		List<Prize> list = query.list();
		if(list.size() > 0){
			Prize resource = (Prize)query.list().get(0);
			return resource;
		}
		return null;
	}

}

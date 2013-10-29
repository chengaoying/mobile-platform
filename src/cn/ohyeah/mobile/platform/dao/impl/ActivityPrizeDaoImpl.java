package cn.ohyeah.mobile.platform.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ohyeah.mobile.platform.dao.IActivityPrizeDao;
import cn.ohyeah.mobile.platform.model.ActivityPrize;

@Repository("activityPrizeDao")
public class ActivityPrizeDaoImpl extends BaseDaoImpl implements IActivityPrizeDao{

	@Override
	public void save(ActivityPrize activityPrize) {
		getSession().save(activityPrize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActivityPrize load(int id) {
		String sql = "from ActivityPrize where id=:id";
		Query query = getSession().createQuery(sql);
		query.setParameter("id", id); 
		List<ActivityPrize> list = query.list();
		if(list.size() > 0){
			ActivityPrize record = (ActivityPrize)query.list().get(0);
			return record;
		}
		return null;
	}

	@Override
	public void delete(int id) {
		getSession().delete(load(id));
	}

	@Override
	public void update(ActivityPrize activityPrize) {
		getSession().update(activityPrize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityPrize> queryList() {
		String sql = "from ActivityPrize";
		Query query = getSession().createQuery(sql);
		List<ActivityPrize> list = query.list();
		return list;
	}

}

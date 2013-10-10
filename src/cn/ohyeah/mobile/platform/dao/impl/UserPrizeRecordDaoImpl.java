package cn.ohyeah.mobile.platform.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ohyeah.mobile.platform.dao.IUserPrizeRecordDao;
import cn.ohyeah.mobile.platform.model.UserPrizeRecord;

@Repository("userPrizeRecordDao")
public class UserPrizeRecordDaoImpl extends BaseDaoImpl implements IUserPrizeRecordDao{

	@Override
	public void save(UserPrizeRecord record) {
		getSession().save(record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPrizeRecord> loadRecordList(int userid) {
		String sql = "from UserPrizeRecord where userid=:userid";
		Query query = getSession().createQuery(sql);
		query.setParameter("userid", userid);
		List<UserPrizeRecord> list = query.list();
		return list;
	}

	@Override
	public void delete(int id) {
		getSession().delete(load(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserPrizeRecord load(int id) {
		String sql = "from UserPrizeRecord where id=:id";
		Query query = getSession().createQuery(sql);
		query.setParameter("id", id); 
		List<UserPrizeRecord> list = query.list();
		if(list.size() > 0){
			UserPrizeRecord record = (UserPrizeRecord)query.list().get(0);
			return record;
		}
		return null;
	}

}

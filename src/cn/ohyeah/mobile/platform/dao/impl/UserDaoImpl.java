package cn.ohyeah.mobile.platform.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.ohyeah.mobile.platform.dao.IUserDao;
import cn.ohyeah.mobile.platform.model.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl  implements IUserDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public void save(User user) {
		getSession().save(user);
		LOGGER.info("save user");
	}


	@SuppressWarnings("unchecked")
	@Override
	public User loadUserByName(String name) {
		String sql = "from User where name=:name";
		Query query = getSession().createQuery(sql);
		query.setParameter("name", name);
		List<User> list = query.list();
		if(list.size() > 0){
			User user = (User)query.list().get(0);
			return user;
		}
		return null;
	}


	@Override
	public void delete(int userid) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(String name) {
		getSession().delete(loadUserByName(name));
	}


	@Override
	public void update(User user) {
		getSession().update(user);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryList() {
		String sql = "from User";
		Query query = getSession().createQuery(sql);
		List<User> list = query.list();
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public User loadUserById(int userid) {
		String sql = "from User where userid=:userid";
		Query query = getSession().createQuery(sql);
		query.setParameter("userid", userid);
		List<User> list = query.list();
		if(list.size() > 0){
			User user = (User)query.list().get(0);
			return user;
		}
		return null;
	}

}

package cn.ohyeah.statics.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.ohyeah.statics.dao.IUserDao;
import cn.ohyeah.statics.model.User;

@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl  implements IUserDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	@Override
	public void saveUser(User user) {
		getSession().save(user);
		LOGGER.info("save user");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAllUsers(int pageNo, int pageSize) {
		String sql = "from User";
		Query query = getSession().createQuery(sql);
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public void deleteUser(int id) {
		getSession().delete(queryUserById(id));
	}

	@Override
	public User queryUserById(int id) {
		String sql = "from User where id=:id";
		Query query = getSession().createQuery(sql);
		query.setParameter("id", id);
		User user = (User) query.list().get(0);
		return user;
	}

	@Override
	public User queryUserByName(String name) {
		String sql = "from User where name=:name";
		Query query = getSession().createQuery(sql);
		query.setParameter("name", name);
		if(query.list().size()>0){
			User user = (User) query.list().get(0);
			return user;
		}
		return null;
	}

	@Override
	public int queryTotalCounts() {
		String sql = "from User";
		Query query = getSession().createQuery(sql);
		return query.list().size();
	}

	@Override
	public void updateUser(User user) {
		getSession().update(user);
	}

}

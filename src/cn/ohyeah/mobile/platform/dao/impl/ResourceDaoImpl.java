package cn.ohyeah.mobile.platform.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.ohyeah.mobile.platform.dao.IResourceDao;
import cn.ohyeah.mobile.platform.model.Resource;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl implements IResourceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public void save(Resource resource) {
		getSession().save(resource);
		LOGGER.info("save user");
	}

	@Override
	public void delete(int id) {
		getSession().delete(loadById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Resource loadById(int id) {
		String sql = "from Resource where prizeid=:prizeid";
		Query query = getSession().createQuery(sql);
		query.setParameter("prizeid", id);
		List<Resource> list = query.list();
		if(list.size() > 0){
			Resource resource = (Resource)query.list().get(0);
			return resource;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> loadByType(int type) {
		String sql = "from Resource where type=:type";
		Query query = getSession().createQuery(sql);
		query.setParameter("type", type);
		List<Resource> list = query.list();
		return list;
	}

}

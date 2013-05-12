package cn.ohyeah.statics.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cn.ohyeah.statics.dao.IDataDicDao;
import cn.ohyeah.statics.model.DataDictionary;

@Repository("dataDicDao")
public class DataDicDaoImpl implements IDataDicDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataDicDaoImpl.class);
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DataDictionary> queryAllDataDic() {
		String sql = "from DataDictionary";
		Query q = getSession().createQuery(sql);
		LOGGER.info("query datadictionary===>");
		return q.list();
	}

}

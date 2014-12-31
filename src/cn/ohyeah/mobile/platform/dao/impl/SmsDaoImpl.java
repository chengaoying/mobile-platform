package cn.ohyeah.mobile.platform.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.ohyeah.mobile.platform.dao.ISmsDao;
import cn.ohyeah.mobile.platform.model.Sms;

@Repository("smsDao")
public class SmsDaoImpl extends BaseDaoImpl  implements ISmsDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmsDaoImpl.class);

	@Override
	public void save(Sms sms) {
		getSession().save(sms);
		LOGGER.info("save user");
	}
	

}

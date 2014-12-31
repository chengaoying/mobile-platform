package cn.ohyeah.mobile.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.mobile.platform.dao.ISmsDao;
import cn.ohyeah.mobile.platform.model.Sms;

@Service("smsService")
public class SmsService {

	private ISmsDao smsDao;

	@Autowired
	@Qualifier("smsDao")
	public void setSmsDao(ISmsDao smsDao) {
		this.smsDao = smsDao;
	}

	public void save(Sms sms){
		smsDao.save(sms);
	}
	
}

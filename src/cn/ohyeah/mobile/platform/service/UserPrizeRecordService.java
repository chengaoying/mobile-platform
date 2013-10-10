package cn.ohyeah.mobile.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.mobile.platform.dao.IUserPrizeRecordDao;
import cn.ohyeah.mobile.platform.model.UserPrizeRecord;

@Service("userPrizeRecordService")
public class UserPrizeRecordService {

	@Autowired
	@Qualifier("userPrizeRecordDao")
	private IUserPrizeRecordDao userPrizeRecordDao;

	
	public void save(UserPrizeRecord record){
		userPrizeRecordDao.save(record);
	}
	
	public UserPrizeRecord load(int id){
		return userPrizeRecordDao.load(id);
	}
	
	public List<UserPrizeRecord> loadRecordList(int userid){
		return userPrizeRecordDao.loadRecordList(userid);
	}
	
	public void delete(int id){
		userPrizeRecordDao.delete(id);
	}
	
	public void setUserPrizeRecordDao(IUserPrizeRecordDao userPrizeRecordDao) {
		this.userPrizeRecordDao = userPrizeRecordDao;
	}
}

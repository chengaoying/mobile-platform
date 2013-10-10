package cn.ohyeah.mobile.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.mobile.platform.dao.IActivityPrizeDao;
import cn.ohyeah.mobile.platform.model.ActivityPrize;

@Service("activityPrizeService")
public class ActivityPrizeService {

	@Autowired
	@Qualifier("activityPrizeDao")
	private IActivityPrizeDao activityPrizeDao;

	public void save(ActivityPrize activityPrize){
		activityPrizeDao.save(activityPrize);
	}
	
	public void delete(int id){
		activityPrizeDao.delete(id);
	}
	
	public ActivityPrize load(int id){
		return activityPrizeDao.load(id);
	}
	
	public void update(ActivityPrize activityPrize){
		activityPrizeDao.update(activityPrize);
	}
	
	public void setActivityPrizeDao(IActivityPrizeDao activityPrizeDao) {
		this.activityPrizeDao = activityPrizeDao;
	}
}

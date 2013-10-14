package cn.ohyeah.mobile.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.mobile.platform.dao.IPrizeDao;
import cn.ohyeah.mobile.platform.model.Prize;

@Service("prizeService")
public class PrizeService {

	@Autowired
	@Qualifier("prizeDao")
	private IPrizeDao prizeDao;

	public void save(Prize resource){
		prizeDao.save(resource);
	}
	
	public void delete(int id){
		prizeDao.delete(id);
	}
	
	public Prize loadById(int id){
		return prizeDao.loadById(id);
	}
	
	public Prize loadByName(String name){
		return prizeDao.loadByName(name);
	}
	
	public List<Prize> loadByActivityid(int activityid){
		return prizeDao.loadByActivityid(activityid);
	}
}

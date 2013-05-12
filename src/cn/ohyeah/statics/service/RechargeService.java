package cn.ohyeah.statics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cn.ohyeah.statics.dao.IRechargeDao;

@Repository("RechargeService")
public class RechargeService {

	private IRechargeDao rechargeDao;
	
	@Autowired
	@Qualifier("RechargeDao")
	public void setRechargeDao(IRechargeDao rechargeDao) {
		this.rechargeDao = rechargeDao;
	}
	
	public Map<String, String> getTableColumns(String startTime, String endTime) {
		return rechargeDao.getTableColumns(startTime, endTime);
	}

	public Map<String, Integer> getSubscribeDetail(String startTime,
			String endTime) {
		return rechargeDao.getSubscribeDetail(startTime, endTime);
	}

	public List<String> getTableRows(String startTime, String endTime) {
		return rechargeDao.getTableRows(startTime, endTime);
	}
	
	public Map<String, Integer> getAllSubscribeDetail(String startTime,
			String endTime) {
		return rechargeDao.getAllSubscribeDetail(startTime, endTime);
	}
}

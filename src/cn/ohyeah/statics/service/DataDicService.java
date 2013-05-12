package cn.ohyeah.statics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import cn.ohyeah.statics.dao.IDataDicDao;
import cn.ohyeah.statics.model.DataDictionary;

@Repository("dataDicservice")
public class DataDicService {

	private IDataDicDao dataDicDao;

	@Autowired
	@Qualifier("dataDicDao")
	public void setDao(IDataDicDao dataDicDao) {
		this.dataDicDao = dataDicDao;
	}
	
	public List<DataDictionary> queryAllDataDicService(){
		return dataDicDao.queryAllDataDic();
	}
}

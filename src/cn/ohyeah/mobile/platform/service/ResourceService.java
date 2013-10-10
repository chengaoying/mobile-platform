package cn.ohyeah.mobile.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.mobile.platform.dao.IResourceDao;
import cn.ohyeah.mobile.platform.model.Resource;

@Service("resourceService")
public class ResourceService {

	@Autowired
	@Qualifier("resourceDao")
	private IResourceDao resourceDao;

	public void save(Resource resource){
		resourceDao.save(resource);
	}
	
	public void delete(int id){
		resourceDao.delete(id);
	}
	
	public Resource loadById(int id){
		return resourceDao.loadById(id);
	}
	
	public List<Resource> loadByType(int type){
		return resourceDao.loadByType(type);
	}
	
	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
}

package cn.ohyeah.mobile.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.mobile.platform.dao.IUserDao;
import cn.ohyeah.mobile.platform.model.User;

@Service("userService")
public class UserService {

	private IUserDao userDao;

	@Autowired
	@Qualifier("userDao")
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void save(User user){
		userDao.save(user);
	}
	
	public User loadUserByName(String name){
		return userDao.loadUserByName(name);
	}
	
	public void delete(int id){
		//TODO
	}
	
	public void delete(String name){
		userDao.delete(name);
	}
	
	public void update(User user){
		userDao.update(user);
	}
	
	public List<User> queryList(){
		return userDao.queryList();
	}
	
	
	
	
	
	
	
	
	
	
}

package cn.ohyeah.statics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.statics.dao.IUserDao;
import cn.ohyeah.statics.model.User;

@Service("UserService")
public class UserService {

	private IUserDao userDao;

	@Autowired
	@Qualifier("UserDao")
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void saveUser(User user){
		userDao.saveUser(user);
	}
	
	public User loadUserById(int id){
		return userDao.queryUserById(id);
	}
	
	public User loadUserByName(String name){
		return userDao.queryUserByName(name);
	}
	
	public List<User> queryAllUser(int pageNo, int pageSize){
		return userDao.queryAllUsers( pageNo, pageSize);
	}
	
	public int queryCounts(){
		return userDao.queryTotalCounts();
	}
	
	public void deleteUser(int id){
		userDao.deleteUser(id);
	}
	
	public void updateUser(User user){
		userDao.updateUser(user);
	}
}

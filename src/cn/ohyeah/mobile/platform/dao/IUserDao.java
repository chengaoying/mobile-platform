package cn.ohyeah.mobile.platform.dao;

import java.util.List;

import cn.ohyeah.mobile.platform.model.User;

public interface IUserDao {
	/**
	 * 添加用户
	 * @param user
	 */
	public void save(User user);
	
	/**
	 * 通过用户名查询用户
	 * @param name
	 * @return
	 */
	public User loadUserByName(String name);
	
	/**
	 * 通过userid删除一个用户
	 * @param userid
	 */
	public void delete(int userid);
	
	/**
	 * 通过用户名删除一个用户
	 * @param name
	 */
	public void delete(String name);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void update(User user);
	
	/**
	 * 查询用户列表
	 * @return
	 */
	public List<User> queryList();
}

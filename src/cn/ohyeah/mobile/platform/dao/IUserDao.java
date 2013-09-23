package cn.ohyeah.mobile.platform.dao;

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
}

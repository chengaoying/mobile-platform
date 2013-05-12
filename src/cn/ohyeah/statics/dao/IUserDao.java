package cn.ohyeah.statics.dao;

import java.util.List;

import cn.ohyeah.statics.model.User;

public interface IUserDao {
	/**
	 * 添加用户
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * 分页查询用户
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<User> queryAllUsers(int pageNo, int pageSize);
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(int id);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 根据用户id查询用户
	 * @param id
	 * @return
	 */
	public User queryUserById(int id);
	
	/**
	 * 根据用户名查询用户
	 * @param name
	 * @return
	 */
	public User queryUserByName(String name);
	
	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryTotalCounts();
}

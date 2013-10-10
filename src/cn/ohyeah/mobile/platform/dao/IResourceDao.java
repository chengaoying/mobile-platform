package cn.ohyeah.mobile.platform.dao;

import java.util.List;

import cn.ohyeah.mobile.platform.model.Resource;

public interface IResourceDao {
	
	/**
	 * 保存
	 * @param resource
	 */
	public void save(Resource resource);
	
	/**
	 * 通过资源id删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 通过资源id加载一个资源
	 * @param id
	 * @return
	 */
	public Resource loadById(int id);
	
	/**
	 * 通过资源类型，加载某一类资源
	 * @param type
	 * @return
	 */
	public List<Resource> loadByType(int type);
}

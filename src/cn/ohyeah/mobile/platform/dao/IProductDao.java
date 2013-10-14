package cn.ohyeah.mobile.platform.dao;

import java.util.List;

import cn.ohyeah.mobile.platform.model.Product;

public interface IProductDao {

	/**
	 * 添加产品
	 * @param product
	 */
	public void save(Product product);
	
	/**
	 * 加载一个产品对象
	 * @param productid
	 * @return
	 */
	public Product load(int productid);
	
	/**
	 * 加载一个产品对象通过产品名
	 * @param name
	 * @return
	 */
	public Product loadByName(String name);
	
	/**
	 * 删除一个产品
	 * @param productid
	 */
	public void delete(int productid);
	
	/**
	 * 更新一个产品
	 * @param product
	 */
	public void saveOrUpdate(Product product);
	
	/**
	 * 查询游戏产品列表
	 * @return
	 */
	public List<Product> queryList();
}

package cn.ohyeah.mobile.platform.dao;

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
	 * 删除一个产品
	 * @param productid
	 */
	public void delete(int productid);
	
	/**
	 * 更新一个产品
	 * @param product
	 */
	public void saveOrUpdate(Product product);
}

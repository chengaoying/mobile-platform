package cn.ohyeah.mobile.platform.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ohyeah.mobile.platform.dao.IProductDao;
import cn.ohyeah.mobile.platform.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends BaseDaoImpl implements IProductDao{
	
	private static final Log log = LogFactory.getLog(ProductDaoImpl.class);

	@Override
	public void save(Product product) {
		getSession().save(product);
		log.info("添加一个游戏产品");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product load(int productid) {
		String sql = "from Product where productid=:productid";
		Query query = getSession().createQuery(sql);
		query.setParameter("productid", productid);
		List<Product> list = query.list();
		if(list.size() > 0){
			Product product = (Product)query.list().get(0);
			return product;
		}
		return null;
	}

	@Override
	public void delete(int productid) {
		getSession().delete(load(productid));
	}

	@Override
	public void saveOrUpdate(Product product) {
		getSession().saveOrUpdate(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> queryList() {
		String sql = "from Product";
		Query query = getSession().createQuery(sql);
		List<Product> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product loadByName(String name) {
		String sql = "from Product where productname=:productname";
		Query query = getSession().createQuery(sql);
		query.setParameter("productname", name);
		List<Product> list = query.list();
		if(list.size() > 0){
			Product product = (Product)query.list().get(0);
			return product;
		}
		return null;
	}

}

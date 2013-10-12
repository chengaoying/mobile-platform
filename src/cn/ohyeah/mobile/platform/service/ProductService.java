package cn.ohyeah.mobile.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.ohyeah.mobile.platform.dao.IProductDao;
import cn.ohyeah.mobile.platform.model.Product;

@Service("productService")
public class ProductService {

	@Autowired
	@Qualifier("productDao")
	private IProductDao productDao;

	public void save(Product product){
		productDao.save(product);
	}
	
	public Product load(int productid){
		return productDao.load(productid);
	}
	
	public void delete(int productid){
		productDao.delete(productid);
	}
	
	public void saveOrUpdate(Product product){
		productDao.saveOrUpdate(product);
	}
	
	public List<Product> queryList(){
		return productDao.queryList();
	}
}

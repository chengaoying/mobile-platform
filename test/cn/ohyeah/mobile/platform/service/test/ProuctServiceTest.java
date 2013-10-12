package cn.ohyeah.mobile.platform.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.Product;
import cn.ohyeah.mobile.platform.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ProuctServiceTest {

	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@Test
	public void save(){
		for(int i=0;i<10;i++){
			Product product = new Product();
			product.setAppname("appname_"+i);
			product.setProductname("name_"+i);
			product.setProvider("dd");
			product.setProducttype(0);
			product.setState(0);
			product.setAuthorization(0);
			productService.save(product);
		}
	}
	
	@Test
	public void load(){
		int productid = 1;
		Product product = productService.load(productid);
		System.out.println(product.getProductname());
	}
	
	
}

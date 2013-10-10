package cn.ohyeah.mobile.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.Product;
import cn.ohyeah.mobile.platform.model.Resource;
import cn.ohyeah.mobile.platform.model.User;
import cn.ohyeah.mobile.platform.service.ProductService;
import cn.ohyeah.mobile.platform.service.ResourceService;
import cn.ohyeah.mobile.platform.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ResourceServiceTest {

	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@Test
	public void test(){
		User user = new User();
		user.setName("test");
		user.setPassword("test");
		user.setCreatetime(new java.util.Date());
		user.setLevel(User.PRIVILEGE_MEMBER);
		user.setType("360");
		userService.save(user);
		
		Product product = new Product();
		product.setAppname("appname");
		product.setProductname("name");
		product.setProvider("dd");
		product.setProducttype(0);
		product.setState(0);
		product.setAuthorization(0);
		productService.save(product);
		
		Resource resource = new Resource();
		resource.setProductid(1);
		resource.setLocation("ss");
		resource.setName("sd");
		resource.setType(0);
		resource.setPrice(1000);
		resourceService.save(resource);
	}
}

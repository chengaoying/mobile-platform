package cn.ohyeah.mobile.platform.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.Resource;
import cn.ohyeah.mobile.platform.service.ResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ResourceServiceTest {

	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;
	
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@Test
	public void save(){
		for(int i=0;i<10;i++){
			Resource resource = new Resource();
			resource.setProductid(1);
			resource.setLocation("path");
			resource.setName("resource_"+i);
			resource.setType(i%2==0?0:1);
			resource.setPrice(1000);
			resource.setActivityid(1);
			resourceService.save(resource);
		}
	}
	
	@Test  
	public void delete(){
		int id = 1;
		resourceService.delete(id);
	}
	
	@Test
	public void load(){
		int id = 1;
		Resource resource = resourceService.loadById(id);
		System.out.println(resource.getName());
	}
	
	@Test
	public void loadByType(){
		int type = 0;
		List<Resource> list = resourceService.loadByType(type);
		System.out.println(list.size());
	}
	
	public void update(){}
}

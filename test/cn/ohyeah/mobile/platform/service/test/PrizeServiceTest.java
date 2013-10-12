package cn.ohyeah.mobile.platform.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.Prize;
import cn.ohyeah.mobile.platform.service.PrizeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class PrizeServiceTest {

	@Autowired
	@Qualifier("prizeService")
	private PrizeService prizeService;
	
	@Test
	public void save(){
		for(int i=0;i<10;i++){
			Prize resource = new Prize();
			resource.setProductid(1);
			resource.setLocation("path");
			resource.setName("prize_"+i);
			resource.setPrice(1000);
			resource.setActivityid(1);
			prizeService.save(resource);
		}
	}
	
	@Test  
	public void delete(){
		int id = 1;
		prizeService.delete(id);
	}
	
	@Test
	public void load(){
		int id = 1;
		Prize resource = prizeService.loadById(id);
		System.out.println(resource.getName());
	}
	
	@Test
	public void loadByType(){
		int type = 0;
		List<Prize> list = prizeService.loadByType(type);
		System.out.println(list.size());
	}
	
	public void update(){}
}

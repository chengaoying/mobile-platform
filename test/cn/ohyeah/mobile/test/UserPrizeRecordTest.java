package cn.ohyeah.mobile.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.Resource;
import cn.ohyeah.mobile.platform.model.UserPrizeRecord;
import cn.ohyeah.mobile.platform.service.ResourceService;
import cn.ohyeah.mobile.platform.service.UserPrizeRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class UserPrizeRecordTest {

	@Autowired
	@Qualifier("userPrizeRecordService")
	private UserPrizeRecordService userPrizeRecordService;
	
	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;
	
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	public void setUserPrizeRecordService(
			UserPrizeRecordService userPrizeRecordService) {
		this.userPrizeRecordService = userPrizeRecordService;
	}
	
	@Test
	public void test(){
		Resource resource = resourceService.loadById(1);
		UserPrizeRecord record = new UserPrizeRecord();
		record.setPrize(resource);
		record.setUserid(1);
		record.setTime(new java.util.Date());
		userPrizeRecordService.save(record);
		
	}
	
	@Test
	public void test2(){
		UserPrizeRecord record = userPrizeRecordService.load(1);
		Resource resource = record.getPrize();
		System.out.println(resource.getName());
		System.out.println(resource.getPrice());
		System.out.println(resource.getPrizeid());
	}
}

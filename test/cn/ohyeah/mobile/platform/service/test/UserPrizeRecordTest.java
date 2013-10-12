package cn.ohyeah.mobile.platform.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.Prize;
import cn.ohyeah.mobile.platform.model.UserPrizeRecord;
import cn.ohyeah.mobile.platform.service.PrizeService;
import cn.ohyeah.mobile.platform.service.UserPrizeRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class UserPrizeRecordTest {

	@Autowired
	@Qualifier("userPrizeRecordService")
	private UserPrizeRecordService userPrizeRecordService;
	
	@Autowired
	@Qualifier("prizeService")
	private PrizeService prizeService;
	
	@Test
	public void save(){
		for(int i=0;i<10;i++){
			Prize resource = prizeService.loadById(1+i);
			UserPrizeRecord record = new UserPrizeRecord();
			record.setPrize(resource);
			record.setUserid(1);
			record.setTime(new java.util.Date());
			userPrizeRecordService.save(record);
		}
		
	}
	
	@Test
	public void load(){
		UserPrizeRecord record = userPrizeRecordService.load(2);
		Prize resource = record.getPrize();
		System.out.println(resource.getName());
		System.out.println(resource.getPrice());
		System.out.println(resource.getPrizeid());
	}
	
	@Test
	public void delete(){
		int id = 1;
		userPrizeRecordService.delete(id); 
	}
	
	@Test
	public void loadList(){
		int userid = 1;
		List<UserPrizeRecord> list = userPrizeRecordService.loadRecordList(userid);
		System.out.println(list.size());
	}
	
	
}

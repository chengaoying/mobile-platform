package cn.ohyeah.mobile.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.ActivityPrize;
import cn.ohyeah.mobile.platform.model.Resource;
import cn.ohyeah.mobile.platform.model.UserPrizeRecord;
import cn.ohyeah.mobile.platform.service.ActivityPrizeService;
import cn.ohyeah.mobile.platform.service.ResourceService;
import cn.ohyeah.mobile.platform.service.UserPrizeRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ActivityPrizeServiceTest {
	
	@Autowired
	@Qualifier("activityPrizeService")
	private ActivityPrizeService activityPrizeService;

	@Autowired
	@Qualifier("userPrizeRecordService")
	private UserPrizeRecordService userPrizeRecordService;
	
	@Test
	public void test(){
		ActivityPrize activityPrize = new ActivityPrize();
		activityPrize.setStarttime(new java.util.Date());
		activityPrize.setEndtime(new java.util.Date());
		Set<Resource> rs = new HashSet<Resource>();
		/*UserPrizeRecord record = userPrizeRecordService.load(1);
		Resource resource = record.getPrize();
		rs.add(resource);*/
		activityPrize.setPrizes(rs);
		activityPrizeService.save(activityPrize);
	}
}

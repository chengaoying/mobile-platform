package cn.ohyeah.mobile.platform.service.test;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.ActivityPrize;
import cn.ohyeah.mobile.platform.model.Resource;
import cn.ohyeah.mobile.platform.service.ActivityPrizeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class ActivityPrizeServiceTest {
	
	@Autowired
	@Qualifier("activityPrizeService")
	private ActivityPrizeService activityPrizeService;

	@Test
	public void save(){
		ActivityPrize activityPrize = new ActivityPrize();
		activityPrize.setStarttime(new java.util.Date());
		activityPrize.setEndtime(new java.util.Date());
		activityPrizeService.save(activityPrize);
	}
	
	@Test
	public void load(){
		ActivityPrize activityPrize = activityPrizeService.load(1);
		Set<Resource> rs = activityPrize.getPrizes();
		Iterator<Resource> i = rs.iterator();
		System.out.println(activityPrize.getActivityid());
		System.out.println(rs.size());
		while(i.hasNext()){
			Resource r = i.next();
			System.out.println(r.getName());
		}
	}
	
	@Test
	public void delete(){
		int id = 0;
		activityPrizeService.delete(id);
	}
	
	@Test
	public void update(){
		ActivityPrize activityPrize = activityPrizeService.load(1);
		activityPrize.setEndtime(new java.util.Date());
		activityPrizeService.update(activityPrize);
	}
}

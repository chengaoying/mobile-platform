package cn.ohyeah.mobile.platform.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.GameRecord;
import cn.ohyeah.mobile.platform.service.GameRecordService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class GameRecordServiceTest {

	@Autowired
	@Qualifier("gameRecordService")
	private GameRecordService gameRecordService;
	
	@Test
	public void save(){
		GameRecord gameRecord = new GameRecord();
		gameRecord.setProductid(0);
		gameRecord.setRecordindex(1);
		gameRecord.setTime(new java.util.Date());
		gameRecord.setData("test data");
		gameRecordService.save(gameRecord);
	}
	
	@Test
	public void load(){
		GameRecord gameRecord = gameRecordService.loadById(1);
		System.out.println(gameRecord.getData());
	}
	
	@Test
	public void loadByRecordindex(){
		
	}
	
	@Test
	public void queryList(){
		List<GameRecord> list = gameRecordService.queryList(0);
		System.out.println("size==>"+list.size());
	}
}

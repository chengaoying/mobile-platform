package cn.ohyeah.mobile.platform.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ohyeah.mobile.platform.model.User;
import cn.ohyeah.mobile.platform.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class UserServiceTest {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Test
	public void save(){
		for(int i=0;i<10;i++){
			User user = new User();
			user.setName("test_"+i);
			user.setPassword("password_"+i);
			user.setCreatetime(new java.util.Date());
			user.setLevel(User.PRIVILEGE_MEMBER);
			user.setType("360");
			userService.save(user);
		}
	}
}

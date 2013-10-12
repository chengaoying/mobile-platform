package cn.ohyeah.mobile.platform.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ohyeah.mobile.bussness.model.DataInfo;
import cn.ohyeah.mobile.bussness.model.ReturnInfo;
import cn.ohyeah.mobile.bussness.model.UserInfo;
import cn.ohyeah.mobile.bussness.service.IUserService;
import cn.ohyeah.mobile.global.CodeList;
import cn.ohyeah.mobile.global.RequestContext;
import cn.ohyeah.mobile.platform.model.User;
import cn.ohyeah.mobile.platform.service.UserService;

@Controller("userController")
@RequestMapping(value = "/user")
public class UserController extends AbstractController{
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("service360")
	private IUserService service360;

	@RequestMapping(value = "/register")
	public ModelAndView register(@RequestParam("name")String name, @RequestParam("password")String password){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		User u = userService.loadUserByName(name);
		
		ReturnInfo<DataInfo> info = new ReturnInfo<DataInfo>();
		List<DataInfo> list = new ArrayList<DataInfo>();
		DataInfo di = new DataInfo();
		if(u != null){
			info.setCode(CodeList.EC_USER_EXIST);
			info.setMessage(CodeList.getErrorMessage(CodeList.EC_USER_EXIST));
			di.setId(u.getAccountid());
			di.setName(u.getName());
			list.add(di);
			info.setData(list);
		}else{
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setCreatetime(new java.util.Date());
			user.setLevel(User.PRIVILEGE_MEMBER);
			user.setType("360");
			userService.save(user);
			
			info.setCode(CodeList.SUCCESS);
			info.setMessage(CodeList.getErrorMessage(CodeList.SUCCESS));
		}
		mv.addObject(info);
		return mv;
	}
	
	/**
	 * 360platform login
	 * @param code
	 * @param appKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login_360")
	public ModelAndView login_360(@RequestParam("code")String code, @RequestParam("appKey")String appKey){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		
		/**
		 * get token 
		 */
		Map<String, Object> map = service360.getTokenByCode(code, appKey);
		
		ReturnInfo<UserInfo> info = new ReturnInfo<UserInfo>();
		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo ui = new UserInfo();
		String error = String.valueOf(map.get("error"));
		if(error == null || error.equals("")){
			String access_token = String.valueOf(map.get("access_token"));
			
			/**
			 * get 360 user info
			 */
			map = service360.getUserInfoByToken(access_token, "id,name,avatar,sex,area,nick");
			
			info.setCode(CodeList.SUCCESS);
			info.setMessage(CodeList.getErrorMessage(CodeList.SUCCESS));
			ui.setId(Integer.parseInt(String.valueOf(map.get("id"))));
			ui.setName(String.valueOf(map.get("name")));
			ui.setArea(String.valueOf(map.get("area")));
			ui.setAvatar(String.valueOf(map.get("avatar")));
			ui.setSex(String.valueOf(map.get("sex")));
			ui.setNick(String.valueOf(map.get("nikc")));
			list.add(ui);
			info.setData(list);
			
			if(userService.loadUserByName(ui.getName()) == null){
				User user = new User();
				user.setAccountid(ui.getId());
				user.setName(ui.getName());
				user.setArea(ui.getArea());
				user.setAvatar(ui.getAvatar());
				user.setSex(ui.getSex());
				user.setNick(ui.getNick());
				user.setPassword("");
				user.setCreatetime(new java.util.Date());
				user.setLevel(User.PRIVILEGE_MEMBER);
				user.setType("360");
				userService.save(user);
			}
			
		}else{
			String error_code = String.valueOf(map.get("error_code"));
			info.setMessage(error);
			info.setCode(Integer.parseInt(error_code));
		}
		mv.addObject(info);
		return mv;
	}
	
	@RequestMapping(value = "/validate")
	public ModelAndView validate(@RequestParam("name")String name){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		User u = userService.loadUserByName(name);
		
		ReturnInfo<DataInfo> info = new ReturnInfo<DataInfo>();
		List<DataInfo> list = new ArrayList<DataInfo>();
		DataInfo di = new DataInfo();
		if(u != null){
			info.setCode(CodeList.EC_USER_EXIST);
			info.setMessage(CodeList.getErrorMessage(CodeList.EC_USER_EXIST));
			di.setId(u.getAccountid());
			di.setName(u.getName());
			list.add(di);
			info.setData(list);
		}else{
			info.setCode(CodeList.SUCCESS);
			info.setMessage(CodeList.getErrorMessage(CodeList.SUCCESS));
		}
		mv.addObject(info);
		return mv;
	}
	
	@RequestMapping(value = "/save")
	public String save(){
		return "user/save";
	}
}

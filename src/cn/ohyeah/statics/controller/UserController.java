package cn.ohyeah.statics.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import cn.ohyeah.statics.model.User;
import cn.ohyeah.statics.service.DataDicService;
import cn.ohyeah.statics.service.UserService;
import cn.ohyeah.statics.utils.PageModel;

@Controller("userController")
public class UserController {
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	private UserService userService;
	private DataDicService dataDicservice;
	
	@Autowired
	@Qualifier("dataDicservice")        
	public void setDataDicservice(DataDicService dataDicservice) {
		this.dataDicservice = dataDicservice;
	}

	@Autowired
	@Qualifier("UserService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute("user") User user, SessionStatus status, HttpServletRequest request) {
		User u = userService.loadUserByName(user.getName());
		if(u!=null){
			if(u.getPassWord().equals(user.getPassWord())){
				u.setLogintime(new Date());
				userService.updateUser(u);
				request.getSession().setAttribute("name", u.getName());
				return "admin/main";
			}else{
				user.setName("登入失败：密码错误");
				return "error_all";
			}
		}else{
			user.setName("登入失败：用户名错误");
			return "error_all";
		}
	}
	
	@ModelAttribute("pageModel")
	public PageModel<User> initPageModel(){
		PageModel<User> pageModel = new PageModel<User>();
		pageModel.setPageSize(2);
		return pageModel;
	}
	
	@RequestMapping(value = "/list")
	public String list(@ModelAttribute("pageModel")PageModel<User> pageModel, @RequestParam("pageNo")int pageNo){
		pageModel.setList(userService.queryAllUser(pageNo, pageModel.getPageSize()));
		pageModel.setPageNo(pageNo);
		pageModel.setTotalRecords(userService.queryCounts());
		return "admin/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(@ModelAttribute("pageModel")PageModel<User> pageModel, @RequestParam("pageNo")int pageNo,
			@RequestParam("id")int id){
		userService.deleteUser(id);
		return list(pageModel, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	@ModelAttribute
	public void initList(@SuppressWarnings("rawtypes") Map model){
		model.put("roleList", dataDicservice.queryAllDataDicService());
		return ;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)  
	public String addList(){
		return "admin/add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("pageModel")PageModel<User> pageModel, @RequestParam("pageNo")int pageNo,
			@ModelAttribute("user")User user){
		if(user.getRole().equals("admin")){
			user.setAuthority("1");
		}else if(user.getRole().equals("worker")){
			user.setAuthority("2");
		}else if(user.getRole().equals("partner")){
			user.setAuthority("3");
		}
		user.setCreatetime(new Date());
		user.setLogintime(new Date());
		userService.saveUser(user);
		return list(pageModel, pageNo);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updatePage(@ModelAttribute("user2")User user2){
		System.out.println("userid:"+user2.getId());
		return "admin/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("pageModel")PageModel<User> pageModel, @RequestParam("pageNo")int pageNo,
			@ModelAttribute("user3")User user3){
		User u = userService.loadUserById(user3.getId());
		user3.setCreatetime(u.getCreatetime());
		user3.setLogintime(u.getLogintime());
		if(user3.getRole().equals("admin")){
			user3.setAuthority("1");
		}else if(user3.getRole().equals("worker")){
			user3.setAuthority("2");
		}else if(user3.getRole().equals("partner")){
			user3.setAuthority("3");
		}
		userService.updateUser(user3);
		return list(pageModel, pageNo);
	}
	
	@RequestMapping(value = "/validate")
	public void validateUser(@RequestParam("name")String name, HttpServletRequest request, HttpServletResponse response) throws Exception{
		User user = userService.loadUserByName(name);
		if(user!=null){
			response.getWriter().write("user exists");
		}else{
			response.getWriter().write("");
		}
		return;
	}
}

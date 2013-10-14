package cn.ohyeah.mobile.platform.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.ohyeah.mobile.bussness.model.PrizeInfo;
import cn.ohyeah.mobile.bussness.model.ReturnInfo;
import cn.ohyeah.mobile.global.CodeList;
import cn.ohyeah.mobile.global.Configurations;
import cn.ohyeah.mobile.global.RequestContext;
import cn.ohyeah.mobile.platform.model.Prize;
import cn.ohyeah.mobile.platform.service.ActivityPrizeService;
import cn.ohyeah.mobile.platform.service.PrizeService;
import cn.ohyeah.mobile.platform.service.ProductService;
import cn.ohyeah.mobile.utils.FileUtils;
import cn.ohyean.mobile.exception.BusinessException;


@Controller("prizeController")
@RequestMapping(value = "/prize")
public class PrizeController extends AbstractController {

	private static final Log log = LogFactory.getLog(PrizeController.class);
	
	@Autowired
	@Qualifier("prizeService")
	private PrizeService prizeService;
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	
	@Autowired
	@Qualifier("activityPrizeService")
	private ActivityPrizeService activityPrizeService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ModelAttribute
	public void list(Map model){
		model.put("products", productService.queryList());
		model.put("activityPrizes", activityPrizeService.queryList());
	}
	
	@RequestMapping("/index")
	public String index(){
		return "prize/add";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@RequestParam("file") CommonsMultipartFile file, @ModelAttribute("prize")Prize prize, HttpServletRequest request){
		if(!file.isEmpty()){
			prize.setLocation(Configurations.getProperty("resourcesPath", "prize")+file.getOriginalFilename());
			//String path = request.getSession().getServletContext().getRealPath(Configurations.getProperty("resourcesPath", "prize"));
			String path = Configurations.getProperty("resourcesPath", "prize");
			log.debug("文件输出路径："+path+"\\"+file.getOriginalFilename());
			try {
				FileCopyUtils.copy(file.getBytes(), new File(path+"\\"+file.getOriginalFilename()));
			} catch (FileNotFoundException e) {
				log.error("未找到文件，原因："+e);
				throw new BusinessException(e);
			} catch (IOException e) {
				log.error("文件上传错误，原因："+e);
				throw new BusinessException(e);
			}
			prizeService.save(prize);
			return "prize/success";
		}else{  
			return "prize/error";
		}
	}
	
	@RequestMapping(value = "/validate")
	public void validateUser(@RequestParam("name")String name, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Prize prize = prizeService.loadByName(name);
		if(prize!=null){
			response.getWriter().write("此名称已存在，请更换其他名");
		}else{
			response.getWriter().write("");
		}
		return;
	}
	
	public ModelAndView loadPrizes(@RequestParam("activityid")int activityid){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		ReturnInfo<PrizeInfo> info = new ReturnInfo<PrizeInfo>();
		List<PrizeInfo> list = new ArrayList<PrizeInfo>();
		List<Prize> prizes = prizeService.loadByActivityid(activityid);
		if(prizes != null && prizes.size() > 0){
			for(Prize prize:prizes){
				PrizeInfo pi = new PrizeInfo();
				pi.setActivityid(prize.getActivityid());
				pi.setName(prize.getName());
				pi.setPrice(prize.getPrice());
				pi.setProductid(prize.getProductid());
				pi.setBytes(FileUtils.FileToByteArray(new File(prize.getLocation())));
				list.add(pi);
			}
			info.setCode(CodeList.SUCCESS);
			info.setData(list);
		}else{
			info.setCode(CodeList.EC_PRIZE_NOT_EXIST);
			info.setMessage(CodeList.getErrorMessage(CodeList.EC_PRIZE_NOT_EXIST));
		}
		mv.addObject(info);
		return mv;
	}
}

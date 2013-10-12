package cn.ohyeah.mobile.platform.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.ohyeah.mobile.global.Configurations;
import cn.ohyeah.mobile.platform.model.Prize;
import cn.ohyeah.mobile.platform.service.ActivityPrizeService;
import cn.ohyeah.mobile.platform.service.PrizeService;
import cn.ohyeah.mobile.platform.service.ProductService;


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
			System.out.println("图片路径："+Configurations.getProperty("resourcesPath", "prize")+file.getOriginalFilename());
			prize.setLocation(Configurations.getProperty("resourcesPath", "prize")+file.getOriginalFilename());
			log.info("文件名："+file.getOriginalFilename());
			return "prize/success";
		}else{  
			return "error";
		}
	}
	
	
	
	
}

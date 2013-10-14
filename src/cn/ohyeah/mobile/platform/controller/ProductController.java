package cn.ohyeah.mobile.platform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.ohyeah.mobile.platform.model.Product;
import cn.ohyeah.mobile.platform.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;

	@RequestMapping("/index")
	public String index(){
		return "product/add";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@ModelAttribute("product")Product product){
		product.setAuthorization(0);
		product.setProducttype(0);
		product.setUptime(new java.util.Date());
		productService.save(product);
		return "product/success";
	}
	
	@RequestMapping(value = "/validate")
	public void validateUser(@RequestParam("productname")String productname, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Product product = productService.loadByName(productname);
		if(product!=null){
			response.getWriter().write("此名称已存在，请更换其他名");
		}else{
			response.getWriter().write("");
		}
		return;
	}
}

package cn.ohyeah.mobile.platform.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

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

import cn.ohyeah.mobile.global.Configurations;
import cn.ohyeah.mobile.platform.model.Product;
import cn.ohyeah.mobile.platform.service.ProductService;
import cn.ohyean.mobile.exception.BusinessException;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static final Log log = LogFactory.getLog(ProductController.class);

	@Autowired
	@Qualifier("productService")
	private ProductService productService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		request.setAttribute("products", productService.queryList());
		return "product/index";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(){
		return "product/add";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(@ModelAttribute("product")Product product,@RequestParam("file") CommonsMultipartFile file){
		if(!file.isEmpty()){
			String path = Configurations.getProperty("resourcesPath", "product");
			log.debug("文件输出路径："+path + file.getOriginalFilename());
			try {
				FileCopyUtils.copy(file.getBytes(), new File(path + file.getOriginalFilename()));
			} catch (FileNotFoundException e) {
				log.error("未找到文件，原因："+e);
				throw new BusinessException("未找到文件，原因："+e);
			} catch (IOException e) {
				log.error("文件上传错误，原因："+e);
				throw new BusinessException("文件上传错误，原因："+e);
			}
			product.setAuthorization(0);
			product.setProducttype(0);
			product.setUptime(new java.util.Date());
			product.setAppname(file.getOriginalFilename());
			productService.save(product);
			return "product/success";
		}else{
			return "error";
		}
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
	
	@RequestMapping(value="/download",method = RequestMethod.POST)
	public void download(@RequestParam("productid") int productid,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Product product = productService.load(productid);
		String path = Configurations.getProperty("resourcesPath", "product");
		String nowPath = "";
		if (!path.endsWith("/")) {
			nowPath = path + "/" + product.getAppname();
		} else {
			nowPath = path + product.getAppname();
		}
		File file = new File(nowPath);;
		
		response.reset();

		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename=" 
				+ new String(product.getAppname().getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
		response.addHeader("Content-Length", "" + file.length());

		try {
			// 以流的形式下载文件
			InputStream fis = new BufferedInputStream(new FileInputStream(nowPath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

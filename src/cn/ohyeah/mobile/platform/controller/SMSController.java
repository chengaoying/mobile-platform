package cn.ohyeah.mobile.platform.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ohyeah.mobile.bussness.model.ReturnInfo;
import cn.ohyeah.mobile.bussness.model.SmsInfo;
import cn.ohyeah.mobile.bussness.service.ISmsService;
import cn.ohyeah.mobile.global.RequestContext;
import cn.ohyeah.mobile.platform.model.Sms;
import cn.ohyeah.mobile.platform.service.SmsService;


@Controller("smsController")
@RequestMapping(value = "/sms")
public class SMSController extends AbstractController {

	private static final Log log = LogFactory.getLog(SMSController.class);
	
	@Autowired
	@Qualifier("service_sms")
	private ISmsService service_sms;
	
	@Autowired
	@Qualifier("smsService")
	private SmsService smsService;
	
	@RequestMapping(value="/getSmsContent")
	public ModelAndView getSmsContent(@RequestParam("price")String price,@RequestParam("imsi")String imsi,@RequestParam("imei")String imei,@RequestParam("phone")String phone){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		Map<String, Object> map = service_sms.getSmsContent(price,imsi,imei,phone);
		SmsInfo sms = new SmsInfo();
		sms.setCode(String.valueOf(map.get("code")));
		sms.setMessage(String.valueOf(map.get("message")));
		sms.setPort(String.valueOf(map.get("port")));
		sms.setSms(String.valueOf(map.get("sms")));
		mv.addObject(sms);
		return mv;
	}
	
	@RequestMapping(value="/callBack")
	public ModelAndView callBack(HttpServletRequest request){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		String usernumber = request.getParameter("usernumber");
		String linkid 	  = request.getParameter("linkid");
		String report 	  = request.getParameter("report");
		String srvcode    = request.getParameter("srvcode");
		String feetype    = request.getParameter("feetype");
		String feecode    = request.getParameter("feecode");
		String date 	  = request.getParameter("date");
		String msg 		  = request.getParameter("msg");
		
		Sms sms = new Sms();
		sms.setUserNumber(usernumber);
		sms.setLinkId(linkid);
		sms.setReport(report);
		sms.setSrvCode(srvcode);
		sms.setFeeType(feetype);
		sms.setFeeCode(feecode);
		sms.setDate(date);
		sms.setMsg(msg);
		smsService.save(sms);
		log.debug("用户消费记录，手机号："+usernumber);
		return mv;
	}
	
	
	public static void main(String[] args){
		String str = "MF50ae23e181b04f16a26e977d9fcf462e59a6594ae2f34c488253a0ffe1dfaea03c93100df2da4458b4e381fbd6842a2e";
		System.out.println(str.length());
	}
	
}

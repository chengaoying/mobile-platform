package cn.ohyeah.mobile.platform.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import cn.ohyeah.mobile.global.CodeList;
import cn.ohyeah.mobile.global.RequestContext;


@Controller("smsController")
@RequestMapping(value = "/sms")
public class SMSController extends AbstractController {

	private static final Log log = LogFactory.getLog(SMSController.class);
	
	@Autowired
	@Qualifier("smsService")
	private ISmsService smsService;
	
	@RequestMapping(value="/getSmsContent")
	public ModelAndView getSmsContent(@RequestParam("price")String price,@RequestParam("imsi")String imsi,@RequestParam("imei")String imei,@RequestParam("phone")String phone){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		Map<String, Object> map = smsService.getSmsContent(price,imsi,imei,phone);
		SmsInfo sms = new SmsInfo();
		sms.setCode(String.valueOf(map.get("code")));
		sms.setMessage(String.valueOf(map.get("message")));
		sms.setPort(String.valueOf(map.get("port")));
		sms.setSms(String.valueOf(map.get("sms")));
		mv.addObject(sms);
		return mv;
	}
	
}

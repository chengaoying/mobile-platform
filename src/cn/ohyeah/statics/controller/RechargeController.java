package cn.ohyeah.statics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ohyeah.statics.service.RechargeService;

@Controller
public class RechargeController {

	private RechargeService rechargeService;
	
	@Autowired
	@Qualifier("RechargeService")
	public void setRechargeService(RechargeService rechargeService) {
		this.rechargeService = rechargeService;
	}

	@RequestMapping(value = "recharge")
	public String queryRechargeInfo(){
		System.out.println("save product");
		String startTime = "2012-04-01";
		String endTime = "2012-10-10";
		//rechargeService.getTableColumns(startTime, endTime);
		//rechargeService.getTableRows(startTime, endTime);
		rechargeService.getSubscribeDetail(startTime, endTime);
		return "recharge/recharge";
	}
}

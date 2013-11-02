package cn.ohyeah.mobile.platform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ohyeah.mobile.bussness.model.DataInfo;
import cn.ohyeah.mobile.bussness.model.ReturnInfo;
import cn.ohyeah.mobile.bussness.model.UserPrizeInfo;
import cn.ohyeah.mobile.global.CodeList;
import cn.ohyeah.mobile.global.RequestContext;
import cn.ohyeah.mobile.platform.model.Prize;
import cn.ohyeah.mobile.platform.model.UserPrizeRecord;
import cn.ohyeah.mobile.platform.service.PrizeService;
import cn.ohyeah.mobile.platform.service.UserPrizeRecordService;

@Controller("userPrizeController")
@RequestMapping(value = "/userPrize")
public class UserPrizeRecordController extends AbstractController{

	@Autowired
	@Qualifier("userPrizeRecordService")
	private UserPrizeRecordService userPrizeRecordService;
	
	@Autowired
	@Qualifier("prizeService")
	private PrizeService prizeService;
	
	@RequestMapping("/save")
	public ModelAndView save(@RequestParam("userid")int userid, @RequestParam("prizeid")int prizeid){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		Prize prize = prizeService.loadById(prizeid);
		UserPrizeRecord record = new UserPrizeRecord();
		record.setUserid(userid);
		record.setPrize(prize);
		record.setTime(new java.util.Date());
		userPrizeRecordService.save(record);
		
		ReturnInfo<DataInfo> info = new ReturnInfo<DataInfo>();
		//List<DataInfo> list = new ArrayList<DataInfo>();
		//DataInfo di = new DataInfo();
		info.setCode(CodeList.SUCCESS);
		info.setMessage(CodeList.getErrorMessage(CodeList.SUCCESS));
		mv.addObject(info);
		return mv;
	}
	
	public ModelAndView load(@RequestParam("userid")int userid){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		List<UserPrizeRecord> prizes = userPrizeRecordService.loadRecordList(userid);
		
		ReturnInfo<UserPrizeInfo> info = new ReturnInfo<UserPrizeInfo>();
		List<UserPrizeInfo> list = new ArrayList<UserPrizeInfo>();
		UserPrizeInfo pi = new UserPrizeInfo();
		if(prizes.size() > 0){
			for(UserPrizeRecord record:prizes){
				pi.setId(record.getId());
				pi.setPrizeid(record.getPrizeid());
				pi.setPrize(record.getPrize());
				pi.setUserid(record.getUserid());
				pi.setTime(record.getTime());
				list.add(pi);
			}
			info.setCode(CodeList.SUCCESS);
			info.setData(list);
		}else{
			info.setCode(CodeList.EC_USER_PRIZE_RECORD_NOT_EXIST);
			info.setMessage(CodeList.getErrorMessage(CodeList.EC_USER_PRIZE_RECORD_NOT_EXIST));
		}
		mv.addObject(info);
		return mv;
	}
	
	
}

package cn.ohyeah.mobile.platform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.ohyeah.mobile.bussness.model.RecordInfo;
import cn.ohyeah.mobile.bussness.model.ReturnInfo;
import cn.ohyeah.mobile.global.CodeList;
import cn.ohyeah.mobile.global.RequestContext;
import cn.ohyeah.mobile.platform.model.GameRecord;
import cn.ohyeah.mobile.platform.service.GameRecordService;

@Controller("gameRecordController")
@RequestMapping(value="/gameRecord")
public class GameRecordController extends AbstractController{
	
	@Autowired
	@Qualifier("gameRecordService")
	private GameRecordService gameRecordService;

	@RequestMapping("/save")
	public ModelAndView save(/*@RequestParam("productid")int productid,*/ @RequestParam("recordindex")int recordindex,
			@RequestParam("data")String data){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		
		GameRecord gameRecord = new GameRecord();
		gameRecord.setProductid(1/*productid*/);
		gameRecord.setRecordindex(recordindex);
		gameRecord.setData(data);
		
		ReturnInfo<RecordInfo> info = new ReturnInfo<RecordInfo>();
		gameRecordService.save(gameRecord);
		info.setCode(CodeList.SUCCESS);
		mv.addObject(info);
		return mv;
	}
	
	@RequestMapping("/load")
	public ModelAndView load(/*@RequestParam("productid")int productid,*/ @RequestParam("recordindex")int recordindex){
		RequestContext rc = RequestContext.get();
		ModelAndView mv = getView(rc);
		
		ReturnInfo<RecordInfo> info = new ReturnInfo<RecordInfo>();
		GameRecord record = gameRecordService.loadByRecordindex(recordindex);
		if(record != null){
			RecordInfo recordinfo = new RecordInfo();
			recordinfo.setId(record.getId());
			recordinfo.setProductid(record.getProductid());
			recordinfo.setRecordindex(record.getRecordindex());
			recordinfo.setTime(record.getTime());
			recordinfo.setData(record.getData());
			List<RecordInfo> list = new ArrayList<RecordInfo>();
			list.add(recordinfo);
			info.setData(list);
			
		}else{
			info.setCode(CodeList.EC_RECORD_NOT_EXIST);
			info.setMessage(CodeList.getErrorMessage(CodeList.EC_RECORD_NOT_EXIST));
		}
		
		mv.addObject(info);
		return mv;
	}
}

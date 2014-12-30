package cn.ohyeah.mobile.platform.controller;

import org.springframework.web.servlet.ModelAndView;

import cn.ohyeah.mobile.global.Constant;
import cn.ohyeah.mobile.global.RequestContext;

public class AbstractController {

	/**
	 * json or xml view
	 * @param rc
	 * @return
	 */
	protected ModelAndView getView(RequestContext rc){
		String dataType = String.valueOf(rc.getRequest().getParameter("dataType"));
		ModelAndView mv = null;
		if(dataType.equals(Constant.XML)){
			mv = new ModelAndView(Constant.VIEW_XML);
		}else{
			mv = new ModelAndView(Constant.VIEW_JSON);
		}
		return mv;
	}
}

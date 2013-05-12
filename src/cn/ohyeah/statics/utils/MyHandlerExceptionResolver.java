package cn.ohyeah.statics.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ModelAndView resolveException(HttpServletRequest req,
			HttpServletResponse res, Object obj, Exception ex) {
		 Map model = new HashMap();
		 model.put("ex", ex.getClass().getSimpleName());
		 model.put("error", ex.getMessage());
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("model",model);
		 mv.setViewName("error");
		return mv;
	}

}

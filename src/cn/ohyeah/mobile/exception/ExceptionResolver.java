package cn.ohyeah.mobile.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.ohyeah.mobile.utils.StringPrintWriter;

/**
 * 统一异常处理
 * @author jackey
 *
 */
public class ExceptionResolver implements HandlerExceptionResolver{
	
	private static final Log log = LogFactory.getLog(ExceptionResolver.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ModelAndView resolveException(HttpServletRequest req,
			HttpServletResponse res, Object obj, Exception ex) {
		log.info(ex);
		Map model = new HashMap();
		StringPrintWriter strintPrintWriter = new StringPrintWriter();  
        ex.printStackTrace(strintPrintWriter);  
        model.put("error", strintPrintWriter.getString());
        ModelAndView mv = new ModelAndView();
		mv.addObject("model",model);
		mv.setViewName("error");
		return mv;
	}

}

package cn.ohyeah.mobile.platform.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.ohyeah.mobile.global.RequestContext;

public class GlobalIntercept extends HandlerInterceptorAdapter {
	
	private Log log = LogFactory.getLog(GlobalIntercept.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		RequestContext.begin(request, response);
		String requrl = request.getRequestURL().toString();
		String queryStr = request.getQueryString();
		if (queryStr != null) {
			requrl += "?" + queryStr;
		}
		log.debug("requestUrl===>" + requrl);
		log.debug("remoteIp===>"+request.getRemoteAddr());
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		RequestContext.get().end();
		System.out.println("request over");
	}

}

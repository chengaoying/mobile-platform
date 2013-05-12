package cn.ohyeah.statics.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginIntercept extends HandlerInterceptorAdapter {

	private String loginUrl;
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
    
    private String indexUrl;
    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if(request.getServletPath().startsWith(indexUrl)){
			System.out.println("enter index.jsp");
			return true;
		}
		
		if(request.getSession().getAttribute("name")!=null){
			System.out.println("用户已登入");
			return true;
		}
		
		if(request.getServletPath().startsWith(loginUrl)){
			System.out.println("登入验证");
			return true;
		}
		
		System.out.println("重定向");
		response.sendRedirect(request.getContextPath()+indexUrl);
		return false;
	}

}

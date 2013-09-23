package cn.ohyeah.mobile.global;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

  
public class RequestContext {

	private final static ThreadLocal<RequestContext> contexts = new ThreadLocal<RequestContext>();
	
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Cookie> cookies;
	
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map<String, Cookie> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, Cookie> cookies) {
		this.cookies = cookies;
	}

	private RequestContext(){};
	
	/**
	 * 初始化请求上下文
	 * @param ctx
	 * @param req
	 * @param res
	 */
	public static void begin(HttpServletRequest req, HttpServletResponse res) {
		RequestContext rc = new RequestContext();
		rc.request = req;   
		rc.response = res;
		rc.response.setCharacterEncoding("UTF_8");
		rc.session = req.getSession(true);
		rc.cookies = new HashMap<String, Cookie>();
		Cookie[] cookies = req.getCookies();
		if(cookies != null)
			for(Cookie ck : cookies) {
				rc.cookies.put(ck.getName(), ck);
			}
		contexts.set(rc);
	}
	
	/**
	 * 获取当前请求的上下文
	 * @return
	 */
	public static RequestContext get(){
		return contexts.get();
	}
	
	public void end() {
		this.request = null;
		this.response = null;
		this.session = null;
		this.cookies = null;
		contexts.remove();
	}
	
	public void closeCache(){
        header("Pragma","No-cache");
        header("Cache-Control","no-cache");
        header("Expires", 0L);
	}
	
	public void addSessionAtt(String name, Object value){
		session.setAttribute(name, value);
	}
	
	public Object getSessionAtt(String name){
		return session.getAttribute(name);
	}
	
	public void addRequestAtt(String name, Object value){
		request.setAttribute(name, value);
	}
	
	public Object requestAtt(String name){
		return request.getAttribute(name);
	}
	
	public String header(String name) { return request.getHeader(name); }
	public void header(String name, String value) { response.setHeader(name, value); }
	public void header(String name, int value) { response.setIntHeader(name, value); }
	public void header(String name, long value) { response.setDateHeader(name, value); }
}

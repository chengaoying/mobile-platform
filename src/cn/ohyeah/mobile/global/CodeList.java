package cn.ohyeah.mobile.global;

public class CodeList {
	
	public static final int SUCCESS = 0;							//成功
	public static final int EC_REQUEST_PARAM = -1001;				//请求参数错误
	public static final int EC_USER_INVALID = -1002;				//用户不存在
	public static final int EC_PASSWORD_ERROR = -1003;			//密码不正确
	public static final int EC_USER_EXIST = -1004;				//用户已存在
	
	public static final int EC_360_ERROR = -2001;					//360获取Token接口异常			
	
	public static String getErrorMessage(int errorCode){
		switch (errorCode){
		case 0:
			return "success";
		case -1001:
			return "请求参数错误";
		case -1002:
			return "用户不存在";
		case -1003:
			return "密码不正确";
		case -1004:
			return "用户已经存在";
		case -2001:
			return "360获取Token接口异常";
		default: return "未知错误";
		}
	}

}

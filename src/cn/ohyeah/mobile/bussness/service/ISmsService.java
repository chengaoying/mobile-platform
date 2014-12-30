package cn.ohyeah.mobile.bussness.service;

import java.util.Map;


public interface ISmsService {
	
	/**
	 * 获取动态指令
	 * @param code
	 * @param appKey
	 * @return 
	 */
	public Map<String, Object> getSmsContent(String price, String imsi, String imei, String phope);
	
	
}

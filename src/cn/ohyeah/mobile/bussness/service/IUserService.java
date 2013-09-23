package cn.ohyeah.mobile.bussness.service;

import java.util.Map;

public interface IUserService {
	
	/**
	 * 360平台获取token
	 * @param code
	 * @param appKey
	 * @return 
	 */
	public Map<String, Object> getTokenByCode(String code, String appKey);
	
	/**
	 * 360平台获取用户信息
	 * @param token
	 * @param fields（需要返回的字段，id,name,avatar,sex,area,nick）
	 * @return
	 */
	public Map<String, Object> getUserInfoByToken(String token, String fields);
	
	/**
	 * 360平台刷新access_token
	 * @param grant_type 定值refresh_token
	 * @param refreshToken 用于刷新access_token的refresh_token
	 * @param client_id   应用appKey
	 * @param client_sercet  应用appSecret
	 * @param scope  定值basic
	 * @return
	 */
	public Map<String, Object> refreshToken(String refreshToken, String client_id);

}

package cn.ohyeah.mobile.bussness.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.ohyeah.mobile.bussness.service.IUserService;
import cn.ohyeah.mobile.global.CodeList;
import cn.ohyeah.mobile.global.Configurations;
import cn.ohyeah.mobile.utils.ThreadSafeOfConnectionManager;
import cn.ohyean.mobile.exception.BusinessException;

@Service("service360")
public class UserServiceImpl_360 implements IUserService{
	
	private static Log log = LogFactory.getLog(UserServiceImpl_360.class);

	@Override
	public Map<String, Object> getTokenByCode(String code, String appKey) {
		String appSecret = Configurations.getProperty("360", "app_secret");
		String url = Configurations.getProperty("360", "preUrl") + Configurations.getProperty("360", "tokenUrl");
		url = String.format(url, code, appKey, appSecret);
		log.debug("360_getToken url ===>"+url);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpClient httpclient = ThreadSafeOfConnectionManager.buildDefaultHttpsClient();
			HttpGet httpGet = new HttpGet(url);
			String str = ThreadSafeOfConnectionManager.executeForBodyString(httpclient, httpGet);
			log.debug(str);
			
			ObjectMapper om = new ObjectMapper();
			JsonNode node = om.readValue(str, JsonNode.class);
			String error = String.valueOf(node.get("error"));
			if(error == null || error.equals("")){
				map.put("access_token", formatString(String.valueOf(node.get("access_token"))));
				map.put("expires_in", formatString(String.valueOf(node.get("expires_in"))));
				map.put("scope", formatString(String.valueOf(node.get("scope"))));
				map.put("refresh_token", formatString(String.valueOf(node.get("refresh_token"))));
				map.put("time", System.currentTimeMillis()/1000);
			}else{
				map.put("error", formatString(error));
				map.put("error_code", formatString(String.valueOf(node.get("error_code"))));
			} 
			return map;
		} catch (Exception e) {
			map.put("error", CodeList.getErrorMessage(CodeList.EC_360_ERROR) + e.getMessage());
			map.put("error_code", String.valueOf(CodeList.EC_360_ERROR));
			throw new BusinessException(e);
		}
	} 
	
	private static String formatString(String str){
		if(str.contains("\"")){
			str = str.substring(1, str.length()-1);
		}
		return str;
	}

	@Override
	public Map<String, Object> getUserInfoByToken(String token, String fields) {
		String url = Configurations.getProperty("360", "preUrl") + Configurations.getProperty("360", "userInfoUrl");
		url = String.format(url, token, fields);
		log.debug("360_getUserInfo url ===>"+url);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpClient httpclient = ThreadSafeOfConnectionManager.buildDefaultHttpsClient();
			HttpGet httpGet = new HttpGet(url);
			String str = ThreadSafeOfConnectionManager.executeForBodyString(httpclient, httpGet);
			log.debug(str);
			
			ObjectMapper om = new ObjectMapper();
			JsonNode node = om.readValue(str, JsonNode.class);
			String error = String.valueOf(node.get("error"));
			if(error == null || error.equals("")){
				map.put("id", formatString(String.valueOf(node.get("id"))));
				map.put("name", formatString(String.valueOf(node.get("name"))));
				map.put("avatar", formatString(String.valueOf(node.get("avatar"))));
				map.put("sex", formatString(String.valueOf(node.get("sex"))));
				map.put("area", formatString(String.valueOf(node.get("area"))));
				map.put("nick", formatString(String.valueOf(node.get("nick"))));
			}else{
				map.put("error", formatString(error));
				map.put("error_code", formatString(String.valueOf(node.get("error_code"))));
			}
			return map;
		} catch (Exception e) {
			log.debug(e);
			map.put("error", CodeList.getErrorMessage(CodeList.EC_360_ERROR) + e.getMessage());
			map.put("error_code", String.valueOf(CodeList.EC_360_ERROR));
			throw new BusinessException(e);
		}
	}

	@Override
	public Map<String, Object> refreshToken(String refreshToken, String client_id) {
		String client_sercet = Configurations.getProperty("360", "app_secret");
		String url = Configurations.getProperty("360", "preUrl") + Configurations.getProperty("360", "refreshTokenUrl");
		url = String.format(url, refreshToken, client_id, client_sercet);
		log.debug("360_refreshToken url ===>"+url);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpClient httpclient = ThreadSafeOfConnectionManager.buildDefaultHttpsClient();
			HttpGet httpGet = new HttpGet(url);
			String str = ThreadSafeOfConnectionManager.executeForBodyString(httpclient, httpGet);
			log.debug(str);
			
			ObjectMapper om = new ObjectMapper();
			JsonNode node = om.readValue(str, JsonNode.class);
			String error = String.valueOf(node.get("error"));
			if(error == null || error.equals("")){
				map.put("access_token", formatString(String.valueOf(node.get("access_token"))));
				map.put("expires_in", formatString(String.valueOf(node.get("expires_in"))));
				map.put("scope", formatString(String.valueOf(node.get("scope"))));
				map.put("refresh_token", formatString(String.valueOf(node.get("refresh_token"))));
				map.put("time", System.currentTimeMillis()/1000);
			}else{
				map.put("error", formatString(error));
				map.put("error_code", formatString(String.valueOf(node.get("error_code"))));
			} 
			return map;
		} catch (Exception e) {
			map.put("error", CodeList.getErrorMessage(CodeList.EC_360_ERROR) + e.getMessage());
			map.put("error_code", String.valueOf(CodeList.EC_360_ERROR));
			throw new BusinessException(e);
		}
	}

}

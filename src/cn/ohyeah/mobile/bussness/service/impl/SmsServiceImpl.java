package cn.ohyeah.mobile.bussness.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.ohyeah.mobile.bussness.service.ISmsService;
import cn.ohyeah.mobile.exception.BusinessException;
import cn.ohyeah.mobile.global.Configurations;
import cn.ohyeah.mobile.utils.ThreadSafeOfConnectionManager;
import cn.ohyeah.mobile.utils.Util;

@Service("service_sms")
public class SmsServiceImpl implements ISmsService{
	
	private static Log log = LogFactory.getLog(SmsServiceImpl.class);

	@Override
	public Map<String, Object> getSmsContent(String price, String imsi,
			String imei, String phope) {
		String url = Configurations.getProperty("sms", "url");
		log.debug("getSmsContent url ===>"+url);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpClient httpclient = ThreadSafeOfConnectionManager.buildDefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			List <NameValuePair> data = new ArrayList <NameValuePair>();      
	        data.add(new BasicNameValuePair("price", price));  
	        data.add(new BasicNameValuePair("appId", Configurations.getProperty("sms", "appid")));  
	        data.add(new BasicNameValuePair("imsi", imsi));  
	        data.add(new BasicNameValuePair("imei", imei));  
	        data.add(new BasicNameValuePair("phope", Util.encodeByBase64(phope)));  
	        httpPost.setEntity(new UrlEncodedFormEntity(data));
			String str = ThreadSafeOfConnectionManager.executeForBodyString(httpclient, httpPost);
			str = Util.decodeByBase64(str);
			log.debug("获取动态指令结果："+str);
			ObjectMapper om = new ObjectMapper();
			JsonNode node = om.readValue(str, JsonNode.class);
			map.put("port", formatString(String.valueOf(node.get("port"))));
			map.put("message", formatString(String.valueOf(node.get("message"))));
			map.put("code", formatString(String.valueOf(node.get("code"))));
			map.put("sms", formatString(String.valueOf(node.get("sms"))));
			return map;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	private static String formatString(String str){
		if(str.contains("\"")){
			str = str.substring(1, str.length()-1);
		}
		return str;
	}
}

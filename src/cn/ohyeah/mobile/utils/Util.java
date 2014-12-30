package cn.ohyeah.mobile.utils;

import org.apache.commons.codec.binary.Base64;

public class Util {

	/** 
	 * BASE64解码
     * @param str 
     * @return String
     */  
    public static String decodeByBase64(String str) {  
        return new String(Base64.decodeBase64(str.getBytes()));  
    }  
  
    /** 
     * BASE64编码
     * @param str 
     * @return String
     */  
    public static String encodeByBase64(String str) {  
        return new String(Base64.encodeBase64(str.getBytes()));  
    } 
    
    
    
}

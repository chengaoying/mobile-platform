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
    
    
    public static void main(String[] args){
    	System.out.println(Util.decodeByBase64("MF400d2ba731604fefbbe25f70cb698d00b30a1242dbe549f2a04f53e134ea80f1dc375e3b7fd24a4b91bf3e6229a307bf"));
    }
    
}

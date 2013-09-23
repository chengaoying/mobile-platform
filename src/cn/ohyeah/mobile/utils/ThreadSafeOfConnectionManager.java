package cn.ohyeah.mobile.utils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class ThreadSafeOfConnectionManager implements Runnable{
	
	/**
	 * 请求超时10秒钟
	 */
	private static int REQUEST_TIMEOUT = 30000;		
	
	/**
	 * 等待数据超时时间10秒钟
	 */
	private static int SO_TIMEOUT = 60000;			
	
	/**
	 * 客户端总并行链接最大数
	 */
	private static int MAXTOTAL = 400;				
	
	/**
	 * 每个主机的最大并行链接数 
	 */
	private static int MAXPERROUTE = 400;		
	
	
	private static int EXPIRE_CHECK_PERIOD = 60000;
	
	private static boolean shutdown;
	
	private static Log log = LogFactory.getLog(ThreadSafeOfConnectionManager.class);
	private static PoolingClientConnectionManager pccm;
	private static DefaultHttpClient client;
	private static ThreadSafeOfConnectionManager instance;
	
	static{
		instance = new ThreadSafeOfConnectionManager();
		pccm = new PoolingClientConnectionManager();
		pccm.setDefaultMaxPerRoute(MAXPERROUTE);	
		pccm.setMaxTotal(MAXTOTAL);	
		new Thread(instance).start();
	}
	
	/**
	 * https协议多线程的HttpClient,用httpClient4.2.5实现
	 * @return DefaultHttpClient
	 */
	public static DefaultHttpClient buildDefaultHttpsClient(){  		
		HttpParams params = new BasicHttpParams(); 
	    HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1); 
	    HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1"); 
	    HttpProtocolParams.setUseExpectContinue(params, true); 	  

	    params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, REQUEST_TIMEOUT);  
	    params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT); 
	  
	    try { 
		    SSLContext sslcontext = SSLContext.getInstance("TLS");    
	        sslcontext.init(null, new TrustManager[] { truseAllManager }, null);    
	        SSLSocketFactory sf = new SSLSocketFactory(sslcontext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);    
	        Scheme scheme = new Scheme("https", 443, sf);   	 
	        pccm.getSchemeRegistry().register(scheme);
			client = new DefaultHttpClient(pccm, params);  
	    } catch (Exception e) {    
            e.printStackTrace();    
        }  
		return client;
	}

    /**  
     * 重写验证方法，取消检测ssl  
     */    
    private static TrustManager truseAllManager = new X509TrustManager(){    
    
        public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)    
                throws CertificateException {    
        }    
        public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)    
                throws CertificateException {    
        }    
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {    
            return null;    
        }    
    };   
    
	public static ResponseHandler<String> stringHandler = new ResponseHandler<String>() {
		@Override
		public String handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity);
			} else {
				return null;
			}
		}
	};
	
	public static ResponseHandler<byte[]> byteArrayHandler = new ResponseHandler<byte[]>() {
		@Override
		public byte[] handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			} else {
				return null;
			}
		}
	};
	
	@Override
    protected void finalize() throws Throwable {
    	super.finalize();
    	shutdown = true;
    }
	
	public static <T> T execute(
			final HttpClient httpClient,
            final HttpUriRequest request,
            final ResponseHandler<? extends T> responseHandler)
                throws Exception {
		try {
			return httpClient.execute(request, responseHandler);
		}
		catch (Exception e) {
			log.error("Error occured when execute request", e);
    		if (request != null) {
    			request.abort();
    		}
			throw e;
		}
	}
	
	public static String executeForBodyString(
			final HttpClient httpClient,
            final HttpUriRequest request)
                throws Exception {
		return execute(httpClient, request, stringHandler);
	}
	
	public static byte[] executeForBodyByteArray(
			final HttpClient httpClient,
            final HttpUriRequest request)
                throws Exception {
		return execute(httpClient, request, byteArrayHandler);
	}
    
	@Override
	public void run() {
		long lastTime = System.currentTimeMillis();
		while(!shutdown){
			try{
				long currTime = System.currentTimeMillis();
				if((currTime - lastTime) >= EXPIRE_CHECK_PERIOD){
					pccm.closeExpiredConnections();
					pccm.closeIdleConnections(30, TimeUnit.SECONDS);
					lastTime = currTime;
					Thread.sleep(EXPIRE_CHECK_PERIOD);
					log.info("关闭过期连接");
				}else{
					Thread.sleep(EXPIRE_CHECK_PERIOD - (currTime - lastTime));
				}
			}catch(Throwable t){
				log.error("Error occured when loop for httpclient connection expire check", t);
			}
		}
		pccm.shutdown();
	}
    
}

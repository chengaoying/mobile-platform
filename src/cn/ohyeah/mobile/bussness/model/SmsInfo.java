package cn.ohyeah.mobile.bussness.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class SmsInfo {
	
	private String code;
	
	private String message;
	
	private String port;
	
	private String sms;
	
	
	@XmlElement
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElement
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@XmlElement
	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

}

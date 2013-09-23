package cn.ohyeah.mobile.bussness.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="result")
public class ReturnInfo<T> {
	
	private int code;
	
	private String message;
	
	private List<T> data;
	
	@XmlAttribute
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@XmlAttribute
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlElement
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}

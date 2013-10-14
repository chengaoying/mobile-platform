package cn.ohyeah.mobile.bussness.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="data")
public class PrizeInfo {

	private int productid;
	
	private String name;
	
	private int price;
	
	private int activityid;
	
	private byte[] bytes;

	@XmlElement
	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@XmlElement
	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	@XmlElement
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
}

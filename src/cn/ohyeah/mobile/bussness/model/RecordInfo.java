package cn.ohyeah.mobile.bussness.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="data")
public class RecordInfo {

	private int id;
	private int productid;
	private int recordindex;
	private java.util.Date time;
	private String data;
	
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	
	@XmlElement
	public int getRecordindex() {
		return recordindex;
	}
	public void setRecordindex(int recordindex) {
		this.recordindex = recordindex;
	}
	
	@XmlElement
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	
	@XmlElement
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}

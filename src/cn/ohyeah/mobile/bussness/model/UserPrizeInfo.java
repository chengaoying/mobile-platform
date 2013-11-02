package cn.ohyeah.mobile.bussness.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import cn.ohyeah.mobile.platform.model.Prize;

@XmlRootElement(name="data")
public class UserPrizeInfo {

	private int id;
	
	private int userid;
	
	private int prizeid;
	
	private java.util.Date time;
	
	private Prize prize;

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@XmlElement
	public int getPrizeid() {
		return prizeid;
	}

	public void setPrizeid(int prizeid) {
		this.prizeid = prizeid;
	}

	@XmlElement
	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	@XmlElement
	public Prize getPrize() {
		return prize;
	}

	public void setPrize(Prize prize) {
		this.prize = prize;
	}
	
}

package cn.ohyeah.mobile.platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 消息实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_sms")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Sms {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="userNumber")
	private String userNumber;
	
	@Column(name="linkId")
	private String linkId;
	
	@Column(name="report")
	private String report;
	
	@Column(name="srvCode")
	private String srvCode;
	
	@Column(name="feeType")
	private String feeType;
	
	@Column(name="feeCode")
	private String feeCode;
	
	@Column(name="date")
	private String date;
	
	@Column(name="msg")
	private String msg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getSrvCode() {
		return srvCode;
	}
	public void setSrvCode(String srvCode) {
		this.srvCode = srvCode;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

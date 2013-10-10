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
 * 游戏产品实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_product")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="productid", nullable=false)
	private int productid;
	
	@Column(name="productname", nullable=false)
	private String productname;
	
	@Column(name="appname", nullable=false)
	private String appname;
	
	@Column(name="provider", nullable=false)
	private String provider;
	
	@Column(name="state", nullable=false)
	private int state;
	
	@Column(name="uptime", nullable=false)
	private java.util.Date uptime;
	
	@Column(name="downtime", nullable=false)
	private java.util.Date downtime;
	
	@Column(name="producttype", nullable=false)
	private int producttype;
	
	@Column(name="downloads", nullable=false)
	private int downloads;
	
	@Column(name="authorization", nullable=false)
	private int authorization;
	
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public java.util.Date getUptime() {
		return uptime;
	}
	public void setUptime(java.util.Date uptime) {
		this.uptime = uptime;
	}
	public java.util.Date getDowntime() {
		return downtime;
	}
	public void setDowntime(java.util.Date downtime) {
		this.downtime = downtime;
	}
	public int getproducttype() {
		return producttype;
	}
	public void setProducttype(int producttype) {
		this.producttype = producttype;
	}
	public int getDownloads() {
		return downloads;
	}
	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
	public int getAuthorization() {
		return authorization;
	}
	public void setAuthorization(int authorization) {
		this.authorization = authorization;
	}
	
}

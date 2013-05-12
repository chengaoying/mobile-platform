package cn.ohyeah.statics.model;

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
 * @author xiaochen 2011-12-2
 *
 */

@Entity
@Table(name="Product")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	/*@GeneratedValue(generator = "IdGenerator")  
	@GenericGenerator(name = "IdGenerator", strategy = "increment")*/
	@Column(name="productId", nullable=false)
	private int productId;
	
	private String productName;
	
	private int productClass;
	
	private String appName;
	
	private String appType;
	
	private String description;
	
	private boolean supportDataManager;
	
	private String location;
	
	private int state;
	
	private java.util.Date updateTime;
	
	private java.util.Date createTime;
	
	private int providerID;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductClass() {
		return productClass;
	}
	public void setProductClass(int productClass) {
		this.productClass = productClass;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isSupportDataManager() {
		return supportDataManager;
	}
	public void setSupportDataManager(boolean supportDataManager) {
		this.supportDataManager = supportDataManager;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}

}

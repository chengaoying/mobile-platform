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
 * 奖品类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_prize")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Prize {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prizeid", nullable=false)
	private int prizeid;

	@Column(name="productid", nullable=false)
	private int productid;

	@Column(name="name", nullable=false)
	private String name;

	@Column(name="location", nullable=false)
	private String location;
	
	@Column(name="price", nullable=false)
	private int price;
	
	@Column(name="activityid", nullable=false)
	private int activityid;

	public int getActivityid() {
		return activityid;
	}

	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public void setPrizeid(int prizeid) {
		this.prizeid = prizeid;
	}

	public int getPrizeid() {
		return prizeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}

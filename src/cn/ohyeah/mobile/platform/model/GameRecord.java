package cn.ohyeah.mobile.platform.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 游戏记录
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_game_record")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class GameRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="recordindex", nullable=false)
	private int recordindex;
	
	@Column(name="productid", nullable=false)
	private int productid;
	
	@Column(name="time")
	private Date time;
	
	@Column(name="data")
	private String data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecordindex() {
		return recordindex;
	}
	public void setRecordindex(int recordindex) {
		this.recordindex = recordindex;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}

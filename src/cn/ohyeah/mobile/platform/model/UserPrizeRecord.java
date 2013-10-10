package cn.ohyeah.mobile.platform.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 用户中奖记录实体类
 * @author Jackey Chan
 * 
 * 难点：单向一对一映射
 * 1，外键prizeid(表中一个字段)要加上insertable=false, updatable=false
 * 2，在关联属性prize上加上@OneToOne和@JoinColumn，并且@JoinColumn的name要指向外键
 */
@Entity
@Table(name="tb_user_prize_record")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class UserPrizeRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private int id;
	
	
	@Column(name="userid", nullable=false)
	private int userid;
	
	@Column(name="prizeid", nullable=false, insertable=false, updatable=false)
	private int prizeid;
	
	@Column(name="time", nullable=false)
	private java.util.Date time;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="prizeid",unique=true)
	private Resource prize;

	public Resource getPrize() {
		return prize;
	}

	public void setPrize(Resource prize) {
		this.prize = prize;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPrizeid() {
		return prizeid;
	}

	public void setPrizeid(int prizeid) {
		this.prizeid = prizeid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}
	
	
	
}

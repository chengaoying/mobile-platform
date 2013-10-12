package cn.ohyeah.mobile.platform.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 重点：单向一对多映射，关键字@OneToMany和@JoinColumn(name="activityid")
 * @JoinColumn(name="activityid")加上这个注解的话，多的一端需要添加一个字段activityid作为外键映射，
 * 不加上的话就要另建一张表，所以方便起见加上比较好
 */
@Entity
@Table(name="tb_activity_prize")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ActivityPrize {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="activityid", nullable=false)
	private int activityid;
	
	@Column(name="starttime", nullable=false)
	private java.util.Date starttime;
	
	@Column(name="endtime", nullable=false)
	private java.util.Date endtime;
	
	@OneToMany
	@JoinColumn(name="activityid")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Prize> prizes = new HashSet<Prize>();
	
	public int getActivityid() {
		return activityid;
	}
	public void setActivityid(int activityid) {
		this.activityid = activityid;
	}
	public java.util.Date getStarttime() {
		return starttime;
	}
	public void setStarttime(java.util.Date starttime) {
		this.starttime = starttime;
	}
	public java.util.Date getEndtime() {
		return endtime;
	}
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	public Set<Prize> getPrizes() {
		return prizes;
	}
	public void setPrizes(Set<Prize> prizes) {
		this.prizes = prizes;
	}
	
}

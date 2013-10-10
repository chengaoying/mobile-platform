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

@Entity
@Table(name="tb_activity_prize")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ActivityPrize {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="starttime", nullable=false)
	private java.util.Date starttime;
	
	@Column(name="endtime", nullable=false)
	private java.util.Date endtime;
	
	@OneToMany
	@JoinColumn(name="set")
	private Set<Resource> prizes = new HashSet<Resource>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Set<Resource> getPrizes() {
		return prizes;
	}
	public void setPrizes(Set<Resource> prizes) {
		this.prizes = prizes;
	}
	
}

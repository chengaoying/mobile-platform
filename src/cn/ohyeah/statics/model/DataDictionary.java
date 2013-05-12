package cn.ohyeah.statics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="tb_dataDictionary")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DataDictionary {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private int id;
	
	@Pattern(regexp="[A-Za-z0-9]{5,20}", message="{name.illegal}")
	private String name;
	
	@Pattern(regexp="[A-Za-z0-9]{5,20}", message="{value.illegal}")
	private String value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

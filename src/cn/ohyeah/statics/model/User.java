package cn.ohyeah.statics.model;

import java.util.Date;

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
@Table(name="tb_user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private int id;
	
	@Pattern(regexp="[A-Za-z0-9]{5,20}", message="{name.illegal}")
	private String name;
	
	@Pattern(regexp = "[A-Za-z0-9]{5,20}", message = "{password.illegal}") 
	private String passWord;
	
	@Pattern(regexp = "[A-Za-z0-9]{5,20}", message = "{role.illegal}") 
	private String role;
	
	@Pattern(regexp = "[0-9]", message = "{authority.illegal}") 
	private String authority;
	
	private Date createtime;

	private Date logintime;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
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
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}

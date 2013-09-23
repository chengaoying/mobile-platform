package cn.ohyeah.mobile.platform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="tb_user")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class User {
	
	public static final byte PRIVILEGE_INVALID = -1;			/*无效*/
	public static final byte PRIVILEGE_VISITOR = 1;			/*游客*/
	public static final byte PRIVILEGE_MEMBER = 2;			/*会员*/
	public static final byte PRIVILEGE_VIP = 3;				/*vip会员*/
	public static final byte PRIVILEGE_SUPER_USER = 4;		/*超级用户*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private int id;
	
	@Column(name="accountid", nullable=false)
	private int accountid;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="email")
	private String email;
	
	@Column(name="createtime", nullable=false)
	private java.util.Date createtime;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="scores")
	private int scores;
	
	@Column(name="level")
	private int level;
	
	@Column(name="area")
	private String area;
	
	@Column(name="nick")
	private String nick;
	
	@Column(name="type", nullable=false)
	private String type;
	
	@Column(name="avatar")
	private String avatar;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}

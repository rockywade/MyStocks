package com.cxstock.pojo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

/**
 * Users entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="users")
public class Users implements java.io.Serializable {

	// Fields

	private Integer userid;
	private Set<Role> roles;
	private String logincode;
	private String password;
	private String ssyq;
	private Integer state;
	//昵称
	private String  nickname;
	//上传资料积分
	private Integer zljf;
	
	
	// Constructors

	/** default constructor */
	public Users() {
	}


	/** full constructor */
	public Users(String logincode, String password,
			Integer state) {
		this.logincode = logincode;
		this.password = password;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns=@JoinColumn(name="userid"),
            inverseJoinColumns=@JoinColumn(name="roleid")
    )
	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	public String getSsyq() {
		return ssyq;
	}


	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}


	public String getLogincode() {
		return this.logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Integer getZljf() {
		return zljf;
	}

	public void setZljf(Integer zljf) {
		this.zljf = zljf;
	}
	
	

}
package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_leadership")//院系领导
public class LeaderShip implements Serializable {
	//id
	private Integer id;
	//职工号
	private String zgh;
	//姓名
	private String xm;
	//性别
	private String xb;
	//教师手机号码
	private String phone;
	
	private String ssyq;
	
	public LeaderShip(){
		
	}
	
	public LeaderShip(Integer id,String zgh,String xm,String xb,String phone){
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
		this.id = id;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}
	
	
}

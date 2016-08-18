package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_headmaster")//班主任
public class HeadMaster implements Serializable {
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
	//班主任
	private Set<Classes> hclass;
	
	public HeadMaster(){
	}
	
	public HeadMaster(Integer id){
		this.id = id;
	}
	
	public HeadMaster(String zgh,String xm,String xb){
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
	}
	
	public HeadMaster(Integer id,String zgh,String xm,String xb,String phone,Set<Classes> hclass){
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
		this.id = id;
		this.hclass = hclass;
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
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
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="headmaster_id")  
	public Set<Classes> getHclass() {
		return hclass;
	}
	public void setHclass(Set<Classes> hclass) {
		this.hclass = hclass;
	}
}

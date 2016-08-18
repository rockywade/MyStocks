package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_expert")//专家
public class Expert implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//职工号
	private String zgh;
	//姓名
	private String xm;
	//性别
	private String xb;
	//教师手机号码
	private String phone;
	
	private String photo;
	
	private String unit;
	
	private String email;
	
	private String expertType;
	
	private String introduce;
	
	private String ssyq;
	
	public Expert(){
	}
	
	public Expert(Integer id){
		this.id = id;
	}
	
	public Expert(Integer id,String zgh,String xm,String xb,String phone,String photo,
			String unit,
			String email,
			String expertType,
			String introduce){
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
		this.id = id;
		this.photo = photo;
		this.unit = unit;
		this.email = email;
		this.expertType = expertType;
		this.introduce = introduce;
	}
	
	public Expert(Integer id,String zgh,String xm,String xb,String phone,String photo,
			String unit,
			String email,
			String expertType,
			String introduce,
			String ssyq){
		this.zgh = zgh;
		this.xm = xm;
		this.xb = xb;
		this.phone = phone;
		this.id = id;
		this.photo = photo;
		this.unit = unit;
		this.email = email;
		this.expertType = expertType;
		this.introduce = introduce;
		this.ssyq = ssyq;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpertType() {
		return expertType;
	}
	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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

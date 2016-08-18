package com.cxstock.biz.expertbespeak.dto;

import java.io.Serializable;

import com.cxstock.pojo.Expert;
import com.cxstock.pojo.Student;

public class StartExpertInfoDTO implements Serializable {
	//专家id
	private Integer id;
	//专家照片
	private String expertPhoto;
	//专家姓名
	private String expertName;
	//职工号
	private String expertZgh;
	//性别
	private String expertXb;
	//专家类别
	private String expertGenre;
	//学院
	private String expertXymc;
	//专业
	private String expertZymc;
	//邮箱
	private String expertEmail;
	//手机号
	private String expertphone;
	//单位
	private String expertUnit;
	//空余时间
	private String freetime;
	//办公地址
	private String workaddress;
	//个人简介
	private String personalInfro;
	//预约id
	private Integer seifid;
	
	
	
	public Integer getSeifid() {
		return seifid;
	}
	public void setSeifid(Integer seifid) {
		this.seifid = seifid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExpertPhoto() {
		return expertPhoto;
	}
	
	public void setExpertPhoto(String expertPhoto) {
		this.expertPhoto = expertPhoto;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	public String getExpertZgh() {
		return expertZgh;
	}
	public void setExpertZgh(String expertZgh) {
		this.expertZgh = expertZgh;
	}
	public String getExpertXb() {
		return expertXb;
	}
	public void setExpertXb(String expertXb) {
		this.expertXb = expertXb;
	}
	
	public String getExpertGenre() {
		return expertGenre;
	}
	public void setExpertGenre(String expertGenre) {
		this.expertGenre = expertGenre;
	}
	public String getExpertXymc() {
		return expertXymc;
	}
	public void setExpertXymc(String expertXymc) {
		this.expertXymc = expertXymc;
	}
	public String getExpertZymc() {
		return expertZymc;
	}
	public void setExpertZymc(String expertZymc) {
		this.expertZymc = expertZymc;
	}
	public String getExpertEmail() {
		return expertEmail;
	}
	public void setExpertEmail(String expertEmail) {
		this.expertEmail = expertEmail;
	}
	public String getExpertphone() {
		return expertphone;
	}
	public void setExpertphone(String expertphone) {
		this.expertphone = expertphone;
	}
	public String getExpertUnit() {
		return expertUnit;
	}
	public void setExpertUnit(String expertUnit) {
		this.expertUnit = expertUnit;
	}
	public String getFreetime() {
		return freetime;
	}
	public void setFreetime(String freetime) {
		this.freetime = freetime;
	}
	public String getWorkaddress() {
		return workaddress;
	}
	public void setWorkaddress(String workaddress) {
		this.workaddress = workaddress;
	}
	public String getPersonalInfro() {
		return personalInfro;
	}
	public void setPersonalInfro(String personalInfro) {
		this.personalInfro = personalInfro;
	}
	
}

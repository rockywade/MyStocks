package com.cxstock.action.student.vo;

import java.io.Serializable;
public class StudentExcel implements Serializable {
	
	//学号
	private String xh;
	//姓名
	private String xm;
	//性别
	private String xb;
	//专业名称
	private String zymc;
	//行政班 
	private String classes;
	//寝室名称
	private String qsmc;
	//寝室号
	private String qsh;
	//手机号码
	private String phone;
	
	private String fqphone;
	
	private String mqphone;
	//辅导员职工号
	private String instructor;
	//辅导员姓名
	private String instructorName;
	//辅导员手机号
	private String instructorPhone;
	//班主任职工号
	private String headmaster;
	//班主任姓名
	private String headmasterName;
	//班主任手机号
	private String headmasterPhone;
	//所属园区
	private String ssyq;
	
	public StudentExcel(){
	}
	
	
	
	public String getInstructorName() {
		return instructorName;
	}



	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}



	public String getInstructorPhone() {
		return instructorPhone;
	}



	public void setInstructorPhone(String instructorPhone) {
		this.instructorPhone = instructorPhone;
	}



	public String getHeadmasterName() {
		return headmasterName;
	}



	public void setHeadmasterName(String headmasterName) {
		this.headmasterName = headmasterName;
	}



	public String getHeadmasterPhone() {
		return headmasterPhone;
	}



	public void setHeadmasterPhone(String headmasterPhone) {
		this.headmasterPhone = headmasterPhone;
	}



	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getHeadmaster() {
		return headmaster;
	}

	public void setHeadmaster(String headmaster) {
		this.headmaster = headmaster;
	}

	public String getFqphone() {
		return fqphone;
	}

	public void setFqphone(String fqphone) {
		this.fqphone = fqphone;
	}

	public String getMqphone() {
		return mqphone;
	}

	public void setMqphone(String mqphone) {
		this.mqphone = mqphone;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
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
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getQsmc() {
		return qsmc;
	}
	public void setQsmc(String qsmc) {
		this.qsmc = qsmc;
	}
	
	

}


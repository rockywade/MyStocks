package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author root
 *申请记录表
 */
@Entity
@Table(name="tbl_leaveInfoLog")
public class LeaveInfoLog implements Serializable {

	//序号
	private static final long serialVersionUID = 1L;
	//学生学号
	private  String studentnum;
	//请假学生名称
	private String studentname;
	//行政班
	private String classcode; 
	//专业
	private String major;
	//寝室
	private String bedroom;   
	//联系电话
	private String relationtel;
	//班主任
	private String classth;
	//辅导员
	private String counsellor;
	//请假累计次数
	private  Integer totalnum;
	//操作时间
	private Date  operatTime;
	
	public LeaveInfoLog(){
		
	}
	
	@Id
	public String getStudentnum() {
		return studentnum;
	}
	public void setStudentnum(String studentnum) {
		this.studentnum = studentnum;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getClasscode() {
		return classcode;
	}
	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getBedroom() {
		return bedroom;
	}
	public void setBedroom(String bedroom) {
		this.bedroom = bedroom;
	}
	public String getRelationtel() {
		return relationtel;
	}
	public void setRelationtel(String relationtel) {
		this.relationtel = relationtel;
	}
	public String getClassth() {
		return classth;
	}
	public void setClassth(String classth) {
		this.classth = classth;
	}
	public String getCounsellor() {
		return counsellor;
	}
	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}
	public Integer getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public Date getOperatTime() {
		return operatTime;
	}

	public void setOperatTime(Date operatTime) {
		this.operatTime = operatTime;
	}
	
}

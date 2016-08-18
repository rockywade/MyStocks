
package com.cxstock.biz.leaveinfo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cxstock.pojo.LeaveInfo;

/**
 * 请假信息接收数据实体类
 * @ wzx
 * 2016-5-24
 * 
 */
public class LeaveInfoDTO {
	
	//表的标示
	private Integer  id;
	
	//请假学生名称
	private String studentname;
	
	//学号
	private String studentnum;
	
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
	
	//请假原因
	private  String  leavereason;
	
	//离校时间
	private  String  leavetime;
	
	//请假天数
	private  Integer  daysum;
	
	//返校时间
	private  String  backsctime;
	
	//父母知情
	private  String  parentsinfo;
	
	//请假累计次数
	private  Integer totalnum;
	
	//辅导员意见
	private  String tutoropinion;
	
	//院系领导员意见
	private  String schoolsopinion;
	
	//状态：待审核，通过，不通过
	private  String status;
	
	//父母电话
	private  String parentstel;
	
	//学生守则说明标示：
	private String rulesstate;
	
	//审核内容
	private  String checksopinion;
	
	//辅导员意见
	private  String tutorstatus;
	
	//院系领导意见
	private  String schoolstatus;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LeaveInfoDTO() {
		super();
	}
	
	
	
	
	
	
	
	
	

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
   
	public String getStudentnum() {
		return studentnum;
	}

	public void setStudentnum(String studentnum) {
		this.studentnum = studentnum;
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

	public String getLeavereason() {
		return leavereason;
	}

	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}


	public Integer getDaysum() {
		return daysum;
	}

	public void setDaysum(Integer daysum) {
		this.daysum = daysum;
	}


	public String getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(String leavetime) {
		this.leavetime = leavetime;
	}

	public String getBacksctime() {
		return backsctime;
	}

	public void setBacksctime(String backsctime) {
		this.backsctime = backsctime;
	}

	public String getParentsinfo() {
		return parentsinfo;
	}

	public void setParentsinfo(String parentsinfo) {
		this.parentsinfo = parentsinfo;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}

	public String getTutoropinion() {
		return tutoropinion;
	}

	public void setTutoropinion(String tutoropinion) {
		this.tutoropinion = tutoropinion;
	}

	public String getSchoolsopinion() {
		return schoolsopinion;
	}

	public void setSchoolsopinion(String schoolsopinion) {
		this.schoolsopinion = schoolsopinion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentstel() {
		return parentstel;
	}

	public void setParentstel(String parentstel) {
		this.parentstel = parentstel;
	}

	public String getRulesstate() {
		return rulesstate;
	}

	public void setRulesstate(String rulesstate) {
		this.rulesstate = rulesstate;
	}

	public String getChecksopinion() {
		return checksopinion;
	}

	public void setChecksopinion(String checksopinion) {
		this.checksopinion = checksopinion;
	}

	public String getTutorstatus() {
		return tutorstatus;
	}

	public void setTutorstatus(String tutorstatus) {
		this.tutorstatus = tutorstatus;
	}

	public String getSchoolstatus() {
		return schoolstatus;
	}

	public void setSchoolstatus(String schoolstatus) {
		this.schoolstatus = schoolstatus;
	}


}

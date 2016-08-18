package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_wheresaboutscensus")
public class Wheresaboutscensus implements Serializable {
	//登记id
	private String censusid;
	//登记人编号
	private String censususernum;	
	//登记人名称
	private String censususername;	
	//假日去向id
	private	String launchid;
	//假日去向名称
	private String launchname;
	//假日时间
	private String holidaytime;
	//班级
	private String classes;		
	//专业
	private String mojar;		
	//寝室
	private String dorm;
	//联系方式
	private String userphone;	
	//班主任
	private String teacher;	
	//辅导员
	private String counsellor;
	//去向情况
	private String wheresfact;	
	//目的地
	private String termini;	
	//紧急联系人电话
	private String urgentphone;		
	//离校时间
	private String leaveschooltime;		
	//返校时间
	private String returnschooltime;	
	//天数
	private String daysnum;		
	//父母是否知情
	private String parentsknows;	
	//父母电话
	private String parentsphone;	
	//同意学校安全协议
	private String enteragreement;	
	//统计截止时间
	private String censusendtime;
	
	@Id
	public String getCensusid() {
		return censusid;
	}
	public void setCensusid(String censusid) {
		this.censusid = censusid;
	}
	public String getCensususernum() {
		return censususernum;
	}
	public void setCensususernum(String censususernum) {
		this.censususernum = censususernum;
	}
	public String getCensususername() {
		return censususername;
	}
	public void setCensususername(String censususername) {
		this.censususername = censususername;
	}
	public String getLaunchid() {
		return launchid;
	}
	public void setLaunchid(String launchid) {
		this.launchid = launchid;
	}
	public String getLaunchname() {
		return launchname;
	}
	public void setLaunchname(String launchname) {
		this.launchname = launchname;
	}
	public String getHolidaytime() {
		return holidaytime;
	}
	public void setHolidaytime(String holidaytime) {
		this.holidaytime = holidaytime;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getMojar() {
		return mojar;
	}
	public void setMojar(String mojar) {
		this.mojar = mojar;
	}
	public String getDorm() {
		return dorm;
	}
	public void setDorm(String dorm) {
		this.dorm = dorm;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getCounsellor() {
		return counsellor;
	}
	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}
	public String getWheresfact() {
		return wheresfact;
	}
	public void setWheresfact(String wheresfact) {
		this.wheresfact = wheresfact;
	}
	public String getTermini() {
		return termini;
	}
	public void setTermini(String termini) {
		this.termini = termini;
	}
	public String getUrgentphone() {
		return urgentphone;
	}
	public void setUrgentphone(String urgentphone) {
		this.urgentphone = urgentphone;
	}
	public String getLeaveschooltime() {
		return leaveschooltime;
	}
	public void setLeaveschooltime(String leaveschooltime) {
		this.leaveschooltime = leaveschooltime;
	}
	public String getReturnschooltime() {
		return returnschooltime;
	}
	public void setReturnschooltime(String returnschooltime) {
		this.returnschooltime = returnschooltime;
	}
	public String getDaysnum() {
		return daysnum;
	}
	public void setDaysnum(String daysnum) {
		this.daysnum = daysnum;
	}
	public String getParentsknows() {
		return parentsknows;
	}
	public void setParentsknows(String parentsknows) {
		this.parentsknows = parentsknows;
	}
	public String getParentsphone() {
		return parentsphone;
	}
	public void setParentsphone(String parentsphone) {
		this.parentsphone = parentsphone;
	}
	public String getEnteragreement() {
		return enteragreement;
	}
	public void setEnteragreement(String enteragreement) {
		this.enteragreement = enteragreement;
	}
	public String getCensusendtime() {
		return censusendtime;
	}
	public void setCensusendtime(String censusendtime) {
		this.censusendtime = censusendtime;
	}
	
}

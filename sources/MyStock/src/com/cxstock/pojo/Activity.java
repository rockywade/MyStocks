package com.cxstock.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="tbl_activity")
public class Activity implements Serializable {
	//活动id
	private String activityid;
	//活动名称
	private String activityname;
	//申请人
	private String applyuser;
	//学号
	private String studentnum;	
	//学生联系方式
	private String studentphonenum;
	//组织名称
	private String organizename;
	//指导老师
	private String teacher;	
	//指导老师联系方式
	private String phonenum;
	//活动类别
	private String activitygenre;
	//活动时间
	private String activitytime;
	//所在学期
	private String inschoolterm;
	//面向对象
	private String faceobj;	
	//容量
	private int capacity;
	//报名人数
	private int attendnum;
	//活动场地
	private String activityplace;
	//时长
	private String timeofduration;	
	//活动内容
	private String activitycontent;	
	//报名截止时间
	private String signupendtime;
	//考评分
	private int score;		
	//活动申请状态
	private String applystyle;	
	//审批时间
	private String checktime;	
	 //新闻提交时间
	private String uploadnewstime;  
	//活动公示时间
	private String activitypublicitytime;
	//新闻审核状态
	private String newscheckstyle;
	//公示审核状态
	private String publicitycheckstyle;
	//理由（不同意理由或者活动考评分修改理由）
	private String refuse;
	
	public Activity(){
		
	}
	public Activity(String activityname,String applyuser,String studentnum,
			String studentphonenum,String organizename,String teacher,
			String phonenum,String activitygenre,String activitytime,
			String inschoolterm,String faceobj,int capacity,
			String activityplace,String timeofduration,
			String activitycontent,String signupendtime,
			int score){
		this.activityname = activityname;
		this.applyuser = applyuser;
		this.studentnum = studentnum;
		this.studentphonenum = studentphonenum;
		this.organizename = organizename;
		this.teacher = teacher;
		this.phonenum = phonenum;
		this.activitygenre = activitygenre;
		this.activitytime = activitytime;
		this.inschoolterm = inschoolterm;
		this.faceobj = faceobj;
		this.capacity = capacity;
		this.activityplace = activityplace;
		this.timeofduration = timeofduration;
		this.activitycontent = activitycontent;
		this.signupendtime = signupendtime;
		this.score = score;
		
	}
	
	@Id
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	public String getApplyuser() {
		return applyuser;
	}
	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}
	public String getStudentnum() {
		return studentnum;
	}
	public void setStudentnum(String studentnum) {
		this.studentnum = studentnum;
	}
	public String getStudentphonenum() {
		return studentphonenum;
	}
	public void setStudentphonenum(String studentphonenum) {
		this.studentphonenum = studentphonenum;
	}
	public String getOrganizename() {
		return organizename;
	}
	public void setOrganizename(String organizename) {
		this.organizename = organizename;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getActivitygenre() {
		return activitygenre;
	}
	public void setActivitygenre(String activitygenre) {
		this.activitygenre = activitygenre;
	}
	public String getActivitytime() {
		return activitytime;
	}
	public void setActivitytime(String activitytime) {
		this.activitytime = activitytime;
	}
	public String getInschoolterm() {
		return inschoolterm;
	}
	public void setInschoolterm(String inschoolterm) {
		this.inschoolterm = inschoolterm;
	}
	public String getFaceobj() {
		return faceobj;
	}
	public void setFaceobj(String faceobj) {
		this.faceobj = faceobj;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getAttendnum() {
		return attendnum;
	}
	public void setAttendnum(int attendnum) {
		this.attendnum = attendnum;
	}
	public String getActivityplace() {
		return activityplace;
	}
	public void setActivityplace(String activityplace) {
		this.activityplace = activityplace;
	}
	public String getTimeofduration() {
		return timeofduration;
	}
	public void setTimeofduration(String timeofduration) {
		this.timeofduration = timeofduration;
	}
	public String getActivitycontent() {
		return activitycontent;
	}
	public void setActivitycontent(String activitycontent) {
		this.activitycontent = activitycontent;
	}
	public String getSignupendtime() {
		return signupendtime;
	}
	public void setSignupendtime(String signupendtime) {
		this.signupendtime = signupendtime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getApplystyle() {
		return applystyle;
	}
	public void setApplystyle(String applystyle) {
		this.applystyle = applystyle;
	}
	public String getChecktime() {
		return checktime;
	}
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	public String getUploadnewstime() {
		return uploadnewstime;
	}
	public void setUploadnewstime(String uploadnewstime) {
		this.uploadnewstime = uploadnewstime;
	}
	public String getActivitypublicitytime() {
		return activitypublicitytime;
	}
	public void setActivitypublicitytime(String activitypublicitytime) {
		this.activitypublicitytime = activitypublicitytime;
	}
	public String getNewscheckstyle() {
		return newscheckstyle;
	}
	public void setNewscheckstyle(String newscheckstyle) {
		this.newscheckstyle = newscheckstyle;
	}
	public String getPublicitycheckstyle() {
		return publicitycheckstyle;
	}
	public void setPublicitycheckstyle(String publicitycheckstyle) {
		this.publicitycheckstyle = publicitycheckstyle;
	}
	public String getRefuse() {
		return refuse;
	}
	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}
}

package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_attend")
public class Attend implements Serializable {
	//id
	private String id;
	//编号
	private String usernum;
	//名字
	private String username;
	//专业
	private String major;
	//班级
	private String classes;
	//指导老师
	private String counsellor;
	//手机号
	private String attendstudentphonenum;
	//寝室
	private String dorm;
	//活动id
	private String activityid;
	//活动名称
	private String activityname;
	//活动类型
	private String activitygenre;
	//活动时间
	private String activitytime;
	//所在学期
	private String inschoolterm;
	//活动地点
	private String activityplace; 
	//考评分
	private int score;
	//参加状态
	private String state;
	//获得分数
	private int gotscore;
	//总分数
	private Integer gross;
	
	//private Set<Student> student;
	
	public Attend(){
		
	}
	
	public Attend(String id,String usernum,String username,String classes,String counsellor,
			String dorm,String attendstudentphonenum,String activityid,String activityname,
			String activitygenre,String activitytime,String inschoolterm,String activityplace,
			int score ){
		this.id = id;
		this.usernum = usernum;
		this.username = username;
		this.classes = classes;
		this.counsellor = counsellor;
		this.dorm = dorm;
		this.attendstudentphonenum = attendstudentphonenum;
		this.activityid = activityid;
		this.activityname = activityname;
		this.activitygenre = activitygenre;
		this.activitytime = activitytime;
		this.inschoolterm = inschoolterm;
		this.activityplace = activityplace;
		this.score = score;
	}
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getCounsellor() {
		return counsellor;
	}
	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}
	public String getAttendstudentphonenum() {
		return attendstudentphonenum;
	}
	public void setAttendstudentphonenum(String attendstudentphonenum) {
		this.attendstudentphonenum = attendstudentphonenum;
	}
	public String getDorm() {
		return dorm;
	}
	public void setDorm(String dorm) {
		this.dorm = dorm;
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
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
	public String getActivityplace() {
		return activityplace;
	}
	public void setActivityplace(String activityplace) {
		this.activityplace = activityplace;
	}
	public int getGotscore() {
		return gotscore;
	}
	public void setGotscore(int gotscore) {
		this.gotscore = gotscore;
	}
	public Integer getGross() {
		return gross;
	}
	public void setGross(Integer gross) {
		this.gross = gross;
	}
	/*@ManyToMany(mappedBy="attend")
	public Set<Student> getStudent() {
		return student;
	}
	public void setStudent(Set<Student> student) {
		this.student = student;
	}*/
	
	
	
}

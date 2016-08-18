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
@Table(name="tbl_studentbespeak")
public class Studentbespeak implements Serializable {
	//id
	private Integer stid;
	//邮箱
	private String studentemail;
	//预约时间
	private String bespeaktime;
	//预约地点
	private String bespeakplace;
	//预约类别
	private String applygenre;
	//详细信息
	private String detailinfo;
	//预约提交时间
	private String uploadbespeaktime;
	//预约状态
	private String bespeakstate;
	//学生
	private Student student;
	//专家
	private Expert expert;
	//谈话内容
	private String talkcontent;
	//预约方式,随机预约or特定预约
	private String bespeakstyle;
	//是否分配老师
	private String haveornotexpert;
	//专家反馈状态
	private String expertfeedbackstate;
	public Studentbespeak(){
		
	}
	
	public Studentbespeak(Integer stid, String studentemail,String bespeaktime,String bespeakplace,
						String applygenre,String detailinfo,String uploadbespeaktime,String bespeakstate,
						Student student,Expert expert){
		this.stid = stid;
		this.studentemail = studentemail;
		this.bespeaktime = bespeaktime;
		this.bespeakplace = bespeakplace;
		this.applygenre = applygenre;
		this.detailinfo = detailinfo;
		this.uploadbespeaktime = uploadbespeaktime;
		this.bespeakstate = bespeakstate;
		this.student = student;
		this.expert = expert;
	}
	/**
	 *get/set
	 **/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getStid() {
		return stid;
	}
	public void setStid(Integer stid) {
		this.stid = stid;
	}
	public String getStudentemail() {
		return studentemail;
	}
	public void setStudentemail(String studentemail) {
		this.studentemail = studentemail;
	}
	public String getBespeaktime() {
		return bespeaktime;
	}
	public void setBespeaktime(String bespeaktime) {
		this.bespeaktime = bespeaktime;
	}
	public String getBespeakplace() {
		return bespeakplace;
	}
	public void setBespeakplace(String bespeakplace) {
		this.bespeakplace = bespeakplace;
	}
	public String getApplygenre() {
		return applygenre;
	}
	public void setApplygenre(String applygenre) {
		this.applygenre = applygenre;
	}
	public String getDetailinfo() {
		return detailinfo;
	}
	public void setDetailinfo(String detailinfo) {
		this.detailinfo = detailinfo;
	}
	public String getUploadbespeaktime() {
		return uploadbespeaktime;
	}
	public void setUploadbespeaktime(String uploadbespeaktime) {
		this.uploadbespeaktime = uploadbespeaktime;
	}
	public String getBespeakstate() {
		return bespeakstate;
	}
	public void setBespeakstate(String bespeakstate) {
		this.bespeakstate = bespeakstate;
	}
	
	@ManyToOne
	@JoinColumn(name="student_id")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@ManyToOne
	@JoinColumn(name="expert_id")
	public Expert getExpert() {
		return expert;
	}
	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public String getTalkcontent() {
		return talkcontent;
	}

	public void setTalkcontent(String talkcontent) {
		this.talkcontent = talkcontent;
	}

	public String getBespeakstyle() {
		return bespeakstyle;
	}

	public void setBespeakstyle(String bespeakstyle) {
		this.bespeakstyle = bespeakstyle;
	}

	public String getHaveornotexpert() {
		return haveornotexpert;
	}

	public void setHaveornotexpert(String haveornotexpert) {
		this.haveornotexpert = haveornotexpert;
	}

	public String getExpertfeedbackstate() {
		return expertfeedbackstate;
	}

	public void setExpertfeedbackstate(String expertfeedbackstate) {
		this.expertfeedbackstate = expertfeedbackstate;
	}
	
}

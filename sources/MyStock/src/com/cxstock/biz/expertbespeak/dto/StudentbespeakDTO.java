package com.cxstock.biz.expertbespeak.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentbespeakDTO implements Serializable {
	//学生id
	private Integer stdid;
	//学生姓名
	private String studentName;
	//学号
	private String studentXh;
	//性别
	private String studentXb;
	//学院
	private String studentXymc;
	//专业
	private String studentZymc;
	//班级
	private String studentBjmc;
	//邮箱
	private String studentEmail;
	//手机号
	private String studentphone;
	//预约id
	private Integer stid;
	//预约详情
	//private String bespeakDetail;
	//专家名称
	private String expertName;
	//专家头像
	private String expertPhoto;
	//专家手机
	private String expertPhone;
	//专家邮箱
	private String expertEmail;
	//专家办公地址
	private String expertWorkAdd;
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
	//谈话内容
	private String talkcontent;
	//预约方式,随机预约or特定预约
	private String bespeakstyle;
	//是否分配老师
	private String haveornotexpert;
	//专家反馈状态
	private String expertfeedbackstate;
	
	public Integer getStdid() {
		return stdid;
	}
	public void setStdid(Integer stdid) {
		this.stdid = stdid;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentXh() {
		return studentXh;
	}
	public void setStudentXh(String studentXh) {
		this.studentXh = studentXh;
	}
	public String getStudentXb() {
		return studentXb;
	}
	public void setStudentXb(String studentXb) {
		this.studentXb = studentXb;
	}
	public String getStudentXymc() {
		return studentXymc;
	}
	public void setStudentXymc(String studentXymc) {
		this.studentXymc = studentXymc;
	}
	public String getStudentZymc() {
		return studentZymc;
	}
	public void setStudentZymc(String studentZymc) {
		this.studentZymc = studentZymc;
	}
	public String getStudentBjmc() {
		return studentBjmc;
	}
	public void setStudentBjmc(String studentBjmc) {
		this.studentBjmc = studentBjmc;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentphone() {
		return studentphone;
	}
	public void setStudentphone(String studentphone) {
		this.studentphone = studentphone;
	}
	public Integer getStid() {
		return stid;
	}
	public void setStid(Integer stid) {
		this.stid = stid;
	}
	/*public String getBespeakDetail() {
		return bespeakDetail;
	}
	public void setBespeakDetail(String bespeakDetail) {
		this.bespeakDetail = bespeakDetail;
	}*/
	
	public String getExpertName() {
		return expertName;
	}
	
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	
	public String getExpertPhoto() {
		return expertPhoto;
	}
	public void setExpertPhoto(String expertPhoto) {
		this.expertPhoto = expertPhoto;
	}
	public String getExpertPhone() {
		return expertPhone;
	}
	public void setExpertPhone(String expertPhone) {
		this.expertPhone = expertPhone;
	}
	public String getExpertEmail() {
		return expertEmail;
	}
	public void setExpertEmail(String expertEmail) {
		this.expertEmail = expertEmail;
	}
	public String getExpertWorkAdd() {
		return expertWorkAdd;
	}
	public void setExpertWorkAdd(String expertWorkAdd) {
		this.expertWorkAdd = expertWorkAdd;
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

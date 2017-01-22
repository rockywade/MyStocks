
package com.cxstock.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cxstock.utils.system.DateTime;

/**
 * 请假信息实体类
 * @ wzx
 * 2016-5-23
 * 
 */
@Entity
@Table(name="leaveinfo")
public class LeaveInfo implements java.io.Serializable{
     
	//序列化
	private static final long serialVersionUID = 1L;
	
	//标示
	private Integer  id; 
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
	//请假原因
	private  String  leavereason;
	//离校时间
	private  Date  leavetime;
	//请假天数
	private  Integer  daysum;
	//返校时间
	private  Date  backsctime;
	//父母知情
	private  String  parentsinfo;
	//请假累计次数
	private  Integer totalnum;
	//辅导员意见
	private  String tutorstatus;
	//辅导员审核内容
	private  String checksopinion;
	//院系状态
	private  String schoolstatus;
	//院系领导员意见
	private  String schoolsopinion;
	//状态：待审核，通过，不通过
	private  String status;
	//父母电话
	private  String parentstel;
	//学生守则说明标示：
	private String rulesstate;
	//医院证明图片
	private String image;
	
	
	public LeaveInfo() {
	}
	
	public LeaveInfo( String studentnum, String studentname,String major ,String classcode,
			String bedroom, String relationtel,String classth, String counsellor,
			String  leavereason,Date  leavetime,Integer  daysum,Date  backsctime,
			String  parentsinfo,Integer totalnum,String tutorstatus,
			String schoolsopinion,String status,String parentstel, String rulesstate,
			String checksopinion,String schoolstatus) {
		super();
		this.studentnum = studentnum;
		this.studentname = studentname;
		this.classcode = classcode;
		this.bedroom = bedroom;
		this.major = major;
		this.relationtel = relationtel;
		this.classth = classth;
		this.counsellor = counsellor;
		this.leavereason = leavereason;
		this.leavetime = leavetime;
		this.daysum = daysum;
		this.backsctime = backsctime;
		this.parentsinfo = parentsinfo;
		this.totalnum = totalnum;
		this.tutorstatus = tutorstatus;
		this.schoolsopinion = schoolsopinion;
		this.parentstel = parentstel;
		this.rulesstate = rulesstate;
		this.checksopinion = checksopinion;
		this.schoolstatus = schoolstatus;
		
		
	}
	
	public LeaveInfo(Integer id,String studentnum, String studentname,String major ,String classcode,
			String bedroom, String relationtel,String classth, String counsellor,
			String  leavereason,Date  leavetime,Integer  daysum,Date  backsctime,
			String  parentsinfo,Integer totalnum,String tutorstatus,
			String schoolsopinion,String status,String parentstel, String rulesstate,
			String checksopinion ,String schoolstatus) {
		super();
		this.studentnum = studentnum;
		this.studentname = studentname;
		this.classcode = classcode;
		this.bedroom = bedroom;
		this.major = major;
		this.relationtel = relationtel;
		this.classth = classth;
		this.counsellor = counsellor;
		this.leavereason = leavereason;
		this.leavetime = leavetime;
		this.daysum = daysum;
		this.backsctime = backsctime;
		this.parentsinfo = parentsinfo;
		this.totalnum = totalnum;
		this.tutorstatus = tutorstatus;
		this.schoolsopinion = schoolsopinion;
		this.status = status;
		this.parentstel = parentstel;
		this.rulesstate = rulesstate;
		this.checksopinion = checksopinion;
		this.schoolstatus = schoolstatus;
		
		}
	

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getLeavereason() {
		return leavereason;
	}


	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}

	public Date getLeavetime() {
		return leavetime;
	}

	public void setLeavetime(Date leavetime) {
		this.leavetime = leavetime;
	}

	public Integer getDaysum() {
		return daysum;
	}

	public void setDaysum(Integer daysum) {
		this.daysum = daysum;
	}

	public Date getBacksctime() {
		return backsctime;
	}

	public void setBacksctime(Date backsctime) {
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

	public String getStudentnum() {
		return studentnum;
	}

	public void setStudentnum(String studentnum) {
		this.studentnum = studentnum;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	 
}

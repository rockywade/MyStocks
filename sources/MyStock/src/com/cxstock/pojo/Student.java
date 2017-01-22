package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Set;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//学号
	private String xh;
	//姓名
	private String xm;
	//性别
	private String xb;
	
	private String xn;
	//班级 
	private Classes classes;
	//手机号码
	private String phone;
	//寝室号
	private String qsh;
	//所属园区
	private String ssyq;
	//寝室名称
	private String qsmc;
	//大类(相对大一)
	private  String  dalei;
	
	private String mq;
	
	private String fq;
	
	private String fqphone;
	
	private String mqphone;
	
	private String zymc;
	//辅导员
	private Instructor instructor;
	//班主任
	private HeadMaster headmaster;
	//参加活动
	private Set<Attend> attend;
	//学园
	private Lyceum  lyceum;
	//积分
	private Integer integral;
	
	public Student(){
	}
	
	public Student(Integer id){
		this.id = id;
	}
	
	public Student(String xh,String xm,String bjdm,String sjhm,String qsh){
		this.xh = xh;
		this.xm = xm;
		this.phone = sjhm;
		this.classes = new Classes(bjdm);
		this.qsh = qsh;
	}
	
	public Student(Integer id,String xh,String xm,String xb,String phone,String qsh,String qsmc,Classes classes,Instructor instructor,HeadMaster headmaster,NewFriends newfriends){
		this.id = id;
		this.xh = xh;
		this.xm = xm;
		this.xb = xb;
		this.classes = classes;
		this.phone = phone;
		this.qsh = qsh;
		this.qsmc = qsmc;
		this.instructor = instructor;
		this.headmaster = headmaster;
	}
	
	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public String getMq() {
		return mq;
	}

	public void setMq(String mq) {
		this.mq = mq;
	}

	public String getFq() {
		return fq;
	}

	public void setFq(String fq) {
		this.fq = fq;
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

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
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
	
	
	public String getDalei() {
		return dalei;
	}

	public void setDalei(String dalei) {
		this.dalei = dalei;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="classes_id")
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="instructor_id")
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="lyceum_id")
	public Lyceum getLyceum() {
		return lyceum;
	}
	
	public void setLyceum(Lyceum lyceum) {
		this.lyceum = lyceum;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="headmaster_id")
	public HeadMaster getHeadmaster() {
		return headmaster;
	}
	public void setHeadmaster(HeadMaster headmaster) {
		this.headmaster = headmaster;
	}
	
	
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="student_attend",
			joinColumns=@JoinColumn(name="studentid"),
			inverseJoinColumns=@JoinColumn(name="attendid"))
	public Set<Attend> getAttend() {
		return attend;
	}

	public void setAttend(Set<Attend> attend) {
		this.attend = attend;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
}


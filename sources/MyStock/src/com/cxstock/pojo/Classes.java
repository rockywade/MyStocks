package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_classes")
public class Classes implements Serializable {
	
	//序号化
	private static final long serialVersionUID = 1L;
	//班级代码
	private String bjdm;
	
	private String ssyq;
	
	private Set<Student> students;
	
	private Instructor instructor;
	
	private HeadMaster headMaster;
	
	
	public Classes(){
	}
	
	public Classes(String bjdm){
		this.bjdm = bjdm;
	}
	
	@OneToMany
	@JoinColumn(name="classes_id") 
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	@OneToOne
	@JoinColumn(name="headmaster_id") 
	public HeadMaster getHeadMaster() {
		return headMaster;
	}

	public void setHeadMaster(HeadMaster headMaster) {
		this.headMaster = headMaster;
	}

	
	@Id
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	
	

	@OneToOne
	@JoinColumn(name="instructor_id") 
	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getSsyq() {
		return ssyq;
	}

	public void setSsyq(String ssyq) {
		this.ssyq = ssyq;
	}
	
	
}

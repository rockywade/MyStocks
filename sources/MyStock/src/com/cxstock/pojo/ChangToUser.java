package com.cxstock.pojo;

import java.io.Serializable;

public class ChangToUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//编号
	private String userNum;
	//姓名
	private String userName;
	//性别
	private String userSex;
	//手机号
	private String userPhone;
	
	//班级
	private String userClasses;
	//园区
	private String userYq;
	//寝室
	private String userDorm;
	//指导老师(辅导员)
	private String userInstructor;
	//班主任
	private String userHeadmaster;
	
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserClasses() {
		return userClasses;
	}
	public void setUserClasses(String userClasses) {
		this.userClasses = userClasses;
	}
	public String getUserYq() {
		return userYq;
	}
	public void setUserYq(String userYq) {
		this.userYq = userYq;
	}
	public String getUserDorm() {
		return userDorm;
	}
	public void setUserDorm(String userDorm) {
		this.userDorm = userDorm;
	}
	public String getUserInstructor() {
		return userInstructor;
	}
	public void setUserInstructor(String userInstructor) {
		this.userInstructor = userInstructor;
	}
	public String getUserHeadmaster() {
		return userHeadmaster;
	}
	public void setUserHeadmaster(String userHeadmaster) {
		this.userHeadmaster = userHeadmaster;
	}
	
}

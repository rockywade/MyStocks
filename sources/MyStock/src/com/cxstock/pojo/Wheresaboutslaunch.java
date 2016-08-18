package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_wherelaunch")
public class Wheresaboutslaunch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//去向发起id
	private String launchid;
	//发起名称
	private String launchname;
	//发起开始时间
	private String launchstarttime;	
	//发起结束时间
	private String launchendtime;
	//发起时间
	private String launchtime;	
	//统计截止时间
	private String censusendtime;	
	//登计人数
	private int censuspersonnum;	
	//假日去向发起状态
	private String launchstyle;		
	
	@Id
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
	public String getLaunchstarttime() {
		return launchstarttime;
	}
	public void setLaunchstarttime(String launchstarttime) {
		this.launchstarttime = launchstarttime;
	}
	public String getLaunchendtime() {
		return launchendtime;
	}
	public void setLaunchendtime(String launchendtime) {
		this.launchendtime = launchendtime;
	}
	public String getLaunchtime() {
		return launchtime;
	}
	public void setLaunchtime(String launchtime) {
		this.launchtime = launchtime;
	}
	public String getCensusendtime() {
		return censusendtime;
	}
	public void setCensusendtime(String censusendtime) {
		this.censusendtime = censusendtime;
	}
	public int getCensuspersonnum() {
		return censuspersonnum;
	}
	public void setCensuspersonnum(int censuspersonnum) {
		this.censuspersonnum = censuspersonnum;
	}
	public String getLaunchstyle() {
		return launchstyle;
	}
	public void setLaunchstyle(String launchstyle) {
		this.launchstyle = launchstyle;
	}
	
}

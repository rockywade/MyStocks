package com.cxstock.biz.activity.dto;

import java.io.Serializable;

public class ActivityDTO implements Serializable {
	private String aid;
	private String aname;
	private String agenre;
	private Integer attendnum;
	private Integer capacity;
	
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAgenre() {
		return agenre;
	}
	public void setAgenre(String agenre) {
		this.agenre = agenre;
	}
	public Integer getAttendnum() {
		return attendnum;
	}
	public void setAttendnum(Integer attendnum) {
		this.attendnum = attendnum;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	
}

package com.cxstock.pojo;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 场地信息实体类
 * @author wzx
 *
 * 2016-5-27
 */
public class SiteInfoDTO implements Serializable{
     
	
	
	private String id;
	
	private Integer cid;
	
	private String title;
	
	private String start;
	
	private String end;
	
	private String notes;
	
	//项目id
	private String logId;
	
	
	
	public SiteInfoDTO() {
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Integer getCid() {
		return cid;
	}



	public void setCid(Integer cid) {
		this.cid = cid;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getStart() {
		return start;
	}



	public void setStart(String start) {
		this.start = start;
	}



	public String getEnd() {
		return end;
	}



	public void setEnd(String end) {
		this.end = end;
	}



	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) {
		this.notes = notes;
	}



	public String getLogId() {
		return logId;
	}



	public void setLogId(String logId) {
		this.logId = logId;
	}

}

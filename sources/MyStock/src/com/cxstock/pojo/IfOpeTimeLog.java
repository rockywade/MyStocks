package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 记录场地不对外开放
 * @author root
 */

@Entity
@Table(name="tbl_ifopetimelog")
public class IfOpeTimeLog implements Serializable{

	//序列化
	private static final long serialVersionUID = 1L;
	
	//表的标示
	private  String  opeId;
	
	//场地编号（对应场地id）
	private String  siteid;

    //不对外开放日期
	private Date  activitydate;
	
	//不对外开放时间
	private String activitytime;
	
	public  IfOpeTimeLog(){
		
	}
	
    @Id
	public String getOpeId() {
		return opeId;
	}

	public void setOpeId(String opeId) {
		this.opeId = opeId;
	}

	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public Date getActivitydate() {
		return activitydate;
	}

	public void setActivitydate(Date activitydate) {
		this.activitydate = activitydate;
	}

	public String getActivitytime() {
		return activitytime;
	}

	public void setActivitytime(String activitytime) {
		this.activitytime = activitytime;
	}
	
	
	
}

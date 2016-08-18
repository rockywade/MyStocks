package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 场地申请记录表
 * @author root
 *
 */
@Entity
@Table(name="tbl_siteinfolog")
public class SiteInfoLog implements Serializable{

	//序列化 
	private static final long serialVersionUID = 1L;
   
	//记录表id
	private String  logId;
	//场地编号(对应登录者登录号)方便查询
	private String siteid;
	//场地名称
	private String sitename;
	//场地条件（会议室）
	private String sitecondition;
	//场地类型
	private String  sitetype;
	//园区
	private String  park;
	//申请人
	private String proposer;
	//学号
	private String xh;
	//联系电话
	private String relationtel;
	//辅导员
	private String counsellor;
	//活动名称
	private String activityname;
	//活动内容
	private String activitycontent;
	//活动日期
	private Date activitydate;
	//活动时间
	private String activitytime;
	//活动类型
	private String activitytype;
	//所属组织
	private String beorganize;
	//指导老师
	private String guideth;
	//场地规模
	private Integer sitemodle;
	//是否是外校
	private String ifschool;
	//场地协议
	private String agreement;
	//审核状态
	private String status;
	//责任说明
	private String  dutystate;
	//使用时间
	private Integer usetime;
	//预约情况
	private String nowstatus;
	
	//申请时间
	private Date applyTime;
	
	@Id
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getSiteid() {
		return siteid;
	}
	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getSitecondition() {
		return sitecondition;
	}
	public void setSitecondition(String sitecondition) {
		this.sitecondition = sitecondition;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getRelationtel() {
		return relationtel;
	}
	public void setRelationtel(String relationtel) {
		this.relationtel = relationtel;
	}
	public String getCounsellor() {
		return counsellor;
	}
	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	public String getActivitycontent() {
		return activitycontent;
	}
	public void setActivitycontent(String activitycontent) {
		this.activitycontent = activitycontent;
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
	public String getActivitytype() {
		return activitytype;
	}
	public void setActivitytype(String activitytype) {
		this.activitytype = activitytype;
	}
	public String getBeorganize() {
		return beorganize;
	}
	public void setBeorganize(String beorganize) {
		this.beorganize = beorganize;
	}
	public String getGuideth() {
		return guideth;
	}
	public void setGuideth(String guideth) {
		this.guideth = guideth;
	}
	public Integer getSitemodle() {
		return sitemodle;
	}
	public void setSitemodle(Integer sitemodle) {
		this.sitemodle = sitemodle;
	}
	public String getIfschool() {
		return ifschool;
	}
	public void setIfschool(String ifschool) {
		this.ifschool = ifschool;
	}
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDutystate() {
		return dutystate;
	}
	public void setDutystate(String dutystate) {
		this.dutystate = dutystate;
	}
	public Integer getUsetime() {
		return usetime;
	}
	public void setUsetime(Integer usetime) {
		this.usetime = usetime;
	}
	public String getNowstatus() {
		return nowstatus;
	}
	public void setNowstatus(String nowstatus) {
		this.nowstatus = nowstatus;
	}
	public String getSitetype() {
		return sitetype;
	}
	public void setSitetype(String sitetype) {
		this.sitetype = sitetype;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
}

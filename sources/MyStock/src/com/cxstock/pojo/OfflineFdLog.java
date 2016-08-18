package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 线下辅导报名
 * 记录表
 * @author root
 *
 */
@Entity
@Table(name="tbl_offlineFdLog")
public class OfflineFdLog implements Serializable{
	//序列化
	private static final long serialVersionUID = 1L;
	//报名人的id（对应学生学号）
	private  String bmId;
	//报名人的名称（学生姓名）
	private  String bmName;
	//大类 
    private String dalei;
	//班级
    private String classes;
    //联系电话
	private String phone;
	//报名项目编号（对应项目的id）
	private  String xmid;
	//项目名称
	private  String xmmc;
	//报名时间
	private  Date  bmtime;
	//辅学地址
    private  String  fxaddress;
   //辅学老师
    private  String  fxteacher;
   //老师电话
    private  String  teachertel;
   //项目简介
    private  String  xmintro;
    //报名人数
    private  Integer bmnumber;
   //规模
    private  Integer  xmzise;
   //报名状态：可报名，已报名，已满员，
    private  String  bmstatus;
    //评论条数
    private Integer plnumber;
    //编辑人（项目创建人）
    private  String  creatername;
	//特别关注标示N:未关注，Y：已关注
	private String tbTag;
	//报名标示1已报名，0未报名
	private String bmTag;
	//评论内容
	private String plnr;
	//评论时间
	private Date plnrTime;
	//评论标示 Y已评论  N未评论
	private String  plTag;
	//项目序号
	private String xmxh;
	//备注
	private String bz;
	
	
	@Id
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	
	public String getBmId() {
		return bmId;
	}
	public void setBmId(String bmId) {
		this.bmId = bmId;
	}
	public String getBmName() {
		return bmName;
	}
	public void setBmName(String bmName) {
		this.bmName = bmName;
	}
	
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public Date getBmtime() {
		return bmtime;
	}
	public void setBmtime(Date bmtime) {
		this.bmtime = bmtime;
	}
	public String getTbTag() {
		return tbTag;
	}
	public void setTbTag(String tbTag) {
		this.tbTag = tbTag;
	}
	
	public String getBmTag() {
		return bmTag;
	}
	public void setBmTag(String bmTag) {
		this.bmTag = bmTag;
	}
	public String getPlnr() {
		return plnr;
	}
	public void setPlnr(String plnr) {
		this.plnr = plnr;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getFxaddress() {
		return fxaddress;
	}
	public void setFxaddress(String fxaddress) {
		this.fxaddress = fxaddress;
	}
	public String getFxteacher() {
		return fxteacher;
	}
	public void setFxteacher(String fxteacher) {
		this.fxteacher = fxteacher;
	}
	public String getTeachertel() {
		return teachertel;
	}
	public void setTeachertel(String teachertel) {
		this.teachertel = teachertel;
	}
	public String getXmintro() {
		return xmintro;
	}
	public void setXmintro(String xmintro) {
		this.xmintro = xmintro;
	}
	public Integer getBmnumber() {
		return bmnumber;
	}
	public void setBmnumber(Integer bmnumber) {
		this.bmnumber = bmnumber;
	}
	public Integer getXmzise() {
		return xmzise;
	}
	public void setXmzise(Integer xmzise) {
		this.xmzise = xmzise;
	}
	public String getBmstatus() {
		return bmstatus;
	}
	public void setBmstatus(String bmstatus) {
		this.bmstatus = bmstatus;
	}
	public Integer getPlnumber() {
		return plnumber;
	}
	public void setPlnumber(Integer plnumber) {
		this.plnumber = plnumber;
	}
	public String getCreatername() {
		return creatername;
	}
	public void setCreatername(String creatername) {
		this.creatername = creatername;
	}
	public String getDalei() {
		return dalei;
	}
	public void setDalei(String dalei) {
		this.dalei = dalei;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getXmxh() {
		return xmxh;
	}
	public void setXmxh(String xmxh) {
		this.xmxh = xmxh;
	}
	public Date getPlnrTime() {
		return plnrTime;
	}
	public void setPlnrTime(Date plnrTime) {
		this.plnrTime = plnrTime;
	}
	public String getPlTag() {
		return plTag;
	}
	public void setPlTag(String plTag) {
		this.plTag = plTag;
	}
	
}



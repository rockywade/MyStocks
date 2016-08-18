package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 朋辈辅学线下辅导信息实体类
 * @author root
 */

@Entity
@Table(name="offlinefd")
public class OfflineFd implements Serializable{
	
	 //序列化
	private static final long serialVersionUID = 1L;

	//项目id
     private  String  xmid;
     
     //项目序号
     private  String  xmxh;
     
    //辅学项目
     private  String  fxxm;
     
    //辅学时间
     private  String  fxtime;
     
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
     
     //状态：已发布，已发布/置顶/高亮，已发布/置顶，已发布/高亮，隐藏中
     private  String  status;
     
     
     //评论条数
     private Integer plnumber;
     
    //备注
     private  String  bz;
     
     //置顶时间
     private Date  topTime; 
     
     //人气
     private  Integer  renqi;
     
     //创建时间
     private  Date  createtime;
     
     //创建人id
     private  Integer  createrid;
     
    //创建人名称
     private  String  creatername;
     
    //修改时间
     private  Date  updatetime;
     
    //修改人id
     private  Integer  updaterid;
     
    //修改人名称
     private  String  updatername;
   
     
     
    @Id
	public String getXmid() {
		return xmid;
	}

	public void setXmid(String xmid) {
		this.xmid = xmid;
	}

	public String getXmxh() {
		return xmxh;
	}

	public void setXmxh(String xmxh) {
		this.xmxh = xmxh;
	}

	public String getFxxm() {
		return fxxm;
	}

	public void setFxxm(String fxxm) {
		this.fxxm = fxxm;
	}

	public String getFxtime() {
		return fxtime;
	}

	public void setFxtime(String fxtime) {
		this.fxtime = fxtime;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
	public Integer getPlnumber() {
		return plnumber;
	}

	public void setPlnumber(Integer plnumber) {
		this.plnumber = plnumber;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCreaterid() {
		return createrid;
	}

	public void setCreaterid(Integer createrid) {
		this.createrid = createrid;
	}

	public String getCreatername() {
		return creatername;
	}

	public void setCreatername(String creatername) {
		this.creatername = creatername;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getUpdaterid() {
		return updaterid;
	}

	public void setUpdaterid(Integer updaterid) {
		this.updaterid = updaterid;
	}

	public String getUpdatername() {
		return updatername;
	}

	public void setUpdatername(String updatername) {
		this.updatername = updatername;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}

	public Integer getRenqi() {
		return renqi;
	}

	public void setRenqi(Integer renqi) {
		this.renqi = renqi;
	}
     
     
}

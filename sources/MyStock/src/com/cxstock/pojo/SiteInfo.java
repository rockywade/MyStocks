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
@Entity
@Table(name="siteinfo")
public class SiteInfo implements Serializable{
     
	//标示
	private Integer id;
	
	//场地编号
	private String siteid;
	
	//场地名称
	private String sitename;
	
	//场地类型
	private String sitetype;
	
	//场地图片
	private String photo;
	
	//场地条件
	private String sitecondition;
	
	//场地规模
	private Integer sitemodle;
	
	//辅导员
	private String counsellor;
	
	//联系电话
	private String relationtel;
	
	//园区
	private String park;
	
	private  Integer  usetime;
	
	//场地协议
	private String agreement;
	
	//审核状态
	private String status;
	
	//责任说明
	private String  dutystate;
	
	//设置每周不对外开放的时间
	private  String opeTime;
	
	//创建时间
	private Date  createtime;
	
	//场地使用情况
	private String nowstatus;
	
	//创建人id
	private Integer  createrid;
	
	//创建人名称
	private String  creatername;
	
	//更新时间
	private Date  updatetime;
	
	//更新人id
	private Integer  updaterid;
	
	//更新人名称
	private String  updatername;
	
	public SiteInfo() {
	}
	
	public SiteInfo(Integer id,String siteid,String counsellor,
			String relationtel,String sitename,String sitetype,
			Integer sitemodle,String park,String photo,
			String sitecondition,String agreement,
			String status,String  dutystate){
		super();
		this.id = id;
		this.siteid = siteid;
		this.counsellor = counsellor;
		this.relationtel = relationtel;
		this.sitename = sitename;
		this.sitetype = sitetype;
		this.sitemodle = sitemodle;
		this.park = park;
		this.photo = photo;
		this.sitecondition = sitecondition;
		this.agreement = agreement;
		this.status = status;
		this.dutystate = dutystate;
		
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getSiteid() {
		return siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
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



	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getSitetype() {
		return sitetype;
	}

	public void setSitetype(String sitetype) {
		this.sitetype = sitetype;
	}



	public Integer getSitemodle() {
		return sitemodle;
	}

	public void setSitemodle(Integer sitemodle) {
		this.sitemodle = sitemodle;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSitecondition() {
		return sitecondition;
	}

	public void setSitecondition(String sitecondition) {
		this.sitecondition = sitecondition;
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

	public String getNowstatus() {
		return nowstatus;
	}

	public void setNowstatus(String nowstatus) {
		this.nowstatus = nowstatus;
	}

	public Integer getUsetime() {
		return usetime;
	}

	public void setUsetime(Integer usetime) {
		this.usetime = usetime;
	}

	public String getOpeTime() {
		return opeTime;
	}

	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}

}

package com.cxstock.biz.siteinfo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.cxstock.pojo.SiteInfo;

public class SiteInfoDTO {
	
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
	
	//活动日期
	private Date activitydate;
	
	//活动时间
	private String activitytime;
	

	//园区
	private String park;
	
	//场地协议
	private String agreement;
	
	//审核状态
	private String status;
	
	//责任说明
	private String  dutystate;
	
	
   private SiteInfoDTO(String siteid,String sitename,String counsellor,String relationtel,
	     String sitetype,Integer sitemodle,String park,String photo,
		String sitecondition,String status){
	super();
	
	this.siteid = siteid;
	this.relationtel = relationtel;
	this.counsellor = counsellor;
	this.sitename = sitename;
	this.sitetype = sitetype;
	this.sitemodle = sitemodle;
	this.park = park;
	this.photo = photo;
	this.sitecondition = sitecondition;
	this.status = status;
	}
   
	public static SiteInfoDTO createDto(SiteInfo pojo) {
		SiteInfoDTO dto = null;
		if (pojo != null) {
			dto = new SiteInfoDTO(pojo.getSiteid(),pojo.getSitename(),pojo.getCounsellor(),pojo.getRelationtel(),
					pojo.getSitetype(),	pojo.getSitemodle(),pojo.getPark(),
					pojo.getPhoto(),pojo.getSitecondition(),pojo.getStatus());
					
		}
		return dto;
	}
	
	
	public static List createDtos(Collection pojos) {
		List<SiteInfoDTO> list = new ArrayList<SiteInfoDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				SiteInfo siteInfo = (SiteInfo)iterator.next();
				SiteInfoDTO dto = createDto(siteInfo);
				list.add(dto);
			}
		}
		return list;
	}

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

	public Integer getSitemodle() {
		return sitemodle;
	}

	public void setSitemodle(Integer sitemodle) {
		this.sitemodle = sitemodle;
	}

	public String getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(String counsellor) {
		this.counsellor = counsellor;
	}

	public String getRelationtel() {
		return relationtel;
	}

	public void setRelationtel(String relationtel) {
		this.relationtel = relationtel;
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

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
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
	
	
}

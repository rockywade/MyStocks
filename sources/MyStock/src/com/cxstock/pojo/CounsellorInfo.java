package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 辅导员实体类信息
 * @author root
 *
 */

@Entity
@Table(name="counsellor")
public class CounsellorInfo implements Serializable{

	//序列化
	private static final long serialVersionUID = 1L;
	
	//辅导员id
	private String counsellorid;
	
	//职工号
	private  String zgh;
	
	//名称
	private  String xm;
	
	//性别：1男，0女
	private  String xb;
	
	//所属院系代码
	private  String ssyxdm;
	
	//所属院系名称
	private  String ssyxmc;
	
	//备注
	private  String bz;

	@Id
	public String getCounsellorid() {
		return counsellorid;
	}

	public void setCounsellorid(String counsellorid) {
		this.counsellorid = counsellorid;
	}

	public String getZgh() {
		return zgh;
	}
	
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getSsyxdm() {
		return ssyxdm;
	}

	public void setSsyxdm(String ssyxdm) {
		this.ssyxdm = ssyxdm;
	}

	public String getSsyxmc() {
		return ssyxmc;
	}

	public void setSsyxmc(String ssyxmc) {
		this.ssyxmc = ssyxmc;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
   
}

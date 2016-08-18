package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 评论信息实体类
 * @author root
 *
 */
@Entity
@Table(name="plinfo")
public class Plinfo implements Serializable{
    
	//序列化
	private static final long serialVersionUID = 1L;
	
	//评论id对应 项目id
	private  String  plxmid;
	
	//评论项目名称
	private  String  plxmtitle;
	
	//评论内容
	private  String  plnr;
	
	//评论人
	private  String  plname;
	
	//评论人id
	private  Integer  plid;
	
	//评论时间
	private  Date  pltime;
	
	//备注
	private  String  bz;

	public String getPlxmid() {
		return plxmid;
	}

	public void setPlxmid(String plxmid) {
		this.plxmid = plxmid;
	}

	public String getPlxmtitle() {
		return plxmtitle;
	}

	public void setPlxmtitle(String plxmtitle) {
		this.plxmtitle = plxmtitle;
	}

	public String getPlnr() {
		return plnr;
	}

	public void setPlnr(String plnr) {
		this.plnr = plnr;
	}

	public String getPlname() {
		return plname;
	}

	public void setPlname(String plname) {
		this.plname = plname;
	}

	public Integer getPlid() {
		return plid;
	}

	public void setPlid(Integer plid) {
		this.plid = plid;
	}

	public Date getPltime() {
		return pltime;
	}

	public void setPltime(Date pltime) {
		this.pltime = pltime;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}

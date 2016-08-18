package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 下载资料记录实体
 * @author root
 */
@Entity
@Table(name="tbl_downLog")
public class DownLog implements Serializable{

	//序列化
	private static final long serialVersionUID = 1L;
	//下载人id
	private String downid;
	//下载人名称
	private String downname;
	//下载资料编号
	private String datumnumber;
	//下载资料名称
	private String datumname;
	//资料内容
	private String  datumcontent;
	//资料大小 ：如12M 
	private  String  datumsize;
	
	//资料格式：pdf
	private  String datumstyle;
	
	//资料评分
	private Integer sharegrade;
	//评分标示Y:已评分 N未评分
	private String gradeTag;
	
	//下载时间
	private Date dowtime;
	
	//下载编号
	private String downnumber;
	
	//备注
	private String bz;
	
	public DownLog(){
		
	}

	@Id
	public String getDownid() {
		return downid;
	}

	public void setDownid(String downid) {
		this.downid = downid;
	}
	
	public String getDownname() {
		return downname;
	}
	public void setDownname(String downname) {
		this.downname = downname;
	}

	public String getDatumnumber() {
		return datumnumber;
	}

	public void setDatumnumber(String datumnumber) {
		this.datumnumber = datumnumber;
	}

	public String getDatumname() {
		return datumname;
	}

	public void setDatumname(String datumname) {
		this.datumname = datumname;
	}

	public Integer getSharegrade() {
		return sharegrade;
	}

	public void setSharegrade(Integer sharegrade) {
		this.sharegrade = sharegrade;
	}
	
	public String getGradeTag() {
		return gradeTag;
	}

	public void setGradeTag(String gradeTag) {
		this.gradeTag = gradeTag;
	}
   
	public Date getDowtime() {
		return dowtime;
	}

	public void setDowtime(Date dowtime) {
		this.dowtime = dowtime;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDatumsize() {
		return datumsize;
	}

	public void setDatumsize(String datumsize) {
		this.datumsize = datumsize;
	}

	public String getDatumstyle() {
		return datumstyle;
	}

	public void setDatumstyle(String datumstyle) {
		this.datumstyle = datumstyle;
	}

	public String getDatumcontent() {
		return datumcontent;
	}

	public void setDatumcontent(String datumcontent) {
		this.datumcontent = datumcontent;
	}

	public String getDownnumber() {
		return downnumber;
	}

	public void setDownnumber(String downnumber) {
		this.downnumber = downnumber;
	}
     
	
}

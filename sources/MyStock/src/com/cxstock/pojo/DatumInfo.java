package com.cxstock.pojo;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学习资料信息实体类
 * @author root
 */
@Entity
@Table(name="datuminfo")
public class DatumInfo implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	//标示
	private Integer  id;
	
	//资料名称
	private  String datumname;
	
	//资料编号（登录者编号）
	private String datumnumber;
	
	//资料大小 ：如12M
	private  String  datumsize;
	
	//资料格式：pdf
	private  String datumstyle;
	
	//分享人
	private  String shareman;
	
	//分享时间
	private Date  sharetime;
	
	//顶置时间
	private Date toptime;
	
	//资料评分
	private  Integer sharegrade;
	
	//资料内容
	private String  datumcontent;
	
	//下载次数
	private Integer downnum;
	
	//下载编号
	private String downnumber;
	
	//状态：待审核，展示中，隐藏中，展示/已置顶
	private String status;
	
	//传输标示:上传（1）
	private  String  transfetag;
	
	//创建时间
	private Date  createtime;
	
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
	
	public  DatumInfo(){
		
	}

    public  DatumInfo(Integer  id,String datumname,String datumnumber,String  datumsize,
    		 String datumstyle,String shareman,Date  sharetime,Date toptime,Integer sharegrade,
    		 String  datumcontent, Integer downnum,String downnumber, String status,String  transfetag
    		 ){
    	super();
    	this.id = id;
     	this.datumname = datumname;
     	this.datumnumber = datumnumber;
     	this.datumsize = datumsize;
     	this.datumstyle = datumstyle;
     	this.shareman = shareman;
     	this.sharetime = sharetime;
     	this.toptime = toptime;
     	this.sharegrade = sharegrade;
     	this.datumcontent = datumcontent;
    	this.downnum = downnum;
    	this.downnumber = downnumber;
     	this.status = status;
    	this.transfetag = transfetag;
     	
		
	}
	
    @Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDatumname() {
		return datumname;
	}

	public void setDatumname(String datumname) {
		this.datumname = datumname;
	}

	public String getDatumnumber() {
		return datumnumber;
	}

	public void setDatumnumber(String datumnumber) {
		this.datumnumber = datumnumber;
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

	public String getShareman() {
		return shareman;
	}

	public void setShareman(String shareman) {
		this.shareman = shareman;
	}

	public Date getSharetime() {
		return sharetime;
	}

	public void setSharetime(Date sharetime) {
		this.sharetime = sharetime;
	}

	public Date getToptime() {
		return toptime;
	}

	public void setToptime(Date toptime) {
		this.toptime = toptime;
	}

	public Integer getSharegrade() {
		return sharegrade;
	}

	public void setSharegrade(Integer sharegrade) {
		this.sharegrade = sharegrade;
	}
	
	public String getDatumcontent() {
		return datumcontent;
	}

	public void setDatumcontent(String datumcontent) {
		this.datumcontent = datumcontent;
	}

	public Integer getDownnum() {
		return downnum;
	}

	public void setDownnum(Integer downnum) {
		this.downnum = downnum;
	}
	public String getDownnumber() {
		return downnumber;
	}

	public void setDownnumber(String downnumber) {
		this.downnumber = downnumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransfetag() {
		return transfetag;
	}

	public void setTransfetag(String transfetag) {
		this.transfetag = transfetag;
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
	
	
}

package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_orga")
public class Orga implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String orgaName;
	private Integer orderNum;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getOrgaName() {
		return orgaName;
	}


	public void setOrgaName(String orgaName) {
		this.orgaName = orgaName;
	}


	public Integer getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


	public Orga(){
		
	}
	
	public Orga(Integer id,String orgaName,Integer orderNum){
		this.id = id;
		this.orgaName = orgaName;
		this.orderNum = orderNum;
	}
	
}

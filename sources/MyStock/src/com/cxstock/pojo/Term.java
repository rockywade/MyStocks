package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_term")
public class Term implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String termName;
	private Integer orderNum;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}




	public String getTermName() {
		return termName;
	}


	public void setTermName(String termName) {
		this.termName = termName;
	}


	public Integer getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


	public Term(){
		
	}
	
	public Term(Integer id,String termName,Integer orderNum){
		this.id = id;
		this.termName = termName;
		this.orderNum = orderNum;
	}
	
}

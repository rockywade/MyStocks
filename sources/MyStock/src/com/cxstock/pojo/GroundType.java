package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_groundtype")
public class GroundType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String typeName;
	private Integer orderNum;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public Integer getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


	public GroundType(){
		
	}
	
	public GroundType(Integer id,String typeName,Integer orderNum){
		this.id = id;
		this.typeName = typeName;
		this.orderNum = orderNum;
	}
	
}

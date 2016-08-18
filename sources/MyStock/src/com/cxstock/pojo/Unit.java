package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_unit")
public class Unit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String unitName;
	private Integer orderNum;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public Integer getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


	public Unit(){
		
	}
	
	public Unit(Integer id,String unitName,Integer orderNum){
		this.id = id;
		this.unitName = unitName;
		this.orderNum = orderNum;
	}
}

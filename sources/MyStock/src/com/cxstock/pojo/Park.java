package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_park")
public class Park implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String parkName;
	private Integer orderNum;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getParkName() {
		return parkName;
	}


	public void setParkName(String parkName) {
		this.parkName = parkName;
	}


	public Integer getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}


	public Park(){
		
	}
	
	public Park(Integer id,String parkName,Integer orderNum){
		this.id = id;
		this.parkName = parkName;
		this.orderNum = orderNum;
	}
}

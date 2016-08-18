package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学园表
 * @author root
 *
 */
@Entity
@Table(name="tbl_lyceum")
public class Lyceum implements Serializable{
	
	//序列化
	private static final long serialVersionUID = 1L;
	//标示
	private Integer  id;
	
	//学园名称
	private String lyceumName;


	public Lyceum(){
		
	}
	
	public Lyceum(Integer  id,String lyceumName){
		super();
		this.id = id;
		this.lyceumName = lyceumName;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLyceumName() {
		return lyceumName;
	}

	public void setLyceumName(String lyceumName) {
		this.lyceumName = lyceumName;
	}
	
	

}

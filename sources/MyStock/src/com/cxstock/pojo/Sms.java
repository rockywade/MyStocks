package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_sms")
public class Sms implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String smsName;
	private String content;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}



	public String getSmsName() {
		return smsName;
	}


	public void setSmsName(String smsName) {
		this.smsName = smsName;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Sms(){
		
	}
	
	public Sms(Integer id,String smsName,String content){
		this.id = id;
		this.smsName = smsName;
		this.content = content;
	}
}

package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_conversation")
public class Conversation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//谈话时间
	private String conversationtime;
	//地点
	private String conversationpalce;
	//访谈人姓名
	private String conversationname;
	//访谈人类别
	private String conversationtype;
	//主要问题
	private String problem;
	//辅导方案
	private String solution;
	//材料
	private String stuff;
	
	private Integer specialcare_id;
	
	
	public Integer getSpecialcare_id() {
		return specialcare_id;
	}


	public void setSpecialcare_id(Integer specialcareId) {
		specialcare_id = specialcareId;
	}


	public Conversation(){
	}
	
	public Conversation(String conversationtime,String conversationpalce,String conversationname,String conversationtype,String problem,String solution,String stuff,Integer specialcare_id){
		this.conversationtime = conversationtime;
		this.conversationpalce = conversationpalce;
		this.conversationname = conversationname;
		this.conversationtype = conversationtype;
		this.problem = problem;
		this.stuff = stuff;
		this.solution = solution;
		this.specialcare_id = specialcare_id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	public String getConversationtime() {
		return conversationtime;
	}


	public void setConversationtime(String conversationtime) {
		this.conversationtime = conversationtime;
	}


	public String getConversationpalce() {
		return conversationpalce;
	}


	public void setConversationpalce(String conversationpalce) {
		this.conversationpalce = conversationpalce;
	}


	public String getConversationname() {
		return conversationname;
	}


	public void setConversationname(String conversationname) {
		this.conversationname = conversationname;
	}


	public String getConversationtype() {
		return conversationtype;
	}


	public void setConversationtype(String conversationtype) {
		this.conversationtype = conversationtype;
	}


	public String getProblem() {
		return problem;
	}


	public void setProblem(String problem) {
		this.problem = problem;
	}


	public String getSolution() {
		return solution;
	}


	public void setSolution(String solution) {
		this.solution = solution;
	}


	public String getStuff() {
		return stuff;
	}


	public void setStuff(String stuff) {
		this.stuff = stuff;
	}

}


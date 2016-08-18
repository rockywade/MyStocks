package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *二手书店评论
 * @author root
 */
@Entity
@Table(name="tbl_secondbookstorejudge")
public class SecondBookStoreJudge implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	//标示
	private Integer  id;
	
	private Integer  storeid;
	
	//内容
	private String content;
	
	//评论人ID
	private  Integer judger;
	
	//评论人名称
	private  String nickname;
	
	//评论时间
	private String judgetime;
	

	public  SecondBookStoreJudge(){
		
	}

    public  SecondBookStoreJudge(Integer  id,Integer storeid,String content,Integer judger,String nickname,String judgetime){
    	super();
    	this.id = id;
    	this.storeid = storeid;
    	this.content = content;
    	this.judger = judger;
    	this.nickname = nickname;
    	this.judgetime = judgetime;
	}
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getJudger() {
		return judger;
	}

	public void setJudger(Integer judger) {
		this.judger = judger;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getJudgetime() {
		return judgetime;
	}

	public void setJudgetime(String judgetime) {
		this.judgetime = judgetime;
	}
}

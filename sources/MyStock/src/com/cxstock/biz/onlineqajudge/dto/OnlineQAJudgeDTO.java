package com.cxstock.biz.onlineqajudge.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.cxstock.biz.student.dto.StudentDTO;
import com.cxstock.pojo.OnlineQAJudge;
import com.cxstock.pojo.Student;

/**
 * 
 * @author root
 */
public class OnlineQAJudgeDTO implements Serializable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	//标示
	private Integer  id;
	
	//内容
	private String content;
	
	//评论人ID
	private  Integer judger;
	
	//评论人名称
	private  String nickname;
	
	//评论时间
	private String judgetime;
	
	private Integer selfFlg;
	

	public  OnlineQAJudgeDTO(){
		
	}

    public  OnlineQAJudgeDTO(Integer  id,String content,Integer judger,String nickname,String judgetime,Integer selfFlg){
    	super();
    	this.id = id;
    	this.content = content;
    	this.judger = judger;
    	this.nickname = nickname;
    	this.judgetime = judgetime;
    	this.selfFlg = selfFlg;
	}
    
    

	public static OnlineQAJudgeDTO createDto(OnlineQAJudge pojo,Integer userId) {
    	OnlineQAJudgeDTO dto = null;
		if (pojo != null) {
			Integer s = 0;
			if(userId.equals(pojo.getJudger())){
				s = 1;
			}
			dto = new OnlineQAJudgeDTO(pojo.getId(), pojo.getContent(),pojo.getJudger(), 
					pojo.getNickname(),pojo.getJudgetime(),s);
		}
		return dto;
	}
	
	public static List createDtos(Collection pojos,Integer userId) {
		List<OnlineQAJudgeDTO> list = new ArrayList<OnlineQAJudgeDTO>();
		if (pojos != null) {
			Iterator iterator = pojos.iterator();
			while (iterator.hasNext()) {
				OnlineQAJudge onlineQAJudge = (OnlineQAJudge)iterator.next();
				OnlineQAJudgeDTO dto = createDto(onlineQAJudge,userId);
				list.add(dto);
			}
		}
		return list;
	}
    
	public Integer getSelfFlg() {
		return selfFlg;
	}

	public void setSelfFlg(Integer selfFlg) {
		this.selfFlg = selfFlg;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

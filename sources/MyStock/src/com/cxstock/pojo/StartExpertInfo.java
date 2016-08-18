package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_startexpertinfo")
public class StartExpertInfo implements Serializable {
	
	//id
	private Integer seifid;
	
	//空余时间
	private String freetime;
	
	//办公地址
	private String workaddress;
	
	//专家
	private Expert expert;
	
	public StartExpertInfo(){
		
	}
	
	public StartExpertInfo (Integer seifid,String freetime,String workaddress,Expert expert){
		this.seifid=seifid;
		this.freetime=freetime;
		this.workaddress = workaddress;
		this.expert = expert;
	}

	/*get set 方法*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getSeifid() {
		return seifid;
	}

	public void setSeifid(Integer seifid) {
		this.seifid = seifid;
	}
	
	public String getFreetime() {
		return freetime;
	}
	
	public void setFreetime(String freetime) {
		this.freetime = freetime;
	}

	public String getWorkaddress() {
		return workaddress;
	}

	public void setWorkaddress(String workaddress) {
		this.workaddress = workaddress;
	}

	@ManyToOne
	@JoinColumn(name="expert_id")
	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	
}

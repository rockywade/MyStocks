package com.cxstock.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 上传资料积分记录表
 * @author root
 *
 */
@Entity
@Table(name="tbl_datajf")
public class DataJiFen implements Serializable{

	//序列化
	private static final long serialVersionUID = 1L;
	//id(对应的登录号)
	private Integer  id;
	//分享人(昵称)
	private  String nickname;
	//上传积分(上传一次加2分)
	private Integer  zljf;
	
	public DataJiFen(){
		
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getZljf() {
		return zljf;
	}
	public void setZljf(Integer zljf) {
		this.zljf = zljf;
	}
	
}

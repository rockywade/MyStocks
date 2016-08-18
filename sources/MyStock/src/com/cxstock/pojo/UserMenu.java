package com.cxstock.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usermenu")
public class UserMenu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String menuurl;
	private String zgh;
	private String icon;
	private String menuname;
	private Integer ordernum;
	private Integer menuid;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuurl() {
		return menuurl;
	}


	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}




	public String getZgh() {
		return zgh;
	}


	public void setZgh(String zgh) {
		this.zgh = zgh;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getMenuname() {
		return menuname;
	}


	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}


	public Integer getMenuid() {
		return menuid;
	}


	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}


	public Integer getOrdernum() {
		return ordernum;
	}


	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}


	public UserMenu(){
		
	}
	
}

package com.cxstock.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Rolemenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="rolemenu")
public class Rolemenu implements java.io.Serializable {

	// Fields
	private RolemenuId id;
	private Role role;
	private Menu menu;

	// Constructors

	/** default constructor */
	public Rolemenu() {
	}

	/** full constructor */
	public Rolemenu(RolemenuId id, Role role, Menu menu) {
		this.id = id;
		this.role = role;
		this.menu = menu;
	}

	// Property accessors
	@Id
	public RolemenuId getId() {
		return this.id;
	}

	public void setId(RolemenuId id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="roleid",insertable=false,updatable=false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn(name="menuid",insertable=false,updatable=false)
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
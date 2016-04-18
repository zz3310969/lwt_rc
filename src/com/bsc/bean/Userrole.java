package com.bsc.bean;

import java.util.Date;

/**
 * Userrole entity. @author MyEclipse Persistence Tools
 */

public class Userrole implements java.io.Serializable {

	// Fields

	private UserroleId id;
	private Systemuser systemuser;
	private Role role;
	private Date addTime;
	private String userId;
	private String roleId;
	
	
	// Constructors

	/** default constructor */
	public Userrole() {
	}

	/** minimal constructor */
	public Userrole(UserroleId id, Systemuser systemuser, Role role) {
		this.id = id;
		this.systemuser = systemuser;
		this.role = role;
	}

	/** full constructor */
	public Userrole(UserroleId id, Systemuser systemuser, Role role,
			Date addTime) {
		this.id = id;
		this.systemuser = systemuser;
		this.role = role;
		this.addTime = addTime;
	}

	// Property accessors

	public UserroleId getId() {
		return this.id;
	}

	public void setId(UserroleId id) {
		this.id = id;
	}

	public Systemuser getSystemuser() {
		return this.systemuser;
	}

	public void setSystemuser(Systemuser systemuser) {
		this.systemuser = systemuser;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	
}
package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private String roleId;
	private String roleName;
	private Date addTime;
	private Date updateTime;
	private String description;
	private Set rolerights = new HashSet(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String roleId) {
		this.roleId = roleId;
	}

	public Role(String roleId, String roleName, Date addTime, Date updateTime,
			String description) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.description = description;
	}

	/** full constructor */
	public Role(String roleId, String roleName, Date addTime,
			Date updateTime, String description, Set rolerights) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.description = description;
		this.rolerights = rolerights;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getRolerights() {
		return this.rolerights;
	}

	public void setRolerights(Set rolerights) {
		this.rolerights = rolerights;
	}


}
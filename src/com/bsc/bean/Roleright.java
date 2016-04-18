package com.bsc.bean;

import java.util.Date;

/**
 * Roleright entity. @author MyEclipse Persistence Tools
 */

public class Roleright implements java.io.Serializable {

	// Fields

	private RolerightId id;
	private Functionmodule functionmodule;
	private Role role;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Roleright() {
	}

	/** minimal constructor */
	public Roleright(RolerightId id, Functionmodule functionmodule, Role role) {
		this.id = id;
		this.functionmodule = functionmodule;
		this.role = role;
	}

	/** full constructor */
	public Roleright(RolerightId id, Functionmodule functionmodule, Role role,
			Date addTime) {
		this.id = id;
		this.functionmodule = functionmodule;
		this.role = role;
		this.addTime = addTime;
	}

	// Property accessors

	public RolerightId getId() {
		return this.id;
	}

	public void setId(RolerightId id) {
		this.id = id;
	}

	public Functionmodule getFunctionmodule() {
		return this.functionmodule;
	}

	public void setFunctionmodule(Functionmodule functionmodule) {
		this.functionmodule = functionmodule;
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

}
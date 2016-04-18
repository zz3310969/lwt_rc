package com.bsc.bean;

import java.util.Date;

/**
 * Dictgeneralsectionoffice entity. @author MyEclipse Persistence Tools
 */

public class Dictgeneralsectionoffice implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String level;
	private String fartherId;
	private String description;
	private Date addTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Dictgeneralsectionoffice() {
	}

	/** minimal constructor */
	public Dictgeneralsectionoffice(String id) {
		this.id = id;
	}

	/** full constructor */
	public Dictgeneralsectionoffice(String id, String name, String level,
			String fartherId, String description, Date addTime,
			Date updateTime) {
		this.id = id;
		this.name = name;
		this.level = level;
		this.fartherId = fartherId;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFartherId() {
		return this.fartherId;
	}

	public void setFartherId(String fartherId) {
		this.fartherId = fartherId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
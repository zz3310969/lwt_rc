package com.bsc.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Hospitalsectionoffice entity. @author MyEclipse Persistence Tools
 */

public class Hospitalsectionoffice implements java.io.Serializable {

	// Fields
	private String primaryKey;
	private String id;
	private Hospital hospital;
	private String hospitalId;
	private String hospitalName;
	private String name;
	private String level;
	private String levelText;
	private String fartherId;
	private String fartherName;
	private String description;
	private Date addTime;
	private Date updateTime;
	private Set systemusers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Hospitalsectionoffice() {
	}

	/** full constructor */
	public Hospitalsectionoffice(Hospital hospital, String name, String level,
			String fartherId, String description, Date addTime,
			Date updateTime, Set systemusers) {
		this.hospital = hospital;
		this.name = name;
		this.level = level;
		this.fartherId = fartherId;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.systemusers = systemusers;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return this.hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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

	public Set getSystemusers() {
		return this.systemusers;
	}

	public void setSystemusers(Set systemusers) {
		this.systemusers = systemusers;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getFartherName() {
		return fartherName;
	}

	public void setFartherName(String fartherName) {
		this.fartherName = fartherName;
	}

	public String getLevelText() {
		return levelText;
	}

	public void setLevelText(String levelText) {
		this.levelText = levelText;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	
	
}
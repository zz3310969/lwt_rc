package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Digitalimage entity. @author MyEclipse Persistence Tools
 */

public class Digitalimage implements java.io.Serializable {

	// Fields

	private String id;
	private String patientId;
	private String url;
	private String description;
	private Date addTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Digitalimage() {
	}

	/** minimal constructor */
	public Digitalimage(String id) {
		this.id = id;
	}

	public Digitalimage(String id, String patientId, String url,
			String description, Date addTime, Date updateTime) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.url = url;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


}
package com.bsc.bean;

import java.util.Date;

/**
 * Dictionary entity. @author MyEclipse Persistence Tools
 */

public class Dictionary implements java.io.Serializable {

	// Fields

	private Integer id;
	private Dictionarytype dictionarytype;
	private String typeId;
	private String code;
	private String name;
	private String description;
	private Date addTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Dictionary() {
	}

	/** minimal constructor */
	public Dictionary(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Dictionary(Integer id, Dictionarytype dictionarytype, String code,
			String name, String description, Date addTime,
			Date updateTime) {
		this.id = id;
		this.dictionarytype = dictionarytype;
		this.code = code;
		this.name = name;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dictionarytype getDictionarytype() {
		return this.dictionarytype;
	}

	public void setDictionarytype(Dictionarytype dictionarytype) {
		this.dictionarytype = dictionarytype;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	
	
}
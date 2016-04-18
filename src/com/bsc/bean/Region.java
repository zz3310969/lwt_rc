package com.bsc.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Region entity. @author MyEclipse Persistence Tools
 */

public class Region implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String category;
	private String fartherId;
	private Set hospitals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Region() {
	}

	/** minimal constructor */
	public Region(String id) {
		this.id = id;
	}

	/** full constructor */
	public Region(String id, String name, String category, String fartherId,
			Set hospitals) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.fartherId = fartherId;
		this.hospitals = hospitals;
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

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFartherId() {
		return this.fartherId;
	}

	public void setFartherId(String fartherId) {
		this.fartherId = fartherId;
	}

	public Set getHospitals() {
		return this.hospitals;
	}

	public void setHospitals(Set hospitals) {
		this.hospitals = hospitals;
	}

}
package com.bsc.bean;

/**
 * Nation entity. @author MyEclipse Persistence Tools
 */

public class Nation implements java.io.Serializable {

	// Fields

	private Integer code;
	private String name;

	// Constructors

	/** default constructor */
	public Nation() {
	}

	/** full constructor */
	public Nation(String name) {
		this.name = name;
	}

	// Property accessors


	public String getName() {
		return this.name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

}
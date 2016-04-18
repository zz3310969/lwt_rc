package com.bsc.bean;

import java.util.Date;

/**
 * Functionpage entity. @author MyEclipse Persistence Tools
 */

public class Functionpage implements java.io.Serializable {

	// Fields

	private FunctionpageId id;
	private Functionmodule functionmodule;
	private Webpage webpage;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Functionpage() {
	}

	/** minimal constructor */
	public Functionpage(FunctionpageId id, Functionmodule functionmodule,
			Webpage webpage) {
		this.id = id;
		this.functionmodule = functionmodule;
		this.webpage = webpage;
	}

	/** full constructor */
	public Functionpage(FunctionpageId id, Functionmodule functionmodule,
			Webpage webpage, Date addTime) {
		this.id = id;
		this.functionmodule = functionmodule;
		this.webpage = webpage;
		this.addTime = addTime;
	}

	// Property accessors

	public FunctionpageId getId() {
		return this.id;
	}

	public void setId(FunctionpageId id) {
		this.id = id;
	}

	public Functionmodule getFunctionmodule() {
		return this.functionmodule;
	}

	public void setFunctionmodule(Functionmodule functionmodule) {
		this.functionmodule = functionmodule;
	}

	public Webpage getWebpage() {
		return this.webpage;
	}

	public void setWebpage(Webpage webpage) {
		this.webpage = webpage;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
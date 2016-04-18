package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Webpage entity. @author MyEclipse Persistence Tools
 */

public class Webpage implements java.io.Serializable {

	// Fields

	private String pageId;
	private String url;
	private String pageName;
	private String description;
	private Date addTime;
	private Date updateTime;
	private Set functionpages = new HashSet(0);
	private Set linkmenus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Webpage() {
	}

	/** minimal constructor */
	public Webpage(String pageId) {
		this.pageId = pageId;
	}

	/** full constructor */
	public Webpage(String pageId, String url, String pageName,
			String description, Date addTime, Date updateTime,
			Set functionpages, Set linkmenus) {
		this.pageId = pageId;
		this.url = url;
		this.pageName = pageName;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.functionpages = functionpages;
		this.linkmenus = linkmenus;
	}

	// Property accessors

	public String getPageId() {
		return this.pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
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

	public Set getFunctionpages() {
		return this.functionpages;
	}

	public void setFunctionpages(Set functionpages) {
		this.functionpages = functionpages;
	}

	public Set getLinkmenus() {
		return this.linkmenus;
	}

	public void setLinkmenus(Set linkmenus) {
		this.linkmenus = linkmenus;
	}

}
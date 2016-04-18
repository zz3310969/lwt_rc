package com.bsc.bean;

import java.util.Date;

/**
 * Linkmenu entity. @author MyEclipse Persistence Tools
 */

public class Linkmenu implements java.io.Serializable {

	// Fields
	private String primaryKey;
	private String menuId;
	private Webpage webpage;
	private String webPageId;
	private String menuName;
	private String description;
	private String functionModuleId;
	private String parentFunctionModuleId;
	private Functionmodule functionModuleRef;
	private Date addTime;
	private Date updateTime;	
	private String menuLevel;
	private String pageUrl;
	private String sortField;
	// Constructors

	/** default constructor */
	public Linkmenu() {
	}

	/** minimal constructor */
	public Linkmenu(String menuId) {
		this.menuId = menuId;
	}

	/** full constructor */
	public Linkmenu(String menuId, Webpage webpage, String menuName,
			String description, String functionModuleId, Date addTime,
			Date updateTime) {
		this.menuId = menuId;
		this.webpage = webpage;
		this.menuName = menuName;
		this.description = description;
		this.functionModuleId = functionModuleId;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Webpage getWebpage() {
		return this.webpage;
	}

	public void setWebpage(Webpage webpage) {
		this.webpage = webpage;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFunctionModuleId() {
		return this.functionModuleId;
	}

	public void setFunctionModuleId(String functionModuleId) {
		this.functionModuleId = functionModuleId;
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

	public String getWebPageId() {
		return webPageId;
	}

	public void setWebPageId(String webPageId) {
		this.webPageId = webPageId;
	}

	public Functionmodule getFunctionModuleRef() {
		return functionModuleRef;
	}

	public void setFunctionModuleRef(Functionmodule functionModuleRef) {
		this.functionModuleRef = functionModuleRef;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	
	public String getParentFunctionModuleId() {
		return parentFunctionModuleId;
	}

	
	public void setParentFunctionModuleId(String parentFunctionModuleId) {
		this.parentFunctionModuleId = parentFunctionModuleId;
	}

	
	public String getSortField() {
		return sortField;
	}

	
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	
	
}
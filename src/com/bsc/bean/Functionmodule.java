package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Functionmodule entity. @author MyEclipse Persistence Tools
 */

public class Functionmodule implements java.io.Serializable {

	// Fields
	private String primaryKey;
	private String moduleId;
	private Functionmodule functionmodule;
	private String parentModuleId;
	private String moduleName;
	private String moduleLevel;
	private String moduleLevelText;
	private String sortField;
	private String isPublic;
	private String description;
	private Date addTime;
	private Date updateTime;
	private Set functionmodules = new HashSet(0);
	private Set functionpages = new HashSet(0);

	// Constructors

	/** default constructor */
	public Functionmodule() {
	}

	/** minimal constructor */
	public Functionmodule(String moduleId) {
		this.moduleId = moduleId;
	}
	
	
	

	public Functionmodule(String moduleId, String moduleName) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}

	/** full constructor */
	public Functionmodule(String moduleId, Functionmodule functionmodule,
			String moduleName, String moduleLevel, String sortField,
			String isPublic, String description, Date addTime,
			Date updateTime, Set functionmodules,
			Set functionpages) {
		this.moduleId = moduleId;
		this.functionmodule = functionmodule;
		this.moduleName = moduleName;
		this.moduleLevel = moduleLevel;
		this.sortField = sortField;
		this.isPublic = isPublic;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.functionmodules = functionmodules;
		this.functionpages = functionpages;
	}

	
	
	// Property accessors

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Functionmodule getFunctionmodule() {
		return this.functionmodule;
	}

	public void setFunctionmodule(Functionmodule functionmodule) {
		this.functionmodule = functionmodule;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleLevel() {
		return this.moduleLevel;
	}

	public void setModuleLevel(String moduleLevel) {
		this.moduleLevel = moduleLevel;
	}

	public String getSortField() {
		return this.sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
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

	public Set getFunctionmodules() {
		return this.functionmodules;
	}

	public void setFunctionmodules(Set functionmodules) {
		this.functionmodules = functionmodules;
	}

	public Set getFunctionpages() {
		return this.functionpages;
	}

	public void setFunctionpages(Set functionpages) {
		this.functionpages = functionpages;
	}

	public String getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(String parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public String getModuleLevelText() {
		return moduleLevelText;
	}

	public void setModuleLevelText(String moduleLevelText) {
		this.moduleLevelText = moduleLevelText;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	
	
}
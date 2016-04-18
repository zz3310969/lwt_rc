package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Loginlog entity. @author MyEclipse Persistence Tools
 */

public class Loginlog implements java.io.Serializable {

	// Fields

	private String logId;
	private Systemuser systemuser;
	private Date loginTime;
	private Date logoutTime;
	private Integer durationTime;
	private Set operationlogs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Loginlog() {
	}

	/** minimal constructor */
	public Loginlog(String logId) {
		this.logId = logId;
	}

	/** full constructor */
	public Loginlog(String logId, Systemuser systemuser, Date loginTime,
			Date logoutTime, Integer durationTime, Set operationlogs) {
		this.logId = logId;
		this.systemuser = systemuser;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.durationTime = durationTime;
		this.operationlogs = operationlogs;
	}

	// Property accessors

	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Systemuser getSystemuser() {
		return this.systemuser;
	}

	public void setSystemuser(Systemuser systemuser) {
		this.systemuser = systemuser;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Integer getDurationTime() {
		return this.durationTime;
	}

	public void setDurationTime(Integer durationTime) {
		this.durationTime = durationTime;
	}

	public Set getOperationlogs() {
		return this.operationlogs;
	}

	public void setOperationlogs(Set operationlogs) {
		this.operationlogs = operationlogs;
	}

}
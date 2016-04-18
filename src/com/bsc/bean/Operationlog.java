package com.bsc.bean;

import java.util.Date;

/**
 * Operationlog entity. @author MyEclipse Persistence Tools
 */

public class Operationlog implements java.io.Serializable {

	// Fields

	private String primaryKey;
	private String id;
	private Loginlog loginlog;
	private Systemuser systemuser;
	private String systemUserId;
	private String userName;
	private String operationType;
	private String operationTypeText;
	private String operationTable;
	private String operationContent;
	private String isSuccess;
	private String isSuccessText;
	private String resultCode;
	private String objectRecordId;
	private String resultMessage;
	private Date addTime;

	// Constructors
	/** default constructor */
	public Operationlog() {
	}

	/** minimal constructor */
	public Operationlog(String id) {
		this.id = id;
	}

	/** full constructor */
	public Operationlog(String id, Loginlog loginlog, Systemuser systemuser,
			String operationType, String operationTable,
			String operationContent, String isSuccess, String resultCode,
			String objectRecordId, String reusltMessage, Date addTime) {
		this.id = id;
		this.loginlog = loginlog;
		this.systemuser = systemuser;
		this.operationType = operationType;
		this.operationTable = operationTable;
		this.operationContent = operationContent;
		this.isSuccess = isSuccess;
		this.resultCode = resultCode;
		this.objectRecordId = objectRecordId;
		this.resultMessage = resultMessage;
		this.addTime = addTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Loginlog getLoginlog() {
		return this.loginlog;
	}

	public void setLoginlog(Loginlog loginlog) {
		this.loginlog = loginlog;
	}

	public Systemuser getSystemuser() {
		return this.systemuser;
	}

	public void setSystemuser(Systemuser systemuser) {
		this.systemuser = systemuser;
	}

	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationTable() {
		return this.operationTable;
	}

	public void setOperationTable(String operationTable) {
		this.operationTable = operationTable;
	}

	public String getOperationContent() {
		return this.operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public String getIsSuccess() {
		return this.isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getObjectRecordId() {
		return this.objectRecordId;
	}

	public void setObjectRecordId(String objectRecordId) {
		this.objectRecordId = objectRecordId;
	}


	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperationTypeText() {
		return operationTypeText;
	}

	public void setOperationTypeText(String operationTypeText) {
		this.operationTypeText = operationTypeText;
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	
	public String getSystemUserId() {
		return systemUserId;
	}

	
	public void setSystemUserId(String systemUserId) {
		this.systemUserId = systemUserId;
	}

	public String getIsSuccessText() {
		return isSuccessText;
	}

	public void setIsSuccessText(String isSuccessText) {
		this.isSuccessText = isSuccessText;
	}

	
	
}
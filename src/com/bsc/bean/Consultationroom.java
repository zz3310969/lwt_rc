package com.bsc.bean;

import java.util.Date;

/**
 * Consultationroom entity. @author MyEclipse Persistence Tools
 */

public class Consultationroom implements java.io.Serializable {

	// Fields
	private String primaryKey;
	private String id;
	private Hospital hospital;
	private String hospitalId;
	private String hospitalName;
	private String name;
	private String ip;
	private String deviceType;
	private String deviceTypeText;
	private String description;
	private Date addTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Consultationroom() {
	}

	/** minimal constructor */
	public Consultationroom(String id) {
		this.id = id;
	}

	/** full constructor */
	public Consultationroom(String id, Hospital hospital, String name,
			String ip, String deviceType, String description, Date addTime,
			Date updateTime) {
		this.id = id;
		this.hospital = hospital;
		this.name = name;
		this.ip = ip;
		this.deviceType = deviceType;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return this.hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
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

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getDeviceTypeText() {
		return deviceTypeText;
	}

	public void setDeviceTypeText(String deviceTypeText) {
		this.deviceTypeText = deviceTypeText;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

}
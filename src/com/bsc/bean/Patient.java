package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Patient entity. @author MyEclipse Persistence Tools
 */

public class Patient implements java.io.Serializable {

	// Fields

	private String id;
	private Hospital hospital;
	private String name;
	private String gender;
	private String marriage;
	private Date born;
	private String idType;
	private String idNumber;
	private Date hospitalTime;
	private String createrId;
	private String currentAddress;
	private String contactName;
	private String contactPhone;
	private String telephone;
	private String description;
	private Date addTime;
	private Date updateTime;
	private String nation;
	private String shebao;
	private String workunit;

	// Constructors

	/** default constructor */
	public Patient() {
	}

	/** minimal constructor */
	public Patient(String id) {
		this.id = id;
	}

	public Patient(String id, Hospital hospital, String name, String gender,
			String marriage, Date born, String idType, String idNumber,
			Date hospitalTime, String createrId, String currentAddress,
			String contactName, String contactPhone, String telephone,
			String description, Date addTime, Date updateTime, String nation,
			String shebao, String workunit) {
		super();
		this.id = id;
		this.hospital = hospital;
		this.name = name;
		this.gender = gender;
		this.marriage = marriage;
		this.born = born;
		this.idType = idType;
		this.idNumber = idNumber;
		this.hospitalTime = hospitalTime;
		this.createrId = createrId;
		this.currentAddress = currentAddress;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.telephone = telephone;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.nation = nation;
		this.shebao = shebao;
		this.workunit = workunit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getHospitalTime() {
		return hospitalTime;
	}

	public void setHospitalTime(Date hospitalTime) {
		this.hospitalTime = hospitalTime;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getShebao() {
		return shebao;
	}

	public void setShebao(String shebao) {
		this.shebao = shebao;
	}

	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}



}
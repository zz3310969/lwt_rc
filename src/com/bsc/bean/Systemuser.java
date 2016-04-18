package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Systemuser entity. @author MyEclipse Persistence Tools
 */

public class Systemuser implements java.io.Serializable {

	// Fields

	private String primaryKey;
	private String userId;
	private Hospitalsectionoffice hospitalsectionoffice;
	private String hospitalSectionOfficeId;
	private String hospitalSectionOfficeName;
	private Hospital hospital;
	private String hospitalId;
	private String hospitalName;
	private String userName;
	private String realName;
	private String gender;
	private String genderText;
	private String onlineFlag;
	private String deleteFlag;
	private String idNumber;
	private String password;
	private String description;
	private String telephone;
	private String photo;
	private String email;
	private Date addTime;
	private Date updateTime;
	private Dictionary dict;
	private Set operationlogs = new HashSet(0);
	private Set userroles = new HashSet(0);

	public Systemuser() {
		super();
	}

	public Systemuser(String primaryKey, String userId,
			Hospitalsectionoffice hospitalsectionoffice, Hospital hospital,
			String hospitalId, String hospitalSectionOfficeId, String userName,
			String realName, String gender, String genderText,
			String onlineFlag, String deleteFlag, String idNumber,
			String password, String description, String telephone,
			String photo, String email, Date addTime, Date updateTime,
			Dictionary dict, Set operationlogs, Set userroles) {
		super();
		this.primaryKey = primaryKey;
		this.userId = userId;
		this.hospitalsectionoffice = hospitalsectionoffice;
		this.hospital = hospital;
		this.hospitalId = hospitalId;
		this.hospitalSectionOfficeId = hospitalSectionOfficeId;
		this.userName = userName;
		this.realName = realName;
		this.gender = gender;
		this.genderText = genderText;
		this.onlineFlag = onlineFlag;
		this.deleteFlag = deleteFlag;
		this.idNumber = idNumber;
		this.password = password;
		this.description = description;
		this.telephone = telephone;
		this.photo = photo;
		this.email = email;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.dict = dict;
		this.operationlogs = operationlogs;
		this.userroles = userroles;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Hospitalsectionoffice getHospitalsectionoffice() {
		return hospitalsectionoffice;
	}

	public void setHospitalsectionoffice(
			Hospitalsectionoffice hospitalsectionoffice) {
		this.hospitalsectionoffice = hospitalsectionoffice;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalSectionOfficeId() {
		return hospitalSectionOfficeId;
	}

	public void setHospitalSectionOfficeId(String hospitalSectionOfficeId) {
		this.hospitalSectionOfficeId = hospitalSectionOfficeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGenderText() {
		return genderText;
	}

	public void setGenderText(String genderText) {
		this.genderText = genderText;
	}

	public String getOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Dictionary getDict() {
		return dict;
	}

	public void setDict(Dictionary dict) {
		this.dict = dict;
	}

	public Set getOperationlogs() {
		return operationlogs;
	}

	public void setOperationlogs(Set operationlogs) {
		this.operationlogs = operationlogs;
	}

	public Set getUserroles() {
		return userroles;
	}

	public void setUserroles(Set userroles) {
		this.userroles = userroles;
	}

	public String getHospitalSectionOfficeName() {
		return hospitalSectionOfficeName;
	}

	public void setHospitalSectionOfficeName(String hospitalSectionOfficeName) {
		this.hospitalSectionOfficeName = hospitalSectionOfficeName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

}
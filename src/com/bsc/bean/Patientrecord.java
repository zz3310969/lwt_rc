package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Patientrecord entity. @author MyEclipse Persistence Tools
 */

public class Patientrecord implements java.io.Serializable {

	// Fields

	private String id;
	private Patient patient;
	private String representor;
	private String chiefComplaint;
	private String historyPresentIllness;
	private String historyPastIllness;
	private String historyPersonal;
	private String historyFamily;
	private String physicalExam;
	private String inHospitalDiagnostic;
	private String confirmedDiagnostic;
	private Date createDate;
	private String description;
	private Date addTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Patientrecord() {
	}

	/** minimal constructor */
	public Patientrecord(String id) {
		this.id = id;
	}

	public Patientrecord(String id, Patient patient, String representor,
			String chiefComplaint, String historyPresentIllness,
			String historyPastIllness, String historyPersonal,
			String historyFamily, String physicalExam,
			String inHospitalDiagnostic, String confirmedDiagnostic,
			Date createDate, String description, Date addTime, Date updateTime) {
		super();
		this.id = id;
		this.patient = patient;
		this.representor = representor;
		this.chiefComplaint = chiefComplaint;
		this.historyPresentIllness = historyPresentIllness;
		this.historyPastIllness = historyPastIllness;
		this.historyPersonal = historyPersonal;
		this.historyFamily = historyFamily;
		this.physicalExam = physicalExam;
		this.inHospitalDiagnostic = inHospitalDiagnostic;
		this.confirmedDiagnostic = confirmedDiagnostic;
		this.createDate = createDate;
		this.description = description;
		this.addTime = addTime;
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getRepresentor() {
		return representor;
	}

	public void setRepresentor(String representor) {
		this.representor = representor;
	}

	public String getChiefComplaint() {
		return chiefComplaint;
	}

	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public String getHistoryPresentIllness() {
		return historyPresentIllness;
	}

	public void setHistoryPresentIllness(String historyPresentIllness) {
		this.historyPresentIllness = historyPresentIllness;
	}

	public String getHistoryPastIllness() {
		return historyPastIllness;
	}

	public void setHistoryPastIllness(String historyPastIllness) {
		this.historyPastIllness = historyPastIllness;
	}

	public String getHistoryPersonal() {
		return historyPersonal;
	}

	public void setHistoryPersonal(String historyPersonal) {
		this.historyPersonal = historyPersonal;
	}

	public String getHistoryFamily() {
		return historyFamily;
	}

	public void setHistoryFamily(String historyFamily) {
		this.historyFamily = historyFamily;
	}

	public String getPhysicalExam() {
		return physicalExam;
	}

	public void setPhysicalExam(String physicalExam) {
		this.physicalExam = physicalExam;
	}

	public String getInHospitalDiagnostic() {
		return inHospitalDiagnostic;
	}

	public void setInHospitalDiagnostic(String inHospitalDiagnostic) {
		this.inHospitalDiagnostic = inHospitalDiagnostic;
	}

	public String getConfirmedDiagnostic() {
		return confirmedDiagnostic;
	}

	public void setConfirmedDiagnostic(String confirmedDiagnostic) {
		this.confirmedDiagnostic = confirmedDiagnostic;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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


}
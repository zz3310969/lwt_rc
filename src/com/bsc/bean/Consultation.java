package com.bsc.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Consultation entity. @author MyEclipse Persistence Tools
 */

public class Consultation implements java.io.Serializable {

	// Fields

	private String id;
	private Patient patient;
	private String callerId;
	private String receiverId;
	private String consulationMode;
	private String consulationType;
	private String chargeType;
	private String description;
	private String conObjective;
	private String conFile;
	private String sectionOfficeId;
	private String requestDoctorId;
	private String requestDoctorId2;
	private String requestDoctorId3;
	private String requestDoctorId4;
	private String actualDoctorId;
	private String actualDoctorId2;
	private String actualDoctorId3;
	private String actualDoctorId4;
	private Date requestTime;
	private Date processRequestTime;
	private Date arrangeConTime;
	private Date beginConTime;
	private Date endConTime;
	private String callerConRoomId;
	private String receiverConRoomId;
	private String diagnosis;
	private String diagnosisFile;
	private String notPassResult;
	private String cancelConResult;
	private Date addTime;
	private Date updateTime;
	private String consultationStatus;
	private String hospitalNumber;
	private String conditions;
	private String Conabstract;
	private String parpose;
	private Hospital callHospital;
	private Hospital receiverHospital;
	private String diagnosisReport;
	private String reportUser;
	private String revDiag;
	private String opinion;
	// Constructors

	/** default constructor */
	public Consultation() {
	}

	public Consultation(String id, Patient patient, String callerId,
			String receiverId, String consulationMode, String consulationType,
			String chargeType, String description, String conObjective,
			String conFile, String sectionOfficeId, String requestDoctorId,
			String requestDoctorId2, String requestDoctorId3,
			String requestDoctorId4, String actualDoctorId,
			String actualDoctorId2, String actualDoctorId3,
			String actualDoctorId4, Date requestTime, Date processRequestTime,
			Date arrangeConTime, Date beginConTime, Date endConTime,
			String callerConRoomId, String receiverConRoomId, String diagnosis,
			String diagnosisFile, String notPassResult, String cancelConResult,
			Date addTime, Date updateTime, String consultationStatus,
			String hospitalNumber, String conditions, String conabstract,
			String parpose, Hospital callHospital, Hospital receiverHospital,
			String diagnosisReport, String reportUser, String revDiag,
			String opinion) {
		super();
		this.id = id;
		this.patient = patient;
		this.callerId = callerId;
		this.receiverId = receiverId;
		this.consulationMode = consulationMode;
		this.consulationType = consulationType;
		this.chargeType = chargeType;
		this.description = description;
		this.conObjective = conObjective;
		this.conFile = conFile;
		this.sectionOfficeId = sectionOfficeId;
		this.requestDoctorId = requestDoctorId;
		this.requestDoctorId2 = requestDoctorId2;
		this.requestDoctorId3 = requestDoctorId3;
		this.requestDoctorId4 = requestDoctorId4;
		this.actualDoctorId = actualDoctorId;
		this.actualDoctorId2 = actualDoctorId2;
		this.actualDoctorId3 = actualDoctorId3;
		this.actualDoctorId4 = actualDoctorId4;
		this.requestTime = requestTime;
		this.processRequestTime = processRequestTime;
		this.arrangeConTime = arrangeConTime;
		this.beginConTime = beginConTime;
		this.endConTime = endConTime;
		this.callerConRoomId = callerConRoomId;
		this.receiverConRoomId = receiverConRoomId;
		this.diagnosis = diagnosis;
		this.diagnosisFile = diagnosisFile;
		this.notPassResult = notPassResult;
		this.cancelConResult = cancelConResult;
		this.addTime = addTime;
		this.updateTime = updateTime;
		this.consultationStatus = consultationStatus;
		this.hospitalNumber = hospitalNumber;
		this.conditions = conditions;
		Conabstract = conabstract;
		this.parpose = parpose;
		this.callHospital = callHospital;
		this.receiverHospital = receiverHospital;
		this.diagnosisReport = diagnosisReport;
		this.reportUser = reportUser;
		this.revDiag = revDiag;
		this.opinion = opinion;
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

	public String getCallerId() {
		return callerId;
	}

	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getConsulationMode() {
		return consulationMode;
	}

	public void setConsulationMode(String consulationMode) {
		this.consulationMode = consulationMode;
	}

	public String getConsulationType() {
		return consulationType;
	}

	public void setConsulationType(String consulationType) {
		this.consulationType = consulationType;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getConObjective() {
		return conObjective;
	}

	public void setConObjective(String conObjective) {
		this.conObjective = conObjective;
	}

	public String getConFile() {
		return conFile;
	}

	public void setConFile(String conFile) {
		this.conFile = conFile;
	}

	public String getSectionOfficeId() {
		return sectionOfficeId;
	}

	public void setSectionOfficeId(String sectionOfficeId) {
		this.sectionOfficeId = sectionOfficeId;
	}

	public String getRequestDoctorId() {
		return requestDoctorId;
	}

	public void setRequestDoctorId(String requestDoctorId) {
		this.requestDoctorId = requestDoctorId;
	}

	public String getRequestDoctorId2() {
		return requestDoctorId2;
	}

	public void setRequestDoctorId2(String requestDoctorId2) {
		this.requestDoctorId2 = requestDoctorId2;
	}

	public String getRequestDoctorId3() {
		return requestDoctorId3;
	}

	public void setRequestDoctorId3(String requestDoctorId3) {
		this.requestDoctorId3 = requestDoctorId3;
	}

	public String getRequestDoctorId4() {
		return requestDoctorId4;
	}

	public void setRequestDoctorId4(String requestDoctorId4) {
		this.requestDoctorId4 = requestDoctorId4;
	}

	public String getActualDoctorId() {
		return actualDoctorId;
	}

	public void setActualDoctorId(String actualDoctorId) {
		this.actualDoctorId = actualDoctorId;
	}

	public String getActualDoctorId2() {
		return actualDoctorId2;
	}

	public void setActualDoctorId2(String actualDoctorId2) {
		this.actualDoctorId2 = actualDoctorId2;
	}

	public String getActualDoctorId3() {
		return actualDoctorId3;
	}

	public void setActualDoctorId3(String actualDoctorId3) {
		this.actualDoctorId3 = actualDoctorId3;
	}

	public String getActualDoctorId4() {
		return actualDoctorId4;
	}

	public void setActualDoctorId4(String actualDoctorId4) {
		this.actualDoctorId4 = actualDoctorId4;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getProcessRequestTime() {
		return processRequestTime;
	}

	public void setProcessRequestTime(Date processRequestTime) {
		this.processRequestTime = processRequestTime;
	}

	public Date getArrangeConTime() {
		return arrangeConTime;
	}

	public void setArrangeConTime(Date arrangeConTime) {
		this.arrangeConTime = arrangeConTime;
	}

	public Date getBeginConTime() {
		return beginConTime;
	}

	public void setBeginConTime(Date beginConTime) {
		this.beginConTime = beginConTime;
	}

	public Date getEndConTime() {
		return endConTime;
	}

	public void setEndConTime(Date endConTime) {
		this.endConTime = endConTime;
	}

	public String getCallerConRoomId() {
		return callerConRoomId;
	}

	public void setCallerConRoomId(String callerConRoomId) {
		this.callerConRoomId = callerConRoomId;
	}

	public String getReceiverConRoomId() {
		return receiverConRoomId;
	}

	public void setReceiverConRoomId(String receiverConRoomId) {
		this.receiverConRoomId = receiverConRoomId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDiagnosisFile() {
		return diagnosisFile;
	}

	public void setDiagnosisFile(String diagnosisFile) {
		this.diagnosisFile = diagnosisFile;
	}

	public String getNotPassResult() {
		return notPassResult;
	}

	public void setNotPassResult(String notPassResult) {
		this.notPassResult = notPassResult;
	}

	public String getCancelConResult() {
		return cancelConResult;
	}

	public void setCancelConResult(String cancelConResult) {
		this.cancelConResult = cancelConResult;
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

	public String getConsultationStatus() {
		return consultationStatus;
	}

	public void setConsultationStatus(String consultationStatus) {
		this.consultationStatus = consultationStatus;
	}

	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getConabstract() {
		return Conabstract;
	}

	public void setConabstract(String conabstract) {
		Conabstract = conabstract;
	}

	public String getParpose() {
		return parpose;
	}

	public void setParpose(String parpose) {
		this.parpose = parpose;
	}

	public Hospital getCallHospital() {
		return callHospital;
	}

	public void setCallHospital(Hospital callHospital) {
		this.callHospital = callHospital;
	}

	public Hospital getReceiverHospital() {
		return receiverHospital;
	}

	public void setReceiverHospital(Hospital receiverHospital) {
		this.receiverHospital = receiverHospital;
	}

	public String getDiagnosisReport() {
		return diagnosisReport;
	}

	public void setDiagnosisReport(String diagnosisReport) {
		this.diagnosisReport = diagnosisReport;
	}

	public String getReportUser() {
		return reportUser;
	}

	public void setReportUser(String reportUser) {
		this.reportUser = reportUser;
	}

	public String getRevDiag() {
		return revDiag;
	}

	public void setRevDiag(String revDiag) {
		this.revDiag = revDiag;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
}
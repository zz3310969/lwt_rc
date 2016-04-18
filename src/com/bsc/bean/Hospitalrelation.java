package com.bsc.bean;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Hospitalrelation entity. @author MyEclipse Persistence Tools
 */

public class Hospitalrelation implements java.io.Serializable {

	private HospitalrelationId id;
	private Hospital receiverHospital;
	private Hospital callerHospital;
	private String callerId;
	private String receiverId;
	private String receiverHospitalName;
	private String callerHospitalName;
	private String receiverProvince;
	private String callerProvince;
	private Date addTime;

	public Hospitalrelation() {

	}

	public Hospitalrelation(HospitalrelationId id, Hospital receiverHospital,
			Hospital callerHospital, String callerId, String receiverId,
			String receiverHospitalName, String callerHospitalName,
			String receiverProvince, String callerProvince, Date addTime) {
		super();
		this.id = id;
		this.receiverHospital = receiverHospital;
		this.callerHospital = callerHospital;
		this.callerId = callerId;
		this.receiverId = receiverId;
		this.receiverHospitalName = receiverHospitalName;
		this.callerHospitalName = callerHospitalName;
		this.receiverProvince = receiverProvince;
		this.callerProvince = callerProvince;
		this.addTime = addTime;
	}

	public HospitalrelationId getId() {
		return id;
	}

	public void setId(HospitalrelationId id) {
		this.id = id;
	}

	public Hospital getReceiverHospital() {
		return receiverHospital;
	}

	public void setReceiverHospital(Hospital receiverHospital) {
		this.receiverHospital = receiverHospital;
	}

	public Hospital getCallerHospital() {
		return callerHospital;
	}

	public void setCallerHospital(Hospital callerHospital) {
		this.callerHospital = callerHospital;
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

	public String getReceiverHospitalName() {
		return receiverHospitalName;
	}

	public void setReceiverHospitalName(String receiverHospitalName) {
		this.receiverHospitalName = receiverHospitalName;
	}

	public String getCallerHospitalName() {
		return callerHospitalName;
	}

	public void setCallerHospitalName(String callerHospitalName) {
		this.callerHospitalName = callerHospitalName;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getCallerProvince() {
		return callerProvince;
	}

	public void setCallerProvince(String callerProvince) {
		this.callerProvince = callerProvince;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
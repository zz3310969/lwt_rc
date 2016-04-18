package com.bsc.bean;

import java.util.Date;

/**
 * Consultationpatientrecord entity. @author MyEclipse Persistence Tools
 */

public class Consultationpatientrecord implements java.io.Serializable {

	// Fields

	private ConsultationpatientrecordId id;
	private Patientrecord patientrecord;
	private Consultation consultation;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Consultationpatientrecord() {
	}

	/** minimal constructor */
	public Consultationpatientrecord(ConsultationpatientrecordId id,
			Patientrecord patientrecord, Consultation consultation) {
		this.id = id;
		this.patientrecord = patientrecord;
		this.consultation = consultation;
	}

	/** full constructor */
	public Consultationpatientrecord(ConsultationpatientrecordId id,
			Patientrecord patientrecord, Consultation consultation,
			Date addTime) {
		this.id = id;
		this.patientrecord = patientrecord;
		this.consultation = consultation;
		this.addTime = addTime;
	}

	// Property accessors

	public ConsultationpatientrecordId getId() {
		return this.id;
	}

	public void setId(ConsultationpatientrecordId id) {
		this.id = id;
	}

	public Patientrecord getPatientrecord() {
		return this.patientrecord;
	}

	public void setPatientrecord(Patientrecord patientrecord) {
		this.patientrecord = patientrecord;
	}

	public Consultation getConsultation() {
		return this.consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

}
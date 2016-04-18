package com.bsc.bean;

import java.util.Date;

/**
 * Consultationdigitalimage entity. @author MyEclipse Persistence Tools
 */

public class Consultationdigitalimage implements java.io.Serializable {

	// Fields

	private ConsultationdigitalimageId id;
	private Digitalimage digitalimage;
	private Consultation consultation;
	private Date addTime;

	// Constructors

	/** default constructor */
	public Consultationdigitalimage() {
	}

	/** minimal constructor */
	public Consultationdigitalimage(ConsultationdigitalimageId id,
			Digitalimage digitalimage, Consultation consultation) {
		this.id = id;
		this.digitalimage = digitalimage;
		this.consultation = consultation;
	}

	/** full constructor */
	public Consultationdigitalimage(ConsultationdigitalimageId id,
			Digitalimage digitalimage, Consultation consultation,
			Date addTime) {
		this.id = id;
		this.digitalimage = digitalimage;
		this.consultation = consultation;
		this.addTime = addTime;
	}

	// Property accessors

	public ConsultationdigitalimageId getId() {
		return this.id;
	}

	public void setId(ConsultationdigitalimageId id) {
		this.id = id;
	}

	public Digitalimage getDigitalimage() {
		return this.digitalimage;
	}

	public void setDigitalimage(Digitalimage digitalimage) {
		this.digitalimage = digitalimage;
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
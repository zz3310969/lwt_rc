package com.bsc.bean;

/**
 * ConsultationpatientrecordId entity. @author MyEclipse Persistence Tools
 */

public class ConsultationpatientrecordId implements java.io.Serializable {

	// Fields

	private String consultationId;
	private String patientRecordId;

	// Constructors

	/** default constructor */
	public ConsultationpatientrecordId() {
	}

	/** full constructor */
	public ConsultationpatientrecordId(String consultationId,
			String patientRecordId) {
		this.consultationId = consultationId;
		this.patientRecordId = patientRecordId;
	}

	// Property accessors

	public String getConsultationId() {
		return this.consultationId;
	}

	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}

	public String getPatientRecordId() {
		return this.patientRecordId;
	}

	public void setPatientRecordId(String patientRecordId) {
		this.patientRecordId = patientRecordId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConsultationpatientrecordId))
			return false;
		ConsultationpatientrecordId castOther = (ConsultationpatientrecordId) other;

		return ((this.getConsultationId() == castOther.getConsultationId()) || (this
				.getConsultationId() != null
				&& castOther.getConsultationId() != null && this
				.getConsultationId().equals(castOther.getConsultationId())))
				&& ((this.getPatientRecordId() == castOther
						.getPatientRecordId()) || (this.getPatientRecordId() != null
						&& castOther.getPatientRecordId() != null && this
						.getPatientRecordId().equals(
								castOther.getPatientRecordId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConsultationId() == null ? 0 : this.getConsultationId()
						.hashCode());
		result = 37
				* result
				+ (getPatientRecordId() == null ? 0 : this.getPatientRecordId()
						.hashCode());
		return result;
	}

}
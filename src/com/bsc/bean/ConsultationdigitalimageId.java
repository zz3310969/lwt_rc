package com.bsc.bean;

/**
 * ConsultationdigitalimageId entity. @author MyEclipse Persistence Tools
 */

public class ConsultationdigitalimageId implements java.io.Serializable {

	// Fields

	private String consultationId;
	private String digitalImageId;

	// Constructors

	/** default constructor */
	public ConsultationdigitalimageId() {
	}

	/** full constructor */
	public ConsultationdigitalimageId(String consultationId,
			String digitalImageId) {
		this.consultationId = consultationId;
		this.digitalImageId = digitalImageId;
	}

	// Property accessors

	public String getConsultationId() {
		return this.consultationId;
	}

	public void setConsultationId(String consultationId) {
		this.consultationId = consultationId;
	}

	public String getDigitalImageId() {
		return this.digitalImageId;
	}

	public void setDigitalImageId(String digitalImageId) {
		this.digitalImageId = digitalImageId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConsultationdigitalimageId))
			return false;
		ConsultationdigitalimageId castOther = (ConsultationdigitalimageId) other;

		return ((this.getConsultationId() == castOther.getConsultationId()) || (this
				.getConsultationId() != null
				&& castOther.getConsultationId() != null && this
				.getConsultationId().equals(castOther.getConsultationId())))
				&& ((this.getDigitalImageId() == castOther.getDigitalImageId()) || (this
						.getDigitalImageId() != null
						&& castOther.getDigitalImageId() != null && this
						.getDigitalImageId().equals(
								castOther.getDigitalImageId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getConsultationId() == null ? 0 : this.getConsultationId()
						.hashCode());
		result = 37
				* result
				+ (getDigitalImageId() == null ? 0 : this.getDigitalImageId()
						.hashCode());
		return result;
	}

}
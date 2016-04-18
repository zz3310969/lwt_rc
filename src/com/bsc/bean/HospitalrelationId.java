package com.bsc.bean;

/**
 * HospitalrelationId entity. @author MyEclipse Persistence Tools
 */

public class HospitalrelationId implements java.io.Serializable {

	// Fields

	private String callerId;
	private String receiverId;

	// Constructors

	/** default constructor */
	public HospitalrelationId() {
	}

	/** full constructor */
	public HospitalrelationId(String callerId, String receiverId) {
		this.callerId = callerId;
		this.receiverId = receiverId;
	}

	// Property accessors

	public String getCallerId() {
		return this.callerId;
	}

	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}

	public String getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HospitalrelationId))
			return false;
		HospitalrelationId castOther = (HospitalrelationId) other;

		return ((this.getCallerId() == castOther.getCallerId()) || (this
				.getCallerId() != null && castOther.getCallerId() != null && this
				.getCallerId().equals(castOther.getCallerId())))
				&& ((this.getReceiverId() == castOther.getReceiverId()) || (this
						.getReceiverId() != null
						&& castOther.getReceiverId() != null && this
						.getReceiverId().equals(castOther.getReceiverId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCallerId() == null ? 0 : this.getCallerId().hashCode());
		result = 37
				* result
				+ (getReceiverId() == null ? 0 : this.getReceiverId()
						.hashCode());
		return result;
	}

}
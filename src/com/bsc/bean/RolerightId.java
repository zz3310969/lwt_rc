package com.bsc.bean;

/**
 * RolerightId entity. @author MyEclipse Persistence Tools
 */

public class RolerightId implements java.io.Serializable {

	// Fields

	private String roleId;
	private String functionModuleId;

	// Constructors

	/** default constructor */
	public RolerightId() {
	}

	/** full constructor */
	public RolerightId(String roleId, String functionModuleId) {
		this.roleId = roleId;
		this.functionModuleId = functionModuleId;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFunctionModuleId() {
		return this.functionModuleId;
	}

	public void setFunctionModuleId(String functionModuleId) {
		this.functionModuleId = functionModuleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolerightId))
			return false;
		RolerightId castOther = (RolerightId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this
				.getRoleId() != null && castOther.getRoleId() != null && this
				.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getFunctionModuleId() == castOther
						.getFunctionModuleId()) || (this.getFunctionModuleId() != null
						&& castOther.getFunctionModuleId() != null && this
						.getFunctionModuleId().equals(
								castOther.getFunctionModuleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37
				* result
				+ (getFunctionModuleId() == null ? 0 : this
						.getFunctionModuleId().hashCode());
		return result;
	}

}
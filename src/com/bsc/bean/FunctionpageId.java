package com.bsc.bean;

/**
 * FunctionpageId entity. @author MyEclipse Persistence Tools
 */

public class FunctionpageId implements java.io.Serializable {

	// Fields

	private String functionModuleId;
	private String webPageId;

	// Constructors

	/** default constructor */
	public FunctionpageId() {
	}

	/** full constructor */
	public FunctionpageId(String functionModuleId, String webPageId) {
		this.functionModuleId = functionModuleId;
		this.webPageId = webPageId;
	}

	// Property accessors

	public String getFunctionModuleId() {
		return this.functionModuleId;
	}

	public void setFunctionModuleId(String functionModuleId) {
		this.functionModuleId = functionModuleId;
	}

	public String getWebPageId() {
		return this.webPageId;
	}

	public void setWebPageId(String webPageId) {
		this.webPageId = webPageId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FunctionpageId))
			return false;
		FunctionpageId castOther = (FunctionpageId) other;

		return ((this.getFunctionModuleId() == castOther.getFunctionModuleId()) || (this
				.getFunctionModuleId() != null
				&& castOther.getFunctionModuleId() != null && this
				.getFunctionModuleId().equals(castOther.getFunctionModuleId())))
				&& ((this.getWebPageId() == castOther.getWebPageId()) || (this
						.getWebPageId() != null
						&& castOther.getWebPageId() != null && this
						.getWebPageId().equals(castOther.getWebPageId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFunctionModuleId() == null ? 0 : this
						.getFunctionModuleId().hashCode());
		result = 37 * result
				+ (getWebPageId() == null ? 0 : this.getWebPageId().hashCode());
		return result;
	}

}
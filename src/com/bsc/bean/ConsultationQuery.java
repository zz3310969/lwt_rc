package com.bsc.bean;

import java.util.Date;

public class ConsultationQuery {
	private String receiverHospital;
	private String name;
	private String office;
	private String status;
	private Date start_date;
	private Date end_date;
	public String getReceiverHospital() {
		return receiverHospital;
	}
	public void setReceiverHospital(String receiverHospital) {
		this.receiverHospital = receiverHospital;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
}

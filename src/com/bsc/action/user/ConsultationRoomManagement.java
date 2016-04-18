package com.bsc.action.user;

import java.util.Date;
import java.util.UUID;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Consultationroom;
import com.bsc.bean.Systemuser;
import com.bsc.service.system.impl.ConsultationRoomServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

public class ConsultationRoomManagement extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String primaryKey;
	private ConsultationRoomServiceImpl consultationRoomServiceImpl;
	private int pageSize;
	private int pageIndex;
	private String textId;
	private String textHospitalId;
	private String textHospitalName;
	private String textName;
	private String textIp;
	private String selectDeviceType;
	private String textareaDescription;
	private ResultModel result;

	public String insert() {
		Consultationroom room = new Consultationroom();
		room.setAddTime(new Date());

		if (textareaDescription != null && !textareaDescription.isEmpty())
			room.setDescription(textareaDescription);
		if (selectDeviceType != null && !selectDeviceType.isEmpty())
			room.setDeviceType(selectDeviceType);
		if (textHospitalId != null && !textHospitalId.isEmpty())
			room.setHospitalId(textHospitalId);
		room.setId(UUID.randomUUID().toString());
		if (textIp != null && !textIp.isEmpty())
			room.setIp(textIp);
		if (textName != null && !textName.isEmpty())
			room.setName(textName);

		room.setUpdateTime(new Date());

		result = consultationRoomServiceImpl.insert(room);

		return "json";
	}

	public String delete() {
		result = consultationRoomServiceImpl.delete(primaryKey);
		return "json";
	}

	public String update() {

		Consultationroom room = new Consultationroom();

		if (textareaDescription != null && !textareaDescription.isEmpty())
			room.setDescription(textareaDescription);
		if (selectDeviceType != null && !selectDeviceType.isEmpty())
			room.setDeviceType(selectDeviceType);
		if (textHospitalId != null && !textHospitalId.isEmpty())
			room.setHospitalId(textHospitalId);
		room.setId(UUID.randomUUID().toString());
		if (textId != null && !textId.isEmpty())
			room.setId(textId);
		if (textIp != null && !textIp.isEmpty())
			room.setIp(textIp);
		if (textName != null && !textName.isEmpty())
			room.setName(textName);

		room.setUpdateTime(new Date());

		result = consultationRoomServiceImpl.update(room);

		return "json";
	}

	public String getModel() {
		result = consultationRoomServiceImpl
				.getConsultationRoomById(primaryKey);

		return "json";
	}

	public String queryRecord() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder roomCriterionAndOrder = new CriterionAndOrder();
		roomCriterionAndOrder.setCriteriaName(Consultationroom.class.getName());
		roomCriterionAndOrder.setCriteriaAsName("CO");

		CriterionAndOrder hospitalCriterionAndOrder = new CriterionAndOrder();
		hospitalCriterionAndOrder.setCriteriaName("hospital");
		hospitalCriterionAndOrder.setCriteriaAsName("HO");
		hospitalCriterionAndOrder
				.setCriteriaSpecification(CriteriaSpecification.LEFT_JOIN);

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("CO.id").as("primaryKey"));
		pList.add(Projections.property("CO.name").as("name"));
		pList.add(Projections.property("CO.ip").as("ip"));
		pList.add(Projections.property("CO.deviceTypeText").as("deviceTypeText"));
		pList.add(Projections.property("HO.name").as("hospitalName"));
		pList.add(Projections.property("CO.addTime").as("addTime"));

		if (textName != null && !textName.trim().isEmpty())
			roomCriterionAndOrder.getCriterionList().add(
					Restrictions.like("CO.name", "%" + textName + "%"));
		if (selectDeviceType != null && !selectDeviceType.trim().isEmpty())
			roomCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("CO.deviceType", selectDeviceType));
		if (textHospitalName != null && !textHospitalName.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.like("HO.name", "%" + textHospitalName + "%"));

		queryTerms.setProjectionList(pList);
		queryTerms.setPersistentClass(Consultationroom.class);
		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(roomCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(hospitalCriterionAndOrder);

		roomCriterionAndOrder.getOrderList().add(Order.asc("CO.hospitalId"));

		result = consultationRoomServiceImpl
				.getConsultationRoomList(queryTerms);

		return "json";
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public ConsultationRoomServiceImpl getConsultationRoomServiceImpl() {
		return consultationRoomServiceImpl;
	}

	public void setConsultationRoomServiceImpl(
			ConsultationRoomServiceImpl consultationRoomServiceImpl) {
		this.consultationRoomServiceImpl = consultationRoomServiceImpl;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}

	public String getTextHospitalId() {
		return textHospitalId;
	}

	public void setTextHospitalId(String textHospitalId) {
		this.textHospitalId = textHospitalId;
	}

	public String getTextHospitalName() {
		return textHospitalName;
	}

	public void setTextHospitalName(String textHospitalName) {
		this.textHospitalName = textHospitalName;
	}

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

	public String getTextIp() {
		return textIp;
	}

	public void setTextIp(String textIp) {
		this.textIp = textIp;
	}

	public String getSelectDeviceType() {
		return selectDeviceType;
	}

	public void setSelectDeviceType(String selectDeviceType) {
		this.selectDeviceType = selectDeviceType;
	}

	public String getTextareaDescription() {
		return textareaDescription;
	}

	public void setTextareaDescription(String textareaDescription) {
		this.textareaDescription = textareaDescription;
	}

	public ResultModel getResult() {
		return result;
	}

	public void setResult(ResultModel result) {
		this.result = result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

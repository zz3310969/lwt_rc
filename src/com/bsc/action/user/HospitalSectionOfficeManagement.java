package com.bsc.action.user;

import java.util.Date;
import java.util.UUID;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Hospitalsectionoffice;
import com.bsc.bean.Systemuser;
import com.bsc.service.system.impl.HospitalSectionOfficeServiceImpl;
import com.bsc.service.system.impl.SystemUserServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

public class HospitalSectionOfficeManagement extends BaseAction {
	private static final long serialVersionUID = 1L;
	private HospitalSectionOfficeServiceImpl sectionOfficeService;
	private SystemUserServiceImpl systemUserServiceImpl;
	private int pageSize;
	private int pageIndex;
	private String textId;
	private String primaryKey;
	private String textName;
	private String selectLevel;
	private String selectFartherId;
	private String textHospitalId;
	private String textHospitalName;
	private String textareaDescription;
	private ResultModel result;

	public String insert() {
		Hospitalsectionoffice office = new Hospitalsectionoffice();
		office.setAddTime(new Date());
		office.setId(UUID.randomUUID().toString());

		if (textName != null && !textName.isEmpty())
			office.setName(textName);
		if (selectLevel != null && !selectLevel.isEmpty())
			office.setLevel(selectLevel);
		if (selectFartherId != null && !selectFartherId.isEmpty())
			office.setFartherId(selectFartherId);
		if (textHospitalId != null && !textHospitalId.isEmpty())
			office.setHospitalId(textHospitalId);
		if (textareaDescription != null && !textareaDescription.isEmpty())
			office.setDescription(textareaDescription);

		office.setUpdateTime(new Date());

		result = sectionOfficeService.insert(office);
		return "json";
	}

	public String delete() {
		// 删除科室前先判断此科室下有没有用户 有的话给出提示
		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder userCriterionAndOrder = new CriterionAndOrder();
		userCriterionAndOrder.setCriteriaName(Systemuser.class.getName());
		userCriterionAndOrder.setCriteriaAsName("SY");
		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("SY.userId").as("userId"));
		userCriterionAndOrder.getCriterionList().add(
				Restrictions.eq("SY.hospitalSectionOfficeId", primaryKey));
		queryTerms.setProjectionList(pList);
		queryTerms.setPersistentClass(Systemuser.class);
		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(userCriterionAndOrder);
		result = systemUserServiceImpl.getSystemUserList(queryTerms, false);
		if (result.getTotalCount() != 0) {
			result = new ResultModel();
			result.setIsSuccess(false);
			result.setMessage("请先删除该科室下的用户！");
		} else {
			result = sectionOfficeService.delete(primaryKey);
		}
		return "json";
	}

	public String update() {
		Hospitalsectionoffice office = new Hospitalsectionoffice();

		office.setId(textId);

		if (textName != null && !textName.isEmpty())
			office.setName(textName);
		if (selectLevel != null && !selectLevel.isEmpty())
			office.setLevel(selectLevel);
		if (selectFartherId != null && !selectFartherId.isEmpty())
			office.setFartherId(selectFartherId);
		if (textHospitalId != null && !textHospitalId.isEmpty())
			office.setHospitalId(textHospitalId);
		if (textareaDescription != null && !textareaDescription.isEmpty())
			office.setDescription(textareaDescription);

		office.setUpdateTime(new Date());

		result = sectionOfficeService.update(office);

		return "json";
	}

	public String getModel() {
		result = sectionOfficeService.getHospitalSectionOfficeById(primaryKey);

		return "json";
	}

	public String queryRecord() {
		QueryTerms queryTerms = new QueryTerms();
		CriterionAndOrder officeCriterionAndOrder = new CriterionAndOrder();
		officeCriterionAndOrder.setCriteriaName(Hospitalsectionoffice.class
				.getName());
		officeCriterionAndOrder.setCriteriaAsName("SE");
		CriterionAndOrder hospitalCriterionAndOrder = new CriterionAndOrder();
		hospitalCriterionAndOrder.setCriteriaName("hospital");
		hospitalCriterionAndOrder.setCriteriaAsName("HO");
		hospitalCriterionAndOrder
				.setCriteriaSpecification(CriteriaSpecification.LEFT_JOIN);

		if (textName != null && !textName.trim().isEmpty())
			officeCriterionAndOrder.getCriterionList().add(
					Restrictions.like("SE.name", "%" + textName + "%"));
		if (textHospitalName != null && !textHospitalName.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.like("HO.name", "%" + textHospitalName + "%"));
		if (selectLevel != null && !selectLevel.trim().isEmpty())
			officeCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("SE.level", selectLevel));
		if (selectFartherId != null && !selectFartherId.trim().isEmpty())
			officeCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("SE.fartherId", selectFartherId));

		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("SE.id").as("primaryKey"));
		pList.add(Projections.property("HO.name").as("hospitalName"));
		pList.add(Projections.property("SE.name").as("name"));
		pList.add(Projections.property("SE.levelText").as("levelText"));
		pList.add(Projections.property("SE.fartherName").as("fartherName"));
		pList.add(Projections.property("SE.addTime").as("addTime"));

		queryTerms.setPersistentClass(Hospitalsectionoffice.class);
		queryTerms.setProjectionList(pList);
		queryTerms.getCriterionAndOrderList().add(officeCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(hospitalCriterionAndOrder);
		queryTerms.getOrderList().add(Order.asc("HO.name"));

		// 默认最多只查询前50条数据
		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);

		result = sectionOfficeService.getHospitalSectionOfficeList(queryTerms);

		return "json";
	}

	public String getDropDownList() {
		QueryTerms queryTerms = new QueryTerms();
		CriterionAndOrder officeCriterionAndOrder = new CriterionAndOrder();

		if (textHospitalId != null && !textHospitalId.trim().isEmpty())
			officeCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("H.hospitalId", textHospitalId));
		if (selectLevel != null && !selectLevel.trim().isEmpty())
			officeCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("H.level", selectLevel));
		if (textName != null && !textName.trim().isEmpty())
			officeCriterionAndOrder.getCriterionList().add(
					Restrictions.like("H.name", "%" + textName + "%"));

		officeCriterionAndOrder.setCriteriaName(Hospitalsectionoffice.class
				.getName());
		officeCriterionAndOrder.setCriteriaAsName("H");

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("H.id").as("id"));
		pList.add(Projections.property("H.name").as("name"));

		queryTerms.setPersistentClass(Hospitalsectionoffice.class);
		queryTerms.setProjectionList(pList);
		queryTerms.getCriterionAndOrderList().add(officeCriterionAndOrder);
		queryTerms.getOrderList().add(Order.asc("H.name"));
		queryTerms.setPageSize(20);
		queryTerms.setPageIndex(pageIndex - 1);

		result = sectionOfficeService.getHospitalSectionOfficeList(queryTerms,
				false);

		return "json";
	}

	public HospitalSectionOfficeServiceImpl getSectionOfficeService() {
		return sectionOfficeService;
	}

	public void setSectionOfficeService(
			HospitalSectionOfficeServiceImpl sectionOfficeService) {
		this.sectionOfficeService = sectionOfficeService;
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

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

	public String getSelectLevel() {
		return selectLevel;
	}

	public void setSelectLevel(String selectLevel) {
		this.selectLevel = selectLevel;
	}

	public String getSelectFartherId() {
		return selectFartherId;
	}

	public void setSelectFartherId(String selectFartherId) {
		this.selectFartherId = selectFartherId;
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

	public SystemUserServiceImpl getSystemUserServiceImpl() {
		return systemUserServiceImpl;
	}

	public void setSystemUserServiceImpl(
			SystemUserServiceImpl systemUserServiceImpl) {
		this.systemUserServiceImpl = systemUserServiceImpl;
	}

}

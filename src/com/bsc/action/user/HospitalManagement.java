package com.bsc.action.user;

import java.util.Date;
import java.util.UUID;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Hospital;
import com.bsc.service.system.impl.HospitalServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

public class HospitalManagement extends BaseAction {

	private static final long serialVersionUID = 1L;
	private HospitalServiceImpl hospitalService;
	private String primaryKey;
	private int pageSize;
	private int pageIndex;
	private String textId;
	private String textHospitalName;
	private String textName;
	private String textAddress;
	private String textTelephone;
	private String textPostCode;
	private String textIntroduce;
	private String textareaDescription;
	private String selectProvince;
	private String selectCity;
	private String selectCountry;
	private String selectRegionId;
	private ResultModel result;

	public String insert() {
		Hospital hospital = new Hospital();
		hospital.setAddTime(new Date());
		hospital.setId(UUID.randomUUID().toString());

		if (textHospitalName != null && !textHospitalName.isEmpty())
			hospital.setName(textHospitalName);
		if (textName != null && !textName.isEmpty())
			hospital.setName(textName);
		if (textAddress != null && !textAddress.isEmpty())
			hospital.setAddress(textAddress);
		if (textTelephone != null && !textTelephone.isEmpty())
			hospital.setTelephone(textTelephone);
		if (textPostCode != null && !textPostCode.isEmpty())
			hospital.setPostCode(textPostCode);
		if (textIntroduce != null && !textIntroduce.isEmpty())
			hospital.setDescription(textIntroduce);
		if (textareaDescription != null && !textareaDescription.isEmpty())
			hospital.setDescription(textareaDescription);

		if (selectCountry != null && !selectCountry.trim().isEmpty())
			hospital.setRegionId(selectCountry);
		else if (selectCity != null && !selectCity.trim().isEmpty())
			hospital.setRegionId(selectCity);
		else if (selectProvince != null && !selectProvince.trim().isEmpty())
			hospital.setRegionId(selectProvince);

		hospital.setUpdateTime(new Date());

		result = hospitalService.insert(hospital);

		return "json";
	}

	public String delete() {
		result = hospitalService.delete(primaryKey);
		if(result.getMessage().startsWith("Could not execute JDBC batch update"))
			result.setMessage("请先删除此医院下的用户、病人、科室、会诊室、会诊单等信息！");
		return "json";
	}

	public String update() {
		Hospital hospital = new Hospital();
		hospital.setAddTime(new Date());

		hospital.setId(textId);
		if (textHospitalName != null && !textHospitalName.isEmpty())
			hospital.setName(textHospitalName);
		if (textName != null && !textName.isEmpty())
			hospital.setName(textName);
		if (textAddress != null && !textAddress.isEmpty())
			hospital.setAddress(textAddress);
		if (textTelephone != null && !textTelephone.isEmpty())
			hospital.setTelephone(textTelephone);
		if (textPostCode != null && !textPostCode.isEmpty())
			hospital.setPostCode(textPostCode);
		if (textIntroduce != null && !textIntroduce.isEmpty())
			hospital.setDescription(textIntroduce);
		if (textareaDescription != null && !textareaDescription.isEmpty())
			hospital.setDescription(textareaDescription);
		if (selectCountry != null && !selectCountry.trim().isEmpty())
			hospital.setRegionId(selectCountry);
		else if (selectCity != null && !selectCity.trim().isEmpty())
			hospital.setRegionId(selectCity);
		else if (selectProvince != null && !selectProvince.trim().isEmpty())
			hospital.setRegionId(selectProvince);

		hospital.setUpdateTime(new Date());

		result = hospitalService.update(hospital);

		return "json";
	}

	public String getModel() {
		result = hospitalService.getHospitalById(primaryKey);

		return "json";
	}

	public String queryRecord() {
		QueryTerms queryTerms = new QueryTerms();
		CriterionAndOrder hospitalCriterionAndOrder = new CriterionAndOrder();
		hospitalCriterionAndOrder.setCriteriaName(Hospital.class.getName());
		hospitalCriterionAndOrder.setCriteriaAsName("H");

		if (textHospitalName != null && !textHospitalName.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.like("H.name", "%" + textHospitalName + "%"));
		if (textName != null && !textName.isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.like("H.name", "%" + textName + "%"));
		if (selectCountry != null && !selectCountry.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("H.regionId", selectCountry));
		else if (selectCity != null && !selectCity.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("H.regionId", selectCity));
		else if (selectProvince != null && !selectProvince.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("H.regionId", selectProvince));
		else if (selectRegionId != null && !selectRegionId.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("H.regionId", selectRegionId));

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("H.id").as("primaryKey"));
		pList.add(Projections.property("H.name").as("name"));
		pList.add(Projections.property("H.telephone").as("telephone"));
		pList.add(Projections.property("H.address").as("address"));
		pList.add(Projections.property("H.provinceName").as("provinceName"));
		pList.add(Projections.property("H.addTime").as("addTime"));

		queryTerms.setPersistentClass(Hospital.class);
		queryTerms.setProjectionList(pList);
		queryTerms.getCriterionAndOrderList().add(hospitalCriterionAndOrder);
		queryTerms.getOrderList().add(Order.asc("H.name"));

		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);

		result = hospitalService.getHospitalList(queryTerms);

		return "json";
	}

	public String getAutoCompleteList() {
		QueryTerms queryTerms = new QueryTerms();
		CriterionAndOrder hospitalCriterionAndOrder = new CriterionAndOrder();

		if (textHospitalName != null && !textHospitalName.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.like("H.name", "%" + textHospitalName + "%"));
		if (selectRegionId != null && !selectRegionId.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("H.region", selectRegionId));

		hospitalCriterionAndOrder.setCriteriaName(Hospital.class.getName());
		hospitalCriterionAndOrder.setCriteriaAsName("H");

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("H.id").as("id"));
		pList.add(Projections.property("H.name").as("name"));

		queryTerms.setPersistentClass(Hospital.class);
		queryTerms.setProjectionList(pList);
		queryTerms.getCriterionAndOrderList().add(hospitalCriterionAndOrder);
		queryTerms.getOrderList().add(Order.asc("H.name"));

		// 默认最多只查询前50条数据
		queryTerms.setPageSize(50);
		queryTerms.setPageIndex(pageIndex - 1);

		result = hospitalService.getHospitalList(queryTerms, false);

		return "json";
	}

	public HospitalServiceImpl getHospitalService() {
		return hospitalService;
	}

	public void setHospitalService(HospitalServiceImpl hospitalService) {
		this.hospitalService = hospitalService;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
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

	public String getTextHospitalName() {
		return textHospitalName;
	}

	public void setTextHospitalName(String textHospitalName) {
		this.textHospitalName = textHospitalName;
	}

	public String getTextAddress() {
		return textAddress;
	}

	public void setTextAddress(String textAddress) {
		this.textAddress = textAddress;
	}

	public String getTextTelephone() {
		return textTelephone;
	}

	public void setTextTelephone(String textTelephone) {
		this.textTelephone = textTelephone;
	}

	public String getTextPostCode() {
		return textPostCode;
	}

	public void setTextPostCode(String textPostCode) {
		this.textPostCode = textPostCode;
	}

	public String getTextIntroduce() {
		return textIntroduce;
	}

	public void setTextIntroduce(String textIntroduce) {
		this.textIntroduce = textIntroduce;
	}

	public String getTextareaDescription() {
		return textareaDescription;
	}

	public void setTextareaDescription(String textareaDescription) {
		this.textareaDescription = textareaDescription;
	}

	public String getSelectRegionId() {
		return selectRegionId;
	}

	public void setSelectRegionId(String selectRegionId) {
		this.selectRegionId = selectRegionId;
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

	public String getSelectProvince() {
		return selectProvince;
	}

	public void setSelectProvince(String selectProvince) {
		this.selectProvince = selectProvince;
	}

	public String getSelectCity() {
		return selectCity;
	}

	public void setSelectCity(String selectCity) {
		this.selectCity = selectCity;
	}

	public String getSelectCountry() {
		return selectCountry;
	}

	public void setSelectCountry(String selectCountry) {
		this.selectCountry = selectCountry;
	}

	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}

}

package com.bsc.action.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Systemuser;
import com.bsc.bean.Userrole;
import com.bsc.bean.UserroleId;
import com.bsc.service.system.impl.SystemUserServiceImpl;
import com.bsc.service.system.impl.UserRoleServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
public class SystemUserManagement extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String primaryKey;
	private SystemUserServiceImpl systemUserServiceImpl;
	private UserRoleServiceImpl userRoleServiceImpl;
	private int pageSize;
	private int pageIndex;
	private String textUserId;
	private String textUserName;
	private String textRealName;
	private String textIdNumber;
	private String radioGender;
	private String textPassword;
	private String textComfirmPassword;
	private String textHospitalId;
	private String selectHospitalSectionOfficeId;
	private String textHospitalSectionOfficeId;
	private String textTelephone;
	private String textEmail;
	private String checkboxUserRole;
	private String textareaDescription;
	private ResultModel result;

	public String insert() {
		Systemuser user = new Systemuser();
		user.setAddTime(new Date());
		user.setDeleteFlag("0");
		user.setDescription(textareaDescription);
		user.setEmail(textEmail);
		user.setGender(radioGender);
		user.setHospitalId(textHospitalId);
		user.setHospitalSectionOfficeId(textHospitalSectionOfficeId);
		user.setIdNumber(textIdNumber);
		user.setPassword(textPassword);
		if (textComfirmPassword != null && !textComfirmPassword.isEmpty()) {
			user.setPassword(textComfirmPassword);
		}
		user.setRealName(textRealName);
		user.setTelephone(textTelephone);
		user.setUpdateTime(new Date());
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName(textUserName);
		if (checkboxUserRole != null) {
			String[] userRoleList = checkboxUserRole.split(", ");
			Set userRoleSet = new HashSet();
			for (int i = 0; i < userRoleList.length; i++) {
				Userrole userRole = new Userrole();
				UserroleId userRoleId = new UserroleId();
				userRoleId.setRoleId(userRoleList[i]);
				userRoleId.setUserId(user.getUserId());

				userRole.setUserId(user.getUserId());
				userRole.setId(userRoleId);
				userRole.setAddTime(new Date());
				userRoleSet.add(userRole);
			}
			user.setUserroles(userRoleSet);
		}
		result = systemUserServiceImpl.insert(user);

		return "json";
	}

	public String delete() {
		result = systemUserServiceImpl.delete(primaryKey);
		if(result.getMessage().startsWith("Could not execute JDBC batch update"))
			result.setMessage("请先删除此用户相关的操作日志！");
		return "json";
	}

	public String update() {

		Systemuser user = new Systemuser();
		// user.setAddTime(new Date());
		user.setDeleteFlag("0");
		if (textEmail != null && !textEmail.isEmpty())
			user.setDescription(textareaDescription);
		if (textEmail != null && !textEmail.isEmpty())
			user.setEmail(textEmail);
		if (radioGender != null && !radioGender.isEmpty())
			user.setGender(radioGender);
		if (textHospitalId != null && !textHospitalId.isEmpty())
			user.setHospitalId(textHospitalId);
		if (textHospitalSectionOfficeId != null
				&& !textHospitalSectionOfficeId.isEmpty())
			user.setHospitalSectionOfficeId(textHospitalSectionOfficeId);
		if (textIdNumber != null && !textIdNumber.isEmpty())
			user.setIdNumber(textIdNumber);
		if (textPassword != null && !textPassword.isEmpty())
			user.setPassword(textPassword);
		if (textComfirmPassword != null && !textComfirmPassword.isEmpty()) {
			user.setPassword(textComfirmPassword);
		}
		if (textRealName != null && !textRealName.isEmpty())
			user.setRealName(textRealName);
		if (textTelephone != null && !textTelephone.isEmpty())
			user.setTelephone(textTelephone);

		user.setUpdateTime(new Date());
		if (textUserId != null && !textUserId.isEmpty())
			user.setUserId(textUserId);
		if (textUserName != null && !textUserName.isEmpty())
			user.setUserName(textUserName);
		if (checkboxUserRole != null) {
			String[] userRoleList = checkboxUserRole.split(", ");
			@SuppressWarnings("rawtypes")
			Set userRoleSet = new HashSet();
			for (int i = 0; i < userRoleList.length; i++) {
				Userrole userRole = new Userrole();
				UserroleId userRoleId = new UserroleId();
				userRoleId.setRoleId(userRoleList[i]);
				userRoleId.setUserId(textUserId);

				userRole.setUserId(textUserId);
				userRole.setId(userRoleId);
				userRole.setAddTime(new Date());
				userRoleSet.add(userRole);
			}
			user.setUserroles(userRoleSet);
		}
		// 先删除UserRole中间表的相关数据，再执行修改操作
		result = userRoleServiceImpl.deleteByUserId(user.getUserId());
		if (result.getIsSuccess())
			result = systemUserServiceImpl.update(user);

		return "json";
	}

	public String getModel() {
		result = systemUserServiceImpl.getSystemUserById(primaryKey);

		return "json";
	}

	public String queryRecord() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder userCriterionAndOrder = new CriterionAndOrder();
		userCriterionAndOrder.setCriteriaName(Systemuser.class.getName());
		userCriterionAndOrder.setCriteriaAsName("SY");

		CriterionAndOrder hospitalCriterionAndOrder = new CriterionAndOrder();
		hospitalCriterionAndOrder.setCriteriaName("hospital");
		hospitalCriterionAndOrder.setCriteriaAsName("HO");
		hospitalCriterionAndOrder
				.setCriteriaSpecification(CriteriaSpecification.LEFT_JOIN);

		CriterionAndOrder sectionOfficeCriterionAndOrder = new CriterionAndOrder();
		sectionOfficeCriterionAndOrder.setCriteriaName("hospitalsectionoffice");
		sectionOfficeCriterionAndOrder.setCriteriaAsName("SE");
		sectionOfficeCriterionAndOrder
				.setCriteriaSpecification(CriteriaSpecification.LEFT_JOIN);

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("SY.userId").as("userId"));
		pList.add(Projections.property("SY.userId").as("primaryKey"));
		pList.add(Projections.property("SY.userName").as("userName"));
		pList.add(Projections.property("SY.genderText").as("genderText"));
		pList.add(Projections.property("SY.telephone").as("telephone"));
		pList.add(Projections.property("HO.name").as("hospitalName"));
		pList.add(Projections.property("SE.name").as(
				"hospitalSectionOfficeName"));

		if (textUserName != null && !textUserName.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.like("SY.userName", "%" + textUserName + "%"));
		if (textIdNumber != null && !textIdNumber.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("SY.idNumber", textIdNumber));
		if (textHospitalId != null && !textHospitalId.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("SY.hospitalId", textHospitalId));
		if (selectHospitalSectionOfficeId != null
				&& !selectHospitalSectionOfficeId.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("SY.hospitalSectionOfficeId",
							selectHospitalSectionOfficeId));

		queryTerms.setProjectionList(pList);
		queryTerms.setPersistentClass(Systemuser.class);
		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(userCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(hospitalCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(
				sectionOfficeCriterionAndOrder);

		userCriterionAndOrder.getOrderList().add(Order.asc("userName"));

		result = systemUserServiceImpl.getSystemUserList(queryTerms);

		return "json";
	}

	public String clearSession() {
		ActionContext.getContext().getSession().clear();
		return "json";
	}

	public String ifSession() {
		Map session = ActionContext.getContext().getSession();
		result = new ResultModel();
		Systemuser user = null;
		if (session != null)
			user = (Systemuser) session.get("sysuser");
		if (user != null)
			result.setIsSuccess(true);
		return "json";
	}

	public String queryRecord1() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder userCriterionAndOrder = new CriterionAndOrder();
		userCriterionAndOrder.setCriteriaName(Systemuser.class.getName());

		if (textUserName != null && !textUserName.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.like("userName", "%" + textUserName + "%"));
		if (textIdNumber != null && !textIdNumber.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("idNumber", textIdNumber));
		if (textHospitalId != null && !textHospitalId.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("hospitalId", textHospitalId));
		if (selectHospitalSectionOfficeId != null
				&& !selectHospitalSectionOfficeId.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("hospitalSectionOfficeId",
							selectHospitalSectionOfficeId));
		userCriterionAndOrder.getOrderList().add(Order.asc("userName"));

		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(userCriterionAndOrder);

		result = systemUserServiceImpl.getSystemUserList(queryTerms);

		return "json";
	}

	public String getSystemUserList2() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder userCriterionAndOrder = new CriterionAndOrder();
		userCriterionAndOrder.setCriteriaName(Systemuser.class.getName());

		CriterionAndOrder hospitalCriterionAndOrder = new CriterionAndOrder();
		hospitalCriterionAndOrder.setCriteriaName("hospital");

		CriterionAndOrder sectionOfficeCriterionAndOrder = new CriterionAndOrder();
		sectionOfficeCriterionAndOrder.setCriteriaName("hospitalsectionoffice");

		if (textUserName != null && !textUserName.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.like("userName", "%" + textUserName + "%"));
		if (textIdNumber != null && !textIdNumber.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("idNumber", textIdNumber));
		if (textHospitalId != null && !textHospitalId.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("id", textHospitalId));
		if (selectHospitalSectionOfficeId != null
				&& !selectHospitalSectionOfficeId.trim().isEmpty())
			sectionOfficeCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("id", selectHospitalSectionOfficeId));

		userCriterionAndOrder.getOrderList().add(Order.asc("userId"));

		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(userCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(
				sectionOfficeCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(hospitalCriterionAndOrder);

		result = systemUserServiceImpl.getSystemUserList(queryTerms);

		return "json";
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public SystemUserServiceImpl getSystemUserServiceImpl() {
		return systemUserServiceImpl;
	}

	public void setSystemUserServiceImpl(
			SystemUserServiceImpl systemUserServiceImpl) {
		this.systemUserServiceImpl = systemUserServiceImpl;
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

	public String getTextUserId() {
		return textUserId;
	}

	public void setTextUserId(String textUserId) {
		this.textUserId = textUserId;
	}

	public String getTextUserName() {
		return textUserName;
	}

	public void setTextUserName(String textUserName) {
		this.textUserName = textUserName;
	}

	public String getTextRealName() {
		return textRealName;
	}

	public void setTextRealName(String textRealName) {
		this.textRealName = textRealName;
	}

	public String getTextIdNumber() {
		return textIdNumber;
	}

	public void setTextIdNumber(String textIdNumber) {
		this.textIdNumber = textIdNumber;
	}

	public String getRadioGender() {
		return radioGender;
	}

	public void setRadioGender(String radioGender) {
		this.radioGender = radioGender;
	}

	public String getTextPassword() {
		return textPassword;
	}

	public void setTextPassword(String textPassword) {
		this.textPassword = textPassword;
	}

	public String getTextHospitalId() {
		return textHospitalId;
	}

	public void setTextHospitalId(String textHospitalId) {
		this.textHospitalId = textHospitalId;
	}

	public String getSelectHospitalSectionOfficeId() {
		return selectHospitalSectionOfficeId;
	}

	public void setSelectHospitalSectionOfficeId(
			String selectHospitalSectionOfficeId) {
		this.selectHospitalSectionOfficeId = selectHospitalSectionOfficeId;
	}

	public String getTextHospitalSectionOfficeId() {
		return textHospitalSectionOfficeId;
	}

	public void setTextHospitalSectionOfficeId(
			String textHospitalSectionOfficeId) {
		this.textHospitalSectionOfficeId = textHospitalSectionOfficeId;
	}

	public String getTextTelephone() {
		return textTelephone;
	}

	public void setTextTelephone(String textTelephone) {
		this.textTelephone = textTelephone;
	}

	public String getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(String textEmail) {
		this.textEmail = textEmail;
	}

	public String getCheckboxUserRole() {
		return checkboxUserRole;
	}

	public void setCheckboxUserRole(String checkboxUserRole) {
		this.checkboxUserRole = checkboxUserRole;
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

	public UserRoleServiceImpl getUserRoleServiceImpl() {
		return userRoleServiceImpl;
	}

	public void setUserRoleServiceImpl(UserRoleServiceImpl userRoleServiceImpl) {
		this.userRoleServiceImpl = userRoleServiceImpl;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getTextComfirmPassword() {
		return textComfirmPassword;
	}

	public void setTextComfirmPassword(String textComfirmPassword) {
		this.textComfirmPassword = textComfirmPassword;
	}

}

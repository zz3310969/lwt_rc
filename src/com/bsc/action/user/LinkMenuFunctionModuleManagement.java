package com.bsc.action.user;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Linkmenu;
import com.bsc.service.system.impl.LinkMenuFunctionModuleServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

public class LinkMenuFunctionModuleManagement extends BaseAction {

	private static final long serialVersionUID = 1L;

	private LinkMenuFunctionModuleServiceImpl functionModuleServiceImpl;
	private int pageSize;
	private int pageIndex;
	private String primaryKey;

	private String selectModuleLevel;
	private String selectModuleId;
	private String selectParentModuleId;
	private ResultModel result;

	public String insert() {

		return "json";
	}

	public String delete() {
		result = functionModuleServiceImpl.delete(primaryKey);
		return "json";
	}

	public String update() {

		return "json";
	}

	public String getModel() {
		result = functionModuleServiceImpl.getFunctionModuleById(primaryKey);

		return "json";
	}

	public String queryRecord() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder functionModuleCriterionAndOrder = new CriterionAndOrder();
		functionModuleCriterionAndOrder.setCriteriaName(Functionmodule.class
				.getName());
		functionModuleCriterionAndOrder.setCriteriaAsName("FM");

		if (selectModuleId != null && !selectModuleId.trim().isEmpty())
			functionModuleCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("moduleId", selectModuleId));
		if (selectParentModuleId != null
				&& !selectParentModuleId.trim().isEmpty())
			functionModuleCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("parentModuleId", selectParentModuleId));
		if (selectModuleLevel != null && !selectModuleLevel.trim().isEmpty())
			functionModuleCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("moduleLevel", selectModuleLevel));

		ProjectionList pList = Projections.projectionList();
		pList.add(Projections.property("FM.moduleName").as("moduleName"));
		pList.add(Projections.property("FM.moduleId").as("moduleId"));
		// 要查询成的属性
		queryTerms.setProjectionList(pList);
		// 要序列化成的实体类
		queryTerms.setPersistentClass(Functionmodule.class);

		functionModuleCriterionAndOrder.getOrderList().add(
				Order.asc("moduleName"));
		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(
				functionModuleCriterionAndOrder);

		result = functionModuleServiceImpl.getFunctionModuleList(queryTerms);

		return "json";
	}

	public LinkMenuFunctionModuleServiceImpl getFunctionModuleServiceImpl() {
		return functionModuleServiceImpl;
	}

	public void setFunctionModuleServiceImpl(
			LinkMenuFunctionModuleServiceImpl functionModuleServiceImpl) {
		this.functionModuleServiceImpl = functionModuleServiceImpl;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getSelectModuleId() {
		return selectModuleId;
	}

	public void setSelectModuleId(String selectModuleId) {
		this.selectModuleId = selectModuleId;
	}

	public String getSelectParentModuleId() {
		return selectParentModuleId;
	}

	public void setSelectParentModuleId(String selectParentModuleId) {
		this.selectParentModuleId = selectParentModuleId;
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

	public String getSelectModuleLevel() {
		return selectModuleLevel;
	}

	public void setSelectModuleLevel(String selectModuleLevel) {
		this.selectModuleLevel = selectModuleLevel;
	}

}

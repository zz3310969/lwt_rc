package com.bsc.action.user;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Dictionary;
import com.bsc.service.system.impl.LinkMenuDictionaryServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

public class LinkMenuDictionaryManagement extends BaseAction {

	private static final long serialVersionUID = 1L;

	private LinkMenuDictionaryServiceImpl dictionaryServiceImpl;
	private int pageSize;
	private int pageIndex;
	private String primaryKey;
	private String textTypeId;
	private ResultModel result;

	public String insert() {

		return "json";
	}

	public String delete() {
		result = dictionaryServiceImpl.delete(primaryKey);
		return "json";
	}

	public String update() {

		return "json";
	}

	public String getModel() {
		result = dictionaryServiceImpl.getDictionaryById(primaryKey);

		return "json";
	}

	public String queryRecord() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder functionModuleCriterionAndOrder = new CriterionAndOrder();
		functionModuleCriterionAndOrder.setCriteriaName(Dictionary.class
				.getName());

		if (textTypeId != null && !textTypeId.trim().isEmpty())
			functionModuleCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("typeId", textTypeId));

		functionModuleCriterionAndOrder.getOrderList().add(Order.asc("typeId"));

		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(
				functionModuleCriterionAndOrder);

		result = dictionaryServiceImpl.getDictionaryList(queryTerms);

		return "json";
	}

	public LinkMenuDictionaryServiceImpl getDictionaryServiceImpl() {
		return dictionaryServiceImpl;
	}

	public void setDictionaryServiceImpl(
			LinkMenuDictionaryServiceImpl dictionaryServiceImpl) {
		this.dictionaryServiceImpl = dictionaryServiceImpl;
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

	public String getTextTypeId() {
		return textTypeId;
	}

	public void setTextTypeId(String textTypeId) {
		this.textTypeId = textTypeId;
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

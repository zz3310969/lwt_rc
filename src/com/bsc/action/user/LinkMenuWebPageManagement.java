package com.bsc.action.user;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Webpage;
import com.bsc.service.system.impl.LinkMenuWebPageServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

public class LinkMenuWebPageManagement extends BaseAction {

	private static final long serialVersionUID = 1L;

	private LinkMenuWebPageServiceImpl webPageServiceImpl;
	private int pageSize;
	private int pageIndex;
	private String primaryKey;
	private String textPageId;
	private String textUrl;
	private ResultModel result;

	public String insert() {

		return "json";
	}

	public String delete() {
		result = webPageServiceImpl.delete(primaryKey);
		return "json";
	}

	public String update() {

		return "json";
	}

	public String getModel() {
		result = webPageServiceImpl.getWebPageById(primaryKey);

		return "json";
	}

	public String queryRecord() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder functionModuleCriterionAndOrder = new CriterionAndOrder();
		functionModuleCriterionAndOrder
				.setCriteriaName(Webpage.class.getName());

		if (textPageId != null && !textPageId.trim().isEmpty())
			functionModuleCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("pageId", textPageId));

		if (textUrl != null && !textUrl.trim().isEmpty())
			functionModuleCriterionAndOrder.getCriterionList().add(
					Restrictions.like("url", "%" + textUrl + "%").ignoreCase());

		functionModuleCriterionAndOrder.getOrderList().add(Order.asc("url"));

		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(
				functionModuleCriterionAndOrder);

		result = webPageServiceImpl.getWebPageList(queryTerms);

		return "json";
	}

	public LinkMenuWebPageServiceImpl getWebPageServiceImpl() {
		return webPageServiceImpl;
	}

	public void setWebPageServiceImpl(
			LinkMenuWebPageServiceImpl webPageServiceImpl) {
		this.webPageServiceImpl = webPageServiceImpl;
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

	public String getTextPageId() {
		return textPageId;
	}

	public void setTextPageId(String textPageId) {
		this.textPageId = textPageId;
	}

	public String getTextUrl() {
		return textUrl;
	}

	public void setTextUrl(String textUrl) {
		this.textUrl = textUrl;
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

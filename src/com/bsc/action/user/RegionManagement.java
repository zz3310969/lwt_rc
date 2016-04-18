package com.bsc.action.user;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Region;
import com.bsc.service.system.impl.RegionServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

public class RegionManagement extends BaseAction {

	private static final long serialVersionUID = 1L;
	private RegionServiceImpl regionServiceImpl;
	private int pageSize;
	private int pageIndex;
	private String textFartherId;
	ResultModel result = null;

	public String getRegionListByFartherId() {

		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder regionCriterionAndOrder = new CriterionAndOrder();
		regionCriterionAndOrder.setCriteriaName(Region.class.getName());
		regionCriterionAndOrder.setCriteriaAsName("RE");

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("RE.id").as("id"));
		pList.add(Projections.property("RE.name").as("name"));

		if (textFartherId != null && !textFartherId.trim().isEmpty())
			regionCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("RE.fartherId", textFartherId));

		queryTerms.setProjectionList(pList);
		queryTerms.setPersistentClass(Region.class);
		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);
		queryTerms.getCriterionAndOrderList().add(regionCriterionAndOrder);

		regionCriterionAndOrder.getOrderList().add(Order.asc("id"));

		result = regionServiceImpl.getRegionList(queryTerms, false);

		return "json";
	}


	
	
	
	public RegionServiceImpl getRegionServiceImpl() {
		return regionServiceImpl;
	}

	public void setRegionServiceImpl(RegionServiceImpl regionServiceImpl) {
		this.regionServiceImpl = regionServiceImpl;
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

	public String getTextFartherId() {
		return textFartherId;
	}

	public void setTextFartherId(String textFartherId) {
		this.textFartherId = textFartherId;
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

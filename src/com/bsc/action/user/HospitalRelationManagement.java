package com.bsc.action.user;

import java.util.Date;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Hospitalrelation;
import com.bsc.bean.HospitalrelationId;
import com.bsc.service.system.impl.HospitalRelationServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

public class HospitalRelationManagement extends BaseAction {

	private static final long serialVersionUID = 1L;
	private HospitalRelationServiceImpl relationService;
	private int pageSize;
	private int pageIndex;
	private String callerId;
	private String receiverId;
	private String textHospitalName;

	private ResultModel result;

	public String insert() {
		Hospitalrelation relation = new Hospitalrelation();
		HospitalrelationId id = new HospitalrelationId();

		id.setCallerId(callerId);
		id.setReceiverId(receiverId);

		relation.setAddTime(new Date());
		relation.setId(id);

		result = relationService.insert(relation);

		return "json";
	}

	public String delete() {

		HospitalrelationId id = new HospitalrelationId();

		id.setCallerId(callerId);
		id.setReceiverId(receiverId);

		result = relationService.delete(id);

		return "json";
	}

	public String getModel() {

		return "json";
	}

	public String queryRecord() {
		QueryTerms queryTerms = new QueryTerms();
		CriterionAndOrder relationCriterionAndOrder = new CriterionAndOrder();
		relationCriterionAndOrder.setCriteriaName(Hospitalrelation.class
				.getName());
		relationCriterionAndOrder.setCriteriaAsName("R");

		CriterionAndOrder hospitalCriterionAndOrder = new CriterionAndOrder();
		hospitalCriterionAndOrder.setCriteriaName("receiverHospital");
		hospitalCriterionAndOrder.setCriteriaAsName("H");

		CriterionAndOrder cHospitalCriterionAndOrder = new CriterionAndOrder();
		cHospitalCriterionAndOrder.setCriteriaName("callerHospital");
		cHospitalCriterionAndOrder.setCriteriaAsName("C");

		if (textHospitalName != null && !textHospitalName.trim().isEmpty())
			hospitalCriterionAndOrder.getCriterionList().add(
					Restrictions.like("H.name", "%" + textHospitalName + "%"));
		if (callerId != null && !callerId.trim().isEmpty())
			relationCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("R.callerId", callerId));
		if (receiverId != null && !receiverId.trim().isEmpty())
			relationCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("R.receiverId", receiverId));

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("R.callerId").as("callerId"));
		pList.add(Projections.property("R.receiverId").as("receiverId"));
		pList.add(Projections.property("H.name").as("receiverHospitalName"));
		pList.add(Projections.property("H.provinceName").as("receiverProvince"));
		pList.add(Projections.property("C.name").as("callerHospitalName"));

		queryTerms.setPersistentClass(Hospitalrelation.class);
		queryTerms.setProjectionList(pList);
		queryTerms.getCriterionAndOrderList().add(relationCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(hospitalCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(cHospitalCriterionAndOrder);

		queryTerms.getOrderList().add(Order.asc("R.addTime"));

		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);

		result = relationService.getHospitalRelationList(queryTerms);

		return "json";
	}

	public String getNotCallerHospitalList() {

		String sql = "select h.id,h.name hospitalName,r.name provinceName from hospital h left join region r on substring(r.id,1,3)=substring(h.regionId,1,3) where (r.category=1 or r.category=2 or r.category=3 or r.category=4 or r.category is null) and h.id not in (select distinct receiverId from hospitalrelation where callerId='"
				+ callerId + "') and h.id!='" + callerId + "'";
		if (textHospitalName != null && !textHospitalName.trim().isEmpty())
			sql += " and h.name like '%" + textHospitalName + "%'";
		sql += " order by h.name;";
		String sqlCount = "select count(h.id) from hospital h left join region r on substring(r.id,1,3)=substring(h.regionId,1,3) where (r.category=1 or r.category=2 or r.category=3 or r.category=4 or r.category is null) and h.id not in (select distinct receiverId from hospitalrelation where callerId='"
				+ callerId + "') and h.id!='" + callerId + "'";
		if (textHospitalName != null && !textHospitalName.trim().isEmpty())
			sqlCount += " and h.name like '%" + textHospitalName + "%'";
		sqlCount += " order by h.name;";

		result = relationService.getHospitalRelationList(sql, sqlCount);

		return "json";
	}

	public HospitalRelationServiceImpl getRelationService() {
		return relationService;
	}

	public void setRelationService(HospitalRelationServiceImpl relationService) {
		this.relationService = relationService;
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

	public String getCallerId() {
		return callerId;
	}

	public void setCallerId(String callerId) {
		this.callerId = callerId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getTextHospitalName() {
		return textHospitalName;
	}

	public void setTextHospitalName(String textHospitalName) {
		this.textHospitalName = textHospitalName;
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

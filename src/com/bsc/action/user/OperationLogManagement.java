package com.bsc.action.user;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.service.system.impl.OperationLogServiceImpl;
import com.bsc.util.CriterionAndOrder;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

@SuppressWarnings("unchecked")
public class OperationLogManagement extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String primaryKey;
	private OperationLogServiceImpl operationLogServiceImpl;

	private int pageSize;
	private int pageIndex;
	// 操作者帐号
	private String textUserName;
	// 操作类型
	private String selectOperationType;
	// 操作的实体类名称
	private String textOperationTable;
	// 是否成功
	private String selectIsSuccess;
	// 操作时间
	private String textBeginTime;
	private String textEndTime;
	private ResultModel result;

	public String delete() {
		result = operationLogServiceImpl.delete(primaryKey, true);
		return "json";
	}

	public String getModel() {
		result = operationLogServiceImpl.getOperationLogById(primaryKey, true);

		return "json";
	}

	public String queryRecord() {
		QueryTerms queryTerms = new QueryTerms();

		CriterionAndOrder logCriterionAndOrder = new CriterionAndOrder();
		logCriterionAndOrder.setCriteriaName(Operationlog.class.getName());
		logCriterionAndOrder.setCriteriaAsName("OP");

		CriterionAndOrder userCriterionAndOrder = new CriterionAndOrder();
		userCriterionAndOrder.setCriteriaName("systemuser");
		userCriterionAndOrder.setCriteriaAsName("SY");
		userCriterionAndOrder
				.setCriteriaSpecification(CriteriaSpecification.LEFT_JOIN);

		ProjectionList pList = Projections.projectionList();

		pList.add(Projections.property("OP.id")
				.as("primaryKey"));
		pList.add(Projections.property("OP.operationTable")
				.as("operationTable"));
		pList.add(Projections.property("SY.userName").as("userName"));
		pList.add(Projections.property("OP.operationTypeText").as(
				"operationTypeText"));
		pList.add(Projections.property("OP.resultMessage").as("resultMessage"));
		pList.add(Projections.property("OP.addTime").as("addTime"));

		if (textUserName != null && !textUserName.trim().isEmpty())
			userCriterionAndOrder.getCriterionList().add(
					Restrictions.like("SY.userName", "%" + textUserName + "%"));
		if (selectOperationType != null
				&& !selectOperationType.trim().isEmpty())
			logCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("OP.operationType", selectOperationType));
		if (textOperationTable != null && !textOperationTable.trim().isEmpty())
			logCriterionAndOrder.getCriterionList().add(
					Restrictions.like("OP.operationTable", "%"+textOperationTable+"%"));
		if (selectIsSuccess != null && !selectIsSuccess.trim().isEmpty())
			logCriterionAndOrder.getCriterionList().add(
					Restrictions.eq("OP.isSuccess", selectIsSuccess));
		if (textBeginTime != null && !textBeginTime.trim().isEmpty())
			if (textEndTime != null && !textEndTime.trim().isEmpty())
				logCriterionAndOrder.getCriterionList().add(
						Restrictions.between("OP.addTime", textBeginTime,
								textEndTime));
			else
				logCriterionAndOrder.getCriterionList()
						.add(Restrictions.between("OP.addTime", textBeginTime,
								null));
		else if (textEndTime != null && !textEndTime.trim().isEmpty())
			logCriterionAndOrder.getCriterionList().add(
					Restrictions.between("OP.addTime", null, textEndTime));

		queryTerms.setProjectionList(pList);
		queryTerms.setPersistentClass(Operationlog.class);
		queryTerms.setPageSize(pageSize);
		queryTerms.setPageIndex(pageIndex - 1);

		queryTerms.getCriterionAndOrderList().add(logCriterionAndOrder);
		queryTerms.getCriterionAndOrderList().add(userCriterionAndOrder);

		logCriterionAndOrder.getOrderList().add(Order.desc("addTime"));

		result = operationLogServiceImpl.getOperationLogList(queryTerms,true);

		return "json";
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public OperationLogServiceImpl getOperationLogServiceImpl() {
		return operationLogServiceImpl;
	}

	public void setOperationLogServiceImpl(
			OperationLogServiceImpl operationLogServiceImpl) {
		this.operationLogServiceImpl = operationLogServiceImpl;
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

	public String getTextUserName() {
		return textUserName;
	}

	public void setTextUserName(String textUserName) {
		this.textUserName = textUserName;
	}

	public String getSelectOperationType() {
		return selectOperationType;
	}

	public void setSelectOperationType(String selectOperationType) {
		this.selectOperationType = selectOperationType;
	}

	public String getTextOperationTable() {
		return textOperationTable;
	}

	public void setTextOperationTable(String textOperationTable) {
		this.textOperationTable = textOperationTable;
	}

	public String getSelectIsSuccess() {
		return selectIsSuccess;
	}

	public void setSelectIsSuccess(String selectIsSuccess) {
		this.selectIsSuccess = selectIsSuccess;
	}

	public String getTextBeginTime() {
		return textBeginTime;
	}

	public void setTextBeginTime(String textBeginTime) {
		this.textBeginTime = textBeginTime;
	}

	public String getTextEndTime() {
		return textEndTime;
	}

	public void setTextEndTime(String textEndTime) {
		this.textEndTime = textEndTime;
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

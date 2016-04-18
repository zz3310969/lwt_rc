package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Hospitalrelation;
import com.bsc.bean.HospitalrelationId;
import com.bsc.bean.Linkmenu;
import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.HospitalRelationDAOImpl;
import com.bsc.service.system.IHospitalRelationService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class HospitalRelationServiceImpl implements IHospitalRelationService {

	private HospitalRelationDAOImpl relationDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Hospitalrelation relation) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Hospitalrelation.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			relationDao.insert(relation);

			log.setObjectRecordId(relation.getId().getCallerId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(relation.getId().getCallerId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(relation.getId().getCallerId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(relation.getId().getCallerId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Hospitalrelation relation, boolean isLog) {
		if (isLog)
			return insert(relation);
		ResultModel result = new ResultModel();
		try {
			relationDao.insert(relation);

			result.setIsSuccess(true);
			result.setObjectRecordId(relation.getId().getCallerId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(relation.getId().getCallerId());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(HospitalrelationId relationId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Hospitalrelation.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());

			relationDao.delete(relationId);

			log.setObjectRecordId(relationId.getCallerId());
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(relationId.getCallerId());
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(relationId.getCallerId());
			log.setResultCode(String.valueOf(-9998));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(relationId.getCallerId());
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel delete(HospitalrelationId relationId, boolean isLog) {
		if (isLog)
			return delete(relationId);
		ResultModel result = new ResultModel();

		try {

			relationDao.delete(relationId);

			result.setIsSuccess(true);
			result.setObjectRecordId(relationId.getCallerId());
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(relationId.getCallerId());
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getHospitalRelationList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Hospitalrelation> hospitalList = new ArrayList<Hospitalrelation>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Hospitalrelation.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			hospitalList = (List<Hospitalrelation>) (relationDao
					.getList(queryTerms));
			totalCount = relationDao.getTotalCount(queryTerms);

			result.setTotalCount(totalCount);
			result.setData(hospitalList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			log.setResultCode(String.valueOf(-9995));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setTotalCount(totalCount);
			result.setData(hospitalList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getHospitalRelationList(QueryTerms queryTerms,
			boolean isLog) {
		if (isLog)
			return getHospitalRelationList(queryTerms);

		List<Hospitalrelation> hospitalList = new ArrayList<Hospitalrelation>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			hospitalList = (List<Hospitalrelation>) (relationDao
					.getList(queryTerms));
			totalCount = relationDao.getTotalCount(queryTerms);
			result.setTotalCount(totalCount);
			result.setData(hospitalList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			result.setTotalCount(totalCount);
			result.setData(hospitalList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getHospitalRelationList(String sql, String sqlCount) {
		Operationlog log = new Operationlog();
		List<Hospitalrelation> relationList = new ArrayList<Hospitalrelation>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Linkmenu.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			relationList = (List<Hospitalrelation>) (relationDao.getList(sql));
			totalCount = relationDao.getTotalCount(sqlCount);
			result.setTotalCount(totalCount);
			result.setData(relationList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			log.setResultCode(String.valueOf(-9995));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setTotalCount(totalCount);
			result.setData(relationList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getHospitalRelationList(String sql, String sqlCount,
			boolean isLog) {
		if (isLog)
			return getHospitalRelationList(sql, sqlCount);

		List<Hospitalrelation> relationList = new ArrayList<Hospitalrelation>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			relationList = (List<Hospitalrelation>) (relationDao.getList(sql));
			totalCount = relationDao.getTotalCount(sqlCount);
			result.setTotalCount(totalCount);
			result.setData(relationList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			result.setTotalCount(totalCount);
			result.setData(relationList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public HospitalRelationDAOImpl getRelationDao() {
		return relationDao;
	}

	public void setRelationDao(HospitalRelationDAOImpl relationDao) {
		this.relationDao = relationDao;
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}

}

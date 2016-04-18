package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.OperationLogDAOImpl;
import com.bsc.service.system.IOperationLogService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class OperationLogServiceImpl implements IOperationLogService {

	private OperationLogDAOImpl operationLogDao;

	public ResultModel delete(String logId, boolean isLog) {
		if (!isLog)
			return delete(logId);
		ResultModel result = new ResultModel();
		Operationlog oLog = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			oLog.setSystemUserId(loginUser.getUserId());
			oLog.setId(UUID.randomUUID().toString());
			oLog.setOperationType("02");
			oLog.setOperationTable(Operationlog.class.getSimpleName());
			oLog.setOperationContent("删除记录");
			oLog.setAddTime(new Date());

			Operationlog log = (Operationlog) operationLogDao.getModel(logId);
			operationLogDao.delete(log);

			oLog.setObjectRecordId(logId);
			oLog.setResultCode(String.valueOf(9996));
			oLog.setIsSuccess(String.valueOf(0));
			oLog.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(logId);
			result.setResultCode(9998);
			result.setMessage("删除数据成功");
			return result;
		} catch (Exception e) {
			oLog.setObjectRecordId(logId);
			oLog.setResultCode(String.valueOf(-9996));
			oLog.setIsSuccess(String.valueOf(1));
			oLog.setResultMessage(e.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(logId);
			result.setResultCode(-9996);
			result.setMessage(e.getMessage());
			return result;
		} finally {
			insert(oLog);
		}
	}

	public ResultModel getOperationLogById(String logId, boolean isLog) {
		if (!isLog)
			return getOperationLogById(logId);
		
		Operationlog pLog = new Operationlog();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			pLog .setSystemUserId(loginUser.getUserId());
			pLog .setId(UUID.randomUUID().toString());
			pLog .setOperationType("04");
			pLog .setOperationTable(Operationlog.class.getSimpleName());
			pLog .setOperationContent("查询单条记录");
			pLog .setAddTime(new Date());
			pLog .setObjectRecordId(logId);
			pLog .setResultCode(String.valueOf(9996));

			Operationlog log=operationLogDao.getModel(logId);

			pLog .setIsSuccess(String.valueOf(0));
			pLog .setResultMessage("查询单条记录成功");

			result.setData(log);
			result.setObjectRecordId(logId);
			result.setMessage("查询单条记录成功");
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception e) {
			pLog .setIsSuccess(String.valueOf(1));
			pLog .setResultMessage(e.getMessage());

			result.setObjectRecordId(logId);
			result.setResultCode(-9996);
			result.setData(pLog );
			result.setIsSuccess(true);
			result.setResultCode(-9996);
			result.setMessage(e.getMessage());

			return result;
		}
	}

	public ResultModel getOperationLogList(QueryTerms queryTerms, boolean isLog) {
		if (!isLog)
			return getOperationLogList(queryTerms);
		
		List<Operationlog> operationLogList = new ArrayList<Operationlog>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Operationlog.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());			
			log.setResultCode(String.valueOf(9995));

			operationLogList = (List<Operationlog>) (operationLogDao
					.getList(queryTerms));
			totalCount = operationLogDao.getTotalCount(queryTerms);

			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			result.setTotalCount(totalCount);
			result.setData(operationLogList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			result.setMessage("模糊查询成功");
			return result;
		} catch (Exception e) {

			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(e.getMessage());

			result.setTotalCount(totalCount);
			result.setData(operationLogList);
			result.setIsSuccess(false);
			result.setMessage(e.getMessage());
			result.setResultCode(-9995);
			return result;
		}finally {
			insert(log);
		}
	}

	public ResultModel insert(Operationlog log) {
		ResultModel result = new ResultModel();
		Object id = "";
		try {
			id = operationLogDao.insert(log);
			result.setIsSuccess(true);
			result.setObjectRecordId(id.toString());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			return result;
		}
	}

	public ResultModel delete(String logId) {
		ResultModel result = new ResultModel();
		try {
			Operationlog log = (Operationlog) operationLogDao.getModel(logId);
			operationLogDao.delete(log);
			result.setIsSuccess(true);
			result.setObjectRecordId(logId);
			result.setResultCode(9998);
			return result;
		} catch (Exception e) {
			result.setIsSuccess(false);
			result.setObjectRecordId(logId);
			result.setResultCode(-9998);
			return result;
		}
	}

	public ResultModel update(Operationlog log) {
		ResultModel result = new ResultModel();
		try {
			operationLogDao.update(log);
			result.setIsSuccess(true);
			result.setObjectRecordId(log.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception e) {
			result.setIsSuccess(false);
			result.setObjectRecordId(log.getId());
			result.setResultCode(-9997);
			return result;
		}
	}

	public ResultModel getOperationLogById(String logId) {

		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();
		try {
			log = operationLogDao.getModel(logId);
			result.setData(log);
			result.setIsSuccess(true);
			result.setResultCode(9999);
			return result;
		} catch (Exception e) {
			result.setData(log);
			result.setIsSuccess(true);
			result.setResultCode(-9999);
			return result;
		}
	}

	public ResultModel getOperationLogList(QueryTerms queryTerms) {

		List<Operationlog> operationLogList = new ArrayList<Operationlog>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			operationLogList = (List<Operationlog>) (operationLogDao
					.getList(queryTerms));
			totalCount = operationLogDao.getTotalCount(queryTerms);

			result.setTotalCount(totalCount);
			result.setData(operationLogList);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setTotalCount(totalCount);
			result.setData(operationLogList);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			return result;
		}
	}

	public OperationLogDAOImpl getOperationLogDao() {
		return operationLogDao;
	}

	public void setOperationLogDao(OperationLogDAOImpl operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

}

package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.LinkMenuFunctionModuleDAOImpl;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class LinkMenuFunctionModuleServiceImpl {

	private LinkMenuFunctionModuleDAOImpl linkMenuFunctionModuleDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Functionmodule module) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Functionmodule.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			linkMenuFunctionModuleDao.insert(module);

			log.setObjectRecordId(module.getModuleId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setMessage("添加数据成功");
			result.setObjectRecordId(module.getModuleId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(module.getModuleId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(module.getModuleId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Functionmodule module, boolean isLog) {
		if (isLog)
			return insert(module);
		ResultModel result = new ResultModel();
		try {
			linkMenuFunctionModuleDao.insert(module);

			result.setIsSuccess(true);
			result.setObjectRecordId(module.getModuleId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(module.getModuleId());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(String moduleId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Functionmodule.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Functionmodule module = (Functionmodule) linkMenuFunctionModuleDao
					.getModel(moduleId);
			linkMenuFunctionModuleDao.delete(module);

			log.setObjectRecordId(moduleId);
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(moduleId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(moduleId);
			log.setResultCode(String.valueOf(-9998));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(moduleId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel delete(String moduleId, boolean isLog) {
		if (isLog)
			return delete(moduleId);
		ResultModel result = new ResultModel();
		try {

			Functionmodule module = (Functionmodule) linkMenuFunctionModuleDao
					.getModel(moduleId);
			linkMenuFunctionModuleDao.delete(module);

			result.setIsSuccess(true);
			result.setObjectRecordId(moduleId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(moduleId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel update(Functionmodule module) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Functionmodule.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			linkMenuFunctionModuleDao.update(module);

			log.setObjectRecordId(module.getModuleId());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setMessage("修改数据成功");
			result.setObjectRecordId(module.getModuleId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(module.getModuleId());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(module.getModuleId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Functionmodule module, boolean isLog) {
		if (isLog)
			return update(module);
		ResultModel result = new ResultModel();

		try {
			linkMenuFunctionModuleDao.update(module);
			result.setIsSuccess(true);
			result.setObjectRecordId(module.getModuleId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(module.getModuleId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getFunctionModuleById(String moduleId) {
		Operationlog log = new Operationlog();
		Functionmodule module = new Functionmodule();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Functionmodule.class.getSimpleName());
			log.setOperationContent("查询单记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(moduleId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单记录成功");

			module = linkMenuFunctionModuleDao.getModel(moduleId);

			result.setData(module);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(moduleId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(module);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getFunctionModuleById(String moduleId, boolean isLog) {
		if (isLog)
			return getFunctionModuleById(moduleId);
		Functionmodule module = new Functionmodule();
		ResultModel result = new ResultModel();
		try {

			module = linkMenuFunctionModuleDao.getModel(moduleId);

			result.setObjectRecordId(moduleId);
			result.setData(module);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setObjectRecordId(moduleId);
			result.setData(module);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getFunctionModuleList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Functionmodule> moduleList = new ArrayList<Functionmodule>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Functionmodule.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			moduleList = (List<Functionmodule>) (linkMenuFunctionModuleDao
					.getList(queryTerms));
			totalCount = linkMenuFunctionModuleDao.getTotalCount(queryTerms);

			result.setTotalCount(totalCount);
			result.setData(moduleList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			log.setResultCode(String.valueOf(-9995));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setTotalCount(totalCount);
			result.setData(moduleList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getFunctionModuleList(QueryTerms queryTerms,
			boolean isLog) {
		if (isLog)
			return getFunctionModuleList(queryTerms);

		List<Functionmodule> moduleList = new ArrayList<Functionmodule>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			moduleList = (List<Functionmodule>) (linkMenuFunctionModuleDao
					.getList(queryTerms));
			totalCount = linkMenuFunctionModuleDao.getTotalCount(queryTerms);
			result.setTotalCount(totalCount);
			result.setData(moduleList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			result.setTotalCount(totalCount);
			result.setData(moduleList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}

	public LinkMenuFunctionModuleDAOImpl getLinkMenuFunctionModuleDao() {
		return linkMenuFunctionModuleDao;
	}

	public void setLinkMenuFunctionModuleDao(
			LinkMenuFunctionModuleDAOImpl linkMenuFunctionModuleDao) {
		this.linkMenuFunctionModuleDao = linkMenuFunctionModuleDao;
	}

	
}

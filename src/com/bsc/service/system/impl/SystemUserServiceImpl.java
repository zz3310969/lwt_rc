package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.SystemUserDAOImpl;
import com.bsc.service.system.ISystemUserService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class SystemUserServiceImpl implements ISystemUserService {

	private SystemUserDAOImpl sytemUserDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Systemuser systemUser) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();

		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Systemuser.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			sytemUserDao.insert(systemUser);

			log.setObjectRecordId(systemUser.getUserId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setMessage("添加数据成功");
			result.setObjectRecordId(systemUser.getUserId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(systemUser.getUserId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(systemUser.getUserId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Systemuser systemUser, boolean isLog) {
		if (isLog)
			return insert(systemUser);
		ResultModel result = new ResultModel();
		try {
			sytemUserDao.insert(systemUser);

			result.setIsSuccess(true);
			result.setObjectRecordId(systemUser.getUserId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(systemUser.getUserId());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(String systemUserId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Systemuser.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Systemuser systemUser = (Systemuser) sytemUserDao
					.getModel(systemUserId);
			sytemUserDao.delete(systemUser);

			log.setObjectRecordId(systemUserId);
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(systemUserId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(systemUserId);
			log.setResultCode(String.valueOf(-9998));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(systemUserId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel delete(String systemUserId, boolean isLog) {
		if (isLog)
			return delete(systemUserId);
		ResultModel result = new ResultModel();

		try {

			Systemuser systemUser = (Systemuser) sytemUserDao
					.getModel(systemUserId);
			sytemUserDao.delete(systemUser);

			result.setIsSuccess(true);
			result.setObjectRecordId(systemUserId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(systemUserId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel update(Systemuser systemUser) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Systemuser.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			sytemUserDao.update(systemUser);

			log.setObjectRecordId(systemUser.getUserId());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setMessage("修改数据成功");
			result.setObjectRecordId(systemUser.getUserId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(systemUser.getUserId());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(systemUser.getUserId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Systemuser systemUser, boolean isLog) {
		if (isLog)
			return update(systemUser);
		ResultModel result = new ResultModel();

		try {
			sytemUserDao.update(systemUser);
			result.setIsSuccess(true);
			result.setObjectRecordId(systemUser.getUserId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(systemUser.getUserId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getSystemUserById(String systemUserId) {
		Operationlog log = new Operationlog();
		Systemuser systemUser = new Systemuser();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Systemuser.class.getSimpleName());
			log.setOperationContent("查询单条记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(systemUserId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单条记录成功");

			systemUser = sytemUserDao.getModel(systemUserId);

			result.setData(systemUser);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(systemUserId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(systemUser);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getSystemUserById(String systemUserId, boolean isLog) {
		if (isLog)
			return getSystemUserById(systemUserId);
		Systemuser systemUser = new Systemuser();
		ResultModel result = new ResultModel();
		try {

			systemUser = sytemUserDao.getModel(systemUserId);

			result.setData(systemUser);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setData(systemUser);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getSystemUserList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Systemuser> systemUserList = new ArrayList<Systemuser>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Systemuser.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			systemUserList = (List<Systemuser>) (sytemUserDao
					.getList(queryTerms));
			totalCount = sytemUserDao.getTotalCount(queryTerms);

			result.setTotalCount(totalCount);
			result.setData(systemUserList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			log.setResultCode(String.valueOf(-9995));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setTotalCount(totalCount);
			result.setData(systemUserList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getSystemUserList(QueryTerms queryTerms, boolean isLog) {
		if (isLog)
			return getSystemUserList(queryTerms);

		List<Systemuser> systemUserList = new ArrayList<Systemuser>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			systemUserList = (List<Systemuser>) (sytemUserDao
					.getList(queryTerms));
			totalCount = sytemUserDao.getTotalCount(queryTerms);
			result.setTotalCount(totalCount);
			result.setData(systemUserList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			result.setTotalCount(totalCount);
			result.setData(systemUserList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public List<Systemuser> findHospOfficeUsers(String hospid, String officeid,
			String name) {

		List<Systemuser> list = null;

		try {
			list = this.sytemUserDao
					.findHospOfficeUsers(hospid, officeid, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public SystemUserDAOImpl getSytemUserDao() {
		return sytemUserDao;
	}

	public void setSytemUserDao(SystemUserDAOImpl sytemUserDao) {
		this.sytemUserDao = sytemUserDao;
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}

}

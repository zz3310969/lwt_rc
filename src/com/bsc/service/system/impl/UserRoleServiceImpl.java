package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.bean.Userrole;
import com.bsc.dao.system.impl.UserRoleDAOImpl;
import com.bsc.service.system.IUserRoleService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class UserRoleServiceImpl implements IUserRoleService {

	private UserRoleDAOImpl userRoleDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Userrole systemUser) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();
		Object id = "";
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Userrole.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			id = userRoleDao.insert(systemUser);

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setMessage("添加数据成功");
			result.setObjectRecordId(id.toString());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(id.toString());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Userrole userRole, boolean isLog) {
		if (isLog)
			return insert(userRole);
		ResultModel result = new ResultModel();
		Object id = "";
		try {
			id = userRoleDao.insert(userRole);

			result.setIsSuccess(true);
			result.setObjectRecordId(id.toString());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(id.toString());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(String systemUserId, String roleId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		Object id = "";
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Userrole.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Userrole userRole = (Userrole) userRoleDao.getModel(systemUserId,
					roleId);
			id = userRoleDao.delete(userRole);

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(systemUserId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(id.toString());
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

	public ResultModel delete(String systemUserId, String roleId, boolean isLog) {
		if (isLog)
			return delete(systemUserId, roleId);
		ResultModel result = new ResultModel();

		try {

			Userrole userRole = (Userrole) userRoleDao.getModel(systemUserId,
					roleId);
			userRoleDao.delete(userRole);

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

	public ResultModel deleteByUserId(String userId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		Object id = "";
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Userrole.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());

			id = userRoleDao.deleteByUserId(userId);

			log.setObjectRecordId(userId);
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(userId);
			result.setResultCode(9998);
			result.setTotalCount(Long.parseLong(id.toString()));
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(-9998));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(userId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel deleteByUserId(String userId, boolean isLog) {
		if (isLog)
			return deleteByUserId(userId);
		ResultModel result = new ResultModel();
		Object id = "";
		try {
			id = userRoleDao.deleteByUserId(userId);
			result.setIsSuccess(true);
			result.setObjectRecordId(userId);
			result.setResultCode(9998);
			result.setTotalCount(Long.parseLong(id.toString()));
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(userId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel update(Userrole userRole) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		Object id = "";
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Userrole.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			id = userRoleDao.update(userRole);

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setMessage("修改数据成功");
			result.setObjectRecordId(userRole.getUserId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(userRole.getUserId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Userrole userRole, boolean isLog) {
		if (isLog)
			return update(userRole);
		ResultModel result = new ResultModel();

		try {
			userRoleDao.update(userRole);
			result.setIsSuccess(true);
			result.setObjectRecordId(userRole.getUserId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(userRole.getUserId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getUserRoleById(String systemUserId, String roleId) {
		Operationlog log = new Operationlog();
		Userrole userRole = new Userrole();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Userrole.class.getSimpleName());
			log.setOperationContent("查询单记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(systemUserId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单记录成功");

			userRole = userRoleDao.getModel(systemUserId, roleId);

			result.setData(userRole);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(systemUserId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(userRole);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getUserRoleById(String systemUserId, String roleId,
			boolean isLog) {
		if (isLog)
			return getUserRoleById(systemUserId, roleId);
		Userrole userRole = new Userrole();
		ResultModel result = new ResultModel();
		try {

			userRole = userRoleDao.getModel(systemUserId, roleId);

			result.setData(userRole);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setData(userRole);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getUserRoleList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Userrole> systemUserList = new ArrayList<Userrole>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Userrole.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			systemUserList = (List<Userrole>) (userRoleDao.getList(queryTerms));
			totalCount = userRoleDao.getTotalCount(queryTerms);

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

	public ResultModel getUserRoleList(QueryTerms queryTerms, boolean isLog) {
		if (isLog)
			return getUserRoleList(queryTerms);

		List<Userrole> systemUserList = new ArrayList<Userrole>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			systemUserList = (List<Userrole>) (userRoleDao.getList(queryTerms));
			totalCount = userRoleDao.getTotalCount(queryTerms);
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

	public UserRoleDAOImpl getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDAOImpl userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}

}

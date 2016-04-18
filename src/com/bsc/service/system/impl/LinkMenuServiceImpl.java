package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Linkmenu;
import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.LinkMenuDAOImpl;
import com.bsc.service.system.ILinkMenuService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class LinkMenuServiceImpl implements ILinkMenuService {

	private LinkMenuDAOImpl linkMenuDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Linkmenu linkMenu) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Linkmenu.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			linkMenuDao.insert(linkMenu);

			log.setObjectRecordId(linkMenu.getMenuId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setMessage("添加数据成功");
			result.setObjectRecordId(linkMenu.getMenuId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(linkMenu.getMenuId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(linkMenu.getMenuId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Linkmenu linkMenu, boolean isLog) {
		if (isLog)
			return insert(linkMenu);
		ResultModel result = new ResultModel();
		try {
			linkMenuDao.insert(linkMenu);

			result.setIsSuccess(true);
			result.setObjectRecordId(linkMenu.getMenuId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(linkMenu.getMenuId());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(String linkMenuId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Linkmenu.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Linkmenu linkMenu = (Linkmenu) linkMenuDao.getModel(linkMenuId);
			linkMenuDao.delete(linkMenu);

			log.setObjectRecordId(linkMenuId);
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(linkMenuId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(linkMenuId);
			log.setResultCode(String.valueOf(-9998));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(linkMenuId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel delete(String linkMenuId, boolean isLog) {
		if (isLog)
			return delete(linkMenuId);
		ResultModel result = new ResultModel();
		try {

			Linkmenu linkMenu = (Linkmenu) linkMenuDao.getModel(linkMenuId);
			linkMenuDao.delete(linkMenu);

			result.setIsSuccess(true);
			result.setObjectRecordId(linkMenuId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(linkMenuId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel update(Linkmenu linkMenu) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Linkmenu.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			linkMenuDao.update(linkMenu);

			log.setObjectRecordId(linkMenu.getMenuId());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setMessage("修改数据成功");
			result.setObjectRecordId(linkMenu.getMenuId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(linkMenu.getMenuId());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(linkMenu.getMenuId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Linkmenu linkMenu, boolean isLog) {
		if (isLog)
			return update(linkMenu);
		ResultModel result = new ResultModel();

		try {
			linkMenuDao.update(linkMenu);
			result.setIsSuccess(true);
			result.setObjectRecordId(linkMenu.getMenuId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(linkMenu.getMenuId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getLinkMenuById(String linkMenuId) {
		Operationlog log = new Operationlog();
		Linkmenu linkMenu = new Linkmenu();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Linkmenu.class.getSimpleName());
			log.setOperationContent("查询单记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(linkMenuId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单记录成功");

			linkMenu = linkMenuDao.getModel(linkMenuId);

			result.setData(linkMenu);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(linkMenuId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(linkMenu);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getLinkMenuById(String linkMenuId, boolean isLog) {
		if (isLog)
			return getLinkMenuById(linkMenuId);
		Linkmenu linkMenu = new Linkmenu();
		ResultModel result = new ResultModel();
		try {

			linkMenu = linkMenuDao.getModel(linkMenuId);

			result.setObjectRecordId(linkMenuId);
			result.setData(linkMenu);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setObjectRecordId(linkMenuId);
			result.setData(linkMenu);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getLinkMenuList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Linkmenu> linkMenuList = new ArrayList<Linkmenu>();
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

			linkMenuList = (List<Linkmenu>) (linkMenuDao.getList(queryTerms));
			totalCount = linkMenuDao.getTotalCount(queryTerms);

			result.setTotalCount(totalCount);
			result.setData(linkMenuList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			log.setResultCode(String.valueOf(-9995));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setTotalCount(totalCount);
			result.setData(linkMenuList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getLinkMenuList(QueryTerms queryTerms, boolean isLog) {
		if (isLog)
			return getLinkMenuList(queryTerms);

		List<Linkmenu> linkMenuList = new ArrayList<Linkmenu>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			linkMenuList = (List<Linkmenu>) (linkMenuDao.getList(queryTerms));
			totalCount = linkMenuDao.getTotalCount(queryTerms);
			result.setTotalCount(totalCount);
			result.setData(linkMenuList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			result.setTotalCount(totalCount);
			result.setData(linkMenuList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

		
	public ResultModel getLinkMenuList(String sql) {
		Operationlog log = new Operationlog();
		List<Linkmenu> linkMenuList = new ArrayList<Linkmenu>();
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

			linkMenuList = (List<Linkmenu>) (linkMenuDao.getList(sql));
		

			result.setTotalCount(totalCount);
			result.setData(linkMenuList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			log.setResultCode(String.valueOf(-9995));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setTotalCount(totalCount);
			result.setData(linkMenuList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getLinkMenuList(String sql, boolean isLog) {
		if (isLog)
			return getLinkMenuList(sql);

		List<Linkmenu> linkMenuList = new ArrayList<Linkmenu>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			linkMenuList = (List<Linkmenu>) (linkMenuDao.getList(sql));
			//totalCount = linkMenuDao.getTotalCount(queryTerms);
			result.setTotalCount(totalCount);
			result.setData(linkMenuList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			result.setTotalCount(totalCount);
			result.setData(linkMenuList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	
	
	
	
	public LinkMenuDAOImpl getLinkMenuDao() {
		return linkMenuDao;
	}

	public void setLinkMenuDao(LinkMenuDAOImpl linkMenuDao) {
		this.linkMenuDao = linkMenuDao;
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}

}

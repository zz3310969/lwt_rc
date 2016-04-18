package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Linkmenu;
import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.bean.Webpage;
import com.bsc.dao.system.impl.LinkMenuWebPageDAOImpl;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class LinkMenuWebPageServiceImpl {

	private LinkMenuWebPageDAOImpl linkMenuWebPageDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Webpage webPage) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Webpage.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			linkMenuWebPageDao.insert(webPage);

			log.setObjectRecordId(webPage.getPageId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setMessage("添加数据成功");
			result.setObjectRecordId(webPage.getPageId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(webPage.getPageId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(webPage.getPageId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Webpage webPage, boolean isLog) {
		if (isLog)
			return insert(webPage);
		ResultModel result = new ResultModel();
		try {
			linkMenuWebPageDao.insert(webPage);

			result.setIsSuccess(true);
			result.setObjectRecordId(webPage.getPageId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(webPage.getPageId());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(String dictionaryId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Webpage.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Webpage webPage = (Webpage) linkMenuWebPageDao
					.getModel(dictionaryId);
			linkMenuWebPageDao.delete(webPage);

			log.setObjectRecordId(dictionaryId);
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(dictionaryId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(dictionaryId);
			log.setResultCode(String.valueOf(-9998));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(dictionaryId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel delete(String dictionaryId, boolean isLog) {
		if (isLog)
			return delete(dictionaryId);
		ResultModel result = new ResultModel();
		try {

			Webpage webPage = (Webpage) linkMenuWebPageDao
					.getModel(dictionaryId);
			linkMenuWebPageDao.delete(webPage);

			result.setIsSuccess(true);
			result.setObjectRecordId(dictionaryId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(dictionaryId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel update(Webpage webPage) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Webpage.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			linkMenuWebPageDao.update(webPage);

			log.setObjectRecordId(webPage.getPageId());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setMessage("修改数据成功");
			result.setObjectRecordId(webPage.getPageId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(webPage.getPageId());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(webPage.getPageId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Webpage webPage, boolean isLog) {
		if (isLog)
			return update(webPage);
		ResultModel result = new ResultModel();

		try {
			linkMenuWebPageDao.update(webPage);
			result.setIsSuccess(true);
			result.setObjectRecordId(webPage.getPageId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(webPage.getPageId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getWebPageById(String dictionaryId) {
		Operationlog log = new Operationlog();
		Webpage webPage = new Webpage();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Webpage.class.getSimpleName());
			log.setOperationContent("查询单记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(dictionaryId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单记录成功");

			webPage = linkMenuWebPageDao.getModel(dictionaryId);

			result.setData(webPage);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(dictionaryId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(webPage);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getWebPageById(String dictionaryId, boolean isLog) {
		if (isLog)
			return getWebPageById(dictionaryId);
		Webpage webPage = new Webpage();
		ResultModel result = new ResultModel();
		try {

			webPage = linkMenuWebPageDao.getModel(dictionaryId);

			result.setObjectRecordId(dictionaryId);
			result.setData(webPage);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setObjectRecordId(dictionaryId);
			result.setData(webPage);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getWebPageList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Webpage> moduleList = new ArrayList<Webpage>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Webpage.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			moduleList = (List<Webpage>) (linkMenuWebPageDao
					.getList(queryTerms));
			totalCount = linkMenuWebPageDao.getTotalCount(queryTerms);

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

	public ResultModel getWebPageList(QueryTerms queryTerms, boolean isLog) {
		if (isLog)
			return getWebPageList(queryTerms);

		List<Webpage> moduleList = new ArrayList<Webpage>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			moduleList = (List<Webpage>) (linkMenuWebPageDao
					.getList(queryTerms));
			totalCount = linkMenuWebPageDao.getTotalCount(queryTerms);
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

	public ResultModel getStringList(String sql) {
		Operationlog log = new Operationlog();
		List<String> stringList = new ArrayList<String>();
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

			stringList = (List<String>) (linkMenuWebPageDao.getStringList(sql));

			result.setTotalCount(totalCount);
			result.setData(stringList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			log.setResultCode(String.valueOf(-9995));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setTotalCount(totalCount);
			result.setData(stringList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getStringList(String sql, boolean isLog) {
		if (isLog)
			return getStringList(sql);

		List<String> stringList = new ArrayList<String>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			stringList = (List<String>) (linkMenuWebPageDao.getStringList(sql));
			// totalCount = linkMenuDao.getTotalCount(queryTerms);
			result.setTotalCount(totalCount);
			result.setData(stringList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			result.setTotalCount(totalCount);
			result.setData(stringList);
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

	public LinkMenuWebPageDAOImpl getLinkMenuWebPageDao() {
		return linkMenuWebPageDao;
	}

	public void setLinkMenuWebPageDao(LinkMenuWebPageDAOImpl linkMenuWebPageDao) {
		this.linkMenuWebPageDao = linkMenuWebPageDao;
	}

}

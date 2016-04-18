package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.LinkMenuDictionaryDAOImpl;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class LinkMenuDictionaryServiceImpl {

	private LinkMenuDictionaryDAOImpl linkMenuDictionaryDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Dictionary dictionary) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Dictionary.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			linkMenuDictionaryDao.insert(dictionary);

			log.setObjectRecordId(dictionary.getId().toString());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setMessage("添加数据成功");
			result.setObjectRecordId(dictionary.getId().toString());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(dictionary.getId().toString());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(dictionary.getId().toString());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Dictionary dictionary, boolean isLog) {
		if (isLog)
			return insert(dictionary);
		ResultModel result = new ResultModel();
		try {
			linkMenuDictionaryDao.insert(dictionary);

			result.setIsSuccess(true);
			result.setObjectRecordId(dictionary.getId().toString());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(dictionary.getId().toString());
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
			log.setOperationTable(Dictionary.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Dictionary dictionary = (Dictionary) linkMenuDictionaryDao
					.getModel(dictionaryId);
			linkMenuDictionaryDao.delete(dictionary);

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

			Dictionary dictionary = (Dictionary) linkMenuDictionaryDao
					.getModel(dictionaryId);
			linkMenuDictionaryDao.delete(dictionary);

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

	public ResultModel update(Dictionary dictionary) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Dictionary.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			linkMenuDictionaryDao.update(dictionary);

			log.setObjectRecordId(dictionary.getId().toString());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setMessage("修改数据成功");
			result.setObjectRecordId(dictionary.getId().toString());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(dictionary.getId().toString());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(dictionary.getId().toString());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Dictionary dictionary, boolean isLog) {
		if (isLog)
			return update(dictionary);
		ResultModel result = new ResultModel();

		try {
			linkMenuDictionaryDao.update(dictionary);
			result.setIsSuccess(true);
			result.setObjectRecordId(dictionary.getId().toString());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(dictionary.getId().toString());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getDictionaryById(String dictionaryId) {
		Operationlog log = new Operationlog();
		Dictionary dictionary = new Dictionary();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Dictionary.class.getSimpleName());
			log.setOperationContent("查询单记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(dictionaryId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单记录成功");

			dictionary = linkMenuDictionaryDao.getModel(dictionaryId);

			result.setData(dictionary);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(dictionaryId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(dictionary);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getDictionaryById(String dictionaryId, boolean isLog) {
		if (isLog)
			return getDictionaryById(dictionaryId);
		Dictionary dictionary = new Dictionary();
		ResultModel result = new ResultModel();
		try {

			dictionary = linkMenuDictionaryDao.getModel(dictionaryId);

			result.setObjectRecordId(dictionaryId);
			result.setData(dictionary);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setObjectRecordId(dictionaryId);
			result.setData(dictionary);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getDictionaryList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Dictionary> moduleList = new ArrayList<Dictionary>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Dictionary.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			moduleList = (List<Dictionary>) (linkMenuDictionaryDao
					.getList(queryTerms));
			totalCount = linkMenuDictionaryDao.getTotalCount(queryTerms);

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

	public ResultModel getDictionaryList(QueryTerms queryTerms,
			boolean isLog) {
		if (isLog)
			return getDictionaryList(queryTerms);

		List<Dictionary> moduleList = new ArrayList<Dictionary>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			moduleList = (List<Dictionary>) (linkMenuDictionaryDao
					.getList(queryTerms));
			totalCount = linkMenuDictionaryDao.getTotalCount(queryTerms);
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

	public LinkMenuDictionaryDAOImpl getLinkMenuDictionaryDao() {
		return linkMenuDictionaryDao;
	}

	public void setLinkMenuDictionaryDao(
			LinkMenuDictionaryDAOImpl linkMenuDictionaryDao) {
		this.linkMenuDictionaryDao = linkMenuDictionaryDao;
	}

	
}

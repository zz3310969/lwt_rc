package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Operationlog;
import com.bsc.bean.Region;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.RegionDAOImpl;
import com.bsc.service.system.IRegionService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class RegionServiceImpl implements IRegionService {

	private RegionDAOImpl regionDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Region region) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();

		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Region.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			regionDao.insert(region);

			log.setObjectRecordId(region.getId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setMessage("添加数据成功");
			result.setObjectRecordId(region.getId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(region.getId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(region.getId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Region region, boolean isLog) {
		if (isLog)
			return insert(region);
		ResultModel result = new ResultModel();
		try {
			regionDao.insert(region);

			result.setIsSuccess(true);
			result.setObjectRecordId(region.getId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(region.getId());
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
			log.setOperationTable(Region.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Region region = (Region) regionDao.getModel(systemUserId);
			regionDao.delete(region);

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

			Region region = (Region) regionDao.getModel(systemUserId);
			regionDao.delete(region);

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

	public ResultModel update(Region region) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Region.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			regionDao.update(region);

			log.setObjectRecordId(region.getId());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setMessage("修改数据成功");
			result.setObjectRecordId(region.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(region.getId());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(region.getId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Region region, boolean isLog) {
		if (isLog)
			return update(region);
		ResultModel result = new ResultModel();

		try {
			regionDao.update(region);
			result.setIsSuccess(true);
			result.setObjectRecordId(region.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(region.getId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getRegionById(String systemUserId) {
		Operationlog log = new Operationlog();
		Region region = new Region();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Region.class.getSimpleName());
			log.setOperationContent("查询单条记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(systemUserId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单条记录成功");

			region = regionDao.getModel(systemUserId);

			result.setData(region);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(systemUserId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(region);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getRegionById(String systemUserId, boolean isLog) {
		if (isLog)
			return getRegionById(systemUserId);
		Region region = new Region();
		ResultModel result = new ResultModel();
		try {

			region = regionDao.getModel(systemUserId);

			result.setData(region);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setData(region);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getRegionList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Region> systemUserList = new ArrayList<Region>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Region.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			systemUserList = (List<Region>) (regionDao.getList(queryTerms));
			totalCount = regionDao.getTotalCount(queryTerms);

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

	public ResultModel getRegionList(QueryTerms queryTerms, boolean isLog) {
		if (isLog)
			return getRegionList(queryTerms);

		List<Region> systemUserList = new ArrayList<Region>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			systemUserList = (List<Region>) (regionDao.getList(queryTerms));
			totalCount = regionDao.getTotalCount(queryTerms);
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

	public RegionDAOImpl getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(RegionDAOImpl regionDao) {
		this.regionDao = regionDao;
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}

}

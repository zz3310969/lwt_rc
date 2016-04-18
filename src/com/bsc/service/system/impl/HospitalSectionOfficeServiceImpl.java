package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Hospitalsectionoffice;
import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.HospitalSectionOfficeDAOImpl;
import com.bsc.service.system.IHospitalSectionOfficeService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class HospitalSectionOfficeServiceImpl implements
		IHospitalSectionOfficeService {

	private HospitalSectionOfficeDAOImpl sectionOfficeDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Hospitalsectionoffice sectionOffice) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();

		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Hospitalsectionoffice.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			sectionOfficeDao.insert(sectionOffice);

			log.setObjectRecordId(sectionOffice.getId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(sectionOffice.getId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(sectionOffice.getId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(sectionOffice.getId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Hospitalsectionoffice sectionOffice, boolean isLog) {
		if (isLog)
			return insert(sectionOffice);
		ResultModel result = new ResultModel();

		try {
			sectionOfficeDao.insert(sectionOffice);

			result.setIsSuccess(true);
			result.setObjectRecordId(sectionOffice.getId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(sectionOffice.getId());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(String hospitalId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		Object id = "";
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Hospitalsectionoffice.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Hospitalsectionoffice sectionOffice = (Hospitalsectionoffice) sectionOfficeDao
					.getModel(hospitalId);
			id = sectionOfficeDao.delete(sectionOffice);

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(hospitalId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(-9998));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(hospitalId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel delete(String hospitalId, boolean isLog) {
		if (isLog)
			return delete(hospitalId);
		ResultModel result = new ResultModel();

		try {

			Hospitalsectionoffice sectionOffice = (Hospitalsectionoffice) sectionOfficeDao
					.getModel(hospitalId);
			sectionOfficeDao.delete(sectionOffice);

			result.setIsSuccess(true);
			result.setObjectRecordId(hospitalId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(hospitalId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel update(Hospitalsectionoffice sectionOffice) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		Object id = "";
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Hospitalsectionoffice.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			id = sectionOfficeDao.update(sectionOffice);

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(sectionOffice.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(id.toString());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(sectionOffice.getId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Hospitalsectionoffice sectionOffice, boolean isLog) {
		if (isLog)
			return update(sectionOffice);
		ResultModel result = new ResultModel();

		try {
			sectionOfficeDao.update(sectionOffice);
			result.setIsSuccess(true);
			result.setObjectRecordId(sectionOffice.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(sectionOffice.getId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getHospitalSectionOfficeById(String officeId) {
		Operationlog log = new Operationlog();
		Hospitalsectionoffice sectionOffice = new Hospitalsectionoffice();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Hospitalsectionoffice.class.getSimpleName());
			log.setOperationContent("查询单记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(officeId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单记录成功");

			sectionOffice = sectionOfficeDao.getModel(officeId);

			result.setData(sectionOffice);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(officeId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(sectionOffice);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getHospitalSectionOfficeById(String officeId,
			boolean isLog) {
		if (isLog)
			return getHospitalSectionOfficeById(officeId);
		Hospitalsectionoffice sectionOffice = new Hospitalsectionoffice();
		ResultModel result = new ResultModel();
		try {

			sectionOffice = sectionOfficeDao.getModel(officeId);

			result.setData(sectionOffice);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setData(sectionOffice);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getHospitalSectionOfficeList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Hospitalsectionoffice> hospitalList = new ArrayList<Hospitalsectionoffice>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Hospitalsectionoffice.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			hospitalList = (List<Hospitalsectionoffice>) (sectionOfficeDao
					.getList(queryTerms));
			totalCount = sectionOfficeDao.getTotalCount(queryTerms);

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

	public ResultModel getHospitalSectionOfficeList(QueryTerms queryTerms,
			boolean isLog) {
		if (isLog)
			return getHospitalSectionOfficeList(queryTerms);

		List<Hospitalsectionoffice> hospitalList = new ArrayList<Hospitalsectionoffice>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			hospitalList = (List<Hospitalsectionoffice>) (sectionOfficeDao
					.getList(queryTerms));
			totalCount = sectionOfficeDao.getTotalCount(queryTerms);
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

	public List<Hospitalsectionoffice> findByHospitalOffice(String id) {

		List<Hospitalsectionoffice> list = null;

		try {
			list = this.sectionOfficeDao.findByHospitalOffice(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public HospitalSectionOfficeDAOImpl getSectionOfficeDao() {
		return sectionOfficeDao;
	}

	public void setSectionOfficeDao(
			HospitalSectionOfficeDAOImpl sectionOfficeDao) {
		this.sectionOfficeDao = sectionOfficeDao;
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}

}

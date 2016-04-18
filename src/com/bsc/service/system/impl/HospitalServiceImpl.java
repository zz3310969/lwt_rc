package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Hospital;
import com.bsc.bean.Hospitalrelation;
import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.HospitalDAOImpl;
import com.bsc.service.system.IHospitalService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class HospitalServiceImpl implements IHospitalService {

	private HospitalDAOImpl hospitalDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Hospital hospital) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Hospital.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			 hospitalDao.insert(hospital);

			log.setObjectRecordId(hospital.getId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(hospital.getId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(hospital.getId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(hospital.getId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Hospital hospital, boolean isLog) {
		if (isLog)
			return insert(hospital);
		ResultModel result = new ResultModel();
		try {
			 hospitalDao.insert(hospital);

			result.setIsSuccess(true);
			result.setObjectRecordId(hospital.getId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(hospital.getId());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(String hospitalId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Hospital.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Hospital hospital = (Hospital) hospitalDao.getModel(hospitalId);
 hospitalDao.delete(hospital);

			log.setObjectRecordId(hospitalId);
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(hospitalId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(hospitalId);
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

			Hospital hospital = (Hospital) hospitalDao.getModel(hospitalId);
			hospitalDao.delete(hospital);

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

	public ResultModel update(Hospital hospital) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Hospital.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			 hospitalDao.update(hospital);

			log.setObjectRecordId(hospital.getId());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(hospital.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(hospital.getId());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(hospital.getId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Hospital hospital, boolean isLog) {
		if (isLog)
			return update(hospital);
		ResultModel result = new ResultModel();

		try {
			hospitalDao.update(hospital);
			result.setIsSuccess(true);
			result.setObjectRecordId(hospital.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(hospital.getId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getHospitalById(String hospitalId) {
		Operationlog log = new Operationlog();
		Hospital hospital = new Hospital();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Hospital.class.getSimpleName());
			log.setOperationContent("查询单记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(hospitalId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单记录成功");

			hospital = hospitalDao.getModel(hospitalId);

			result.setData(hospital);
			result.setObjectRecordId(hospitalId);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(hospitalId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(hospitalId);
			result.setData(hospital);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getHospitalById(String hospitalId, boolean isLog) {
		if (isLog)
			return getHospitalById(hospitalId);
		Hospital hospital = new Hospital();
		ResultModel result = new ResultModel();
		try {

			hospital = hospitalDao.getModel(hospitalId);

			result.setObjectRecordId(hospitalId);
			result.setData(hospital);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setObjectRecordId(hospitalId);
			result.setData(hospital);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getHospitalList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Hospital.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			hospitalList = (List<Hospital>) (hospitalDao.getList(queryTerms));
			totalCount = hospitalDao.getTotalCount(queryTerms);

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

	public ResultModel getHospitalList(QueryTerms queryTerms, boolean isLog) {
		if (isLog)
			return getHospitalList(queryTerms);

		List<Hospital> hospitalList = new ArrayList<Hospital>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			hospitalList = (List<Hospital>) (hospitalDao.getList(queryTerms));
			totalCount = hospitalDao.getTotalCount(queryTerms);
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

	public List<Hospitalrelation> findAllNotThen(String hospid) {
		// TODO Auto-generated method stub
		
		List<Hospitalrelation> list = null;
		
		try {
			list = this.hospitalDao.findAllNotThen(hospid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public HospitalDAOImpl getHospitalDao() {
		return hospitalDao;
	}

	public void setHospitalDao(HospitalDAOImpl hospitalDao) {
		this.hospitalDao = hospitalDao;
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}

}

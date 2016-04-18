package com.bsc.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Consultationroom;
import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.impl.ConsultationRoomDAOImpl;
import com.bsc.service.system.IConsultationRoomService;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;
import com.opensymphony.xwork2.ActionContext;

public class ConsultationRoomServiceImpl implements IConsultationRoomService {

	private ConsultationRoomDAOImpl consultationRoomDao;
	private OperationLogServiceImpl operationLogService;

	public ResultModel insert(Consultationroom consultationRoom) {
		Operationlog log = new Operationlog();
		ResultModel result = new ResultModel();

		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");

			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("01");
			log.setOperationTable(Consultationroom.class.getSimpleName());
			log.setOperationContent("添加记录");
			log.setAddTime(new Date());

			consultationRoomDao.insert(consultationRoom);

			log.setObjectRecordId(consultationRoom.getId());
			log.setResultCode(String.valueOf(9999));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("添加数据成功");

			result.setIsSuccess(true);
			result.setMessage("添加数据成功");
			result.setObjectRecordId(consultationRoom.getId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(consultationRoom.getId());
			log.setResultCode(String.valueOf(-9999));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setObjectRecordId(consultationRoom.getId());
			result.setResultCode(-9999);
			result.setIsSuccess(false);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel insert(Consultationroom consultationRoom, boolean isLog) {
		if (isLog)
			return insert(consultationRoom);
		ResultModel result = new ResultModel();
		try {
			consultationRoomDao.insert(consultationRoom);

			result.setIsSuccess(true);
			result.setObjectRecordId(consultationRoom.getId());
			result.setResultCode(9999);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(consultationRoom.getId());
			result.setResultCode(-9999);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel delete(String consultationRoomId) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("02");
			log.setOperationTable(Consultationroom.class.getSimpleName());
			log.setOperationContent("删除记录");
			log.setAddTime(new Date());
			Consultationroom consultationRoom = (Consultationroom) consultationRoomDao
					.getModel(consultationRoomId);
			consultationRoomDao.delete(consultationRoom);

			log.setObjectRecordId(consultationRoomId);
			log.setResultCode(String.valueOf(9998));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("删除数据成功");

			result.setIsSuccess(true);
			result.setObjectRecordId(consultationRoomId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(consultationRoomId);
			log.setResultCode(String.valueOf(-9998));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(consultationRoomId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel delete(String consultationRoomId, boolean isLog) {
		if (isLog)
			return delete(consultationRoomId);
		ResultModel result = new ResultModel();

		try {

			Consultationroom consultationRoom = (Consultationroom) consultationRoomDao
					.getModel(consultationRoomId);
			consultationRoomDao.delete(consultationRoom);

			result.setIsSuccess(true);
			result.setObjectRecordId(consultationRoomId);
			result.setResultCode(9998);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(consultationRoomId);
			result.setResultCode(-9998);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel update(Consultationroom consultationRoom) {
		ResultModel result = new ResultModel();
		Operationlog log = new Operationlog();
		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("03");
			log.setOperationTable(Consultationroom.class.getSimpleName());
			log.setOperationContent("修改记录");
			log.setAddTime(new Date());
			consultationRoomDao.update(consultationRoom);

			log.setObjectRecordId(consultationRoom.getId());
			log.setResultCode(String.valueOf(9997));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("修改数据成功");

			result.setIsSuccess(true);
			result.setMessage("修改数据成功");
			result.setObjectRecordId(consultationRoom.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(consultationRoom.getId());
			log.setResultCode(String.valueOf(-9997));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setIsSuccess(false);
			result.setObjectRecordId(consultationRoom.getId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel update(Consultationroom consultationRoom, boolean isLog) {
		if (isLog)
			return update(consultationRoom);
		ResultModel result = new ResultModel();

		try {
			consultationRoomDao.update(consultationRoom);
			result.setIsSuccess(true);
			result.setObjectRecordId(consultationRoom.getId());
			result.setResultCode(9997);
			return result;
		} catch (Exception ex) {
			result.setIsSuccess(false);
			result.setObjectRecordId(consultationRoom.getId());
			result.setResultCode(-9997);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getConsultationRoomById(String consultationRoomId) {
		Operationlog log = new Operationlog();
		Consultationroom consultationRoom = new Consultationroom();
		ResultModel result = new ResultModel();
		try {
			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("04");
			log.setOperationTable(Consultationroom.class.getSimpleName());
			log.setOperationContent("查询单记录");
			log.setAddTime(new Date());
			log.setObjectRecordId(consultationRoomId);
			log.setResultCode(String.valueOf(9996));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("查询单记录成功");

			consultationRoom = consultationRoomDao.getModel(consultationRoomId);

			result.setData(consultationRoom);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {

			log.setObjectRecordId(consultationRoomId);
			log.setResultCode(String.valueOf(-9996));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setData(consultationRoom);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getConsultationRoomById(String consultationRoomId,
			boolean isLog) {
		if (isLog)
			return getConsultationRoomById(consultationRoomId);
		Consultationroom consultationRoom = new Consultationroom();
		ResultModel result = new ResultModel();
		try {

			consultationRoom = consultationRoomDao.getModel(consultationRoomId);

			result.setData(consultationRoom);
			result.setIsSuccess(true);
			result.setResultCode(9996);
			return result;
		} catch (Exception ex) {
			result.setData(consultationRoom);
			result.setIsSuccess(false);
			result.setResultCode(-9996);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ResultModel getConsultationRoomList(QueryTerms queryTerms) {
		Operationlog log = new Operationlog();
		List<Consultationroom> consultationRoomList = new ArrayList<Consultationroom>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {

			Systemuser loginUser = (Systemuser) ActionContext.getContext()
					.getSession().get("sysuser");
			log.setSystemUserId(loginUser.getUserId());
			log.setId(UUID.randomUUID().toString());
			log.setOperationType("05");
			log.setOperationTable(Consultationroom.class.getSimpleName());
			log.setOperationContent("模糊查询");
			log.setAddTime(new Date());
			log.setResultCode(String.valueOf(9995));
			log.setIsSuccess(String.valueOf(0));
			log.setResultMessage("模糊查询成功");

			consultationRoomList = (List<Consultationroom>) (consultationRoomDao
					.getList(queryTerms));
			totalCount = consultationRoomDao.getTotalCount(queryTerms);

			result.setTotalCount(totalCount);
			result.setData(consultationRoomList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			log.setResultCode(String.valueOf(-9995));
			log.setIsSuccess(String.valueOf(1));
			log.setResultMessage(ex.getMessage());

			result.setTotalCount(totalCount);
			result.setData(consultationRoomList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		} finally {
			operationLogService.insert(log);
		}
	}

	public ResultModel getConsultationRoomList(QueryTerms queryTerms,
			boolean isLog) {
		if (isLog)
			return getConsultationRoomList(queryTerms);

		List<Consultationroom> consultationRoomList = new ArrayList<Consultationroom>();
		Long totalCount = new Long(0);
		ResultModel result = new ResultModel();

		try {
			consultationRoomList = (List<Consultationroom>) (consultationRoomDao
					.getList(queryTerms));
			totalCount = consultationRoomDao.getTotalCount(queryTerms);
			result.setTotalCount(totalCount);
			result.setData(consultationRoomList);
			result.setIsSuccess(true);
			result.setResultCode(9995);
			return result;
		} catch (Exception ex) {
			result.setTotalCount(totalCount);
			result.setData(consultationRoomList);
			result.setIsSuccess(false);
			result.setResultCode(-9995);
			result.setMessage(ex.getMessage());
			return result;
		}
	}

	public ConsultationRoomDAOImpl getConsultationRoomDao() {
		return consultationRoomDao;
	}

	public void setConsultationRoomDao(
			ConsultationRoomDAOImpl consultationRoomDao) {
		this.consultationRoomDao = consultationRoomDao;
	}

	public OperationLogServiceImpl getOperationLogService() {
		return operationLogService;
	}

	public void setOperationLogService(
			OperationLogServiceImpl operationLogService) {
		this.operationLogService = operationLogService;
	}
}

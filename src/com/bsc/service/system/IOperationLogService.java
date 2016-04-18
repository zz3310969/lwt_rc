package com.bsc.service.system;

import com.bsc.bean.Operationlog;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IOperationLogService {

	public ResultModel insert(Operationlog log);

	public ResultModel delete(String logId);

	public ResultModel update(Operationlog log);

	public ResultModel getOperationLogById(String logId);

	public ResultModel getOperationLogList(QueryTerms queryTerms);

}

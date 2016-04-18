package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IOperationLogDAO {

	public boolean insert(Operationlog log) throws Exception;

	public boolean delete(Operationlog log) throws Exception;

	public boolean update(Operationlog log) throws Exception;

	public Operationlog getModel(String logId) throws Exception;

	public List<Operationlog> getList(QueryTerms queryTerms) throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

}

package com.bsc.dao.system.impl;

import java.util.List;

import com.bsc.bean.Operationlog;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.IOperationLogDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class OperationLogDAOImpl implements IOperationLogDAO {
	DataAccessUtility utility;

	public boolean insert(Operationlog log) throws Exception {
		return utility.insert(log);
	}

	public boolean delete(Operationlog log) throws Exception {
		return utility.delete(log);
	}

	public boolean update(Operationlog log) throws Exception {
		return utility.update(log);
	}

	public List<Operationlog> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Operationlog getModel(String id) throws Exception {
		return utility.getModel(Operationlog.class, id);
	}

	public Long getTotalCount(QueryTerms queryTerms) throws Exception {
		return utility.getTotalCount(queryTerms);
	}

	public DataAccessUtility getUtility() {
		return utility;
	}

	public void setUtility(DataAccessUtility utility) {
		this.utility = utility;
	}

}

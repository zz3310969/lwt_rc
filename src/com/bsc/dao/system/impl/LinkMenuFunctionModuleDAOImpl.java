package com.bsc.dao.system.impl;

import java.util.List;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Linkmenu;
import com.bsc.bean.Systemuser;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class LinkMenuFunctionModuleDAOImpl {
	DataAccessUtility utility;

	public boolean insert(Functionmodule module) throws Exception {
		return utility.insert(module);
	}

	public boolean delete(Functionmodule module) throws Exception {
		return utility.delete(module);
	}

	public boolean update(Functionmodule module) throws Exception {
		return utility.update(module);
	}

	public List<Functionmodule> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Functionmodule getModel(String id) throws Exception {
		return utility.getModel(Functionmodule.class, id);
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

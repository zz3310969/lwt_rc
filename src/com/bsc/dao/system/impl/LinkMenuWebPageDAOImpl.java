package com.bsc.dao.system.impl;

import java.util.List;

import com.bsc.bean.Webpage;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class LinkMenuWebPageDAOImpl {
	DataAccessUtility utility;

	public boolean insert(Webpage page) throws Exception {
		return utility.insert(page);
	}

	public boolean delete(Webpage page) throws Exception {
		return utility.delete(page);
	}

	public boolean update(Webpage page) throws Exception {
		return utility.update(page);
	}

	public List<Webpage> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Webpage getModel(String id) throws Exception {
		return utility.getModel(Webpage.class, id);
	}

	public Long getTotalCount(QueryTerms queryTerms) throws Exception {
		return utility.getTotalCount(queryTerms);
	}

	public List<String> getStringList(String sql) throws Exception {
		return utility.getList(sql);
	}

	public DataAccessUtility getUtility() {
		return utility;
	}

	public void setUtility(DataAccessUtility utility) {
		this.utility = utility;
	}

}

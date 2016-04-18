package com.bsc.dao.system.impl;

import java.util.List;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Functionmodule;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class LinkMenuDictionaryDAOImpl {
	DataAccessUtility utility;

	public boolean insert(Dictionary dictionary) throws Exception {
		return utility.insert(dictionary);
	}

	public boolean delete(Dictionary dictionary) throws Exception {
		return utility.delete(dictionary);
	}

	public boolean update(Dictionary dictionary) throws Exception {
		return utility.update(dictionary);
	}

	public List<Dictionary> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Dictionary getModel(String id) throws Exception {
		return utility.getModel(Dictionary.class, id);
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

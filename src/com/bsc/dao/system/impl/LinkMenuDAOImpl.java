package com.bsc.dao.system.impl;

import java.util.List;

import com.bsc.bean.Linkmenu;
import com.bsc.bean.Systemuser;
import com.bsc.dao.system.ILinkMenuDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class LinkMenuDAOImpl implements ILinkMenuDAO {
	DataAccessUtility utility;

	public boolean insert(Linkmenu menu) throws Exception {
		return utility.insert(menu);
	}

	public boolean delete(Linkmenu menu) throws Exception {
		return utility.delete(menu);
	}

	public boolean update(Linkmenu menu) throws Exception {
		return utility.update(menu);
	}

	public List<Linkmenu> getList(String sql) throws Exception {
		return utility.getList(sql);
	}

	public List<Linkmenu> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Linkmenu getModel(String id) throws Exception {
		return utility.getModel(Linkmenu.class, id);
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

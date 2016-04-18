package com.bsc.dao.system.impl;

import java.util.List;

import com.bsc.bean.Region;
import com.bsc.dao.system.IRegionDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class RegionDAOImpl implements IRegionDAO {
	DataAccessUtility utility;

	public boolean insert(Region region) throws Exception {
		return utility.insert(region);
	}

	public boolean delete(Region region) throws Exception {
		return utility.delete(region);
	}

	public boolean update(Region region) throws Exception {
		return utility.update(region);
	}

	public List<Region> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Region getModel(String id) throws Exception {
		return utility.getModel(Region.class, id);
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

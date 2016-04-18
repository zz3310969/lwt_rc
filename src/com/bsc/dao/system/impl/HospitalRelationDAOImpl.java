package com.bsc.dao.system.impl;

import java.math.BigInteger;
import java.util.List;

import com.bsc.bean.Hospitalrelation;
import com.bsc.bean.HospitalrelationId;
import com.bsc.dao.system.IHospitalRelationDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class HospitalRelationDAOImpl implements IHospitalRelationDAO {
	DataAccessUtility utility;

	public boolean insert(Hospitalrelation relation) throws Exception {
		return utility.insert(relation);
	}

	// public boolean delete(Hospitalrelation relation) throws Exception {
	// return utility.delete(relation);
	// }

	public boolean delete(HospitalrelationId relationId) throws Exception {
		String sql = "delete from hospitalrelation where callerId='"
				+ relationId.getCallerId() + "' and receiverId='"
				+ relationId.getReceiverId() + "'";
		Object obj = utility.createSQLQuery(sql);
		if (Integer.valueOf(obj.toString()) == 1)
			return true;
		return false;
	}

	public boolean update(Hospitalrelation relation) throws Exception {
		return utility.update(relation);
	}

	public List<Hospitalrelation> getList(QueryTerms queryTerms)
			throws Exception {
		return utility.getList(queryTerms);
	}

	public List<Hospitalrelation> getList(String sql) throws Exception {
		return utility.getList(sql);
	}

	public Long getTotalCount(String sql) throws Exception {
		BigInteger count = (BigInteger) utility.getTotalCount(sql);
		return count.longValue();
	}

	public Hospitalrelation getModel(HospitalrelationId realtionId)
			throws Exception {
		return utility.getModel(Hospitalrelation.class, realtionId);
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

package com.bsc.dao.system.impl;

import java.util.List;

import com.bsc.bean.Role;
import com.bsc.bean.Systemuser;
import com.bsc.bean.Userrole;
import com.bsc.dao.system.IUserRoleDAO;
import com.bsc.util.DataAccessUtility;
import com.bsc.util.QueryTerms;

public class UserRoleDAOImpl implements IUserRoleDAO {
	DataAccessUtility utility;

	public boolean insert(Userrole role) throws Exception {
		return utility.insert(role);
	}

	public boolean delete(Userrole role) throws Exception {
		return utility.delete(role);
	}

	public boolean update(Userrole role) throws Exception {
		return utility.update(role);
	}

	public List<Userrole> getList(QueryTerms queryTerms) throws Exception {
		return utility.getList(queryTerms);
	}

	public Userrole getModel(String userId, String roleId) throws Exception {
		String sql = "select * FROM userrole WHERE roleId = '" + roleId
				+ "' and userId='" + userId + "'";

		Userrole userRole = (Userrole) utility.getList(sql).get(0);
		return userRole;
	}

	public Long getTotalCount(QueryTerms queryTerms) throws Exception {
		return utility.getTotalCount(queryTerms);
	}

	public Object deleteByUserId(String userId) throws Exception {
		String sql = "delete from userRole where userId='" + userId + "'";

		return utility.createSQLQuery(sql);

	}

	public DataAccessUtility getUtility() {
		return utility;
	}

	public void setUtility(DataAccessUtility utility) {
		this.utility = utility;
	}

}

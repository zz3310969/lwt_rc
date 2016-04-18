package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Systemuser;
import com.bsc.bean.Userrole;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IUserRoleDAO {

	public boolean insert(Userrole role) throws Exception;

	public boolean delete(Userrole role) throws Exception;

	public boolean update(Userrole role) throws Exception;

	public Userrole getModel(String userId,String roleId) throws Exception;

	public List<Userrole> getList(QueryTerms queryTerms) throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

}

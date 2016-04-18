package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Systemuser;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface ISystemUserDAO {

	public boolean insert(Systemuser user) throws Exception;

	public boolean delete(Systemuser user) throws Exception;

	public boolean update(Systemuser user) throws Exception;

	public Systemuser getModel(String userId) throws Exception;

	public List<Systemuser> getList(QueryTerms queryTerms) throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

	public List<Systemuser> findHospOfficeUsers(String hospid,String officeid,String name) throws Exception;
}

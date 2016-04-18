package com.bsc.service.system;

import java.util.List;

import com.bsc.bean.Systemuser;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface ISystemUserService {

	public ResultModel insert(Systemuser user);

	public ResultModel delete(String userId);

	public ResultModel update(Systemuser user);

	public ResultModel getSystemUserById(String userId);

	public ResultModel getSystemUserList(QueryTerms queryTerms);

	public List<Systemuser> findHospOfficeUsers(String hospid, String officeid,
			String name);
}

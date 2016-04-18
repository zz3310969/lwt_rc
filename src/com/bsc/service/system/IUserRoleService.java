package com.bsc.service.system;

import com.bsc.bean.Userrole;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IUserRoleService {

	public ResultModel insert(Userrole userRole );

	public ResultModel delete(String userId,String roleId);

	public ResultModel update(Userrole role);

	public ResultModel getUserRoleById(String userId,String roleId);

	public ResultModel getUserRoleList(QueryTerms queryTerms);

}

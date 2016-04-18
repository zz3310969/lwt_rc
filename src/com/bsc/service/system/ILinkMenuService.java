package com.bsc.service.system;

import com.bsc.bean.Linkmenu;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface ILinkMenuService {

	public ResultModel insert(Linkmenu menu);

	public ResultModel delete(String menuId);

	public ResultModel update(Linkmenu menu);

	public ResultModel getLinkMenuById(String menuId);

	public ResultModel getLinkMenuList(QueryTerms queryTerms);

}

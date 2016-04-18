package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Linkmenu;
import com.bsc.bean.Systemuser;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface ILinkMenuDAO {

	public boolean insert(Linkmenu menu) throws Exception;

	public boolean delete(Linkmenu menu) throws Exception;

	public boolean update(Linkmenu menu) throws Exception;

	public Linkmenu getModel(String menuId) throws Exception;

	public List<Linkmenu> getList(QueryTerms queryTerms) throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

}

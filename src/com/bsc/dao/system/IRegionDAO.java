package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Region;
import com.bsc.util.QueryTerms;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IRegionDAO {

	public boolean insert(Region region) throws Exception;

	public boolean delete(Region region) throws Exception;

	public boolean update(Region region) throws Exception;

	public Region getModel(String regionId) throws Exception;

	public List<Region> getList(QueryTerms queryTerms) throws Exception;

	public Long getTotalCount(QueryTerms queryTerms) throws Exception;

}

package com.bsc.service.system;

import com.bsc.bean.Region;
import com.bsc.util.QueryTerms;
import com.bsc.util.ResultModel;

/**
 * 
 * @author MengXianzhi
 * 
 */
public interface IRegionService {

	public ResultModel insert(Region region);

	public ResultModel delete(String regionId);

	public ResultModel update(Region region);

	public ResultModel getRegionById(String regionId);

	public ResultModel getRegionList(QueryTerms queryTerms);

}

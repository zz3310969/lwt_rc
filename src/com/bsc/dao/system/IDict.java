package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Dictionarytype;
import com.bsc.dao.IDAO;
/**
 * 数据字典操作
 * @author hanjin7278
 *
 */
public interface IDict extends IDAO<Dictionary> {
	/**
	 * 根据ID查询所有该code类型信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Dictionary> findByIds(String id) throws Exception;
	/**
	 * 查询出所有类别信息
	 * @return
	 * @throws Exception
	 */
	public List<Dictionarytype> findAllType() throws Exception;
	/**
	 * 模糊查询
	 * @param typeName
	 * @param kw
	 * @return
	 * @throws Exception
	 */
	public List<Dictionary> findAllkw(String typeName,String kw,int cp,int ls) throws Exception;
	/**
	 * 模糊查询的最大记录数
	 * @param typeName
	 * @param kw
	 * @return
	 * @throws Exception
	 */
	public Long getKwMaxCount(String typeName,String kw) throws Exception;
}

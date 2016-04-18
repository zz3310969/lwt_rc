package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Nation;
/**
 * 民族操作
 * @author hanjin7278
 *
 */
public interface INationDAO {
	/**
	 * 查询全部民族
	 * @return
	 * @throws Exception
	 */
	public List<Nation> findAll() throws Exception;
	/**
	 * 查询一个民族信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Nation findById(int id) throws Exception;
}

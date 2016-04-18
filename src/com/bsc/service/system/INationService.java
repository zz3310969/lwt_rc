package com.bsc.service.system;

import java.util.List;

import com.bsc.bean.Nation;

public interface INationService {
	/**
	 * 查询全部民族
	 * @return
	 */
	public List<Nation> findAll();
	/**
	 * 查询一个民族信息
	 * @return
	 */
	public Nation findByid(int id);
	
}

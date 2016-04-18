package com.bsc.service.system;

import java.util.List;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Dictionarytype;

public interface IDictService {
	/**
	 * 获得该类型的所有信息
	 * @param typeCode
	 * @return
	 */
	public List<Dictionary> getDicts(String typeCode);
	
	/**
	 * 分页查询全部
	 * @param cp
	 * @param ls
	 * @return
	 */
	public List<Dictionary> findAll(int cp,int ls);
	/**
	 * 取出全部记录数
	 * @return
	 */
	public Long getMaxCount();
	/**
	 * 取出所有类别信息列表
	 * @return
	 */
	public List<Dictionarytype> findAllType();
	/**
	 * 查询所有模糊查询
	 * @param typeName
	 * @param kw
	 * @param cp
	 * @param ls
	 * @return
	 */
	public List<Dictionary> findAllkw(final String typeName,final String kw,final int cp,final int ls);
	/**
	 * 模糊查询全部记录数
	 * @param typeName
	 * @param kw
	 * @return
	 */
	public Long getKwMaxCount(String typeName, String kw);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean onDelete(int id);
	/**
	 * 修改
	 * @return
	 */
	public boolean onUpdate(Dictionary dict);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Dictionary findByid(String id);
	/**
	 * 增加
	 * @return
	 */
	public boolean onCreate(Dictionary dict);
}

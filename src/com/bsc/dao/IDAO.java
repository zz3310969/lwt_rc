package com.bsc.dao;

import java.util.List;
/**
 * 全局接口
 * @author 韩进城
 *
 * @param <T>
 */
public interface IDAO<T> {
	/**
	 * 增加
	 * @param t
	 * @return
	 * @throws Exception
	 */
	boolean onCreate(T t) throws Exception;
	/**
	 * 删除
	 * @param t
	 * @return
	 * @throws Exception
	 */
	boolean onRemove(T t) throws Exception;
	/**
	 * 修改
	 * @param t
	 * @return
	 * @throws Exception
	 */
	boolean onUpdate(T t) throws Exception;
	/**
	 * 查询所有
	 * @param cp
	 * @param ls
	 * @return
	 * @throws Exception
	 */
	List<T> findAll(int cp,int ls) throws Exception;
	/**
	 * 获得唯一
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T findById(String id) throws Exception;
	/**
	 * 取得所有记录数
	 * @return
	 * @throws Exception
	 */
	Long getCount() throws Exception;
}

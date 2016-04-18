package com.bsc.dao.system;

import java.util.List;

import com.bsc.bean.Webpage;

public interface IWebPageDAO {
	/**
	 * 增加Webpage
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public boolean onCreatePage(Webpage page) throws Exception;
	/**
	 * 删除
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public boolean onRemove(Webpage page) throws Exception;
	/**
	 * 修改
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public boolean onUpdate(Webpage page) throws Exception;
	/**
	 * 根据ID查询一个webpage
	 * @param pageId
	 * @return
	 * @throws Exception
	 */
	public Webpage findByID(String pageId) throws Exception;
	/**
	 * 查询所有webpage
	 * @return
	 * @throws Exception
	 */
	public List<Webpage> findAll(int pagesize,int currentpage) throws Exception;

	/**
	 * 取得全部记录数
	 * @return
	 * @throws Exception
	 */
	public Long getCountPages()throws Exception;
	/**
	 * 删除全部
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAll() throws Exception;
	/**
	 * 模糊查询
	 * @param kw
	 * @return
	 * @throws Exception
	 */
	public List<Webpage> findAll(String kw,int cp,int ls) throws Exception;
	
	/**
	 * 取得全部记录数
	 * @return
	 * @throws Exception
	 */
	public Long getCountPages(String kw)throws Exception;
	/**
	 * 删除所有funpage
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAllFunpage() throws Exception;
}

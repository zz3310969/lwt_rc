package com.bsc.service.system;

import java.util.List;

import com.bsc.bean.Webpage;

public interface IWebPageService {
	/**
	 * 业务层增加webpage
	 * @param page
	 * @return
	 */
	public boolean onCreate(Webpage page);
	/**
	 * 删除
	 * @param page
	 * @return
	 */
	public boolean onRemove(Webpage page);
	/**
	 * 修改
	 * @param page
	 * @return
	 */
	public boolean onUpdate(Webpage page);
	/**
	 * 根据ID查询
	 * @param pageId
	 * @return
	 */
	public Webpage findById(String pageId);
	/**
	 * 分页查询列表
	 * @param pagesize
	 * @param currentpage
	 * @return
	 */
	public List<Webpage> findAll(int pagesize,int currentpage);
	/**
	 * 取得全部记录数
	 * @return
	 */
	public Long getCount(String kw);
	/**
	 * 删除全部记录
	 * @return
	 */
	public boolean deleteAll();
	/**
	 * 模糊查询URL
	 * @param kw
	 * @return
	 */
	public List<Webpage> findAll(String kw,int cp,int ls);
}

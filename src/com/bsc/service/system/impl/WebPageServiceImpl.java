package com.bsc.service.system.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.bsc.bean.Webpage;
import com.bsc.dao.system.IWebPageDAO;
import com.bsc.dao.system.impl.WebPageDAOImpl;
import com.bsc.service.system.IWebPageService;

public class WebPageServiceImpl implements IWebPageService {

	private IWebPageDAO webpagedao;
	/**
	 * 分页查询webpage
	 */
	public List<Webpage> findAll(int pagesize, int currentpage) {
		// TODO Auto-generated method stub
		
		List<Webpage> pages = null;
		
		try {
			pages = this.webpagedao.findAll(pagesize, currentpage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pages;
	}
	/**
	 * 根据id查询wenpage
	 */
	public Webpage findById(String pageId) {
		// TODO Auto-generated method stub
		Webpage page = null;
		
		try {
			page = this.webpagedao.findByID(pageId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return page;
	}
	/**
	 * 增加webpage
	 */
	public boolean onCreate(Webpage page) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		page.setAddTime(new Date());
		
		page.setPageId(UUID.randomUUID().toString());
		
		try {
			flag = this.webpagedao.onCreatePage(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 删除
	 */
	public boolean onRemove(Webpage page) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.webpagedao.onRemove(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 修改
	 */
	public boolean onUpdate(Webpage page) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			
			page.setUpdateTime(new Date());
			
			flag = this.webpagedao.onUpdate(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 取得全部记录数
	 */
	public Long getCount(String kw) {
		// TODO Auto-generated method stub
		if(kw == null && "".equals(kw)){
			try {
				Long count = this.webpagedao.getCountPages();
				
				return count;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				Long count = this.webpagedao.getCountPages(kw);
				
				return count;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0L;
	}
	/**
	 * 删除全部
	 */
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.webpagedao.deleteAllFunpage();
			flag = this.webpagedao.deleteAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 模糊查询URL
	 */
	public List<Webpage> findAll(String kw,int cp,int ls) {
		// TODO Auto-generated method stub
		try {
			return this.webpagedao.findAll(kw,cp,ls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public IWebPageDAO getWebpagedao() {
		return webpagedao;
	}
	public void setWebpagedao(IWebPageDAO webpagedao) {
		this.webpagedao = webpagedao;
	}
	
}

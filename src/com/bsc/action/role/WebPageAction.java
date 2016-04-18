package com.bsc.action.role;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bsc.bean.Functionpage;
import com.bsc.bean.FunctionpageId;
import com.bsc.bean.Webpage;
import com.bsc.service.system.IFunctionModelService;
import com.bsc.service.system.IWebPageService;
import com.bsc.service.system.impl.WebPageServiceImpl;
import com.bsc.util.GenerateWebPageData;
import com.googlecode.jsonplugin.annotations.JSON;

public class WebPageAction {

	private IWebPageService webpageservice;
	
	private IFunctionModelService functionmodleservice;
	
	private Webpage page;
	
	private String msg;  //提示信息
	
	private int pagesize = 25;  //每页显示记录数
	
	private int currentpage=1; //当前页
	
	private Long maxCount;
	
	private List<Webpage> pagelist;
	
	private String kw ; //模糊查询的URL
	
	/**
	 * 增加webpage
	 * @return
	 */
	public String addWebPage(){
		boolean flag = false;
		
		if(this.page != null){
			
			this.page.setPageId("");
			
			flag = this.webpageservice.onCreate(page);
			
			this.page = null;
		}
		
		if(flag)
			this.msg = "SUCCESS";
		else
			this.msg = "ERROR";
		
		return "json";
	}
	
	/**
	 * 查询一个webpage信息
	 * @return
	 */
	public String findByIdPage(){
		
		if(this.page != null){
			
			this.pagelist = null;
			this.msg = "SUCCESS";
			
			this.page = this.webpageservice.findById(this.page.getPageId());
			
		}
		
		return "json";
	}
	/**
	 * 删除全部
	 * @return
	 */
	public String deleteAll(){
		
		boolean flag = false;
		
		flag = this.webpageservice.deleteAll();
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	
	/**
	 * 更新webpage
	 * @return
	 */
	public String updateWebPage(){
		
		boolean flag = false;
		
		if(this.page != null){
			
			flag = this.webpageservice.onUpdate(this.page);
			
		}
		
		this.msg=flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 删除webpage
	 * @return
	 */
	public String deleteWebPage(){
		
		boolean flag = false;
		
		if(this.page != null){
			
			Functionpage fun = this.functionmodleservice.findFunpageById(this.getPage().getPageId());
			//说明有上层引用
			if(fun != null && fun.getId().getFunctionModuleId()!= null && !"".equals(fun.getId().getFunctionModuleId())){
				
				this.msg = "此页面有上层关系引用，请删除包含该页面的模块！";
				
				return "json";
			}
			
			this.page = this.webpageservice.findById(this.page.getPageId());
			
			flag = this.webpageservice.onRemove(page);
		}
		
		this.msg=flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	
	/**
	 * 分页查询webpage
	 * @return
	 */
	public String findAll(){
		this.maxCount = this.webpageservice.getCount("");  //取得所有记录数
		if(pagesize >30) pagesize = 30;
		this.pagelist = this.webpageservice.findAll(pagesize, currentpage);
		
		return "json";
	}
	
	/**
	 * 模糊查询URLAction
	 * @return
	 */
	public String findKwAll(){
		
		if(!"".equals(this.kw) && this.kw != null && !"请输入查询关键字".equals(this.kw)){
			//如果有关键字查询
			this.pagelist = this.webpageservice.findAll(kw,this.currentpage,this.pagesize);
			
			this.maxCount = this.webpageservice.getCount(kw);  //取得所有记录数
			
		}else{
			//没有查询则默认获取行数据
			this.pagelist = this.webpageservice.findAll(this.pagesize, currentpage);
			this.maxCount = this.webpageservice.getCount("");  //取得所有记录数
		}
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	
	/**
	 * 增加所有
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addAll(){
		
		GenerateWebPageData gen = new GenerateWebPageData();
		
		try {
			ArrayList list = gen.generateFileList(new File(gen.getExt()));
			boolean flag = gen.insertData(list);
			
			this.msg = flag?"SUCCESS":"ERROR";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "json";
	}
	
	@JSON(serialize=false)
	public IWebPageService getWebpageservice() {
		return webpageservice;
	}

	public void setWebpageservice(IWebPageService webpageservice) {
		this.webpageservice = webpageservice;
	}

	public Webpage getPage() {
		return page;
	}



	public void setPage(Webpage page) {
		this.page = page;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public Long getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Long maxCount) {
		this.maxCount = maxCount;
	}
	public List<Webpage> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<Webpage> pagelist) {
		this.pagelist = pagelist;
	}
	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}
	@JSON(serialize=false)
	public IFunctionModelService getFunctionmodleservice() {
		return functionmodleservice;
	}

	public void setFunctionmodleservice(IFunctionModelService functionmodleservice) {
		this.functionmodleservice = functionmodleservice;
	}

}

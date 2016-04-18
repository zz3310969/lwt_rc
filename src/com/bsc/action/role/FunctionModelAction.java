package com.bsc.action.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Hibernate;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Functionmodule;
import com.bsc.bean.Functionpage;
import com.bsc.bean.FunctionpageId;
import com.bsc.bean.Role;
import com.bsc.bean.Roleright;
import com.bsc.bean.Systemuser;
import com.bsc.bean.Userrole;
import com.bsc.bean.Webpage;
import com.bsc.service.system.IDictService;
import com.bsc.service.system.IFunctionModelService;
import com.bsc.service.system.IWebPageService;
import com.bsc.service.system.impl.DictServiceImpl;
import com.bsc.service.system.impl.FunctionModeServiceImpl;
import com.bsc.service.system.impl.WebPageServiceImpl;
import com.googlecode.jsonplugin.annotations.JSON;
import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.opensymphony.xwork2.ActionContext;

public class FunctionModelAction {

	public static final String TYPESORTID="07";  //排序
	
	public static final String TYPEMODEL="06";  //模块等级
	
	public static final String TYPEISPUBLIC="08"; //是否为公共
	
	private IFunctionModelService modelServiceImpl; //IOC注入服务层模块
	
	private IWebPageService webpageserviceimpl;  //IOC注入Webpage服务层
	
	private IDictService	dictservice; //字典service层
	
	private List<Functionmodule> pagelist;  //所有模块

	private Long maxCount; //全部记录数
	
	private int pagesize = 25;  //每页显示记录数
	
	private int currentpage=1; //当前页
	
	private String msg;

	private String modeId;  //取得functionMode对象id
	
	private List<String> webpageids;  //webpage的id集合

	private String temppageid ;  //获取所有的webpageid前台传入
	
	private Functionmodule mode;  //mode对象
	
	private String leveId; //模块等级ID
	
	private String typeId; //字典中的type类型ID

	private List<Dictionary> dicts,sorts,isPublic; //实体列表字典模块的dicts,sorts,排序的

	private List<Webpage> webpases;  //模块里包含的webpage
	
	private String kw; //模糊关键字
	

	//_start
	/**
	 * 获得所有列表数据
	 * @return
	 */
	public String findAll(){
		
		this.pagelist = this.modelServiceImpl.findAll(currentpage, pagesize);
		
		findDict();
		
		return "json";
	}
	/**
	 * 模糊查询
	 * @return
	 */
	public String findAllKw(){
		
		if(this.kw !=null && !"".equals(this.kw) && !"请输入查询关键字".equals(this.kw)){
			this.pagelist = this.modelServiceImpl.findAll(currentpage, pagesize,kw);
		}else{
			
			this.pagelist = this.modelServiceImpl.findAll(currentpage, pagesize);
		}
		
		
		findDict();
		
		return "json";
	}
	
	/**
	 * 取得所有记录数
	 * @return
	 */
	public String getCount(){
		
		if(this.kw == null && "".equals(kw) || "请输入查询关键字".equals(this.kw)){
			
			this.maxCount = this.modelServiceImpl.getCount("");
			
		}else{
			this.maxCount = this.modelServiceImpl.getCount(kw);
		}
		
		
		return "json";
	}
	/**
	 * 保存funpage
	 * @return
	 */
	public String saveFunPages(){
		
		boolean flag = false;
		
		//先取得funmode
		Functionmodule funmode = null;
		
		FunctionpageId fid = null;
		
		Functionpage funpage = null;
		
		List<Functionpage> functionpagelist = new ArrayList<Functionpage>();  //保存functionpage

		if(this.modeId != null && !"".equals(this.modeId)){
			
			funmode = this.modelServiceImpl.findById(modeId);  //取出模块对象
			
			Set<Functionpage> fs = funmode.getFunctionpages();
			
			for(Functionpage fr:fs){
				
				this.modelServiceImpl.onDeleteFunpage(fr);  //将原有的页面全部删除
				
			}
			
			this.webpageids = this.converList(this.temppageid);
			//将所有的webpage信息查询出来
			if(this.webpageids !=null && this.webpageids.size()>0){
				
				fs.clear();
				
				for(String s:this.webpageids){
					
					funpage = new Functionpage();
					
					fid = new FunctionpageId();
					
					Webpage webpage = this.webpageserviceimpl.findById(s);
					
					fid.setFunctionModuleId(this.modeId);
					fid.setWebPageId(webpage.getPageId());
					
					funpage.setId(fid);
					
					funpage.setAddTime(new Date());
					
					funpage.setFunctionmodule(funmode);
					
					funpage.setWebpage(webpage);
					
					fs.add(funpage);
				}
				
				funmode.setFunctionpages(fs);
				
				flag = this.modelServiceImpl.onUpdateMode(funmode);
				
			}
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 将string转换成list
	 * @param arg
	 * @return
	 */
	private List<String> converList(String arg){
		
		if(arg == null || "".equals(arg))
			return null;
		
		List<String> tt = new ArrayList<String>();
		
		String[] ar = arg.toString().split("#");
		
		for(String ss:ar){
			
			tt.add(ss);
			
		}
		return tt;
	}
	//_end
	/**
	 * 查询上级模块
	 * @return
	 */
	public String findParentModel(){
		
		if(this.leveId != null && !"".equals(this.leveId)){
			
			int id = Integer.parseInt(this.leveId);
			
			if(id <= 1){
				
				this.msg = "SUCCESS";
			
				return "json";
			}
			
			int temp = --id;
			
			this.pagelist = this.modelServiceImpl.findParentMode(temp+"");
			this.msg = "SUCCESS";
		}
		
		return "json";
	}
	
	
	/**
	 * 增加mode
	 * @return
	 */
	public String saveMode(){
		boolean flag = false;
		if(this.mode != null){
			this.mode.setAddTime(new Date());
			this.mode.setModuleId(UUID.randomUUID().toString());
			
			if(this.mode.getDescription() == null || "".equals(this.mode.getDescription())){
				
				this.mode.setDescription(this.mode.getModuleName());
				
			}
			if(this.mode.getFunctionmodule() != null){
				
				Functionmodule ffFunctionmodule = this.modelServiceImpl.findById(this.mode.getFunctionmodule().getModuleId());
				this.mode.setFunctionmodule(ffFunctionmodule);
			}
			
			flag = this.modelServiceImpl.onCreateMode(mode);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 修改mode
	 * @return
	 */
	public String updateMode(){
		boolean flag = false;
		if(this.mode != null){
			
			this.mode.setUpdateTime(new Date());
			
			if(this.mode.getFunctionmodule() != null){
				
				Functionmodule ffFunctionmodule = this.modelServiceImpl.findById(this.mode.getFunctionmodule().getModuleId());
				
				this.mode.setFunctionmodule(ffFunctionmodule);
				
			}
			
			flag = this.modelServiceImpl.onUpdateMode(mode);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	
	/**
	 * 删除funmode
	 * @return
	 */
	public String deleteMode(){
		
		boolean flag = false;
		
		if(this.modeId != null){
			
			Functionmodule funmode = this.modelServiceImpl.findById(modeId);  
			
			if(funmode.getModuleLevel().equals("1")){
				
				if(funmode.getFunctionmodule()!=null){
					
					this.msg = "当前模块包含子模块请先删除子模块！";
					
					return "json";
					
				}
				
				List<Functionmodule> listf = this.modelServiceImpl.findSunMode(this.modeId); 
				
				if(!listf.isEmpty()){
					
					this.msg = "当前模块有其他子模块引用，请先删除子模块！";
					return "json";
				}
				flag = this.modelServiceImpl.onRemoveMode(funmode);
				
				return "json";
			}
			
			flag = this.modelServiceImpl.onRemoveMode(funmode);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}

	/**
	 * 取得字典等级根据typeId 
	 * @return
	 */
	public String findDictTypeLeve(){
		
		this.dicts = this.dictservice.getDicts(FunctionModelAction.TYPEMODEL);  //取出所有模块等级
		
		this.sorts = this.dictservice.getDicts(FunctionModelAction.TYPESORTID);  //取出所有排序
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	
	/**
	 * 取得一个mode对象
	 * @return
	 */
	public String findByModeId(){
		
		if(this.modeId != null && !"".equals(this.modeId)){
			
			this.mode = this.modelServiceImpl.findById(modeId);
			
			if(this.mode != null){
				
				Set<Functionpage> funpages = this.mode.getFunctionpages();
				
				Iterator<Functionpage> iterpages = funpages.iterator();
				
				this.webpases = new ArrayList<Webpage>();
				
				while(iterpages.hasNext()){
					
					Functionpage pp = iterpages.next();
					
					this.webpases.add(pp.getWebpage());
				}
				
			}
			
		}
		
		findDict();
		
		this.pagelist = null;
		this.msg = "SUCCESS";
		return "json";
	}
	
	/**
	 * 更新当前mode对象下的functionpage属性
	 * @return
	 */
	public String updateModeFunctionPage(){
		
		boolean flag = false;
		
		if(this.modeId != null && !"".equals(this.modeId)){
			
			this.mode = this.modelServiceImpl.findById(modeId);  //取出模块对象
			
			if(this.webpageids != null && this.webpageids.size()>0){  //取得相应的webpages的id
				
				 List<String> listString = this.converList(this.temppageid);
				
				 Set<Functionpage> funsetFunctionpages = new HashSet<Functionpage>();
				 
				for(String ss:listString){
					
					Functionpage fpageFunctionpage = new Functionpage();
					
					fpageFunctionpage.setWebpage(this.webpageserviceimpl.findById(ss));
					
					funsetFunctionpages.add(fpageFunctionpage);
				}
				 
				this.mode.setFunctionpages(funsetFunctionpages);
				
				flag = this.modelServiceImpl.onUpdateMode(mode);
			}
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 查询字典用到的字段
	 */
	private void findDict(){
		
		this.dicts = this.dictservice.getDicts(FunctionModelAction.TYPEMODEL);  //取出所有模块等级
		
		this.sorts = this.dictservice.getDicts(FunctionModelAction.TYPESORTID);  //取出所有排序
		
		this.isPublic = this.dictservice.getDicts(FunctionModelAction.TYPEISPUBLIC); // 取出公共等级
		
	}
	
	//_start	
	public List<Dictionary> getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(List<Dictionary> isPublic) {
		this.isPublic = isPublic;
	}

	public List<Dictionary> getSorts() {
		return sorts;
	}
	public void setSorts(List<Dictionary> sorts) {
		this.sorts = sorts;
	}
	
	public String getTypeId() {
		return typeId;
	}
	public List<Dictionary> getDicts() {
		return dicts;
	}
	public void setDicts(List<Dictionary> dicts) {
		this.dicts = dicts;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public List<Functionmodule> getPagelist() {
		return pagelist;
	}



	public void setPagelist(List<Functionmodule> pagelist) {
		this.pagelist = pagelist;
	}



	public Long getMaxCount() {
		return maxCount;
	}



	public void setMaxCount(Long maxCount) {
		this.maxCount = maxCount;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getModeId() {
		return modeId;
	}
	public Functionmodule getMode() {
		return mode;
	}


	public void setMode(Functionmodule mode) {
		this.mode = mode;
	}


	public void setModeId(String modeId) {
		this.modeId = modeId;
	}


	public List<String> getWebpageids() {
		return webpageids;
	}


	public void setWebpageids(List<String> webpageids) {
		this.webpageids = webpageids;
	}
	public String getTemppageid() {
		return temppageid;
	}


	public void setTemppageid(String temppageid) {
		this.temppageid = temppageid;
	}
	public String getLeveId() {
		return leveId;
	}


	public void setLeveId(String leveId) {
		this.leveId = leveId;
	}

	@JSON(serialize=false)
	public IFunctionModelService getModelServiceImpl() {
		return modelServiceImpl;
	}


	public void setModelServiceImpl(IFunctionModelService modelServiceImpl) {
		this.modelServiceImpl = modelServiceImpl;
	}

	@JSON(serialize=false)
	public IWebPageService getWebpageserviceimpl() {
		return webpageserviceimpl;
	}


	public void setWebpageserviceimpl(IWebPageService webpageserviceimpl) {
		this.webpageserviceimpl = webpageserviceimpl;
	}

	@JSON(serialize=false)
	public IDictService getDictservice() {
		return dictservice;
	}


	public void setDictservice(IDictService dictservice) {
		this.dictservice = dictservice;
	}
	public List<Webpage> getWebpases() {
		return webpases;
	}


	public void setWebpases(List<Webpage> webpases) {
		this.webpases = webpases;
	}
	public String getKw() {
		return kw;
	}


	public void setKw(String kw) {
		this.kw = kw;
	}
	//_end
}

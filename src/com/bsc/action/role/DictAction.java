package com.bsc.action.role;

import java.util.Date;
import java.util.List;

import com.bsc.bean.Dictionary;
import com.bsc.bean.Dictionarytype;
import com.bsc.service.system.IDictService;
import com.googlecode.jsonplugin.annotations.JSON;
import com.hp.hpl.sparta.xpath.ThisNodeTest;
/**
 * 字典管理Action
 * @author 韩进城
 */
public class DictAction {

	private Long maxCount; //全部记录数
	
	private int pagesize = 25;  //每页显示记录数
	
	private int currentpage=1; //当前页
	
	private String msg;
	
	private IDictService dictservice;
	
	private List<Dictionary> pagelist;
	
	private List<Dictionarytype> typelist;
	
	private String typeName,kw;

	private String id;  //id
	
	private Dictionary dictionary; 
	/**
	 * 增加
	 * @return
	 */
	public String onCreate(){
		boolean flag = false;
		
		if(this.dictionary != null){
			
			Dictionarytype dictype = null;
			
			this.typelist = this.dictservice.findAllType();
			
			for(Dictionarytype type:this.typelist){
				
				if(type.getId().equals(this.dictionary.getDictionarytype().getId())){
					
					dictype = type;
					
					break;
				}
			}
			
			this.dictionary.setAddTime(new Date());
			
			this.dictionary.setDictionarytype(dictype);
			
			flag = this.dictservice.onCreate(dictionary);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	
	/**
	 * 查询所有类型
	 * @return
	 */
	public String findAllType(){
		
		this.typelist = this.dictservice.findAllType(); //模块类型
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	
	/**
	 * 取出全部记录数
	 * @return
	 */
	public String findAll(){
		
		this.pagelist = this.dictservice.findAll(currentpage,pagesize);  //字典信息列表
		
		this.maxCount = this.dictservice.getMaxCount(); //字典信息最大记录数
		
		this.typelist = this.dictservice.findAllType(); //模块类型
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	/**
	 * 模糊查询
	 * @return
	 */
	public String findKw(){
		
		this.pagelist = this.dictservice.findAllkw(typeName, kw, currentpage, pagesize);
		
		this.maxCount = this.dictservice.getKwMaxCount(typeName, kw);
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	/**
	 * 删除
	 * @return
	 */
	public String onDelete(){
		
		boolean flag = false;
		
		int tt = Integer.parseInt(id);
		
		if(tt >0){
			
			flag = this.dictservice.onDelete(tt);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 更新
	 * @return
	 */
	public String onUpdate(){
		boolean flag = false;
		
		if(this.dictionary != null){
			
			this.dictionary.setUpdateTime(new Date());
			
			flag = this.dictservice.onUpdate(this.dictionary);
			
		}
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 根据id查询
	 * @return
	 */
	public String findByid(){
		
		if(this.id!=null && !"".equals(this.id)){
			
			this.dictionary = this.dictservice.findByid(this.id);
			
			if(dictionary.getDescription() == null || "".equals(dictionary.getDescription())){
				
				dictionary.setDescription("");
			}
			
			this.typelist = this.dictservice.findAllType(); //模块类型
		}
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	//_start
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getKw() {
		return kw;
	}
	public void setKw(String kw) {
		this.kw = kw;
	}
	public List<Dictionarytype> getTypelist() {
		return typelist;
	}

	public void setTypelist(List<Dictionarytype> typelist) {
		this.typelist = typelist;
	}
	public List<Dictionary> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<Dictionary> pagelist) {
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
	@JSON(serialize=false)
	public IDictService getDictservice() {
		return dictservice;
	}

	public void setDictservice(IDictService dictservice) {
		this.dictservice = dictservice;
	}
	//_end
}

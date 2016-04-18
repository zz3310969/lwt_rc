package com.bsc.action.role;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Role;
import com.bsc.bean.Roleright;
import com.bsc.service.system.IFunctionModelService;
import com.bsc.service.system.IRoleService;
import com.googlecode.jsonplugin.annotations.JSON;

public class RoleAction {

	private Role role;
	
	private List<Role> pagelist;
	
	private String rid;
	
	private Long maxCount; //全部记录数
	
	private String msg;
	
	private List<Functionmodule> funmodes;  //模块列表
	
	private Map<String,List<Functionmodule>> map;
	
	private IRoleService roleService;  //角色管理服务
	
	private IFunctionModelService funmodeservice; //模块管理

	/**
	 * 取得所有角色信息
	 * @return
	 */
	public String findAll(){
		
		this.pagelist = this.roleService.findAll();
		
		this.msg = "SUCCESS";
		
		return "json";
	}
	/**
	 * 增加角色
	 * @return
	 */
	public String onCreate(){
		
		boolean flag = false;
		
		if(this.role!=null){
			
			role.setAddTime(new Date());
			
			role.setRoleId(this.roleService.findMaxNum()+"");
			
			flag = this.roleService.onCreateRole(this.role);
			
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 刪除角色
	 * @return
	 */
	public String onDeleteRole(){
		
		boolean flag = false;
		
		if(this.rid != null && !"".equals(this.rid)){
			
			this.role = this.roleService.findByid(rid);
			
			flag = this.roleService.onDeleteRole(this.role);
			
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 修改角色
	 * @return
	 */
	public String onUpdateRole(){
		
		boolean flag = false;
		
		if(this.role != null){
			
			Role tt = this.roleService.findByid(this.role.getRoleId());
			
			this.role.setUpdateTime(new Date());
			
			this.role.setAddTime(tt.getAddTime());
			
			flag = this.roleService.onUpdateRole(this.role);
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	
	/**
	 * 根据ID查询role
	 * @return
	 */
	public String findByIdRole(){
		
		this.pagelist = null;
		
		map = new HashMap<String, List<Functionmodule>>();
		
		if(this.rid!=null && !"".equals(this.rid)){
			
			this.role = this.roleService.findByid(this.rid);
			
			List<Functionmodule> llfn = this.funmodeservice.findParentMode("1");  //取得第一层模块信息
			
			for(Functionmodule fn:llfn){  //遍历第一级模块
				
				String temp = fn.getModuleName()+"#"+fn.getModuleId();  //将id和名称用#来分割设置成键值
				//将当前模块总的所有子模块取出
				List<Functionmodule> ffFunctionmodules = this.funmodeservice.findSunMode(fn.getModuleId());
			
				map.put(temp, ffFunctionmodules);
			}
			
		}
		if(this.role!=null){
			
			this.msg ="SUCCESS";
		}else{
			
			this.msg = "ERROR";
		}
		
		return "json";
	}
	

	public Map<String, List<Functionmodule>> getMap() {
		return map;
	}
	public void setMap(Map<String, List<Functionmodule>> map) {
		this.map = map;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public Long getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Long maxCount) {
		this.maxCount = maxCount;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	@JSON(serialize=false)
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public List<Role> getPagelist() {
		return pagelist;
	}
	public void setPagelist(List<Role> pagelist) {
		this.pagelist = pagelist;
	}
	@JSON(serialize=false)
	public IFunctionModelService getFunmodeservice() {
		return funmodeservice;
	}
	public void setFunmodeservice(IFunctionModelService funmodeservice) {
		this.funmodeservice = funmodeservice;
	}
	public List<Functionmodule> getFunmodes() {
		return funmodes;
	}
	public void setFunmodes(List<Functionmodule> funmodes) {
		this.funmodes = funmodes;
	}
}

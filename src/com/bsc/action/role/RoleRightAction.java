package com.bsc.action.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bsc.bean.Functionmodule;
import com.bsc.bean.Role;
import com.bsc.bean.Roleright;
import com.bsc.bean.RolerightId;
import com.bsc.service.system.IFunctionModelService;
import com.bsc.service.system.IRoleRightService;
import com.bsc.service.system.IRoleService;
import com.googlecode.jsonplugin.annotations.JSON;

public class RoleRightAction {

	private String roleid; //角色ID
	
	private String msg;  //提示信息
	
	private String modeids;  //模块信息
	
	private IRoleRightService rolerightservice;
	
	private IRoleService	roleservice;
	
	private IFunctionModelService funcationmode;
	
	
	
	
	/**
	 * 增加角色中的权限
	 * @return
	 */
	public String addRoleRight(){
		
		boolean flag = false;
		
		if(this.roleid !=null && !"".equals(this.roleid)){
			
			Role role = this.roleservice.findByid(roleid);  //取出角色信息
			
			List<Functionmodule> list = StringsConvertTOList();
			
			Set<Roleright> rs = role.getRolerights();
			
			if(!deleteUseRoleRight(rs,list)){
				
				this.msg = "添加权限出错！";
				
				return "json";
			}
			
			for(Functionmodule fn:list){
				Roleright roleright = new Roleright();
				RolerightId rId = new RolerightId();
				rId.setFunctionModuleId(fn.getModuleId());
				rId.setRoleId(roleid);
				roleright.setAddTime(new Date());
				roleright.setFunctionmodule(fn);
				roleright.setId(rId);
				roleright.setRole(role);
				
				flag = this.rolerightservice.onCreateRoleRight(roleright);
			}
			
		}
		
		this.msg = flag?"SUCCESS":"ERROR";
		
		return "json";
	}
	/**
	 * 删除不存在的权限
	 * @param rs
	 */
	private boolean deleteUseRoleRight(Set<Roleright> rs,List<Functionmodule> list){
		boolean flag = false;
		
		if(rs == null || rs.size()==0)
			return true;
		
		Iterator<Roleright> it = rs.iterator();
		
		while(it.hasNext()){
			
			Roleright ro = it.next(); 
			
			flag = this.rolerightservice.onDeleteRoleRight(ro);
		}
		return flag;
	}
	
	
	/**
	 * 将模块字符串转换成模块列表对象
	 * @return
	 */
	private List<Functionmodule> StringsConvertTOList(){
		
		List<Functionmodule> lsFunctionmodules = null;
		
		if(this.modeids != null && !"".equals(this.modeids)){
			
			lsFunctionmodules = new ArrayList<Functionmodule>();
			
			String tt [] = this.modeids.split("#");
			
			for(String s:tt){
				
				Functionmodule fm= this.funcationmode.findById(s);
				
				lsFunctionmodules.add(fm);
				
			}
		}
		
		return lsFunctionmodules;
	}
	
	//_start
	public String getModeids() {
		return modeids;
	}

	public void setModeids(String modeids) {
		this.modeids = modeids;
	}
	public void setRoleservice(IRoleService roleservice) {
		this.roleservice = roleservice;
	}
	@JSON(serialize=false)
	public IRoleService getRoleservice() {
		return roleservice;
	}
	@JSON(serialize=false)
	public IFunctionModelService getFuncationmode() {
		return funcationmode;
	}


	public void setFuncationmode(IFunctionModelService funcationmode) {
		this.funcationmode = funcationmode;
	}
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	@JSON(serialize=false)
	public IRoleRightService getRolerightservice() {
		return rolerightservice;
	}

	public void setRolerightservice(IRoleRightService rolerightservice) {
		this.rolerightservice = rolerightservice;
	}
	//_end
}

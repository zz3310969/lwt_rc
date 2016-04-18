package com.bsc.service.system.impl;

import com.bsc.bean.Roleright;
import com.bsc.dao.system.IRoleRightDAO;
import com.bsc.service.system.IRoleRightService;

public class RoleRightServiceImpl implements IRoleRightService {
	
	private IRoleRightDAO rolerightdao;
	
	/**
	 * 增加角色中的权限
	 */
	public boolean onCreateRoleRight(Roleright roleright) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.rolerightdao.onCreate(roleright);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 删除角色权限信息
	 * @param roleright
	 * @return
	 */
	public boolean onDeleteRoleRight(Roleright roleright){
		
		boolean flag = false;
		
		try {
			flag = this.rolerightdao.onRemove(roleright);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public IRoleRightDAO getRolerightdao() {
		return rolerightdao;
	}

	public void setRolerightdao(IRoleRightDAO rolerightdao) {
		this.rolerightdao = rolerightdao;
	}
}

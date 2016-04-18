package com.bsc.service.system.impl;

import java.util.List;

import com.bsc.bean.Role;
import com.bsc.dao.system.IRoleManager;
import com.bsc.service.system.IRoleService;

public class RoleServiceImpl implements IRoleService {

	private IRoleManager roledao;
	

	/**
	 * 查询所有角色信息
	 */
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		
		List<Role> rols = null;
		
		try {
			rols = this.roledao.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rols;
	}
	/**
	 * 增加角色
	 */
	public boolean onCreateRole(Role role) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.roledao.onCreateRole(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 删除角色
	 */
	public boolean onDeleteRole(Role role) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.roledao.onRemove(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 修改角色
	 */
	public boolean onUpdateRole(Role role) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		try {
			flag = this.roledao.onUpdate(role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 查询一个角色信息
	 */
	public Role findByid(String roleid) {
		// TODO Auto-generated method stub
		Role role = null;
		
		try {
			role = this.roledao.findById(roleid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return role;
	}
	/**
	 * 角色id最大记录数
	 */
	public int findMaxNum() {
		// TODO Auto-generated method stub
		int tt = 0;
		try {
			tt = this.roledao.findMaxNumber();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int bb = ++tt;
		
		return bb;
	}
	
	public IRoleManager getRoledao() {
		return roledao;
	}

	public void setRoledao(IRoleManager roledao) {
		this.roledao = roledao;
	}

}
